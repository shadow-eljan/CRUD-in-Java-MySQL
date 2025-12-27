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

    }
}