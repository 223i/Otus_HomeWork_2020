package base;

import base.webdriverManagers.ChromeDriverManager;
import base.webdriverManagers.FirefoxDriverManager;
import base.webdriverManagers.SafariDriverManager;
import org.openqa.selenium.WebDriver;


public class WebDriverFactory {
    private static final String WEB_DRIVER_PROPERTY = "browser";

    public static WebDriver create(WebDriverNames webDriverName) {
        WebDriver driver = null;
        switch (webDriverName) {
            case CHROME:
                driver = new ChromeDriverManager().getDriver();
                break;
            case SAFARI:
                driver = new SafariDriverManager().getDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriverManager().getDriver();
                break;
            default:
                throw new IllegalArgumentException("Wrong driver type:" + webDriverName);
        }
        return driver;
    }

    public static WebDriver create(WebDriverNames webDriverName, WebDriver.Options options) {
        WebDriver driver = null;
        switch (webDriverName) {
            case CHROME:
                driver = new ChromeDriverManager().getDriver(options);
                break;
            case SAFARI:
                driver = new SafariDriverManager().getDriver(options);
                break;
            case FIREFOX:
                driver = new FirefoxDriverManager().getDriver(options);
                break;
            default:
                throw new IllegalArgumentException("Wrong driver type:" + webDriverName);
        }
        return driver;
    }


    private WebDriverNames getDriverProperty() {
        String nameOfDriver = System.getProperty(WEB_DRIVER_PROPERTY).toUpperCase();
        return WebDriverNames.valueOf(nameOfDriver);
    }
}
