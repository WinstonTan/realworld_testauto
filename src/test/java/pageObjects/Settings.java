package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ElementHelper;

import java.time.Duration;
import java.util.List;


public class Settings {

    private String profilePicURLTFXPath = ".//*[@placeholder='URL of profile picture']";

    private String usernameTFXPath = ".//*[@placeholder='Username']";

    private String shotBioTAXPath = ".//*[@placeholder='Short bio about you']";

    private String emailTFXPath = ".//*[@placeholder='Email']";

    private String newPasswordPFXPath = ".//*[@placeholder='New Password']";

    private String updateSettingsBtnXPath = ".//button[text()='Update Settings']";

    private String logoutBtnXPath = ".//button[text()='Or click here to logout.']";

    private String settingsPageErrorMsgXPath = ".//ul[@class='error-messages']/li";

    ElementHelper eh = new ElementHelper();


    public void enterEmail(WebDriver driver, String email)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(emailTFXPath)));
        WebElement element = driver.findElement(By.xpath(emailTFXPath));
        element.clear();
        element.sendKeys(email);
    }

    public void enterProfilePicURLTFXPath(WebDriver driver, String picURL)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(profilePicURLTFXPath)));
        WebElement element = driver.findElement(By.xpath(profilePicURLTFXPath));
        element.clear();
        element.sendKeys(picURL);
    }

    public void enterUsernameTFXPath(WebDriver driver, String username)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(usernameTFXPath)));
        WebElement element = driver.findElement(By.xpath(usernameTFXPath));
        element.clear();
        element.sendKeys(username);
    }

    public void enterShortBioTAXPath(WebDriver driver, String shotBio)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(shotBioTAXPath)));
        WebElement element = driver.findElement(By.xpath(shotBioTAXPath));
        element.clear();
        element.sendKeys(shotBio);
    }

    public String getProfilePicURLText(WebDriver driver)
    {
        return eh.getElementValue(driver, profilePicURLTFXPath);
    }

    public String getUsernameText(WebDriver driver)
    {
        return eh.getElementValue(driver, usernameTFXPath);
    }

    public String getShortBioText(WebDriver driver)
    {
        return eh.getElementValue(driver, shotBioTAXPath);
    }

    public String getEmailText(WebDriver driver)
    {
        return eh.getElementValue(driver, emailTFXPath);
    }

    public void enterNewPasswordPFXPath(WebDriver driver, String password)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(newPasswordPFXPath)))
                .sendKeys(password);
        WebElement element = driver.findElement(By.xpath(newPasswordPFXPath));
        element.clear();
        element.sendKeys(password);
    }

    public void clickUpdateSettingsBtn(WebDriver driver)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(By.xpath(updateSettingsBtnXPath)))
                .click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickLogoutBtnXPath(WebDriver driver)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(By.xpath(logoutBtnXPath)))
                .click();
    }

    public void validateSettingsPage(WebDriver driver)
    {
        ElementHelper eh = new ElementHelper();

        //Validate presence of email textField
        eh.validatePresenceOfElement(driver, profilePicURLTFXPath);

        //Validate presence of email username textField
        eh.validatePresenceOfElement(driver, usernameTFXPath);

        //Validate presence of email username textField
        eh.validatePresenceOfElement(driver, shotBioTAXPath);

        //Validate presence of email textField
        eh.validatePresenceOfElement(driver, emailTFXPath);

        //Validate presence of New Password field
        eh.validatePresenceOfElement(driver, newPasswordPFXPath);

        //Validate presence of Update Settings button
        eh.validatePresenceOfElement(driver, updateSettingsBtnXPath);

        //Validate presence of Logout button
        eh.validatePresenceOfElement(driver, logoutBtnXPath);
    }

    public List<String> getSettingsPageErrorMsg(WebDriver driver)
    {
        ElementHelper eh = new ElementHelper();
        List<String> strOnScreenErrMsgs = eh.captureElementTextsList(driver, settingsPageErrorMsgXPath);

        return strOnScreenErrMsgs;
    }

    public int getSettingsPageErrorMsgsCount(WebDriver driver)
    {
        if(driver.findElements(By.xpath(settingsPageErrorMsgXPath)).size() > 0)
        {
            System.out.println(driver.findElements(By.xpath(settingsPageErrorMsgXPath)).get(0).getText());
        }

        return driver.findElements(By.xpath(settingsPageErrorMsgXPath)).size();
    }
}
