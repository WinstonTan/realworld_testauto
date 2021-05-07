package stepDef.google.search;

import driverFactory.DriverFactory;
import io.cucumber.java.After;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import pageObjects.GoogleSearch;
import util.ExcelReader;
import util.TimeStampGenerator;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Steps {

    GoogleSearch googleSearch = new GoogleSearch();
    WebDriver driver;
    TimeStampGenerator ugen = new TimeStampGenerator();
    String timestamp = ugen.getTimestamp();

    Scenario scenario;

    @Before
    public void setup(Scenario scenario)
    {
        this.scenario = scenario;
        driver = new DriverFactory().initBrowser();
    }

    @Given("user is on google home page")
    public void user_is_on_google_home_page() {
        driver.get("https://www.google.com");
    }
    @When("user fills the search field from given sheetname {string} and rownumber {int}")
    public void user_fills_the_search_field_from_given_sheetname_and_rownumber(String sheetName, int rowNumber) throws IOException, InvalidFormatException {
        ExcelReader excelReader = new ExcelReader();

        List<Map<String, String>> testData = excelReader.getData(System.getProperty("user.dir") + "/src/test/resources/data/Sample.xlsx", sheetName);

        String fruit = testData.get(rowNumber - 1).get("fruit");
        String color = testData.get(rowNumber - 1).get("color");
        System.out.println(sheetName + ", " + rowNumber);
        System.out.println(fruit + ", " + color);

        googleSearch.insertSearchField(driver, fruit + ", " + color);
        scenario.log("fruit: " + fruit + "\tcolor: " + color);

    }
    @And("user clicks on search button")
    public void user_clicks_on_search_button() {
        googleSearch.clickSearchButton(driver);

    }

    @Then("it shows search result")
    public void it_shows_search_result() {
        googleSearch.validateResultsText(driver);
    }

    @After
    public void tearDown()
    {
        byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        if(scenario.isFailed())
        {
            scenario.attach(screenshot, "image/png", "Page" + timestamp);
        }
        driver.quit();
    }

}
