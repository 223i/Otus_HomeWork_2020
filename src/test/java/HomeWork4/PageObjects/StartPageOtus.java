package HomeWork4.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class StartPageOtus extends AbstractPage {

    private WebElement loginAndRegistrationButton = driver.findElement(By.xpath("//div/div[3]/div[1]/button"));

    public StartPageOtus(WebDriver driver) {
        super(driver);
        this.menuItems = driver.findElements(By.xpath("//div/header[2]/div"));
    }

    @Override
    protected void clickMenuItem(String menuItemName) {
        super.clickMenuItem(menuItemName);
    }

    @Override
    protected void elementClick(WebElement element) {
        super.elementClick(element);
    }

    public LoginRegistrationPage loginAndRegistrationButtonClick() {
        try {
            elementClick(loginAndRegistrationButton);
            return new LoginRegistrationPage(driver);
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
