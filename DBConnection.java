import java.sql.*;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/employee_db";
        String user = "root";
        String password = "your_mysql_password"; // change this

        return DriverManager.getConnection(url, user, password);
    }
}
