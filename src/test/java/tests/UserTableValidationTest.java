package tests;

import base.BaseTest;
import db.QueryExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class UserTableValidationTest extends BaseTest {

    @Test
    public void validateUserTableRowCount() {
        List<Map<String, Object>> result = QueryExecutor.executeQuery(
                "SELECT COUNT(*) as total FROM users");
        long count = (long) result.get(0).get("total");
        Assert.assertTrue(count > 0, "Users table should not be empty");
        System.out.println("Users table validated");
    }

    @Test
    public void validateNoNullEmailsInUsers() {
        List<Map<String, Object>> result = QueryExecutor.executeQuery(
                "SELECT * FROM users WHERE email IS NULL");
        Assert.assertEquals(result.size(), 0, "Found users with null email");
        System.out.println("Emails table validated");
    }
}
