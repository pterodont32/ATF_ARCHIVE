package stepDefinition.UI;

import com.github.javafaker.Faker;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pageObjects.RegistrationPage;
import utils.DriverManager;
import utils.ScenarioContext;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.Messages.*;

public class RegistrationPageSteps extends DriverManager {

    RegistrationPage registrationPage = new RegistrationPage(driver);
    private static final Logger log = LogManager.getLogger(RegistrationPageSteps.class);
    private static final Faker faker = new Faker();
    private final ScenarioContext scenarioContext;

    public RegistrationPageSteps() {
        this.scenarioContext = new ScenarioContext(); // Ensure it is initialized
    }

    //TODO   de adaugat log out
    @And("user creates a new account")
    public void createAccount() {
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        String email_address = faker.internet().emailAddress();
        String password = "fF@" + RandomStringUtils.randomNumeric(6);
        scenarioContext.setContext("email_address", email_address);
        registrationPage.fillRegistrationForm(firstname, lastname, email_address, password, password);
        registrationPage.clickCreateAccountButton();
    }

//TODO to check
    @When("user fills the registration form")
    public void userFillInTheRegistrationFormWithAUsernameThatAlreadyExists(DataTable dataTable) {
        List<Map<String, String>> shippingInfo = dataTable.asMaps(String.class, String.class);
        String email_address;
        for (Map<String, String> row : shippingInfo) {

            String firstname = row.get("NAME");
            String lastname = row.get("LAST NAME");

            if (row.get("EMAIL") == null) {
                email_address = scenarioContext.getContext("email_address", String.class);
            } else {
                email_address = row.get("EMAIL");
            }
            String password = row.get("PASSWORD");
            String passwordConfirmation = row.get("CONFIRM PASSWORD");
            log.info("Filling form with: {}, {}, {}, {}", firstname, lastname, email_address, password);
            registrationPage.fillRegistrationForm(firstname, lastname, email_address, password, passwordConfirmation);
        }
    }

    @And("user clicks register button")
    public void theRegisterButtonIsClicked() {
        registrationPage.clickCreateAccountButton();
    }

    @Then("user should see an error message indicating the username is already taken")
    public void userShouldSeeAnErrorMessageIndicatingTheUsernameIsAlreadyTaken() {
        String actualError = registrationPage.getEmailIsTakenError();
        log.info("Actual error message for username is already taken: {}", actualError);
        assertThat(actualError).isEqualTo(EMAIL_ALREADY_EXISTS.getMessage());
    }

    @Then("user should see an error message indicating the password is too weak")
    public void userShouldSeeAnErrorMessageIndicatingThePasswordIsTooWeak() {
        String actualError = registrationPage.getPassword_Weak();
        log.info("Actual error message for the too weak password: {}", actualError);
        assertThat(actualError).isEqualTo(PASSWORD_WEAK.getMessage());
    }

    @Then("user should see an error message indicating that passwords are not the same")
    public void userShouldSeeAnErrorMessageIndicatingThatPasswordsAreNotTheSame() {
        String actualError = registrationPage.getPassword_confirmation_error();
        log.info("Actual error message for passwords are not the same: {}", actualError);
        assertThat(actualError).isEqualTo(PASSWORD_CONFIRMATION_ERROR.getMessage());
    }
}
