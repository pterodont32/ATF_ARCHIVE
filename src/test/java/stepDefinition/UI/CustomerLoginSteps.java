package stepDefinition.UI;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import pageObjects.CustomerLoginPage;
import utils.DriverManager;
import utils.Messages;

import static utils.PropertyUtils.getPropertyFromConfigFile;

public class CustomerLoginSteps {

    private static final Logger log = LogManager.getLogger(CustomerLoginSteps.class);

    WebDriver driver = DriverManager.getDriver();
    CustomerLoginPage customerLoginPage = new CustomerLoginPage(driver);

    @Given("user is on the Luma registration page")
    public void userIsOnTheLumaRegistrationPage() {
        driver.get(getPropertyFromConfigFile("urlmagentoregister"));
    }

    @And("user enters email {string} and password {string}")
    public void userEnterValidUsernameAndPassword(String email, String password) {
        customerLoginPage.enterEmail(email);
        customerLoginPage.enterPassword(password);
    }

    @And("user clicks the login button")
    public void user_click_the_login_button() {
        customerLoginPage.clickLogInButton();
    }

    @Then("user should see a an error message")
    public void userShouldSeeAAnErrorMessage() {
        customerLoginPage.waitForErrorTextToBeVisible();
        String actualError = customerLoginPage.getErrorText();
        log.info("Actual error message: {}", actualError);
        Assertions.assertThat(actualError).isEqualTo(Messages.FAIL_LOG_IN.getMessage());
    }

}



