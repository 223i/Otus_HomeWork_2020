package base.webdriverManagers;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

public abstract class DriverManagerBase {

    protected WebDriver driver;
    protected abstract void setUpDriver();
    protected abstract void createDriver();
    protected abstract void createDriverWithOptions(Capabilities options);

    public WebDriver getDriver() {
        if (null == driver) {
            setUpDriver();
            createDriver();
        }
        return driver;
    }

    public WebDriver getDriver(Capabilities options) {
        if (null == driver) {
            setUpDriver();
            createDriverWithOptions(options);
        }
        return driver;
    }
}
