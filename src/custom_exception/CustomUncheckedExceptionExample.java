package custom_exception;


import java.util.Scanner;

public class CustomUncheckedExceptionExample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter an integer: ");
            int number = sc.nextInt();


            if (number < 0) {
                throw new NegativeNumberException("Negative numbers are not allowed: " + number);
            }

            System.out.println("You entered: " + number);

        } catch (NegativeNumberException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        sc.close();
    }
}
