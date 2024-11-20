import java.util.Scanner;
import java.util.Random;

public class NumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;

        int totalRounds = 0;
        int roundsWon = 0;

        System.out.println("Welcome to the Enhanced Number Guessing Game!");

        // Main game loop
        while (playAgain) {
            totalRounds++;
            System.out.println("\n--- ROUND " + totalRounds + " ---");

            // Let the user set the range
            System.out.print("Enter the minimum number: ");
            int min = getValidInteger(scanner);
            System.out.print("Enter the maximum number: ");
            int max = getValidInteger(scanner);

            // Validate the range
            while (min >= max) {
                System.out.println("Invalid range! The maximum must be greater than the minimum.");
                System.out.print("Enter the minimum number: ");
                min = getValidInteger(scanner);
                System.out.print("Enter the maximum number: ");
                max = getValidInteger(scanner);
            }

            // Generate a random number within the range
            int randomNumber = random.nextInt(max - min + 1) + min;
            int maxAttempts = Math.max(5, (max - min) / 10); // At least 5 attempts
            int attemptsLeft = maxAttempts;

            System.out.println("I've picked a number between " + min + " and " + max + ".");
            System.out.println("You have " + maxAttempts + " attempts to guess the number. Good luck!");

            boolean guessedCorrectly = false;
            // Loop for user attempts
            while (attemptsLeft > 0) {
                System.out.print("Attempts left (" + attemptsLeft + "). Enter your guess: ");
                int userGuess = getValidInteger(scanner);
                attemptsLeft--;

                // Check if the guess is correct
                if (userGuess == randomNumber) {
                    int score = (attemptsLeft + 1) * 10; // Score based on remaining attempts
                    System.out.println("🎉 Correct! You've guessed the number in " + (maxAttempts - attemptsLeft) + " attempts.");
                    System.out.println("Your score for this round: " + score);
                    guessedCorrectly = true;
                    roundsWon++;
                    break;
                } else if (userGuess < randomNumber) {
                    System.out.println("Too low! Try a higher number.");
                } else {
                    System.out.println("Too high! Try a lower number.");
                }
            }

            // If the user did not guess correctly
            if (!guessedCorrectly) {
                System.out.println("😔 You've used all your attempts. The correct number was " + randomNumber + ".");
            }

            // Ask if the user wants to play another round
            System.out.print("\nDo you want to play another round? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        }

        // Game summary
        System.out.println("\n--- GAME SUMMARY ---");
        System.out.println("Total Rounds Played: " + totalRounds);
        System.out.println("Rounds Won: " + roundsWon);
        System.out.println("Thanks for playing! See you next time.");
        scanner.close();
    }

    // Helper method for input validation
    private static int getValidInteger(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input! Please enter a valid number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
