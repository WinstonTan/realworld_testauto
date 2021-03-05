package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
}
