package exceptional_handling;


class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }


    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient Balance! Available balance: " + balance);
        }
        balance -= amount;
        System.out.println("Withdrawal successful! Remaining Balance: " + balance);
    }
}


public class BankingSystem {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(5000); // initial balance

        try {
            System.out.println("Attempting to withdraw 6000...");
            account.withdraw(6000);
        } catch (InsufficientFundsException e) {
            System.out.println("Exception Caught: " + e.getMessage());
        }

        try {
            System.out.println("\nAttempting to withdraw 3000...");
            account.withdraw(3000);
        } catch (InsufficientFundsException e) {
            System.out.println("Exception Caught: " + e.getMessage());
        }
    }
}

