package stepDefinition.UI;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import pageObjects.GeneralPage;
import utils.DriverManager;
import utils.Messages;

import static utils.PropertyUtils.getPropertyFromConfigFile;

public class GeneralPageSteps extends DriverManager{

//    WebDriver driver = DriverManager.getDriver();
    private static final Logger log = LogManager.getLogger(GeneralPage.class);
    GeneralPage generalPage = new GeneralPage(driver);

    //TODO change directly to page that is testing
    @Given("user is on the Luma  page")
    public void user_is_on_the_Luma_page() {
        driver.get(getPropertyFromConfigFile("urlsigninmagento"));
    }


    @Then("user should see a welcome message with my username")
    public void userShouldSeeAWelcomeMessageWithMyUsername() throws InterruptedException {

        generalPage.waitForWelcomeTextToBeVisible();
        Assertions.assertThat(generalPage.getWelcomeText()).isEqualTo(Messages.WELCOME_TEXT.getMessage());
    }
}


