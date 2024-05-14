Feature: Registering  a new account on The Luma

#  @UI
#  Scenario: Registering with existing username
#    Given user is on the Luma registration page
#    When user fill in the registration form with a username that already exists
#      | NAME | LAST NAME | EMAIL                 | PASSWORD    | CONFIRM PASSWORD |
#      | John | Doe       | pterodont32@gmail.com | Anytown123. | Anytown123.      |
#    And  register button is clicked
#    Then user should see an error message indicating the username is already taken
#
#  @UI
#  Scenario: Check that user with weak password can be registered
#    Given user is on the Luma registration page
#    When user fill in the registration form with a username that already exists
#      | NAME | LAST NAME | EMAIL                 | PASSWORD | CONFIRM PASSWORD |
#      | John | Doe       | pterodont32@gmail.com | Any      | Anyt             |
#    And  register button is clicked
#    Then user should see an error message indicating the password is too weak
#
#  @UI
#  Scenario: Check that password and confirm password should be the same
#    Given user is on the Luma registration page
#    When user fill in the registration form with a username that already exists
#      | NAME | LAST NAME | EMAIL                 | PASSWORD    | CONFIRM PASSWORD |
#      | John | Doe       | pterodont32@gmail.com | Anytown123. | Anyt             |
#    And  register button is clicked
#    Then user should see an error message indicating  that password are not the same
