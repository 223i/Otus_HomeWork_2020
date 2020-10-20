#language: en

Feature: Validate navigation between Otus pages

  Scenario: As user I can open  Otus start page
    Given I open Otus main page by 'https://otus.ru/'
    Then I see 'Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям' page is opened

  Scenario: As user I can open  Otus login page
    Given I open Otus main page by 'https://otus.ru/'
    When I click button 'Вход и регистрация'
    Then I see page with headline 'Войдите в свой аккаунт'  is opened

  Scenario: As user I can open Otus register page
    Given I open Otus main page by 'https://otus.ru/'
    When I click button 'Вход и регистрация'
    And I click button 'Регистрация'
    Then I see page with headline 'Зарегистрируйте свой аккаунт'  is opened

  Scenario: As user I can open  Otus login page and log in
    Given I open Otus main page by 'https://otus.ru/'
    When I click button 'Вход и регистрация'
    And I set 'pisaha1656@brosj.net' and 'TestUser'
    And I click button 'Войти'
    Then I see 'Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям' page is opened

  Scenario Outline: As user I can open  Otus start page and navigate to special courses
    Given I open Otus main page by 'https://otus.ru/'
    When I select tab '<tab>'
    Then I see page with headline '<pageHeadline>'  is opened
    Examples:
      | tab                       | pageHeadline                 |
      | Информационная безопасность | Информационная безопасность |
      | Data Science | Data Science |

