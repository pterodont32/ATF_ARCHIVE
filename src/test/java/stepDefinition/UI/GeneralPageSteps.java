package stepDefinition.UI;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import pageObjects.GeneralPage;
import utils.DriverManager;

import static utils.Messages.WELCOME_TEXT;
import static utils.PropertyUtils.getPropertyFromConfigFile;

public class GeneralPageSteps {

    private static final Logger log = LogManager.getLogger(GeneralPage.class);
    WebDriver driver = DriverManager.getDriver();
    GeneralPage generalPage = new GeneralPage(driver);

    @Given("user is on the Luma page")
    public void user_is_on_the_Luma_page() {
        driver.get(getPropertyFromConfigFile("urlsigninmagento"));
    }

    @Then("user should see a welcome message with my username")
    public void userShouldSeeAWelcomeMessageWithMyUsername() {
        generalPage.waitForWelcomeTextToBeVisible();
        String actualWelcomeText = generalPage.getWelcomeText();
        log.info("Actual welcome message: {}", actualWelcomeText);
        Assertions.assertThat(actualWelcomeText).isEqualTo(WELCOME_TEXT.getMessage());
    }


}