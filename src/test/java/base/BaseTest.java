package base;

import db.DBConnectionManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    @BeforeSuite
    public void setUp() {
        DBConnectionManager.getConnection();
    }

    @AfterSuite
    public void tearDown() {
        DBConnectionManager.closeConnection();
    }
}
