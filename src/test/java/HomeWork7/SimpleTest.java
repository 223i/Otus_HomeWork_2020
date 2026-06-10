package HomeWork7;

import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

@Execution(ExecutionMode.CONCURRENT)
public class SimpleTest extends JUnitTestBase {

    @Test
    public void checkTitleTest (){

        Logger logger = LogManager.getLogger(SimpleTest.class);
        ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
        driver.get("https://otus.ru/");
        logger.info("Otus Page is opened");
        String title = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";
        assertEquals(driver.getTitle(), title);
        logger.info("Title on page is checked");
    }
}
