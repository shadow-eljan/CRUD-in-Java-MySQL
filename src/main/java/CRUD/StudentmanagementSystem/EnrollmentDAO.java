package CRUD.StudentmanagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class EnrollmentDAO extends StudentManagementSystem{

    Scanner scanner= new Scanner(System.in);
    Connection connection=StudentManagementSystem.DBConnection();

    void addEnrollment() {
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

        try{
            PreparedStatement ps = connection.prepareStatement(createEnrollments);
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.setDate(3, sqlDate);

            int rows = ps.executeUpdate();
            System.out.println(rows +" data Created.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
