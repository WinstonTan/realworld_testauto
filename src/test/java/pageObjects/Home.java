package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import util.ElementHelper;

import java.time.Duration;


public class Home {

    private String signUpXPath = ".//*[text()='Sign up']";

    private String signinHypertext = ".//a[text()='Sign in']";

    private String homeHypertext = ".//*[text()='Home']";

    private String homePageTitle = ".//h1[text()='conduit']";

    private String homePageYourFeedLbl = ".//li/a[text()='Your Feed']";

    private String homePageGlobalFeedLbl = ".//li/a[text()='Global Feed']";

    private String settingsHypertext = ".//li/a[@href='#settings']";

    private String newPostHypertext = ".//li/a[contains(text(),'New Post')]";

    public void clickSignUpHypertext(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(By.xpath(signUpXPath)))
                .click();
    }

    public void clickHome(WebDriver driver)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(By.xpath(homeHypertext)))
                .click();
    }

    public void clickSignInHypertext(WebDriver driver)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(By.xpath(signinHypertext)))
                .click();
    }

    public void validateHomePageAnonymous(WebDriver driver)
    {
        ElementHelper eh = new ElementHelper();
        //Validate presence of "conduit" home page title
        eh.validatePresenceOfElement(driver, homePageTitle);

        //Validate presence of Global Feed label
        eh.validatePresenceOfElement(driver, homePageGlobalFeedLbl);
    }

    public void validateHomePageLoginUser(WebDriver driver)
    {
        ElementHelper eh = new ElementHelper();
        //Validate NO presence of "conduit" home page title
        Assert.assertEquals(
                driver.findElements(By.xpath(homePageTitle)).size()
                , 0);

        //Validate presence of Global Feed label
        eh.validatePresenceOfElement(driver, homePageGlobalFeedLbl);
    }

    public void validateLoginSuccessful(WebDriver driver, String username)
    {
        ElementHelper eh = new ElementHelper();
        //Validate presence of user account name hypertext
        eh.validatePresenceOfElement(driver,
                ".//li/a[@class='nav-link' and @href='#@" + username + "']");

        //Validate presence of Settings hypertext
        eh.validatePresenceOfElement(driver, settingsHypertext);

        //Validate presence of Your Feed label
        eh.validatePresenceOfElement(driver, homePageYourFeedLbl);
    }

    public void clickNewPostHypertext(WebDriver driver)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(By.xpath(newPostHypertext)))
                .click();
    }
}
