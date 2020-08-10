package HomeWork3;

import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class YandexTest {
    protected static WebDriver driver;
    private ServerConfig config = ConfigFactory.create(ServerConfig.class);
    private Logger logger = LogManager.getLogger(YandexTest.class);

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void yandexMarketTest() {
//        String showAllBrandsButton = "//fieldset/legend[contains(text(),'Производитель')] /../footer/button[contains(text(), 'Показать всё')]";
        String showAllBrandsButton = "//legend[contains(text(),'Производитель')]/following-sibling::footer/button[contains(text(), 'Показать всё')]";
        String checkboxBrandXiaomi = "//input[@type='checkbox' and @name='Производитель Xiaomi']";
        String checkboxBrandHUAWEI = "//input[@type='checkbox' and @name='Производитель HUAWEI']";
        String checkboxTypeSmartphone = "//input[@type='checkbox' and @name='Тип смартфон']";
        String searchResults = "//article[@data-autotest-id='product-snippet']";
        String brandXiaomi = "XIAOMI";
        String brandHUAWEI = "HUAWEI";
        String filterPrice = "//button[@data-autotest-id='dprice']";
        String comparisonOfItemsBar = "//div[contains(text(), 'Сравнение товаров')]";
        String compareContent = "//div[@data-apiary-widget-id='/content/compareContent']//div/img";
        String allProperties = "//div[@data-apiary-widget-id='/content/compareContent/compareToolbar']//button[contains(text(), 'Все характеристики')]";
        String varyingProperties = "//div[@data-apiary-widget-id='/content/compareContent/compareToolbar']//button[contains(text(), 'Различающиеся характеристики')]";
        String operationalSystem = "//div[@data-apiary-widget-id='/content/compareContent']//div/div/div[contains(text(), 'Операционная система')]";

        driver.get(config.urlYandexMarket());
        logger.info("Test starts");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        new WebDriverWait(driver, 5, 1).until(ExpectedConditions.elementToBeClickable(By.xpath(showAllBrandsButton)));
        clickElement(driver.findElement(By.xpath(showAllBrandsButton)));
        filterBrand(brandXiaomi, checkboxBrandXiaomi);
        filterBrand(brandHUAWEI, checkboxBrandHUAWEI);
        clickElement(driver.findElement(By.xpath(checkboxTypeSmartphone)));
        driver.findElement(By.xpath(filterPrice)).click();
        new WebDriverWait(driver, 8, 1).until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("/html/body/div[3]/div[5]/div[2]/div/div[1]/div/div/div[2]/div/div")));
        addFirstProductToComparison(searchResults, brandXiaomi);
        addFirstProductToComparison(searchResults, brandHUAWEI);
        driver.get(config.urlYandexMarketComparison());
        Assert.assertTrue(driver.findElement(By.xpath(comparisonOfItemsBar)).isDisplayed());
        checkNumberOfItemsInComparison(compareContent);
        clickElement(driver.findElement(By.xpath(allProperties)));
        Assert.assertTrue(driver.findElement(By.xpath(operationalSystem)).isDisplayed());
        clickElement(driver.findElement(By.xpath(varyingProperties)));
        Assert.assertEquals(checkAbsenceOfElement(operationalSystem), true);
        logger.info("Test is finished");
    }

    private boolean checkAbsenceOfElement(String locatorOfElement) {
        try {
            driver.findElement(By.xpath(locatorOfElement));
            logger.error("There is element " + locatorOfElement + " in DOM");
            return false;
        } catch (NoSuchElementException e) {
            logger.warn(e);
            return true;
        }
    }

    private void checkNumberOfItemsInComparison(String compareContent) {
        List<WebElement> listWithResults = driver.findElements(By.xpath(compareContent));
        Assert.assertEquals("The amount of items in comparison is false. 2 items must be placed in the list",
                listWithResults.size(), 2);
    }

    private void addFirstProductToComparison(String searchResults, String necessaryBrandModel) {
        WebDriverWait wait = new WebDriverWait(driver, 15, 1);
        List<WebElement> listWithResults = driver.findElements(By.xpath(searchResults));
        for (int i = 0; i < listWithResults.size(); i++) {
            try {
                if (listWithResults.get(i).getText().contains(necessaryBrandModel)) {
                    Actions actions = new Actions(driver);
                    actions.moveToElement(listWithResults.get(i).findElement(
                            By.xpath("//div[contains(@aria-label,'сравнению')]"))).build().perform();
                    interactWithComparisonButton(necessaryBrandModel, wait, listWithResults, i);
                    break;
                }
            } catch (StaleElementReferenceException e) {
                logger.error("StaleElementReferenceException " + e + " is caught");
                interactWithComparisonButton(necessaryBrandModel, wait, listWithResults, i);
                break;
            }
        }
    }

    private void interactWithComparisonButton(String necessaryBrandModel, WebDriverWait wait, List<WebElement> listWithResults, int i) {
        wait.until(ExpectedConditions.visibilityOf(listWithResults.get(i)));
        wait.until(ExpectedConditions.elementToBeClickable(listWithResults.get(i).findElement(By.xpath("//div[contains(@aria-label,'сравнению')]"))));
        clickElement(listWithResults.get(i).findElement(By.xpath("//div[contains(@aria-label,'сравнению')]")));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(text(), 'добавлен к сравнению')]"))));
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(), 'добавлен к сравнению')]")).isDisplayed());
        logger.info("First product from " + necessaryBrandModel + " is added to the comparison list");
    }

    private void filterBrand(String brandName, String checkboxBrand) {
        WebDriverWait wait = new WebDriverWait(driver, 15, 1);
        String fieldSetFindBrand = "//fieldset/legend[contains(text(),'Производитель')]/..//input[@name='Поле поиска']";
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(fieldSetFindBrand))));
        driver.findElement(By.xpath(fieldSetFindBrand)).sendKeys(brandName);
        driver.findElement(By.xpath(fieldSetFindBrand)).sendKeys(Keys.RETURN);
        clickElement(driver.findElement(By.xpath(checkboxBrand)));
        logger.info("Necessary  Brand " + brandName + " is selected");
        driver.findElement(By.xpath(fieldSetFindBrand)).clear();
    }

    private void clickElement(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        logger.info("Element " + element + " has been clicked");
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}