import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner for user input

        // Ask the user for the number of subjects
        System.out.print("Enter the number of subjects: ");
        int numSubjects = getValidInteger(scanner);

        // Validate the number of subjects
        while (numSubjects <= 0) {
            System.out.print("Invalid input! The number of subjects must be greater than 0: ");
            numSubjects = getValidInteger(scanner);
        }

        int[] marks = new int[numSubjects]; // Array to store marks for each subject
        int totalMarks = 0; // Variable to store the total marks

        // Input marks for each subject
        System.out.println("\nEnter the marks for each subject (out of 100):");
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Subject " + (i + 1) + ": ");
            marks[i] = getValidMarks(scanner); // Get valid marks
            totalMarks += marks[i]; // Add marks to total
        }

        // Calculate average percentage
        double averagePercentage = (double) totalMarks / numSubjects;

        // Determine grade based on average percentage
        char grade = calculateGrade(averagePercentage);

        // Display the results
        System.out.println("\n--- RESULT ---");
        System.out.println("Total Marks: " + totalMarks + " / " + (numSubjects * 100));
        System.out.println("Average Percentage: " + String.format("%.2f", averagePercentage) + "%");
        System.out.println("Grade: " + grade);

        scanner.close(); // Close the scanner
    }

    // Helper method to ensure valid integer input
    private static int getValidInteger(Scanner scanner) {
        while (!scanner.hasNextInt()) { // Check if the input is not an integer
            System.out.print("Invalid input! Please enter a valid number: ");
            scanner.next(); // Consume invalid input
        }
        return scanner.nextInt(); // Return valid integer input
    }

    // Helper method to ensure valid marks input (0-100)
    private static int getValidMarks(Scanner scanner) {
        int marks = getValidInteger(scanner);
        while (marks < 0 || marks > 100) { // Validate marks are within 0 to 100
            System.out.print("Invalid input! Marks must be between 0 and 100. Try again: ");
            marks = getValidInteger(scanner);
        }
        return marks; // Return valid marks
    }

    // Helper method to calculate grade based on percentage
    private static char calculateGrade(double percentage) {
        if (percentage >= 90) {
            return 'A'; // Grade A for 90% or above
        } else if (percentage >= 80) {
            return 'B'; // Grade B for 80% to 89%
        } else if (percentage >= 70) {
            return 'C'; // Grade C for 70% to 79%
        } else if (percentage >= 60) {
            return 'D'; // Grade D for 60% to 69%
        } else {
            return 'F'; // Grade F for below 60%
        }
    }
}
