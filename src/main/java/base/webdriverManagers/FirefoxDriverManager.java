package base.webdriverManagers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverManager extends DriverManagerBase {

    protected void setUpDriver() {
        WebDriverManager.firefoxdriver().setup();
    }

    protected void createDriver() {
        driver = new FirefoxDriver();
    }

    protected void createDriverWithOptions(Capabilities options) {
        driver = new FirefoxDriver((FirefoxOptions) options);
    }
}
