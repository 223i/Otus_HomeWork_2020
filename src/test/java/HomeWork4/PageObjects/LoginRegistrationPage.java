package HomeWork4.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginRegistrationPage extends AbstractPage {

    private WebElement loginComponentButton = driver.findElement(By.xpath("//div[@data-tab-id='login']"));
    private WebElement registerComponentButton = driver.findElement(By.xpath("//div[@data-tab-id='register']"));
    private By emailSetField = By.xpath("//form[@class='new-log-reg__form js-login']//div/input[@name='email']");
    private By passwordSetField = By.xpath("//form[@class='new-log-reg__form js-login']//div/input[@name='password']");
    private WebElement submitButton= driver.findElement(By.xpath("//form[@class='new-log-reg__form js-login']//div/button[@type='submit']"));

    public LoginRegistrationPage(WebDriver driver) {
        super(driver);
        this.menuItems = driver.findElements(By.xpath("//div[@class='new-log-reg__head js-tabs']"));
    }


    public StartPageOtus submitButtonClick() {
        super.elementClick(submitButton);
        return new StartPageOtus(driver);
    }

    public void loginComponentButtonClick() {
        super.elementClick(loginComponentButton);
    }

    public void registerComponentButtonClick() {
        super.elementClick(registerComponentButton);
    }

    public String getEmailSetFieldText() {
        return driver.findElement(emailSetField).getText();
    }

    public void setEmailSetFieldValue(String emailSetFieldValue) {
        driver.findElement(emailSetField).sendKeys(emailSetFieldValue);
    }

    public String getPasswordSetFieldText() {
        return driver.findElement(passwordSetField).getText();
    }

    public void setPasswordSetFieldValue(String passwordSetFieldValue) {
        driver.findElement(passwordSetField).sendKeys(passwordSetFieldValue);
    }
}
