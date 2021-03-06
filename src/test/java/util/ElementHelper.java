package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ElementHelper {

    public void validatePresenceOfElement(WebDriver driver, String xpath)
    {
        try
        {
            new WebDriverWait(driver, Duration.ofSeconds(10)).
                    until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        }
        catch (Exception e)
        {
            System.out.println("XPath not found! : " + xpath);
            System.out.println(e);
        }
    }

    public List<String> elementTextsList(WebDriver driver, String xpath)
    {
        List<String> elementTextsList = new ArrayList<>();

        new WebDriverWait(driver, Duration.ofSeconds(5)).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        List <WebElement> titlesOnPage = driver.findElements(By.xpath(xpath));

        titlesOnPage.forEach(t -> elementTextsList.add(t.getText()));

        return elementTextsList;
    }
}
