import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCourse Enrollment and Grade Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. Assign Grade to Student");
            System.out.println("5. View Student Information by ID");
            System.out.println("6. View Overall Student's Grade");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = getIntInput(scanner, "Invalid choice. Please enter a valid option.");

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    addCourse(scanner);
                    break;
                case 3:
                    enrollStudent(scanner);
                    break;
                case 4:
                    assignGrade(scanner);
                    break;
                case 5:
                    viewStudentInformation(scanner);
                    break;
                case 6:
                    calculateOverallGrade(scanner);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void calculateOverallGrade(Scanner scanner) {
        System.out.print("Enter student ID: ");
        int studentId = getIntInput(scanner, "Student ID must be an integer.");
        Student student = findStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
        } else {
            System.out.print("Overall grade: " + CourseManagement.calculateOverallGrade(student));
        }
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Enter student ID: ");
        int studentId = getIntInput(scanner, "Student ID must be an integer.");
        scanner.nextLine();  // Consume the newline character
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        Student student = new Student(studentName, studentId);
        CourseManagement.getStudents().add(student);
        System.out.println("Student ID " + studentId + ": " + studentName + " added successfully.");
    }

    private static void addCourse(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine(); // Read course code directly.
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine(); // Read course name.
        System.out.print("Enter course maximum capacity: ");
        int capacity = getIntInput(scanner, "Capacity must be an integer."); // Safely read integer input.
        // Clear any leftover newline character after reading an integer.
        scanner.nextLine();
        // Create the Course object.
        Course course = new Course(courseCode, courseName, capacity);
        // Add the course to the CourseManagement system.
        CourseManagement.addCourse(course);
        // Display confirmation message.
        System.out.println("Course " + courseCode + " - " + courseName + " added successfully.");
    }

    private static void enrollStudent(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter student ID: ");
        int studentId = getIntInput(scanner, "Student ID must be an integer.");

        // Find the student and course
        Student student = findStudentById(studentId);
        Course selectedCourse = findCourseByCode(courseCode);

        if (student != null && selectedCourse != null) {
            CourseManagement.enrollStudent(student, selectedCourse);
        } else {
            System.out.println("Invalid student ID or course code.");
        }
    }

    private static void assignGrade(Scanner scanner) {
        System.out.print("Enter student ID: ");
        int studentId = getIntInput(scanner, "Student ID must be an integer.");
        System.out.print("Enter course code: ");
        scanner.nextLine();  // Consume the newline character
        String courseCode = scanner.nextLine();
        System.out.print("Enter grade: ");
        int grade = getIntInput(scanner, "Grade must be an integer.");

        // Find the student and course
        Student student = findStudentById(studentId);
        Course selectedCourse = findCourseByCode(courseCode);

        if (student != null && selectedCourse != null) {
            CourseManagement.assignGradeToStudent(student, selectedCourse, grade);
        } else {
            System.out.println("Invalid student ID or course code.");
        }
    }

    private static void viewStudentInformation(Scanner scanner) {
        System.out.println("Enter student ID to view details:");
        int studentId = getIntInput(scanner, "Student ID must be an integer.");
        Student student = findStudentById(studentId);

        if (student != null) {
            student.printStudentInfo();
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }

    private static Student findStudentById(int studentId) {
        for (Student student : CourseManagement.getStudents()) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }

    private static Course findCourseByCode(String courseCode) {
        for (Course course : CourseManagement.getCourses()) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    // Utility method to get integer input
    private static int getIntInput(Scanner scanner, String errorMessage) {
        while (!scanner.hasNextInt()) {
            System.out.println(errorMessage);
            scanner.next(); // Consume invalid input
        }
        return scanner.nextInt();
    }
}
