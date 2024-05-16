@UI
Feature: Logging into to the Luma

  Background:
    Given user is on the Luma  page

  Scenario: Logging in with valid credentials
    When user enter valid email "fortestingv2000@gmail.com" and password "9gGcYLuW7D"
    And user clicks the login button
    Then user should see a welcome message with my username

  Scenario Outline: Logging in with invalid credential
    When user enter valid email "<email>" and password "<password>"
    And user clicks the login button
    Then user should see a an error message

    Examples:
      | email                    | password              |
      | admin@gmail.com          | Gap31s3gs3ggap        |
#      | pterodont32@gmail.com    | pterodont32@gmail.com |
#      | Gap31s3gs3ggap@gmail.com | Premium               |

