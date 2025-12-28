package CRUD;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;


public class StudentManagementSystem {
    Scanner scanner = new Scanner(System.in);

    String createQuery = "INSERT INTO students(name, email, department) VALUES(?,?,?)";
    String createCourses = "INSERT INTO courses(course_name, credit, fee) VALUES(?,?,?)";
    String viewQuery = "SELECT * FROM new_table";
    String editQuery = "UPDATE new_table SET name = ?, email = ? WHERE name = ?";
    String deleteQuery = "DELETE FROM new_table WHERE name = ?";

    static Connection connection = null;

    public static void main(String[] args) {
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
            System.out.println("1. CREATE");
            System.out.println("2. READ");
            System.out.println("3. UPDATE");
            System.out.println("4. DELETE");
            System.out.println("5. EXIT");
            System.out.println("🌠🌠🌠🌠🌠🌠🌠🌠🌠🌠🌠🌠🌠🌠");

            Scanner scanner = new Scanner(System.in);
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
        }
    }
    void addStudent() {
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
    void addCourse() {
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

}