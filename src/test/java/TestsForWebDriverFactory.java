import base.WebDriverFactory;
import base.WebDriverNames;
import config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestsForWebDriverFactory {
    private Logger logger = LogManager.getLogger(TestsForWebDriverFactory.class);
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    protected static WebDriver driver;

    @Test
    public void test1Chrome(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("--stable-release-mode");
        driver = WebDriverFactory.create(WebDriverNames.CHROME, options);
        logger.info("Driver is started");
        driver.get(cfg.url());
    }

    @Test
    public void test2Chrome(){
        driver = WebDriverFactory.create(WebDriverNames.CHROME);
        logger.info("Driver is configured and started");
        driver.get(cfg.url());
        driver.manage().window().setPosition(new Point(20, 20));
        driver.manage().window().setPosition(new Point(40, 20));
        driver.manage().window().setPosition(new Point(20, 40));
        driver.manage().window().setPosition(new Point(20, 20));
    }

    @Test
    public void test3Safari(){
        driver = WebDriverFactory.create(WebDriverNames.SAFARI);
        logger.info("Driver is configured and started");
        driver.get(cfg.url());
    }

    @After
    public void teardown(){
        driver.quit();
    }
}
