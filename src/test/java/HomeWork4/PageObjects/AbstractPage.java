package HomeWork4.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class AbstractPage {

    protected static WebDriver driver;
    protected List<WebElement> menuItems;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    protected void clickMenuItem(String menuItemName) {
        for (WebElement item : menuItems) {
            if (item.getText().toLowerCase().equals(menuItemName.toLowerCase())) {
                item.click();
                break;
            }
        }
    }

    protected void elementClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}
