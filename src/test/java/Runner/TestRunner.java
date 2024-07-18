package Runner;

import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/autoapp/automation/Features",
        glue = {"src/test/java/StepDefinition", "src/test/java/Utility" }
        plugin = {"pretty", "html:target/cucumber-html-report", "json:cucumber.json"}

)

public class TestRunner {

}
