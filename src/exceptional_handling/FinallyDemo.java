package exceptional_handling;

import java.util.Scanner;

public class FinallyDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {

            System.out.print("Enter an integer to divide 100: ");
            int number = sc.nextInt();


            int result = 100 / number;
            System.out.println("Result: " + result);
        }
        catch (ArithmeticException e) {
            System.out.println("Error: Division by zero is not allowed.");
        }
        finally {

            System.out.println("Division operation is complete.");
            sc.close();
        }
    }
}
