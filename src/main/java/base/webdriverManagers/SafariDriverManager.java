package base.webdriverManagers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class SafariDriverManager extends DriverManagerBase {
    protected void setUpDriver() {
        WebDriverManager.chromiumdriver().setup();
    }

    protected void createDriver() {
        driver = new SafariDriver();
    }

    protected void createDriverWithOptions(WebDriver.Options options) {
        driver = new SafariDriver((SafariOptions) options);
    }
}
