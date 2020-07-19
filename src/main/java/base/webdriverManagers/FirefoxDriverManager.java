package base.webdriverManagers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverManager extends DriverManagerBase {

    protected void setUpDriver() {
        WebDriverManager.firefoxdriver().setup();
    }

    protected void createDriver() {
        driver = new FirefoxDriver();
    }

    protected void createDriverWithOptions(WebDriver.Options options){
        driver = new FirefoxDriver((FirefoxOptions) options);
    }
}
