package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ElementHelper;

import java.time.Duration;


public class AuthorArticlePage {

    private String articleTitleLblPath = ".//div/h1";

    private String authorNameHypertextXPath = ".//a[@class='author']";

    private String editArticleBtnXPath = ".//a[text()=' Edit Article']";

    private String deleteArticleBtnXPath = ".//button[text()=' Delete Article']";

    private String articleMarkdownParagraphXPath = ".//div[@class='row article-content']/div/div/p";

    private String commentTAXPath = ".//textarea[@placeholder='Write a comment...']";

    private String postCommentBtnXPath = ".//button[text()='Post Comment']";

    private String lastCommentCardParagraph = "(.//p[@class='card-text'])[1]";

    private String lastCommentAuthorHypertext = "(.//a[@class='comment-author'])[2]";


    public void enterComment(WebDriver driver, String articleTitle)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(commentTAXPath)))
                .sendKeys(articleTitle);
    }

    public void clickPostCommentBtn(WebDriver driver)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(By.xpath(postCommentBtnXPath)))
                .click();
    }


    public void validateArticlePageUI(WebDriver driver)
    {
        ElementHelper eh = new ElementHelper();

        //Validate presence of Article Title Label
        eh.validatePresenceOfElement(driver, articleTitleLblPath);

        //Validate presence of Author's username hypertext
        eh.validatePresenceOfElement(driver, authorNameHypertextXPath);

        //Validate presence of Edit Article button
        eh.validatePresenceOfElement(driver, editArticleBtnXPath);

        //Validate presence of Delete Article button
        eh.validatePresenceOfElement(driver, deleteArticleBtnXPath);

        //Validate presence of ArticleMarkdownParagraph
        eh.validatePresenceOfElement(driver, articleMarkdownParagraphXPath);

        //Validate presence of Comment textarea
        eh.validatePresenceOfElement(driver, commentTAXPath);

        //Validate presence of Post Comment button
        eh.validatePresenceOfElement(driver, postCommentBtnXPath);
    }

    public String getArticleTitle(WebDriver driver)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(articleTitleLblPath)))
                .getText();
    }

    public String getAuthorName(WebDriver driver)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(authorNameHypertextXPath)))
                .getText();
    }

    public String getArticleMarkdownParagraph(WebDriver driver)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(articleMarkdownParagraphXPath)))
                .getText();
    }

    public String getLastCommentCardText(WebDriver driver)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(lastCommentCardParagraph)))
                .getText();
    }

    public String getLastCommentAuthorHypertext(WebDriver driver)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(lastCommentAuthorHypertext)))
                .getText();
    }
}
