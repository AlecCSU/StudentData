import java.util.*;
import java.io.*;

public class StudentDataCollector {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new LinkedList<>();
        boolean enteringData = true;

        try {
            while (enteringData) {
                System.out.println("Enter student name (or type 'exit' to finish):");
                String name = scanner.nextLine();
                if (name.equalsIgnoreCase("exit")) {
                    break;
                }

                System.out.println("Enter student address:");
                String address = scanner.nextLine();

                double GPA = -1;
                while (GPA < 0 || GPA > 4.0) {
                    System.out.println("Enter student GPA (0.0 to 4.0):");
                    try {
                        GPA = Double.parseDouble(scanner.nextLine());
                        if (GPA < 0 || GPA > 4.0) {
                            System.out.println("Invalid GPA. Please enter a value between 0.0 and 4.0.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a numeric value.");
                    }
                }

                students.add(new Student(name, address, GPA));
            }
        } finally {
            scanner.close();
        }

        Collections.sort(students);
        writeToFile(students);
        System.out.println("Data entry complete. Sorted data has been saved to students.txt.");
    }

    private static void writeToFile(List<Student> students) {
        try (PrintWriter out = new PrintWriter(new FileWriter("students.txt"))) {
            for (Student student : students) {
                out.println(student);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
