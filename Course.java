public class Course {
    private String courseCode;
    private String courseName;
    private int maxCapacity;
    private int currentEnrollment;

    private static int totalEnrolledStudents = 0; // Static variable

    // Constructor
    public Course(String courseCode, String courseName, int maxCapacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.maxCapacity = maxCapacity;
        this.currentEnrollment = 0;
    }

    // Getters
    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCurrentEnrollment() {
        return currentEnrollment;
    }

    // Static method to retrieve total enrolled students
    public static int getTotalEnrolledStudents() {
        return totalEnrolledStudents;
    }

    // Method to add a student if capacity allows
    public boolean addStudent() {
        if (currentEnrollment < maxCapacity) {
            currentEnrollment++;
            totalEnrolledStudents++;
            return true;
        }
        return false;
    }
}
