import java.util.HashMap;
import java.util.Map;

public class Student {
    private String name;
    private int id;
    private Map<Course, Integer> enrolledCourses; // Stores Course objects and grades

    // Constructor
    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        this.enrolledCourses = new HashMap<>();
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Course, Integer> getEnrolledCourses() {
        return enrolledCourses;
    }

    // Method to enroll in a course
    public void enrollCourse(Course course) {
        if (enrolledCourses.containsKey(course)) {
            System.out.println("Already enrolled in this course.");
            return;
        }
        if (course.addStudent()) { // Add student if capacity allows
            enrolledCourses.put(course, null); // Null grade initially
            System.out.println("Enrolled in course: " + course.getCourseName());
        } else {
            System.out.println("Course capacity reached. Cannot enroll.");
        }
    }

    // Method to assign a grade for a course
    public void assignGrade(Course course, int grade) {
        if (enrolledCourses.containsKey(course)) {
            enrolledCourses.put(course, grade);
            System.out.println("Grade assigned: " + grade + " for course " + course.getCourseName());
        } else {
            System.out.println("Student is not enrolled in this course.");
        }
    }
}
