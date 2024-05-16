Feature: Get User by ID

  @API
  Scenario:  Check that user can be create by api request
    Given the API base URL  is available "http://localhost:8080/user"
    When user sends a POST request to create a user "vasile" with age 22 and id "ID321"
    Then the response status code should be 200
    And the "name" response field has value "vasile"

#  @API
#  Scenario: Check that a created user can be deleted by api request
#    Given the necessary user is created
#    When user send an DELETE request with required data id
#    Then the response status code should be 200
#    And user should be deleted
#
#  @API
#  Scenario:  Check that user can be modify by api request
#    Given the necessary user is created
#    When user send an PUT request to modify a user "john" with age 33
#    Then  user should be modified to "john"


