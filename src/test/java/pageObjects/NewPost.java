package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ElementHelper;

import java.time.Duration;


public class NewPost {

    private String articleTitleTFXPath = ".//*[@placeholder='Article Title']";

    private String aboutThisArticleXPath = ".//*[@placeholder=\"What's this article about?\"]";

    private String articleMarkdownTAXPath = ".//*[@placeholder='Write your article (in markdown)']";

    private String tagsXPath = ".//*[@placeholder='Enter tags']";

    private String publishArticleBtnXPath = ".//button[text()='Publish Article']";


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
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(articleMarkdownTAXPath)))
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
                until(ExpectedConditions.elementToBeClickable(By.xpath(publishArticleBtnXPath)))
                .click();
    }

    public void validateNewPostPage(WebDriver driver)
    {
        ElementHelper eh = new ElementHelper();

        //Validate presence of Article Title textField
        eh.validatePresenceOfElement(driver, articleTitleTFXPath);

        //Validate presence of What's This Article About textField
        eh.validatePresenceOfElement(driver, aboutThisArticleXPath);

        //Validate presence of Write Your Article textArea
        eh.validatePresenceOfElement(driver, articleMarkdownTAXPath);

        //Validate presence of Publish Article button
        eh.validatePresenceOfElement(driver, publishArticleBtnXPath);
    }
}
