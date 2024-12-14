import java.io.*;
import java.util.*;

// Student class to represent individual students
class Student implements Serializable {
    private String name; // Stores the name of the student
    private int rollNumber; // Stores the roll number of the student
    private String grade; // Stores the grade of the student

    // Constructor to initialize student details
    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for roll number
    public int getRollNumber() {
        return rollNumber;
    }

    // Getter for grade
    public String getGrade() {
        return grade;
    }

    // Setter to update name
    public void setName(String name) {
        this.name = name;
    }

    // Setter to update grade
    public void setGrade(String grade) {
        this.grade = grade;
    }

    // Overrides toString() method to provide a string representation of the student
    @Override
    public String toString() {
        return "Roll No: " + rollNumber + ", Name: " + name + ", Grade: " + grade;
    }
}

// StudentManagementSystem class to manage the collection of students
class StudentManagementSystem {
    private List<Student> students = new ArrayList<>(); // List to store student objects
    private final String fileName = "students.dat"; // File name for storing student data

    // Constructor to load student data from file on initialization
    public StudentManagementSystem() {
        loadFromFile();
    }

    // Method to add a new student
    public void addStudent(Student student) {
        students.add(student); // Add student to the list
        saveToFile(); // Save updated list to file
        System.out.println("Student added successfully!");
    }

    // Method to remove a student by roll number
    public void removeStudent(int rollNumber) {
        Student student = searchStudent(rollNumber); // Search for the student
        if (student != null) {
            students.remove(student); // Remove the student from the list
            saveToFile(); // Save updated list to file
            System.out.println("Student removed successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    // Method to search for a student by roll number
    public Student searchStudent(int rollNumber) {
        for (Student student : students) { // Iterate through the list of students
            if (student.getRollNumber() == rollNumber) { // Match roll number
                return student;
            }
        }
        return null; // Return null if no match is found
    }

    // Method to display all students
    public void displayAllStudents() {
        if (students.isEmpty()) { // Check if the list is empty
            System.out.println("No students found.");
        } else {
            System.out.println("List of Students:");
            for (Student student : students) { // Iterate through the list and display each student
                System.out.println(student);
            }
        }
    }

    // Method to save student data to a file
    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(students); // Serialize the list of students
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    // Method to load student data from a file
    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            students = (List<Student>) ois.readObject(); // Deserialize the list of students
        } catch (FileNotFoundException e) {
            // Ignore if file does not exist; no data to load
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading from file: " + e.getMessage());
        }
    }
}

// Main class for user interaction
public class StudentManagementSystemApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner for user input
        StudentManagementSystem sms = new StudentManagementSystem(); // Create an instance of the system

        while (true) { // Infinite loop for the menu
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt(); // Read user choice
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Add a student
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Roll Number: ");
                    int rollNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Grade: ");
                    String grade = scanner.nextLine();

                    if (name.isEmpty() || grade.isEmpty()) { // Validate input
                        System.out.println("Name and Grade cannot be empty.");
                        break;
                    }

                    sms.addStudent(new Student(name, rollNumber, grade)); // Add the student
                    break;
                case 2: // Remove a student
                    System.out.print("Enter Roll Number to Remove: ");
                    int rollToRemove = scanner.nextInt();
                    sms.removeStudent(rollToRemove); // Remove the student
                    break;
                case 3: // Search for a student
                    System.out.print("Enter Roll Number to Search: ");
                    int rollToSearch = scanner.nextInt();
                    Student foundStudent = sms.searchStudent(rollToSearch); // Search for the student
                    if (foundStudent != null) {
                        System.out.println("Student Found: " + foundStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4: // Display all students
                    sms.displayAllStudents();
                    break;
                case 5: // Exit the application
                    System.out.println("Exiting... Goodbye!");
                    scanner.close(); // Close the scanner
                    return;
                default: // Handle invalid choices
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
