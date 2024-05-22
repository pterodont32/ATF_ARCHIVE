package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;

import java.time.Duration;

public class RegistrationPage extends DriverManager {

    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstname")
    private WebElement firstNameField;

    @FindBy(id = "lastname")
    private WebElement lastNameField;

    @FindBy(id = "email_address")
    private WebElement emailAddressField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "password-confirmation")
    private WebElement passwordConfirmationField;

    @FindBy(xpath = "//*[@id=\"form-validate\"]/div/div[1]/button")
    private WebElement createAnAccountButton;

    @FindBy(xpath = "/html/body/div[2]/main/div[2]/div[2]/div/div/div")
    private WebElement emailIsTakenError;

    @FindBy(xpath = "//*[@id=\"password-strength-meter-label\"]")
    private WebElement password_Weak;

    @FindBy(xpath = "//*[@id=\"password-confirmation-error\"]")
    private WebElement password_confirmation_error;

    public void clickCreateAccountButton() {
        createAnAccountButton.click();
    }
    public void fillRegistrationForm(String firstname, String lastname, String email_address,String password,String passwordConfirmation) {
        firstNameField.sendKeys(firstname);
        lastNameField.sendKeys(lastname);
        emailAddressField.sendKeys(email_address);
        passwordField.sendKeys(password);
        passwordConfirmationField.sendKeys(passwordConfirmation);
    }


    public void waitForEmailIsTakenErrorToBeVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(emailIsTakenError));
    }

    public String getEmailIsTakenError() {
        return emailIsTakenError.getText();
    }

    public String getPassword_Weak() {
        return password_Weak.getText();
    }

    public String getPassword_confirmation_error() {
        return password_confirmation_error.getText();
    }

}
