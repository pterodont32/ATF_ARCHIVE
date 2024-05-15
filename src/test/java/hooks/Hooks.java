package hooks;
import com.mongodb.DB;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.DatabaseManager;
import utils.DriverManager;

public class Hooks {

    @Before("@UI")
    public void driverSetUp() {
        DriverManager.getDriver();
    }
//TODO add logging

    @After("@UI")
    public void tearDown(Scenario scenario) throws InterruptedException {
        try {
            if (scenario.isFailed() && DriverManager.getDriver() != null) {
                System.out.println("Scenario failed. Taking screenshot...");
                final byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                        .getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "screenshot");
            }
        } catch (Exception e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        } finally { // fainaly se executot tot tip dupa try cath nui  important daca e  succes sau nu
            System.out.println("Closing browser...");
            DriverManager.quitDriver();
        }
    }

    @AfterStep("@UI")
    public void afterEachStep(Scenario scenario) {
        if (scenario.isFailed()) {
            // Take a screenshot...
            System.out.println("Step failed. Taking screenshot...");
            final byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getId());
        }
    }

    @Before("@DB")
    public void driverdbSetUp() {
        DatabaseManager.getConnection();
    }


    @After("@DB")
    public void driverdbClose() {
        DatabaseManager.closeConnection();
    }


}



