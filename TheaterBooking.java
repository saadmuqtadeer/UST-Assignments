import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TheaterBooking {
    private static final int[][] seats = new int[6][6];

    public static boolean checkSeatAvailability(char row, int n, ArrayList<Integer> seatNumbers) {
        row = Character.toUpperCase(row);
        int rowIndex = (int) row - 65;
        Collections.sort(seatNumbers);
        for (int seatNumber : seatNumbers) {
            if (seatNumber < 1 || seatNumber > 6 || seats[rowIndex][seatNumber - 1] == 1)
                return false;
        }
        return true;
    }

    public static void bookSeats(char row, int n, ArrayList<Integer> seatNumbers) {
        int rowIndex = (int) Character.toUpperCase(row) - 65;
        Collections.sort(seatNumbers);
        for (int seatNumber : seatNumbers) {
            seats[rowIndex][seatNumber - 1] = 1;
        }
        System.out.println("Successfully Booked " + n + " Seats");
        System.out.println();
    }

    public static void showAvailableSeats() {
        System.out.println("Available Seats (0 - Available, 1 - Booked):");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int calculateCost(char seatRow, int n) {
        int rowIndex = Character.toUpperCase(seatRow) - 65;
        int[] rowPrices = {200, 100, 75, 50, 40, 30};
        return rowPrices[rowIndex] * n;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("------WELCOME TO UST THEATRE------\n");

        while (true) {
            System.out.println("Book Your Seats");
            showAvailableSeats();

            System.out.println("\nEnter a Row (A to F): ");
            char seatRow = scanner.next().charAt(0);

            System.out.println("Enter Total Number of Seats (Less than 7): ");
            int n = scanner.nextInt();

            ArrayList<Integer> seatNumbers = new ArrayList<>();
            System.out.println("Select " + n + " seats from available seats in row-" + seatRow);
            for (int i = 0; i < n; i++) {
                System.out.println("Enter seat " + (i + 1) + ": ");
                int seatNumber = scanner.nextInt();
                seatNumbers.add(seatNumber);
            }

            if (checkSeatAvailability(seatRow, n, seatNumbers)) {
                bookSeats(seatRow, n, seatNumbers);
                System.out.println("Your Booking is Confirmed");
                showAvailableSeats();
                int totalCost = calculateCost(seatRow, n);
                System.out.println("Total Cost of " + n + " seats in row " + seatRow + " is: " + totalCost);
                System.out.println();
            } else {
                System.out.println("Selected seats are already booked or invalid! Please select again.");
            }
        }
    }
}
