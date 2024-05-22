@API
Feature: User functionality

  Scenario:  Check that user can be created by api request
    Given the API base URL is available "http://localhost:8080/user"
    When user sends a POST request to create a user "vasile" with age 22 and id "ID321"
    Then the response status code should be 200
    And the "name" response field has value "vasile"

  Scenario: Check that a created user can be deleted by api request
    Given the necessary user is created
    When user sends a DELETE request with required data id
    Then the response status code should be 200
    And user should be deleted

  Scenario: Check that user can be modified by api request
    Given the necessary user is created
    When user sends a PUT request to update user name "john" and age 22
    Then user should be modified to "john"
