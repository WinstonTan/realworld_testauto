package testRunner.web.conduit;

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
        features = {"src/test/resources/features/"},
        glue = {"stepDef/hooks",
                "stepDef/conduit"
                },
        tags = ""
        )

public class Runner extends AbstractTestNGCucumberTests {

}
