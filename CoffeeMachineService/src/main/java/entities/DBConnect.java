package entities;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    public Connection ConnectToDB() throws SQLException {

        try {
            DriverManager.registerDriver(new Driver());
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schematest?serverTimezone=UTC",
                    "root", "1088834");

            return connection;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;

    }
}
