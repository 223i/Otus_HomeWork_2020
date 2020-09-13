package HomeWork2;

import base.WebDriverFactory;
import base.WebDriverNames;
import config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class TestsForWebDriverFactorySafari {
    private Logger logger;
    private ServerConfig cfg;
    protected static WebDriver driver;

    @Before
    public void setUp(){

        logger = LogManager.getLogger(TestsForWebDriverFactoryChrome.class);
        cfg = ConfigFactory.create(ServerConfig.class);
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
