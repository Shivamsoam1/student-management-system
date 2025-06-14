package student;

import java.util.*;
import java.io.*;

public class StudentManager {
    private Map<Integer, Student> studentMap = new HashMap<>();

    public boolean addStudent(Student student) {
        if (studentMap.containsKey(student.id)) return false;
        studentMap.put(student.id, student);
        return true;
    }

    public boolean deleteStudent(int id) {
        return studentMap.remove(id) != null;
    }

    public void viewAllStudents() {
        if (studentMap.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student s : studentMap.values()) {
            System.out.println(s);
        }
    }

    public void searchStudentByName(String name) {
        boolean found = false;
        for (Student s : studentMap.values()) {
            if (s.name.equalsIgnoreCase(name)) {
                System.out.println(s);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No student found with name: " + name);
        }
    }

    public void saveToCSV(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Student s : studentMap.values()) {
                writer.println(s);
            }
            System.out.println("Students saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
