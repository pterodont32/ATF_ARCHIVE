package hooks;

import io.cucumber.java.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.DatabaseManager;
import utils.DriverManager;

public class Hooks {
    private static final Logger log = LogManager.getLogger(Hooks.class);

    @Before("@UI")
    public void driverSetUp() {
        DriverManager.getDriver();
    }

    @After("@UI")
    public void tearDown(Scenario scenario) throws InterruptedException {
        try {
            if (scenario.isFailed() && DriverManager.getDriver() != null) {
                log.info("Scenario '{}' failed. Taking screenshot...", scenario.getName());
                final byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                        .getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "screenshot");
            }
        } catch (Exception e) {
            log.error("Failed to capture screenshot for scenario '{}'. Error: {}", scenario.getName(), e.getMessage());
        } finally { // fainaly se executot tot timp dupa try cath nui  important daca e  succes sau nu
            DriverManager.quitDriver();
        }
    }

    @AfterStep("@UI")
    public void afterEachStep(Scenario scenario) {
        if (scenario.isFailed()) {
            log.info("Step failed in scenario '{}'. Taking screenshot...", scenario.getName());
            final byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getId());
        }
    }

    @Before("@DB")
    public void driverDbSetUp() {
        DatabaseManager.getConnection();
    }

    @After("@DB")
    public void driverDbClose() {
        DatabaseManager.closeConnection();
    }
}



