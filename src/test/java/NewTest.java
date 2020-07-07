import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class NewTest {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(NewTest.class);
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("WebDriver is active");
    }

    @Test
    public void checkTitleTest (){
        driver.get(cfg.url());
        logger.info("Otus Page is opened");

        assertEquals(driver.getTitle(), "Онлайн‑курсы для профессионалов, дистанционное" +
                " обучение современным профессиям");
        logger.info("Title on page is checked");
    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
