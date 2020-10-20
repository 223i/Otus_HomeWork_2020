package HomeWork5.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;


public class StartPageOtus extends AbstractPage {

    private WebElement loginAndRegistrationButton = driver.findElement(By.xpath("//div/div[3]/div[1]/button"));

    public StartPageOtus() {
        super();
        this.menuItems = driver.findElements(By.xpath("//div/header[2]/div"));
    }

    @Override
    public void clickMenuItem(String menuItemName) {
        super.clickMenuItem(menuItemName);
    }

    @Override
    public void elementClick(WebElement element) {
        super.elementClick(element);
    }

    public LoginRegistrationPage loginAndRegistrationButtonClick() {
        try {
            elementClick(loginAndRegistrationButton);
            return new LoginRegistrationPage();
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
