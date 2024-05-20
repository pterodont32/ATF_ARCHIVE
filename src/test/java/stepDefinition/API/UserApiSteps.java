package stepDefinition.API;

import api.clients.UserAPIClient;
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
import pageObjects.GeneralPage;
import utils.Messages;
import utils.ScenarioContext;

import static dataProviders.ConfigFileReader.logger;
import static io.restassured.RestAssured.given;

public class UserApiSteps {
    private static final Logger log = LogManager.getLogger(UserApiSteps.class);
    private Response response;
    private final ScenarioContext scenarioContext;
    private static final Faker faker = new Faker();

    public UserApiSteps() {
        this.scenarioContext = new ScenarioContext(); // Ensure it is initialized
    }

    @Given("the API base URL  is available {string}")
    public void theAPIBaseURLIsAvailable(String baseURL) {
        given().when().get(baseURL).then().statusCode(200);
        log.info("API base URL is available: {}", baseURL);

    }

    @When("user sends a POST request to create a user {string} with age {int} and id {string}")
    public void userSendsAPOSTRequestToCreateAUserWithAgeAndId(String name, int age, String id) {
        UserRequestDTO user = UserRequestDTO.builder()
                .age(age)
                .id(id)
                .name(name)
                .build();
        response = UserAPIClient.addNewUser(user);
        scenarioContext.setContext("userId", user.getId());
        scenarioContext.setContext("userName", user.getName());
        log.info("Sent POST request to create user: {}", user);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        log.info("Validating response status code. Expected: {}, Actual: {}", statusCode, response.getStatusCode());
        Assertions.assertThat(response.getStatusCode()).isEqualTo(statusCode);
    }


    @And("the {string} response field has value {string}")
    public void theResponseFieldHasValue(String field, String name) {
        String userId = scenarioContext.getContext("userId", String.class);
        Response getOneUsersResponse = UserAPIClient.getUser(userId);
        String responseBody = getOneUsersResponse.getBody().asString();
        log.info("Validating response field. Field: {}, Expected Value: {}, Actual Response: {}", field, name, responseBody);
        Assertions.assertThat(getOneUsersResponse.getBody().asString()).contains(field,name);
    }

    @Given("the necessary user is created")
    public void theNecessaryUserIsCreated() {
        String name = faker.name().fullName();
        int age = faker.number().numberBetween(18, 65);
        String id = faker.idNumber().valid();
        UserRequestDTO user = UserRequestDTO.builder()
                .age(age)
                .id(id)
                .name(name)
                .build();
        response = UserAPIClient.addNewUser(user);
        scenarioContext.setContext("userId", user.getId());
        log.info("Created necessary user: {}", user);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);

    }

    @When("user send an DELETE request with required data id")
    public void userSendAnDELETERequestWithRequiredData() {
        String userId = scenarioContext.getContext("userId", String.class);
        Response getOneUsersResponse = UserAPIClient.deleteUser(userId);
        String responseBody = getOneUsersResponse.getBody().asString();
        System.out.println(responseBody);
        log.info("Sent DELETE request to delete user with ID: {}", userId);
        log.info("Received response: {}", responseBody);
    }

    @And("user should be deleted")
    public void userShouldBeDeleted() {
        String userId = scenarioContext.getContext("userId", String.class);
        Response getOneUsersResponse = UserAPIClient.getUser(userId);
        Assertions.assertThat(getOneUsersResponse.getBody().asString()).contains(Messages.USER_IS_NOT_PRESENT.getMessage());
    }

    @When("user send an PUT request to modify a user {string} with age {int}")
    public void userSendAnPUTRequestWithRequiredData(String name, int age) {
        String userId = scenarioContext.getContext("userId", String.class);
        UserRequestDTO user = UserRequestDTO.builder()
                .age(age)
                .id(userId)
                .name(name)
                .build();
        response = UserAPIClient.modifyUser(user);
        log.info("Sent PUT request to modify user: {}", user);
    }

    @Then("user should be modified to {string}")
    public void userShouldBeModifyed(String content) {
        String userId = scenarioContext.getContext("userId", String.class);
        Response getOneUsersResponse = UserAPIClient.getUser(userId);
        String responseBody = getOneUsersResponse.getBody().asString();
        logger.info("Response Body+: {}", responseBody);
        log.info("Validating user modification. Expected content: {}, Actual Response: {}", content, responseBody);
        Assertions.assertThat(getOneUsersResponse.getBody().asString()).contains(content);
    }

}

