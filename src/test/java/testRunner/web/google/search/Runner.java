package testRunner.web.google.search;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json"
                },
        features = {"src/test/resources/features/GoogleSearch.feature"},
        glue = {"stepDef/google/search"},
        tags = ""
        )

public class Runner extends AbstractTestNGCucumberTests {

}
