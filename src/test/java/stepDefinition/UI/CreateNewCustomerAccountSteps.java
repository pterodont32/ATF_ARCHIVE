package stepDefinition.UI;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import pageObjects.CreateNewCustomerAccountPage;
import pageObjects.GeneralPage;
import utils.DriverManager;
import utils.Messages;

import java.util.List;
import java.util.Map;

public class CreateNewCustomerAccountSteps extends DriverManager{


    CreateNewCustomerAccountPage createNewCustomerAccountPage = new CreateNewCustomerAccountPage(driver);
    private static final Logger log = LogManager.getLogger(CreateNewCustomerAccountSteps.class);

    @When("user fill in the registration form with a username that already exists")
    public void userFillInTheRegistrationFormWithAUsernameThatAlreadyExists(DataTable dataTable) {
        List<Map<String, String>> shippingInfo = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : shippingInfo) {
            String firstname = row.get("NAME");
            String lastname = row.get("LAST NAME");
            String email_address = row.get("EMAIL");
            String password = row.get("PASSWORD");
            String passwordConfirmation = row.get("CONFIRM PASSWORD");
            log.info("Filling form with: {}, {}, {}, {}", firstname, lastname, email_address, password);
            createNewCustomerAccountPage.fillRegistrationForm(firstname, lastname, email_address, password, passwordConfirmation);
        }
    }

    @And("register button is clicked")
    public void TheRegisterButtonIsClicked() {
        createNewCustomerAccountPage.clickCreateAccountButton();
    }

    @Then("user should see an error message indicating the username is already taken")
    public void userShouldSeeAnErrorMessageIndicatingTheUsernameIsAlreadyTaken() {
        String actualError = createNewCustomerAccountPage.getEmailIsTakenError();
        log.info("Actual error message for username is already taken: {}", actualError);
        Assertions.assertThat(actualError).isEqualTo(Messages.EMAIL_ALREADY_EXISTS.getMessage());
    }

    @Then("user should see an error message indicating the password is too weak")
    public void userShouldSeeAnErrorMessageIndicatingThePasswordIsTooWeak() {
        String actualError = createNewCustomerAccountPage.getPassword_Weak();
        log.info("Actual error message for the too weak password: {}", actualError);
        Assertions.assertThat(actualError).isEqualTo(Messages.PASSWORD_WEAK.getMessage());
    }

    @Then("user should see an error message indicating that passwords are not the same")
    public void userShouldSeeAnErrorMessageIndicatingThatPasswordsAreNotTheSame() {
        String actualError = createNewCustomerAccountPage.getPassword_confirmation_error();
        log.info("Actual error message for passwords are not the same: {}", actualError);
        Assertions.assertThat(actualError).isEqualTo(Messages.PASSWORD_CONFIRMATION_ERROR.getMessage());
    }
}


