package db;

import utils.ResultSetUtils;
import java.sql.*;
import java.util.List;
import java.util.Map;

public class QueryExecutor {

    public static List<Map<String, Object>> executeQuery(String query) {
        try (Statement stmt = DBConnectionManager.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            return ResultSetUtils.toList(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Query execution failed: " + query, e);
        }
    }

    public static int executeUpdate(String query) {
        try (Statement stmt = DBConnectionManager.getConnection().createStatement()) {
            return stmt.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException("Update execution failed: " + query, e);
        }
    }
}
