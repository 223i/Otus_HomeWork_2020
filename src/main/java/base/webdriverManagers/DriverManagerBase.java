package base.webdriverManagers;
import org.openqa.selenium.WebDriver;

public abstract class DriverManagerBase {

    protected WebDriver driver;
    protected abstract void setUpDriver();
    protected abstract void createDriver();
    protected abstract void createDriverWithOptions(WebDriver.Options options);


    public void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }


    public WebDriver getDriver() {
        if (null == driver) {
            setUpDriver();
            createDriver();
        }
        return driver;
    }

    public WebDriver getDriver(WebDriver.Options options) {
        if (null == driver) {
            setUpDriver();
            createDriverWithOptions(options);
        }
        return driver;
    }
}
