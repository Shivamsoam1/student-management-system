package student;

import java.util.*;
//use of this java.io.*
import java.io.*;

public class Main {

    public static Admin loadAdmin(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String[] parts = reader.readLine().split(",");
            return new Admin(parts[0], parts[1]);
        } catch (IOException e) {
            System.out.println("Error loading admin credentials.");
            return null;
        }
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (Exception e) {
            System.out.println("Console couldn't be cleared.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = loadAdmin("student/admin.csv");

        if (admin == null) {
            System.out.println("Admin credentials missing.");
            return;
        }

        System.out.print("Username: ");
        String user = scanner.nextLine();
        System.out.print("Password: ");
        String pass = scanner.nextLine();

        if (!user.equals(admin.getUsername()) || !pass.equals(admin.getPassword())) {
            System.out.println("Invalid login!");
            return;
        }

        StudentManager manager = new StudentManager();

        while (true) {
            clearConsole();
            System.out.println("\n--- Student Management Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. View All Students");
            System.out.println("4. Search Student by Name");
            System.out.println("5. Save to CSV");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Student ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    boolean added = manager.addStudent(new Student(name, id));
                    System.out.println(added ? "Student added." : "Student ID already exists.");
                    break;

                case "2":
                    System.out.print("Enter Student ID to delete: ");
                    int delId = Integer.parseInt(scanner.nextLine());
                    boolean deleted = manager.deleteStudent(delId);
                    System.out.println(deleted ? "Student deleted." : "Student not found.");
                    break;

                case "3":
                    manager.viewAllStudents();
                    break;

                case "4":
                    System.out.print("Enter name to search: ");
                    manager.searchStudentByName(scanner.nextLine());
                    break;

                case "5":
                    manager.saveToCSV("student/students.csv");
                    break;

                case "6":
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid option.");
            }

            System.out.println("Press Enter to continue...");
            scanner.nextLine();
        }
    }
}
