package CRUD;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean repeat = true;
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        String url = dotenv.get("DB_URL");
        String user = dotenv.get("DB_USER");
        String pass = dotenv.get("DB_PASS");

        String createQuery = "INSERT INTO new_table(name, email) VALUES(?,?)";
        String viewQuery = "SELECT * FROM new_table";
        String editQuery = "UPDATE new_table SET name = ?, email = ? WHERE name = ?";
        String deleteQuery = "DELETE FROM new_table WHERE name = ?";

        Connection connection = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Connection Successful.");

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        while(repeat){
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

            switch(choice){
                case 1 -> {
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter your email: ");
                    String email = scanner.nextLine();

                    try{
                        PreparedStatement ps = connection.prepareStatement(createQuery);
                        ps.setString(1, name);
                        ps.setString(2, email);

                        int rows = ps.executeUpdate();
                        System.out.println(rows + " Data Entered.");
                        ps.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 2 -> {
                    try (PreparedStatement ps = connection.prepareStatement(viewQuery)) {
                        ResultSet rs = ps.executeQuery();
                        System.out.println("Your Data:");
                        while ((rs.next())) {
                            String name = rs.getString("name");
                            String email = rs.getString("email");

                            System.out.println(name + " : " + email);
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                }
                case 3 -> {
                    System.out.print("Enter a name to update data: ");
                    String updateName = scanner.nextLine();
                    try(PreparedStatement ps = connection.prepareStatement(editQuery)){
                        System.out.print("Enter new name: ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter new email: ");
                        String newEmail = scanner.nextLine();
                        ps.setString(1, newName);
                        ps.setString(2, newEmail);
                        ps.setString(3, updateName);

                        int rows = ps.executeUpdate();
                        System.out.println(rows + " Data Updated.");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 4 -> {
                    System.out.print("Select a name to delete data from: ");
                    String deleteName = scanner.nextLine();
                    try(PreparedStatement ps = connection.prepareStatement(deleteQuery)){
                        ps.setString(1, deleteName);

                        int rows = ps.executeUpdate();
                        System.out.println(rows + " Data Deleted.");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                }
                case 5 -> repeat = false;
                default -> System.out.println("Invalid input.");
            }
        }
        System.out.println("THANKS FOR WORKING WITH ME 😘😘😘🤣🤣🤣");
    }
}

