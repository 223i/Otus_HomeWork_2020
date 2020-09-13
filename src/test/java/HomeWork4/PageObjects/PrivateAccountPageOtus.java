package HomeWork4.PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PrivateAccountPageOtus extends AbstractPage {

    private By nameLatin = By.xpath("//div/input[@name = 'fname_latin']");
    private By lastNameLatin = By.xpath("//div/input[@name = 'lname_latin']");
    private By dateOfBirth = By.xpath("//div/input[@name = 'date_of_birth']");
    private By typeOfConnectionDropDownList = By.xpath("//div/span[contains(text(),'Способ связи') ]");
    private By firstConnectionSetField = By.xpath("//input[@name='contact-0-value']");
    private By secondConnectionSetField = By.xpath("//input[@name='contact-1-value']");
    private By addNewConnectionButton = By.xpath("//button[contains(text(), 'Добавить')]");
    private By saveAndContinueButton = By.xpath("//button[@title='Сохранить и продолжить']");
    private By countryDropDownList = By.xpath("//label/input[@name='country']/following-sibling::div");
    private By cityDropDownList = By.xpath("//label/input[@name='city']/following-sibling::div");
    private By englishLevelDropDownList = By.xpath("//label/input[@name='english_level']/following-sibling::div");


    public PrivateAccountPageOtus(WebDriver driver) {
        super(driver);
        menuItems = driver.findElements(By.xpath("//div/header[2]/div"));
    }

    @Override
    protected void clickMenuItem(String menuItemName) {
        super.clickMenuItem(menuItemName);
    }

    @Override
    protected void elementClick(WebElement element) {
        super.elementClick(element);
    }

    public void setNameLatinFieldValue(String nameLatinFieldValue) {
        if (this.getNameLatinFieldValue().equals("")) {
            driver.findElement(nameLatin).sendKeys(nameLatinFieldValue);
        }
    }

    public void setTypeOfConnectionDropDownListValue(String typeOfConnectionDropDownListValue) {
        try {
            if (driver.findElement(typeOfConnectionDropDownList).isDisplayed()) {
                driver.findElement(typeOfConnectionDropDownList).click();
                List<WebElement> buttonsInDropDownList = driver.findElements(By.xpath("//button[@data-value='" + typeOfConnectionDropDownListValue + "']"));
                elementClick(buttonsInDropDownList.get(buttonsInDropDownList.size()-1));
            }
        } catch (NoSuchElementException e) {

        }
    }

    public void setCountryDropDownListValue(String countryDropDownListValue) {
        driver.findElement(countryDropDownList).click();
        elementClick(driver.findElement(By.xpath("//div/button[@title='" + countryDropDownListValue + "']")));
    }

    public void setCityDropDownListValue(String cityDropDownListValue) {
        driver.findElement(cityDropDownList).click();
        elementClick(driver.findElement(By.xpath("//div/button[@title='" + cityDropDownListValue + "']")));
    }

    public void setEnglishLevelDropDownListValue(String englishLevelDropDownListValue) {
        driver.findElement(englishLevelDropDownList).click();
        elementClick(driver.findElement(By.xpath("//div/button[@title='" + englishLevelDropDownListValue + "']")));
    }

    public void setLastNameLatinFieldValue(String lastNameLatinFieldValue) {
        if (this.getLastNameLatinFieldValue().equals("")) {
            driver.findElement(lastNameLatin).sendKeys(lastNameLatinFieldValue);
        }
    }

    public void setDateOfBirthFieldValue(String dateOfBirthFieldValue) {
        if (this.getDateOfBirthFieldValue().equals(null)) {
            driver.findElement(dateOfBirth).sendKeys(dateOfBirthFieldValue);
        }
    }

    public void setFirstConnectionSetFieldValue(String firstConnectionSetFieldValue) {
        if (this.getFirstConnectionSetFieldValue().equals("")){
            driver.findElement(firstConnectionSetField).sendKeys(firstConnectionSetFieldValue);
        }
    }

    public void setSecondConnectionSetFieldValue(String secondConnectionSetFieldValue) {
        if(this.getSecondConnectionSetFieldValue().equals("")){
            driver.findElement(secondConnectionSetField).sendKeys(secondConnectionSetFieldValue);
        }
    }

    public String getNameLatinFieldValue() {
        return driver.findElement(nameLatin).getAttribute("value");
    }

    public String getLastNameLatinFieldValue() {
        return driver.findElement(lastNameLatin).getAttribute("value");
    }

    public String getFirstConnectionSetFieldValue() {
        return driver.findElement(firstConnectionSetField).getAttribute("value");
    }

    public String getSecondConnectionSetFieldValue() {
        return driver.findElement(secondConnectionSetField).getAttribute("value");
    }

    public String getCountryFieldValue() {
        return driver.findElement(countryDropDownList).getText();
    }

    public String getCityFieldValue() {
        return driver.findElement(cityDropDownList).getText();
    }

    public String getEnglishLevelFieldValue() {
        return driver.findElement(englishLevelDropDownList).getText();
    }

    public String getDateOfBirthFieldValue() {
        return driver.findElement(dateOfBirth).getAttribute("value");
    }

    public void clickAddNewConnectionButton() {
        driver.findElement(addNewConnectionButton).click();
        new WebDriverWait(driver, 5, 10).until(ExpectedConditions.visibilityOf(
                driver.findElement(typeOfConnectionDropDownList)));
    }

    public void clickSaveAndContinueButton() {
        elementClick(driver.findElement(saveAndContinueButton));
    }
}
