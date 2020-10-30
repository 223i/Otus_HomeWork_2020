package HomeWork1;

import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
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
    @Epic("Otus")
    @Feature("Навигация на сайте")
    @Story("Открытие стартовой страницы")
    @Description("Тест проверяет, что страница открылась и заголовок страницы правильный")
    public void checkTitleTest (){
        driver.get(cfg.url());
        logger.info("Otus Page is opened");
        String title = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";
        assertEquals(driver.getTitle(), title);
        logger.info("Title on page is checked");
    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
