package stepDef;

import driverFactory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.*;
import util.TimeStampGenerator;

import java.util.List;

public class StepDef {

    WebDriver driver;
    Home home = new Home();
    SignUp signUp = new SignUp();
    SignIn signIn = new SignIn();
    PostAndComment postAndComment = new PostAndComment();
    ArticlePage articlePage = new ArticlePage();
    Profile profile = new Profile();

    private String currentUsername;
    private String lastCreatedArticleTitle;
    private String lastAboutArticle;
    private String lastCreatedArticleMarkdown;
    private String lastCommentText;

    TimeStampGenerator ugen = new TimeStampGenerator();
    String timestamp = ugen.getTimestamp();

    @Before
    public void setup(Scenario scenario)
    {
        driver = new DriverFactory().initBrowser();
    }

    @Given("^user is on homepage$")
    public void user_is_on_homepage(){
        driver.get("https://react-redux.realworld.io/");
        home.validateHomePageAnonymous(driver);
    }

    @And("^clicks on Sign up hypertext")
    public void clicks_on_sign_up_hypertext() {
        home.clickSignUpHypertext(driver);
    }

    @Then("^Sign Up page is loaded successfully$")
    public void sign_up_page_is_loaded_successfully()
    {
        signUp.validateSignUpPage(driver);
    }

    @When("^user inserts unique username and email, and a valid password$")
    public void user_inserts_unique_username_and_email_and_a_valid_password()
    {

        String username = "user" + timestamp;
        String email = username + "@mailinator.com";

        //Caching current Username
        currentUsername = username;

        signUp.enterUsername(driver, username);
        signUp.enterEmail(driver, email);
        signUp.enterPassword(driver, "Abcd1234");
    }

    @When("^user enters (.+), (.+) and (.+) in Sign Up page$")
    public void user_enters_username_email_and_password_in_sign_up_page(String username, String email, String password)
    {
        System.out.print(" username: " + username);
        username = username.replace("[blank]", "");
        email = email.replace("[blank]", "");
        password = password.replace("[blank]", "");

        //Caching current username
        currentUsername = username;

        signUp.enterUsername(driver, username);
        signUp.enterEmail(driver, email);
        signUp.enterPassword(driver, password);
    }

    @And("^click on Sign in button$")
    public void click_on_sign_in_button()
    {
        signUp.clickSignInBtn(driver);
    }

    @Then("^successfully signed up username hypertext will be displayed$")
    public void successfully_signed_up_username_hypertext_will_be_displayed()
    {
        home.validateLoginSuccessful(driver, currentUsername);
    }

    @Then("^user will be redirected back to home page$")
    public void user_will_be_redirected_back_to_home_page()
    {
        home.validateHomePageLoginUser(driver);
    }

    @Then("^sign up error message prompted \"(.+)\"$")
    public void sign_up_error_message_prompted(String errorMsg) throws InterruptedException {
        Assert.assertEquals(signUp.getSignUpErrorMsgs(driver).contains(errorMsg), true);
    }

    @And("^clicks on Sign in hypertext")
    public void clicks_on_sign_in_hypertext() {
        home.clickSignInHypertext(driver);
    }

    @Then("^Sign in page is loaded successfully$")
    public void sign_in_page_is_loaded_successfully()
    {
        signIn.validateSignInPage(driver);
    }

    @When("^user enters (.+) and (.+) in Sign In page$")
    public void user_enters_email_and_password_in_sign_in_page(String email, String password)
    {
        email = email.replace("[blank]", "");
        password = password.replace("[blank]", "");

        signIn.enterEmail(driver, email);
        signIn.enterPassword(driver, password);
    }

    @Then("^sign in error message prompted \"(.+)\"$")
    public void sign_in_error_message_prompted(String errorMsg) throws InterruptedException {
        Assert.assertEquals(signIn.getSignInErrorMsgs(driver).contains(errorMsg), true);
    }

    @And("^clicks on New Post hypertext$")
    public void clicks_on_new_post_hypertext()
    {
        home.clickNewPostHypertext(driver);
    }

    @Then("^New Post page is loaded successfully$")
    public void new_post_page_is_loaded_successfully()
    {
        postAndComment.validateNewPostPage(driver);
    }

    @When("^user enters valid articleTitle, aboutArticle, articleMarkdown and tags in New Post page$")
    public void user_enters_valid_articleTitle_aboutArticle_articleMarkdown_and_tags_in_new_post_page()
    {
        lastCreatedArticleTitle = "title_" + timestamp;
        lastAboutArticle = "about_article: " + timestamp;
        lastCreatedArticleMarkdown = "article_markdown " + timestamp;
        String tags = "test_" + timestamp;

        postAndComment.enterArticleTitle(driver, lastCreatedArticleTitle);
        postAndComment.enterAboutThisArticle(driver, lastAboutArticle);
        postAndComment.enterWriteYourArticleInMarkDown(driver, lastCreatedArticleMarkdown);
        postAndComment.enterTags(driver, tags);
    }

    @And("^click on Publish Article button$")
    public void click_on_publish_article_button()
    {
        postAndComment.clickPublishArticleBtn(driver);
    }

    @Then("^target Author Article Page created and loaded successful$")
    public void target_article_page_created_and_loaded_successful()
    {
        articlePage.validateArticlePageUI(driver, true);

        Assert.assertEquals(articlePage.getArticleTitle(driver), lastCreatedArticleTitle);
        Assert.assertEquals(articlePage.getAuthorName(driver), currentUsername);
        Assert.assertEquals(articlePage.getArticleMarkdownParagraph(driver), lastCreatedArticleMarkdown);
    }

    @When("^user enters comment$")
    public void user_enters_comment() {
        lastCommentText = "Test comment 1 2 3 " + timestamp;
        articlePage.enterComment(driver, lastCommentText);

    }

    @And("^click on Post Comment button$")
    public void click_on_post_comment_button() throws InterruptedException {
        articlePage.clickPostCommentBtn(driver);
        Thread.sleep(2000);
    }

    @Then("^new comment card entry is created and displaying on Article page$")
    public void new_comment_card_entry_is_created_and_displaying_on_article_page()
    {
        Assert.assertEquals(articlePage.getLastCommentCardText(driver), lastCommentText);
        Assert.assertEquals(articlePage.getLastCommentAuthorHypertext(driver), currentUsername);
    }

    @When("^user navigates to Home page again$")
    public void user_navigates_to_home_page_again()
    {
        home.clickHome(driver);
    }

    @And("^click on Global Feed tab header$")
    public void click_on_global_feed_tab_header()
    {
        home.clickGlobalFeedTabHeader(driver);
    }

    @Then("^the top 10 latest feeds will be displayed on Global Feed listing$")
    public void the_top_10_latest_feeds_will_be_isplayed_on_global_feed_listing()
    {
        List<String> globalFeedTitlesList = home.getPage1GlobalFeedTitles(driver);
        Assert.assertEquals(globalFeedTitlesList.contains(lastCreatedArticleTitle),
                true);

        globalFeedTitlesList.forEach(s -> System.out.println("Global Feed: " + s));

        Assert.assertEquals(home.getPage1GlobalFeedAuthors(driver)
                .contains(currentUsername),
                true);

        Assert.assertEquals(globalFeedTitlesList.size(), 10);
    }

    @And("^click on the top Global Feed article title$")
    public void click_on_the_top_global_feed_article_title()
    {
        home.clickLastGlobalFeedTitle(driver);
    }

    @Then("^the target article page will be opened$")
    public void the_target_article_page_will_be_opened()
    {
        articlePage.validateArticlePageUI(driver, false);
    }

    @When("^user enters (.+), (.+), (.+) and (.+) in Post form$")
    public void user_enters_articleTitle_aboutArticle_articleMarkdown_and_tags_in_new_post_page(
            String articleTitle, String aboutArticle, String articleMarkdown, String tags)
    {
        articleTitle = articleTitle.replace("[blank]", "");
        aboutArticle = aboutArticle.replace("[blank]", "");
        articleMarkdown = articleMarkdown.replace("[blank]", "");
        tags = tags.replace("[blank]", "");

        postAndComment.enterArticleTitle(driver, articleTitle);
        postAndComment.enterAboutThisArticle(driver, aboutArticle);
        postAndComment.enterWriteYourArticleInMarkDown(driver, articleMarkdown);
        postAndComment.enterTags(driver, tags);
    }

    @Then("^validation message \"(.+)\" appears on new post page$")
    public void validation_message_appears_on_new_post_page(String errorMsg)
    {
        Assert.assertEquals(
                postAndComment.getNewPostErrorMsgs(driver)
                        .contains(errorMsg),
                true);
    }

    @And("^user click on own username hypertext on top right$")
    public void user_click_on_own_username_hypertext_on_top_right()
    {
        profile.clickOwnProfileHypertextByUsername(driver, currentUsername);
    }

    @Then("^the last Article title will displayed in My Articles listing on profile page$")
    public void the_last_article_title_will_displayed_in_my_articles_listing_on_profile_page()
    {
        Assert.assertEquals(profile.getLastCreatedArticleTitle(driver)
            , lastCreatedArticleTitle);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }
}
