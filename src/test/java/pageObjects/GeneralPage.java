package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GeneralPage {
    WebDriver driver;

    public GeneralPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "/html/body/div[2]/header/div[1]/div/ul/li[2]/a")
    private WebElement SignInHeader;
    @FindBy(xpath = "/html/body/div[2]/header/div[1]/div/ul/li[1]/span")
    private WebElement welcomeText;
    public void clickSignInHeader() {
        SignInHeader.click();
    }
    public String getWelcomeText() {
        return welcomeText.getText();
    }
}