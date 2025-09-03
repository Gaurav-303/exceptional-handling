package exceptional_handling;

public class ExceptionHierarchy {

    public static void main(String[] args) {
        try {

            int choice = 2;

            if (choice == 1) {

                int result = 10 / 0;
                System.out.println("Result: " + result);
            } else if (choice == 2) {

                String str = null;
                System.out.println("Length: " + str.length());
            } else {

                int[] arr = new int[2];
                System.out.println(arr[5]);
            }

        } catch (ArithmeticException ae) {
            System.out.println("Caught ArithmeticException: Cannot divide by zero!");
        } catch (NullPointerException npe) {
            System.out.println("Caught NullPointerException: Null value encountered!");
        } catch (Exception e) {
            System.out.println("Caught Generic Exception: " + e);
        }
    }
}

