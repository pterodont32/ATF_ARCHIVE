@UI
Feature:  Unsuccessful registering a the Luma page

  Background:
    Given user is on the Luma registration page

  Scenario: Unsuccessful registration with an registered Email
    And user creates a new account
    And user is on the Luma registration page
    When user fills the registration form
      | NAME | LAST NAME | EMAIL | PASSWORD    | CONFIRM PASSWORD |
      | John | Doe       |       | Anytown123. | Anytown123.      |
    And user clicks register button
    Then user should see an error message indicating the username is already taken

  Scenario: Check that user with weak password can be registered
    When user fills the registration form
      | NAME | LAST NAME | EMAIL                 | PASSWORD | CONFIRM PASSWORD |
      | John | Doe       | pterodont32@gmail.com | Any      | Anyt             |
    And user clicks register button
    Then user should see an error message indicating the password is too weak

  Scenario: Check that password and confirm password should be the same
    When user fills the registration form
      | NAME | LAST NAME | EMAIL                 | PASSWORD    | CONFIRM PASSWORD |
      | John | Doe       | pterodont32@gmail.com | Anytown123. | Anyt             |
    And user clicks register button
    Then user should see an error message indicating that passwords are not the same
