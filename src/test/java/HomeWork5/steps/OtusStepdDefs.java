package HomeWork5.steps;

/**
 * Домашнее задание
 * Реализовать BDD подход
 * Реализовать 10 BDD-сценариев для сайта https://otus.ru/
 * Использовать Page Object и Cucumber
 * Уделить внимание удобству изменения Step Implementations и возможности переиспользовать шаги написанные на Gherkin
 * Критерии оценки: 1. Использование page object и cucumber - 5 баллов
 * 2. Вынесена работа с драйвером, использование hooks, переиспользование steps - 2 балла
 * Рекомендуем сдать до: 27.09.2020
 */

import HomeWork5.pages.LoginRegistrationPage;
import HomeWork5.pages.StartPageOtus;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

import static HomeWork5.driver.DriverManager.getWebDriver;

public class OtusStepdDefs {

    @Given("I open Otus main page by {string}")
    public void navigateToMainPage(String url){
        getWebDriver().navigate().to(url);
    }

    @When("I click button {string}")
    public void clickNavigationButton(String button){
        if (button.equals("Вход и регистрация")) {
            StartPageOtus page = new StartPageOtus();
            page.loginAndRegistrationButtonClick();
        } else if(button.equals("Регистрация")){
            LoginRegistrationPage page = new LoginRegistrationPage();
            page.registerComponentButtonClick();
        } else {
            LoginRegistrationPage page = new LoginRegistrationPage();
            page.submitButtonClick();
        }
    }

    @When("I select tab {string}")
    public void selectTab(String tabName) {
        StartPageOtus otusMainPage =  new StartPageOtus();
        otusMainPage.elementClick(getWebDriver().findElement(By.xpath("//div[@id='categories_id']//a[@title='" + tabName + "']")));
    }

    @And("I set {string} and {string}")
    public void iSetFieldValue(String email, String password) {
        LoginRegistrationPage page = new LoginRegistrationPage();
        page.setEmailSetFieldValue(email);
        page.setPasswordSetFieldValue(password);
    }

    @Then("I see {string} page is opened")
    public void verifyOpenedPage(String pageTitle){
        Assert.assertTrue(getWebDriver().getTitle().contains(pageTitle));
    }

    @Then("I see page with headline {string}  is opened")
    public void verifyOpenedPageHeadline(String pageHeadline){
        Assert.assertTrue(getWebDriver().getPageSource().contains(pageHeadline));
    }

    @When("I choose {string} course")
    public void iChooseCourse(String course) {
        StartPageOtus page = new StartPageOtus();
        page.elementClick(getWebDriver()
                .findElement(
                        By.xpath("//div[@class='lessons__new-item-title lessons__new-item-title_with-bg lessons__new-item-title_bundle' and contains(text(), '"
                                + course + "')]")));
    }
}
