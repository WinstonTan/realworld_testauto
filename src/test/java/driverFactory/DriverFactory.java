package driverFactory;

import io.cucumber.java.hu.De;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.Reporter;

public class DriverFactory {

    public WebDriver initBrowser()
    {
        String browserType = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browserType");
        WebDriver driver;

        switch (browserType.toUpperCase()) {
            case "MACOS_CHROME": default:
                System.setProperty("webdriver.chrome.driver", "src/test/resources/webdrivers/chromedriver");
                driver = new ChromeDriver();
                break;
            case "MACOS_CHROME_HEADLESS":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/webdrivers/chromedriver");
                ChromeOptions options = new ChromeOptions();
                driver = new ChromeDriver(options);
                break;
        }
        return driver;
    }

}
