import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Course Management System ---");
            System.out.println("1. Add Course");
            System.out.println("2. Enroll Student");
            System.out.println("3. Assign Grade");
            System.out.println("4. Calculate Overall Grade");
            System.out.println("5. Exit");

            int choice = 0; // Variable to hold the user's choice
            boolean validInput = false; // Flag to check input validity

            // Loop until valid input is entered
            while (!validInput) {
                System.out.print("Choose an option: ");
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    sc.nextLine(); // Consume the newline
                    validInput = true; // Input is valid, exit the loop
                } else {
                    System.out.println("Invalid input. Please enter a valid number between 1 and 5.");
                    sc.nextLine(); // Consume the invalid input
                }
            }

            // Process the user's choice
            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    enrollStudent();
                    break;
                case 3:
                    assignGrade();
                    break;
                case 4:
                    calculateOverallGrade();
                    break;
                case 5:
                    System.out.println("Exiting system. Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addCourse() {
        System.out.print("Enter course code: ");
        String code = sc.nextLine();
        System.out.print("Enter course name: ");
        String name = sc.nextLine();
        System.out.print("Enter max capacity: ");
        int capacity = sc.nextInt();
        sc.nextLine();

        CourseManagement.addCourse(code, name, capacity);
        System.out.println("Course added successfully!");
    }

    private static void enrollStudent() {
        System.out.print("Enter student name: ");
        String studentName = sc.nextLine();
        System.out.print("Enter student ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consume newline

        Student student = new Student(studentName, id);
        students.add(student);

        System.out.println("Available courses:");
        for (Course c : CourseManagement.getCourses()) {
            System.out.println(c.getCourseCode() + ": " + c.getCourseName());
        }

        System.out.print("Enter course code to enroll: ");
        String enrollCode = sc.nextLine();
        Course selectedCourse = CourseManagement.getCourses().stream()
                .filter(c -> c.getCourseCode().equals(enrollCode))
                .findFirst().orElse(null);

        if (selectedCourse != null) {
            CourseManagement.enrollStudent(student, selectedCourse);
        } else {
            System.out.println("Course not found.");
        }
    }

    private static void assignGrade() {
        System.out.print("Enter student ID: ");
        int studentId = sc.nextInt();
        sc.nextLine();

        Student targetStudent = students.stream()
                .filter(s -> s.getId() == studentId)
                .findFirst().orElse(null);

        if (targetStudent != null) {
            System.out.print("Enter course code: ");
            String gradeCourseCode = sc.nextLine();

            Course gradeCourse = CourseManagement.getCourses().stream()
                    .filter(c -> c.getCourseCode().equals(gradeCourseCode))
                    .findFirst().orElse(null);

            if (gradeCourse != null) {
                System.out.print("Enter grade: ");
                int grade = sc.nextInt();
                CourseManagement.assignGrade(targetStudent, gradeCourse, grade);
                System.out.println("Grade assigned successfully!");
            } else {
                System.out.println("Course not found.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void calculateOverallGrade() {
        System.out.print("Enter student ID: ");
        int targetId = sc.nextInt();
        sc.nextLine();

        Student gradeStudent = students.stream()
                .filter(s -> s.getId() == targetId)
                .findFirst().orElse(null);

        if (gradeStudent != null) {
            CourseManagement.calculateOverallGrade(gradeStudent);
        } else {
            System.out.println("Student not found.");
        }
    }
}
