import java.util.*;

public class CourseManagement {
    private static List<Course> courses = new ArrayList<>();
    private static Map<Student, Double> overallGrades = new HashMap<>();

    // Add a new course
    public static void addCourse(String code, String name, int capacity) {
        courses.add(new Course(code, name, capacity));
        System.out.println("Course added: " + name);
    }

    // Enroll a student in a course
    public static void enrollStudent(Student student, Course course) {
        student.enrollCourse(course);
    }

    // Assign a grade to a student
    public static void assignGrade(Student student, Course course, int grade) {
        student.assignGrade(course, grade);
    }

    // Calculate overall grade for a student
    public static void calculateOverallGrade(Student student) {
        Map<Course, Integer> enrolledCourses = student.getEnrolledCourses();
        int totalGrades = 0, count = 0;

        for (Integer grade : enrolledCourses.values()) {
            if (grade != null) {
                totalGrades += grade;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No grades available for student: " + student.getName());
            return;
        }

        double average = (double) totalGrades / count;
        overallGrades.put(student, average);
        System.out.println("Overall grade for " + student.getName() + " is: " + average);
    }

    // Get all courses
    public static List<Course> getCourses() {
        return courses;
    }
}
