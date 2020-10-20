#language: en

Feature: Validate fast navigation to courses via start page

  Scenario Outline: As user I can open courses via cards on Otus start page
    Given I open Otus main page by 'https://otus.ru/'
    When I choose '<course>' course
    Then I see page with headline '<pageHeadline>'  is opened
    Examples:
      | course                         | pageHeadline                   |
      | Machine Learning               | Machine Learning               |
      | Специализация Data Scientist   | Специализация Data Scientist   |
      | Специализация Java–разработчик | Специализация Java–разработчик |
      | Специализация Java архитектор  | Специализация Java архитектор  |









