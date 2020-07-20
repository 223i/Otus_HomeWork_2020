package base.webdriverManagers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager extends DriverManagerBase {

    protected void setUpDriver() {
        WebDriverManager.chromedriver().setup();
    }

    protected void createDriver() {
        driver = new ChromeDriver();
    }

    protected void createDriverWithOptions(Capabilities options){
        driver = new ChromeDriver((ChromeOptions) options);
    }
}
