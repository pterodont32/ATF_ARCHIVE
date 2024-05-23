package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GeneralPage  {

    private static final Logger log = LogManager.getLogger(GeneralPage.class);
    private final WebDriver driver;

    public GeneralPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "/html/body/div[2]/header/div[1]/div/ul/li[2]/a")
    private WebElement SignInHeader;
    @FindBy(xpath = "//span[@class='logged-in']")
    private WebElement welcomeText;
    @FindBy(xpath = "/html/body/div[2]/header/div[1]/div/ul/li[2]/div/ul/li[3]/a")
    private WebElement signOut;
    @FindBy(xpath = "/html/body/div[2]/header/div[1]/div/ul/li[2]/span/button")
    private WebElement dropdownHeader;

    public void signOut() {
        dropdownHeader.click();
        signOut.click();
    }

    public void waitForWelcomeTextToBeVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(welcomeText));
        log.info("Error text is visible.");
    }

    public String getWelcomeText() {
        return welcomeText.getText();
    }
}