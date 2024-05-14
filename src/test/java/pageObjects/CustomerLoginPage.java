package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerLoginPage {
    WebDriver driver;

    public CustomerLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "login[username]")
    private WebElement emailField;

    @FindBy(name = "login[password]")
    private WebElement passwordField;

    @FindBy(id = "send2")
    private WebElement logInButton;

    @FindBy(xpath = "//*[@id=\"maincontent\"]/div[2]/div[2]/div/div/div")
    private WebElement errorText;

    @FindBy(xpath = "//*[@id=\"maincontent\"]/div[3]/div/div[3]/div[2]/div[2]/div/div/a")
    private WebElement createAccountLink;

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLogInButton() {
        logInButton.click();
    }

    public void clickCreateAccountLink() {
        createAccountLink.click();
    }

    public String getErrorText() {
        return errorText.getText();
    }
}
