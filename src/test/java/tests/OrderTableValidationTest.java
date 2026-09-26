package tests;

import base.BaseTest;
import db.QueryExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class OrderTableValidationTest extends BaseTest {

    @Test
    public void validateOrdersTableHasData() {
        List<Map<String, Object>> result = QueryExecutor.executeQuery(
                "SELECT COUNT(*) as total FROM orders");
        long count = (long) result.get(0).get("total");
        Assert.assertTrue(count > 0, "Orders table should not be empty");
    }

    @Test
    public void validateNoOrdersWithNullStatus() {
        List<Map<String, Object>> result = QueryExecutor.executeQuery(
                "SELECT * FROM orders WHERE status IS NULL");
        Assert.assertEquals(result.size(), 0, "Found orders with null status");
    }

    @Test
    public void validateOrderTotalsAreNonNegative() {
        List<Map<String, Object>> result = QueryExecutor.executeQuery(
                "SELECT * FROM orders WHERE total_amount < 0");
        Assert.assertEquals(result.size(), 0, "Found orders with a negative total_amount");
    }
}