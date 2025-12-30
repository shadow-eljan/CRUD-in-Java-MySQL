package CRUD.StudentmanagementSystem;

import java.sql.*;
import java.util.Scanner;

public class Filter extends StudentManagementSystem{

    Scanner scanner= new Scanner(System.in);
    Connection connection = StudentManagementSystem.DBConnection();

    void search(){
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
