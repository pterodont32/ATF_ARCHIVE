package utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static utils.PropertyUtils.getPropertyFromConfigFile;

public class DriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", getPropertyFromConfigFile("driverPath"));
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }
public static void quitDriver() {
        driver.close();
    driver.quit();
    driver = null;
}
}


