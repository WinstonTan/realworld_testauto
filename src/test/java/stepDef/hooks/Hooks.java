package stepDef.hooks;

import driverFactory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static util.Consts.WEBDRIVER;
import static util.Consts.SCENARIO;

public class Hooks {

    @Before
    public void setup(Scenario scenario) {
        SCENARIO = scenario;
        WEBDRIVER = new DriverFactory().initBrowser();
        WEBDRIVER.manage().window().maximize();
    }

    @AfterStep
    public void afterStep() throws IOException {
        if(SCENARIO.isFailed())
        {
            File path = ((TakesScreenshot)WEBDRIVER).getScreenshotAs(OutputType.FILE);

            byte[] img = FileUtils.readFileToByteArray(path);

            SCENARIO.attach(img, "image/png", "screenshot");
        }
    }

    @After
    public void teardown() throws IOException {
        WEBDRIVER.quit();
    }
}
