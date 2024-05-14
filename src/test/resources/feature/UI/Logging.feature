Feature: Logging into to the Luma

@UI
  Scenario: Logging in with valid credentials
    Given user is on the Luma  page
    When user click on the Sign In button on the header
    And user enter valid email "fortestingv2000@gmail.com" and password "9gGcYLuW7D"
    And user click the login button
    Then user should see a welcome message with my username
#@UI
#  Scenario Outline: Logging in with invalid credential
#    Given user is on the Luma  page
#    When user click on the Sign In button on the header
#    And user enter valid email "<email>" and password "<password>"
#    And user click the login button
#    Then user should see a an error message
#
#    Examples:
#      | email                    | password              |
#      | admin@gmail.com          | Gap31s3gs3ggap        |
#      | pterodont32@gmail.com    | pterodont32@gmail.com |
#      | Gap31s3gs3ggap@gmail.com | Premium               |

