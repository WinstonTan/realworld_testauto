package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ElementHelper;

import java.time.Duration;


public class NewPost {

    private String articleTitleTFXPath = ".//*[text()='Article Title']";
    private String aboutThisArticleXPath = ".//*[text()='What's this article about?']";
    private String writeYourArticleTAXPath = ".//*[text()='Write your article (in markdown)']";
    private String tagsXPath = ".//*[text()='Enter tags']";
    private String publishArticleTitleBtnXPath = ".//button[text()='Publish Article']";


    public void enterArticleTitle(WebDriver driver, String articleTitle)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(articleTitleTFXPath)))
                .sendKeys(articleTitle);
    }

    public void enterAboutThisArticle(WebDriver driver, String aboutThisArticle)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(aboutThisArticleXPath)))
                .sendKeys(aboutThisArticle);
    }

    public void enterWriteYourArticleInMarkDown(WebDriver driver, String writeYourArticleMarkdown)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(writeYourArticleTAXPath)))
                .sendKeys(writeYourArticleMarkdown);
    }

    public void enterTags(WebDriver driver, String tags)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(tagsXPath)))
                .sendKeys(tags);
    }

    public void clickPublishArticleBtn(WebDriver driver)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(By.xpath(publishArticleTitleBtnXPath)))
                .click();
    }
}
