package exceptional_handling;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MultipleCatchBlocks {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter first integer: ");
            int num1 = sc.nextInt();

            System.out.print("Enter second integer: ");
            int num2 = sc.nextInt();

            int result = num1 / num2; // may throw ArithmeticException
            System.out.println("Result: " + num1 + " / " + num2 + " = " + result);

        } catch (ArithmeticException e) {
            System.out.println("Error: Division by zero is not allowed!");
        } catch (InputMismatchException e) {
            System.out.println("Error: Please enter valid integer values only!");
        } finally {
            sc.close();
        }
    }
}

