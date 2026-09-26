package db;

import config.ConfigReader;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionManager {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName(ConfigReader.get("db.driver"));
                connection = DriverManager.getConnection(
                        ConfigReader.get("db.url"),
                        ConfigReader.get("db.username"),
                        ConfigReader.get("db.password")
                );
            }
        } catch (Exception e) {
            throw new RuntimeException("DB connection failed", e);
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to close DB connection", e);
        }
    }
}
