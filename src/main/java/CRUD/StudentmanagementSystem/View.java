package CRUD.StudentmanagementSystem;

import java.sql.*;
import java.util.Scanner;

public class View extends StudentManagementSystem{

    Scanner scanner= new Scanner(System.in);
    Connection connection = StudentManagementSystem.DBConnection();

    void viewStudents(){
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
    void viewEnrollments(){
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
}
