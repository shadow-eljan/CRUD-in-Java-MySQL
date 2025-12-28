package CRUD;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;


public class StudentManagementSystem {
   static Scanner scanner = new Scanner(System.in);

    static String createQuery = "INSERT INTO students(name, email, department) VALUES(?,?,?)";
    static String createCourses = "INSERT INTO courses(course_name, credit, fee) VALUES(?,?,?)";
    static String createEnrollments = "INSERT INTO enrollments(student_id, course_id, enroll_date) VALUES(?,?,?)";
    static String viewQuery1 = "SELECT * FROM students";
    static String viewQuery3 = "SELECT * FROM enrollments";

    static Connection connection = null;

    public static void main(String[] args) throws SQLException {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        String url = dotenv.get("DB_URL");
        String user = dotenv.get("DB_USER");
        String pass = dotenv.get("DB_PASS");
        boolean repeat = true;


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);

            System.out.println("Connection Successfully.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

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
                case 1 -> addStudent();
                case 2 -> addCourse();
                case 3 -> addEnrollment();
                case 4 -> viewStudents();
                case 5 -> viewEnrollments();
                case 6 -> search();
                case 7 -> repeat = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }
    static void addStudent() {
        System.out.print("Enter  student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student email: ");
        String email = scanner.nextLine();

        System.out.print("Enter student department: ");
        String department = scanner.nextLine();

        try(PreparedStatement ps = connection.prepareStatement(createQuery)){
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, department);

            int rows = ps.executeUpdate();
            System.out.println(rows +" data Created.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    static void addCourse() {
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();

        System.out.print("Enter course credit: ");
        int credit = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter course fee: ");
        double fee = scanner.nextDouble();
        scanner.nextLine();

        try(PreparedStatement ps = connection.prepareStatement(createCourses)){
            ps.setString(1, courseName);
            ps.setInt(2, credit);
            ps.setDouble(3, fee);

            int rows = ps.executeUpdate();
            System.out.println(rows +" data Created.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    static void addEnrollment() {
        System.out.print("Enter student Id: ");
        int studentId = scanner.nextInt();

        System.out.print("Enter  course ID: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Year:");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Month:");
        int month = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Date:");
        int day = scanner.nextInt();
        scanner.nextLine();
        LocalDate localDate = LocalDate.of(year, month, day);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);


        try(PreparedStatement ps = connection.prepareStatement(createEnrollments)){
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.setDate(3, sqlDate);

            int rows = ps.executeUpdate();
            System.out.println(rows +" data Created.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    static void viewStudents(){
        try{
            Statement statement = connection.createStatement();
            ResultSet rs1 = statement.executeQuery(viewQuery1);


            while(rs1.next()){
                System.out.println("\nStudent ID: "+ rs1.getInt("student_id"));
                System.out.println("Name: "+ rs1.getString("name"));
                System.out.println("Email: "+ rs1.getString("email"));
                System.out.println("Department: "+ rs1.getString("department"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };
    static void viewEnrollments(){
        try{
            Statement statement = connection.createStatement();
            ResultSet rs3 = statement.executeQuery(viewQuery3);
            while(rs3.next()){
                System.out.println("\nEnrollment ID: " + rs3.getInt("enrollment_id"));
                System.out.println("Student ID: " + rs3.getInt("student_id"));
                System.out.println("Course ID: " + rs3.getInt("course_id"));
                System.out.println("Enrollment Date: " + rs3.getDate("enroll_date"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void search(){
        System.out.println("Which Table: ");
        System.out.println("1. Students");
        System.out.println("2. Courses");
        System.out.println("3. Enrollments");
        System.out.print("Your choice: ");
        int choice2 = scanner.nextInt();
        scanner.nextLine();


        if(choice2 == 1){
            String searchStudent = "SELECT * FROM students WHERE name = ?";
            System.out.print("Enter name to search: ");
            String choice = scanner.nextLine();

            try(PreparedStatement ps = connection.prepareStatement(searchStudent)){
                ps.setString(1, choice);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    System.out.println("\nStudent ID: "+ rs.getInt("student_id"));
                    System.out.println("Name: "+ rs.getString("name"));
                    System.out.println("Email: "+ rs.getString("email"));
                    System.out.println("Department: "+ rs.getString("department"));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (choice2 == 2) {
            String searchCourses = "SELECT * FROM courses WHERE course_name = ?";
            System.out.print("Enter name to search: ");
            String choice = scanner.nextLine();

            try(PreparedStatement ps = connection.prepareStatement(searchCourses)){
                ps.setString(1, choice);
                ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        System.out.println("\nCourse ID: "+ rs.getInt("course_id"));
                        System.out.println("Course Name: "+ rs.getString("course_name"));
                        System.out.println("Course Credit: "+ rs.getInt("credit"));
                        System.out.println("Course Fee: "+ rs.getDouble("fee"));
                    }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else if(choice2 == 3){
            String searchEnrollment = "SELECT * FROM enrollments WHERE enrollment_id =?";
            System.out.print("Enter id to search: ");
            int choice = scanner.nextInt();

            try(PreparedStatement ps = connection.prepareStatement(searchEnrollment)){
                ps.setInt(1, choice);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    System.out.println("\nEnrollment ID: " + rs.getInt("enrollment_id"));
                    System.out.println("Student ID: " + rs.getInt("student_id"));
                    System.out.println("Course ID: " + rs.getInt("course_id"));
                    System.out.println("Enrollment Date: " + rs.getDate("enroll_date"));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("Invalid Choice.");
        }
    }
}