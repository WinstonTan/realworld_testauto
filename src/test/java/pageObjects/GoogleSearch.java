package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ElementHelper;

import java.time.Duration;

public class GoogleSearch {

    private String searchFieldXPath = ".//input[@name='q']";

    private String searchBtnXPath = ".//input[@value='Google Search']";

    private String resultsText = ".//div[@id='result-stats' and contains(text(), 'results')]";

    ElementHelper eh = new ElementHelper();

    public void insertSearchField(WebDriver driver, String text)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchFieldXPath)))
                .sendKeys(text);
    }

    public void clickSearchButton(WebDriver driver)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(By.xpath(searchBtnXPath)))
                .click();
    }

    public void validateResultsText(WebDriver driver)
    {
        eh.validatePresenceOfElement(driver, resultsText);
    }

}
