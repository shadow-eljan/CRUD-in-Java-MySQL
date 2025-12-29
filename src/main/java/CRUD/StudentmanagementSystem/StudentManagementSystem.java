package CRUD.StudentmanagementSystem;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.util.Scanner;


public class StudentManagementSystem {
   static Scanner scanner = new Scanner(System.in);
    static Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
    static String url = dotenv.get("DB_URL");
    static String user = dotenv.get("DB_USER");
    static String pass = dotenv.get("DB_PASS");

    static String createQuery = "INSERT INTO students(name, email, department) VALUES(?,?,?)";
    static String createCourses = "INSERT INTO courses(course_name, credit, fee) VALUES(?,?,?)";
    static String createEnrollments = "INSERT INTO enrollments(student_id, course_id, enroll_date) VALUES(?,?,?)";
    static String viewQuery1 = "SELECT * FROM students";
    static String viewQuery3 = "SELECT * FROM enrollments";

    static Connection connection= null;

    static Connection DBConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Database connected successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        boolean repeat = true;

        DBConnection();
        StudentDAO student = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
        View view = new View();
        Filter filter = new Filter();

        while(repeat) {
            System.out.println("\n🌠🌠🌠🌠🌠🌠🌠🌠🌠🌠🌠🌠🌠🌠");
            System.out.println("CRUD OPTIONS TO CHOOSE:");
            System.out.println("1. Add Student:");
            System.out.println("2. Add Course:");
            System.out.println("3. Add Enrollment:");
            System.out.println("4. View Students");
            System.out.println("5. View Enrollments");
            System.out.println("6. Search");
            System.out.println("7. EXIT");
            System.out.println("🌠🌠🌠🌠🌠🌠🌠🌠🌠🌠🌠🌠🌠🌠");

            Scanner scanner = new Scanner(System.in);
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1 -> student.addStudent();
                case 2 -> courseDAO.addCourse();
                case 3 -> enrollmentDAO.addEnrollment();
                case 4 -> view.viewStudents();
                case 5 -> view.viewEnrollments();
                case 6 -> filter.search();
                case 7 -> repeat = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}