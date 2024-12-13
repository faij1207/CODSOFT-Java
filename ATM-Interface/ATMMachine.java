import java.util.Scanner;

public class ATMMachine {

    // Inner class to represent the Bank Account
    static class BankAccount {
        private double balance; // Stores the account balance

        // Constructor to initialize the account balance
        public BankAccount(double initialBalance) {
            if (initialBalance < 0) {
                System.out.println("Initial balance cannot be negative. Setting balance to 0.");
                this.balance = 0;
            } else {
                this.balance = initialBalance;
            }
        }

        // Method to deposit money into the account
        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount; // Add amount to balance
                System.out.println("Successfully deposited Rs. " + amount);
            } else {
                System.out.println("Deposit amount must be positive.");
            }
        }

        // Method to withdraw money from the account
        public boolean withdraw(double amount) {
            if (amount > balance) {
                System.out.println("Insufficient balance! Transaction failed.");
                return false;
            } else if (amount <= 0) {
                System.out.println("Withdrawal amount must be positive.");
                return false;
            } else {
                balance -= amount; // Deduct amount from balance
                System.out.println("Successfully withdrawn Rs." + amount);
                return true;
            }
        }

        // Method to check the current account balance
        public double getBalance() {
            return balance;
        }
    }

    // Inner class to represent the ATM Machine
    static class ATM {
        private BankAccount account; // Connects the ATM to the user's bank account

        // Constructor to initialize the ATM with a user's bank account
        public ATM(BankAccount account) {
            this.account = account;
        }

        // Method to display the ATM menu
        public void showMenu() {
            Scanner scanner = new Scanner(System.in);
            boolean exit = false;

            // ATM Menu Loop
            while (!exit) {
                System.out.println("\n--- ATM MENU ---");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");

                int choice = getValidInteger(scanner); // Validate user input

                switch (choice) {
                    case 1: // Option to check balance
                        System.out.println("Your current balance is: Rs." + account.getBalance());
                        break;
                    case 2: // Option to deposit money
                        System.out.print("Enter the amount to deposit: Rs.");
                        double depositAmount = getValidDouble(scanner);
                        account.deposit(depositAmount);
                        break;
                    case 3: // Option to withdraw money
                        System.out.print("Enter the amount to withdraw: Rs. ");
                        double withdrawAmount = getValidDouble(scanner);
                        account.withdraw(withdrawAmount);
                        break;
                    case 4: // Option to exit
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        exit = true;
                        break;
                    default: // Invalid choice
                        System.out.println("Invalid choice! Please select a valid option.");
                }
            }

            scanner.close(); // Close the scanner
        }

        // Helper method to validate integer input
        private int getValidInteger(Scanner scanner) {
            while (!scanner.hasNextInt()) { // Check if input is not an integer
                System.out.print("Invalid input! Please enter a valid number: ");
                scanner.next(); // Consume invalid input
            }
            return scanner.nextInt(); // Return valid integer input
        }

        // Helper method to validate double input
        private double getValidDouble(Scanner scanner) {
            while (!scanner.hasNextDouble()) { // Check if input is not a double
                System.out.print("Invalid input! Please enter a valid amount: ");
                scanner.next(); // Consume invalid input
            }
            return scanner.nextDouble(); // Return valid double input
        }
    }

    // Main method to run the program
    public static void main(String[] args) {
        // Initialize a bank account with an initial balance
        BankAccount userAccount = new BankAccount(1000.0); // Starting balance: ₹1000

        // Initialize the ATM with the user's bank account
        ATM atm = new ATM(userAccount);

        // Display the ATM menu
        atm.showMenu();
    }
}
