package HomeWork4;

import HomeWork4.PageObjects.LoginRegistrationPage;
import HomeWork4.PageObjects.PrivateAccountPageOtus;
import HomeWork4.PageObjects.StartPageOtus;
import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class OtusTestWithPageObject {
    private static WebDriver driver;
    private Logger logger = LogManager.getLogger(OtusTestWithPageObject.class);
    private ServerConfig config = ConfigFactory.create(ServerConfig.class);

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Test is ready to be started");
    }


    @Test
    public void otusTestWithPageObject() throws Exception {
        String nameLat = "NameLatin";
        String lastNameLat = "LastNameLatin";
        String birthDay = "22.03.1996";
        String country = "Россия";
        String city = "Красноярск";
        String englishLevel = "Продвинутый (Advanced)";
        logger.info("Test starts");
        driver.get(config.url());
        logger.info("OTUS URL is opened");
        login(config.userEmail(), config.userPassword());
        Thread.sleep(3000);
        openPersonalAccount();
        fulfillInformationAboutYourself(nameLat, lastNameLat, birthDay, country, city, englishLevel);
        Thread.sleep(1000);
        driver.manage().deleteAllCookies();
        driver.get(config.url());
        logger.info("OTUS URL is opened ib 'clear' Browser (without cookies)");
        login(config.userEmail(), config.userPassword());
        Thread.sleep(3000);
        openPersonalAccount();
        PrivateAccountPageOtus privateAccount = new PrivateAccountPageOtus(driver);
        Assert.assertEquals(privateAccount.getNameLatinFieldValue(), nameLat);
        Assert.assertEquals(privateAccount.getLastNameLatinFieldValue(), lastNameLat);
        Assert.assertEquals(privateAccount.getDateOfBirthFieldValue(), birthDay);
        Assert.assertEquals(privateAccount.getCountryFieldValue(), country);
        Assert.assertEquals(privateAccount.getCityFieldValue(), city);
        Assert.assertEquals(privateAccount.getEnglishLevelFieldValue(), englishLevel);
        Assert.assertEquals( config.skypeContact(), privateAccount.getFirstConnectionSetFieldValue());
        Assert.assertEquals(config.telegramContact(), privateAccount.getSecondConnectionSetFieldValue());

    }

    private void fulfillInformationAboutYourself(String nameLat, String lastNameLat, String birthDay, String country, String city, String englishLevel) {
        PrivateAccountPageOtus privateAccount = new PrivateAccountPageOtus(driver);
        privateAccount.setNameLatinFieldValue(nameLat);
        privateAccount.setLastNameLatinFieldValue(lastNameLat);
        privateAccount.setDateOfBirthFieldValue(birthDay);
        privateAccount.setCountryDropDownListValue(country);
        privateAccount.setCityDropDownListValue(city);
        privateAccount.setEnglishLevelDropDownListValue(englishLevel);
        privateAccount.setTypeOfConnectionDropDownListValue("telegram");
        privateAccount.setFirstConnectionSetFieldValue(config.telegramContact());
        privateAccount.clickAddNewConnectionButton();
        privateAccount.setTypeOfConnectionDropDownListValue("skype");
        privateAccount.setSecondConnectionSetFieldValue(config.skypeContact());
        privateAccount.clickSaveAndContinueButton();
        logger.info("Personal information has been added into account");
    }

    private void openPersonalAccount() {
        driver.get(config.urlPersonalAccountBiography());
        WebDriverWait wait = new WebDriverWait(driver, 10, 1);
        wait.until(ExpectedConditions.titleIs("Личный кабинет | OTUS"));
        logger.info("Personal account is opened");
    }

    private void login(String email, String password) {
        StartPageOtus startPage = new StartPageOtus(driver);
        LoginRegistrationPage loginPage = startPage.loginAndRegistrationButtonClick();
        loginPage.setEmailSetFieldValue(email);
        loginPage.setPasswordSetFieldValue(password);
        loginPage.submitButtonClick();
        WebDriverWait wait = new WebDriverWait(driver, 10, 1);
        wait.until(ExpectedConditions.titleIs("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям"));
        logger.info("User logged on to his account");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
