package tests;

import base.BaseTest;
import db.QueryExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class DataIntegrityTest extends BaseTest {

    @Test
    public void validateNoOrphanOrders() {
        // every order.user_id must exist in the users table
        List<Map<String, Object>> result = QueryExecutor.executeQuery(
                "SELECT o.id FROM orders o " +
                        "LEFT JOIN users u ON o.user_id = u.id " +
                        "WHERE u.id IS NULL");
        Assert.assertEquals(result.size(), 0, "Found orders referencing a non-existent user");
    }

    @Test
    public void validateNoDuplicateUserEmails() {
        List<Map<String, Object>> result = QueryExecutor.executeQuery(
                "SELECT email, COUNT(*) as cnt FROM users " +
                        "GROUP BY email HAVING cnt > 1");
        Assert.assertEquals(result.size(), 0, "Found duplicate user emails");
    }
}