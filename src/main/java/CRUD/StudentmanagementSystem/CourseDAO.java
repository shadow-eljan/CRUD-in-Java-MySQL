package CRUD.StudentmanagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CourseDAO extends StudentManagementSystem{

    Scanner scanner= new Scanner(System.in);

    Connection connection=StudentManagementSystem.DBConnection();

    void addCourse() throws ClassNotFoundException, SQLException {
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();

        System.out.print("Enter course credit: ");
        int credit = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter course fee: ");
        double fee = scanner.nextDouble();
        scanner.nextLine();


        try{
             PreparedStatement ps = connection.prepareStatement(createCourses);

            ps.setString(1, courseName);
            ps.setInt(2, credit);
            ps.setDouble(3, fee);

            int rows = ps.executeUpdate();
            System.out.println(rows +" data Created.");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
