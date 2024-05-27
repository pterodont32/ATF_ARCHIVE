package utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static utils.PropertyUtils.getPropertyFromConfigFile;

public class DatabaseManager {
    private static final Logger log = LogManager.getLogger(DatabaseManager.class);
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                String url = getPropertyFromConfigFile("dbUrl");
                String user = getPropertyFromConfigFile("dbUser");
                String password = getPropertyFromConfigFile("dbPassword");

                connection = DriverManager.getConnection(url, user, password);
                log.debug("Database connection established successfully.");
            } catch (SQLException e) {
                log.error("Failed to connect to the database. Error: {}", e.getMessage());
                throw new RuntimeException("Failed to connect to the database", e);
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                log.debug("Database connection closed successfully.");
            } catch (SQLException e) {
                log.error("Error closing the database connection. Error: {}", e.getMessage());
                throw new RuntimeException("Error closing the database connection", e);
            }
        }
    }
}
