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
    private static final Logger log = LogManager.getLogger(GeneralPage.class);

    @When("user fill in the registration form with a username that already exists")
    public void userFillInTheRegistrationFormWithAUsernameThatAlreadyExists(DataTable dataTable) {
        List<Map<String, String>> shippingInfo = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : shippingInfo) {
            String firstname = row.get("NAME");
            String lastname = row.get("LAST NAME");
            String email_address = row.get("EMAIL");
            String password = row.get("PASSWORD");
            String passwordConfirmation = row.get("CONFIRM PASSWORD");
            createNewCustomerAccountPage.fillRegistrationForm(firstname, lastname, email_address, password, passwordConfirmation);
        }
    }

    @And("register button is clicked")
    public void TheRegisterButtonIsClicked() {
        createNewCustomerAccountPage.clickCreateAccountButton();
    }

    @Then("user should see an error message indicating the username is already taken")
    public void userShouldSeeAnErrorMessageIndicatingTheUsernameIsAlreadyTaken() {
        Assertions.assertThat(createNewCustomerAccountPage.getEmailIsTakenError()).isEqualTo(Messages.EMAIL_ALREADY_EXISTS.getMessage());
    }

    @Then("user should see an error message indicating the password is too weak")
    public void userShouldSeeAnErrorMessageIndicatingThePasswordIsTooWeak() {
        Assertions.assertThat(createNewCustomerAccountPage.getPassword_Weak()).isEqualTo(Messages.PASSWORD_WEAK.getMessage());
    }

    @Then("user should see an error message indicating  that password are not the same")
    public void userShouldSeeAnErrorMessageIndicatingThatPasswordAreNotTheSame() {
        Assertions.assertThat(createNewCustomerAccountPage.getPassword_confirmation_error()).isEqualTo(Messages.PASSWORD_CONFIRMATION_ERROR.getMessage());
    }
}

