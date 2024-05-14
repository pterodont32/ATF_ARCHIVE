package stepDefinition.UI;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import pageObjects.CustomerLoginPage;
import pageObjects.GeneralPage;
import utils.DriverManager;
import utils.ErrorMessages;

import static utils.PropertyUtils.getPropertyFromConfigFile;

public class CustomerLoginSteps {

    WebDriver driver = DriverManager.getDriver();
    CustomerLoginPage customerLoginPage = new CustomerLoginPage(driver);
    private static final Logger log = LogManager.getLogger(GeneralPage.class);

    @Given("user is on the Luma registration page")
    public void userIsOnTheLumaRegistrationPage()   {
        driver.get(getPropertyFromConfigFile("urlmagentoregister"));
    }

    @And("user enter valid email {string} and password {string}")
    public void userEnterValidUsernameAndPassword(String email, String password) {
        customerLoginPage.enterEmail(email);
        customerLoginPage.enterPassword(password);
    }

    @And("user click the login button")
    public void user_click_the_login_button() {
        customerLoginPage.clickLogInButton();
    }

    @And("user enter valid email \"([^\"]*)\" and password \"([^\"]*)\"")
    public void userEnterValidEmailUserAndPasswordUser(String email, String password) {
        customerLoginPage.enterEmail(email);
        customerLoginPage.enterPassword(password);
    }

    @Then("user should see a an error message")
    public void userShouldSeeAAnErrorMessage() throws InterruptedException {
        //TODO   change to another waiters
        Thread.sleep(2000);
        Assertions.assertThat(customerLoginPage.getErrorText()).isEqualTo(ErrorMessages.FAIL_LOG_IN.getMessage());
    }

}


