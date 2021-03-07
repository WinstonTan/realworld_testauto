package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ElementHelper;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class SignUp {

    private String usernameTFXPath = ".//*[@placeholder='Username']";

    private String emailTFXPath = ".//*[@placeholder='Email']";

    private String passwordPFXPath = ".//*[@placeholder='Password']";

    private String signInBtnXPath = ".//*[text()='Sign in' and @type='submit']";

    private String signUpErrorMsg = ".//ul[@class='error-messages']/li";

    ElementHelper eh = new ElementHelper();

    public void enterUsername(WebDriver driver, String username)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(usernameTFXPath)))
                .sendKeys(username);
    }

    public void enterEmail(WebDriver driver, String email)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(emailTFXPath)))
                .sendKeys(email);
    }

    public void enterPassword(WebDriver driver, String password)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(passwordPFXPath)))
                .sendKeys(password);
    }

    public void enterUniquePassword(WebDriver driver, String password)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(passwordPFXPath)))
                .sendKeys(password);
    }

    public void clickSignInBtn(WebDriver driver)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(By.xpath(signInBtnXPath)))
                .click();
    }

    public void validateSignUpPage(WebDriver driver)
    {
        ElementHelper eh = new ElementHelper();
        //Validate presence of username textField
        eh.validatePresenceOfElement(driver, usernameTFXPath);

        //Validate presence of email textField
        eh.validatePresenceOfElement(driver, emailTFXPath);

        //Validate presence of password textField
        eh.validatePresenceOfElement(driver, passwordPFXPath);

        //Validate presence of Sign In button
        eh.validatePresenceOfElement(driver, signInBtnXPath);
    }

    public List<String> getSignUpErrorMsgs(WebDriver driver)
    {
        return eh.captureElementTextsList(driver, signUpErrorMsg);
    }
}
