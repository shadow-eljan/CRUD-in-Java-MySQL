package CRUD.StudentmanagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentDAO extends StudentManagementSystem{

    Scanner scanner= new Scanner(System.in);
    Connection connection=StudentManagementSystem.DBConnection();

    void addStudent() throws SQLException, ClassNotFoundException {
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
}
