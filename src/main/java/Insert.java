import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;

public class Insert {
    public static void main(String[] args) {

        String query = "INSERT INTO new_table VALUES (\"Ram\", \"ram@gmail.com\")";
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        String url = dotenv.get("DB_URL");
        String user = dotenv.get("DB_USER");
        String pass = dotenv.get("DB_PASS");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Connection Successful.");
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(query);
            System.out.println("You inserted: "+ rows);
            statement.close();
            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
