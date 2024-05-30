package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static utils.PropertyUtils.getPropertyFromConfigFile;

public class DriverManager {
    public static WebDriver driver;
    private static final Logger log = LogManager.getLogger(DriverManager.class);

    public static WebDriver getDriver() {
        if (driver == null) {
            try {
                String driverPath = getPropertyFromConfigFile("driverPath");
                System.setProperty("webdriver.chrome.driver", driverPath);
                driver = new ChromeDriver();
                log.info("ChromeDriver initialized successfully.");
            } catch (Exception e) {
                log.error("Failed to initialize ChromeDriver. Error: {}", e.getMessage(), e);
                throw new RuntimeException("Failed to initialize ChromeDriver", e);
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
                driver = null;
                log.info("WebDriver closed  successfully.");
            } catch (Exception e) {
                log.error("Failed to close WebDriver. Error: {}", e.getMessage(), e);
                throw new RuntimeException("Failed to close WebDriver", e);
            }
        }
    }
}