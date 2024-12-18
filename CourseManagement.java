import java.util.ArrayList;
import java.util.List;

public class CourseManagement {
    private static List<Course> courses = new ArrayList<>();  // List to store all courses
    private static List<Student> students = new ArrayList<>();  // List to store all students

    // Method to add a new course
    public static void addCourse(Course course) {
        courses.add(course);
    }

    // Method to get all courses
    public static List<Course> getCourses() {
        return courses;
    }

    // Method to get all students
    public static List<Student> getStudents() {
        return students;
    }

    // Method to assign a grade to a student for a course
    public static void assignGradeToStudent(Student student, Course course, int grade) {
        if (grade >= 0) {
            student.assignGrade(course, grade);
            System.out.println("Grade " + grade + " assigned to " + student.getName() + " for " + course.getCourseName() + ".");
        } else {
            System.out.println("Error: Grade cannot be negative.");
        }
    }

    // Method to list all courses
    public static void listCourses() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println(course.getCourseCode() + " - " + course.getCourseName() + " | Max Capacity: " + course.getMaxCapacity());
        }
    }
    // Method to enroll a student in a course
    public static void enrollStudent(Student student, Course course) {
        if (course.hasCapacity()) {
            if (!students.contains(student)) {
                students.add(student);  // Add student to the list if not already present
            }
            student.enrollCourse(course);  // Add course to student's enrolled courses
            course.incrementEnrolledStudents();  // Increment the course's enrolled count
            System.out.println("Student " + student.getName() + " enrolled in " + course.getCourseName() + ".");
        } else {
            System.out.println("Error: Course " + course.getCourseName() + " is full.");
        }
    }

    public static double calculateOverallGrade(Student student) {
        List<Integer> grades = student.getGrades();
        int totalGrades = 0;
        int gradeCount = 0;

        for (Integer grade : grades) {
            if (grade != null) { // Ignore null grades
                totalGrades += grade;
                gradeCount++;
            }
        }

        if (gradeCount == 0) {
            System.out.println("No grades assigned for student: " + student.getName());
            return 0.0; // Return 0.0 if no grades are assigned
        }

        return (double) totalGrades / gradeCount; // Calculate and return the average
    }

}
