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


public class SignIn {

    private String emailTFXPath = ".//*[@placeholder='Email']";

    private String passwordPFXPath = ".//*[@placeholder='Password']";

    private String signInBtnXPath = ".//*[text()='Sign in' and @type='submit']";

    private String signInErrorMsg = ".//ul[@class='error-messages']/li";

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

    public void clickSignInBtn(WebDriver driver)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(By.xpath(signInBtnXPath)))
                .click();
    }

    public void validateSignInPage(WebDriver driver)
    {
        ElementHelper eh = new ElementHelper();

        //Validate presence of email textField
        eh.validatePresenceOfElement(driver, emailTFXPath);

        //Validate presence of password textField
        eh.validatePresenceOfElement(driver, passwordPFXPath);

        //Validate presence of Sign In button
        eh.validatePresenceOfElement(driver, signInBtnXPath);
    }

    public List<String> getSignInErrorMsgs(WebDriver driver)
    {
        ElementHelper eh = new ElementHelper();
        List<String> strOnScreenErrMsgs = eh.captureElementTextsList(driver, signInErrorMsg);

        return strOnScreenErrMsgs;
    }
}
