package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ElementHelper;

import java.time.Duration;
import java.util.List;


public class Profile {

    private String lastCreatedArticleTitle = ".//a[@class='preview-link']/h1";

    public void clickOwnProfileHypertextByUsername(WebDriver driver, String username)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(
                        By.xpath(".//a[text()=\"" + username + "\"]")))
                .click();
    }

    public String getLastCreatedArticleTitle(WebDriver driver)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath(lastCreatedArticleTitle)))
                .getText();
    }
}
