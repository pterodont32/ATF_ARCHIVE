package runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "C:\\Users\\Vlungu\\IdeaProjects\\ATF\\ATF_ARCHIVE\\src\\test\\resources\\feature",
        glue = {"hooks", "stepDefinition"},
        stepNotifications = true,
        tags = "@UI",
        plugin = {
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class TestRunner {
}