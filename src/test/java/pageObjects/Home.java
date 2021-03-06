package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import util.ElementHelper;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class Home {

    private String signUpXPath = ".//*[text()='Sign up']";

    private String signinHypertextXPath = ".//a[text()='Sign in']";

    private String homeHypertextXPath = ".//*[text()='Home']";

    private String homePageTitleXPath = ".//h1[text()='conduit']";

    private String homePageYourFeedLblXPath = ".//li/a[text()='Your Feed']";

    private String homePageGlobalFeedLblXPath = ".//li/a[text()='Global Feed']";

    private String settingsHypertextXPath = ".//li/a[@href='#settings']";

    private String newPostHypertextXPath = ".//li/a[contains(text(),'New Post')]";

    private String globalFeedTabHypertextXPath = "(.//li/a[text()='Global Feed'])[1]";

    private String globalFeedAuthorListXPath = ".//a[@class='author']";

    private String globalFeedTitleListXPath = ".//a[@class='preview-link']/h1";

    private List<String> page1GlobalFeedTitles = new ArrayList<>();

    private List<String> page1GlobalFeedAuthors = new ArrayList<>();
    ElementHelper eh = new ElementHelper();


    public void clickSignUpHypertext(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(By.xpath(signUpXPath)))
                .click();
    }

    public void clickHome(WebDriver driver)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(By.xpath(homeHypertextXPath)))
                .click();
    }

    public void clickSignInHypertext(WebDriver driver)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(By.xpath(signinHypertextXPath)))
                .click();
    }

    public List<String> getPage1GlobalFeedTitles(WebDriver driver)
    {

        return eh.elementTextsList(driver, globalFeedTitleListXPath);
    }

    public List<String> getPage1GlobalFeedAuthors(WebDriver driver)
    {
        return eh.elementTextsList(driver, globalFeedAuthorListXPath);
    }

    public void clickGlobalFeedTabHeader(WebDriver driver)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(By.xpath(globalFeedTabHypertextXPath)))
                .click();
    }

    public void validateHomePageAnonymous(WebDriver driver)
    {
        ElementHelper eh = new ElementHelper();
        //Validate presence of "conduit" home page title
        eh.validatePresenceOfElement(driver, homePageTitleXPath);

        //Validate presence of Global Feed label
        eh.validatePresenceOfElement(driver, homePageGlobalFeedLblXPath);
    }

    public void validateHomePageLoginUser(WebDriver driver)
    {
        ElementHelper eh = new ElementHelper();
        //Validate NO presence of "conduit" home page title
        Assert.assertEquals(
                driver.findElements(By.xpath(homePageTitleXPath)).size()
                , 0);

        //Validate presence of Global Feed label
        eh.validatePresenceOfElement(driver, homePageGlobalFeedLblXPath);
    }

    public void validateLoginSuccessful(WebDriver driver, String username)
    {
        ElementHelper eh = new ElementHelper();
        //Validate presence of user account name hypertext
        eh.validatePresenceOfElement(driver,
                ".//li/a[@class='nav-link' and @href='#@" + username + "']");

        //Validate presence of Settings hypertext
        eh.validatePresenceOfElement(driver, settingsHypertextXPath);

        //Validate presence of Your Feed label
        eh.validatePresenceOfElement(driver, homePageYourFeedLblXPath);
    }

    public void clickNewPostHypertext(WebDriver driver)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(By.xpath(newPostHypertextXPath)))
                .click();
    }

}
