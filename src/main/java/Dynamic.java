import java.sql.*;
import java.util.Scanner;

public class Dynamic {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/jdbc";
        String user = "root";
        String pass = "password@123";
        String query = "INSERT INTO new_table(name, email) VALUES (?,?)";

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name:");
        String name = sc.nextLine();
        System.out.print("Enter your email:");
        String email = sc.nextLine();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Connection Successful.");

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,name);
            ps.setString(2,email);

            int rows = ps.executeUpdate();

            System.out.println("Entered Successfully.");

            ps.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
