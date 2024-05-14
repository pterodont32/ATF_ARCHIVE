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

public class GeneralPageSteps {

    WebDriver driver = DriverManager.getDriver();
    private static final Logger log = LogManager.getLogger(GeneralPage.class);
    GeneralPage generalPage = new GeneralPage(driver);

    @Given("user is on the Luma  page")
    public void user_is_on_the_Luma_page() {
        driver.get(getPropertyFromConfigFile("urlmagento"));
    }

    @When("user click on the Sign In button on the header")
    public void userClickOnTheSignInButton() {
        generalPage.clickSignInHeader();
    }

    @Then("user should see a welcome message with my username")
    public void userShouldSeeAWelcomeMessageWithMyUsername() throws InterruptedException {
//TODO   change to another waiters
        Thread.sleep(6000);
        Assertions.assertThat(generalPage.getWelcomeText()).isEqualTo(ErrorMessages.WELCOME_TEXT.getMessage());
    }
}

