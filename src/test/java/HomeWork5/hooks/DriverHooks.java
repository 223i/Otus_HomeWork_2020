package HomeWork5.hooks;

import HomeWork5.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class DriverHooks {
    @Before()
    public void setupDriver() {
        DriverManager.setupDriver();
    }

    @After()
    public void quitDriver() {
        DriverManager.quitDriver();
    }
}
