package stepDefinition.API;

import api.models.UserRequestDTO;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import utils.ScenarioContext;

import static api.clients.UserAPIClient.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.Messages.USER_IS_NOT_PRESENT;

public class UserApiSteps {
    private static final Logger log = LogManager.getLogger(UserApiSteps.class);
    private Response response;
    private final ScenarioContext scenarioContext;
    private static final Faker faker = new Faker();

    public UserApiSteps() {
        this.scenarioContext = new ScenarioContext(); // Ensure it is initialized
    }

    @Given("the API base URL is available {string}")
    public void theAPIBaseURLIsAvailable(String baseURL) {
        given().when().get(baseURL).then().statusCode(200);
        log.info("API base URL is available: {}", baseURL);
    }

    @When("user sends a POST request to create a user {string} with age {int} and id {string}")
    public void userSendsAPOSTRequestToCreateAUserWithAgeAndId(String name, int age, String id) {
        UserRequestDTO user = UserRequestDTO.builder().age(age).id(id).name(name).build();
        response = addNewUser(user);
        scenarioContext.setContext("userId", id);
        log.info("Sent POST request to create user: {}", user);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        assertThat(response.getStatusCode()).isEqualTo(statusCode);
        log.info("Validating response status code. Expected: {}, Actual: {}", statusCode, response.getStatusCode());
    }

    @And("the {string} response field has value {string}")
    public void theResponseFieldHasValue(String field, String name) {
        String userId = scenarioContext.getContext("userId", String.class);
        response = getUser(userId);
        assertThat(response.getBody().asString()).contains(field, name);
    }

    @Given("the necessary user is created")
    public void theNecessaryUserIsCreated() {
        String name = faker.name().fullName();
        int age = faker.number().numberBetween(18, 65);
        String id = faker.idNumber().valid();
        UserRequestDTO user = UserRequestDTO.builder().age(age).id(id).name(name).build();
        response = addNewUser(user);
        scenarioContext.setContext("userId", id);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
        log.info("Created necessary user: {}", response.getBody().asString());

    }


    //TODO Scenario context enum for id
    @When("user sends a DELETE request with required data id")
    public void userSendAnDELETERequestWithRequiredData() {
        String userId = scenarioContext.getContext("userId", String.class);
        response = deleteUser(userId);
        log.info("Sent DELETE request to delete user with ID: {}", userId);
        log.info("Received response: {}", response.getBody().asString());
    }

    @And("user should be deleted")
    public void userShouldBeDeleted() {
        String userId = scenarioContext.getContext("userId", String.class);
        response = getUser(userId);
        assertThat(response.getBody().asString()).contains(USER_IS_NOT_PRESENT.getMessage());
    }

    @When("user sends a PUT request to update user name {string} and age {int}")
    public void userSendAnPUTRequestWithRequiredData(String name, int age) {
        String userId = scenarioContext.getContext("userId", String.class);
        UserRequestDTO user = UserRequestDTO.builder().age(age).id(userId).name(name).build();
        response = modifyUser(user);
        log.info("Sent PUT request to modify user: {}", response.getBody().asString());
    }

    @Then("user should be modified to {string}")
    public void userShouldBeModified(String content) {
        String userId = scenarioContext.getContext("userId", String.class);
        response = getUser(userId);
        log.info("Validating user modification. Expected content: {}, Actual Response: {}", content, response.getBody().asString());
        assertThat(response.getBody().asString()).contains(content);
    }

}

