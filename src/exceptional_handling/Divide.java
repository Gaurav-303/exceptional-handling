package exceptional_handling;

import java.util.Scanner;

public class Divide {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter an integer: ");
            int num = sc.nextInt();

            int result = 100 / num;
            System.out.println("Result: 100 / " + num + " = " + result);

        } catch (ArithmeticException e) {
            System.out.println("Error: Division by zero is not allowed!");
        }

        sc.close();
    }
}

