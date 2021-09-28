package stepDef.conduit;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import pageObjects.*;
import util.TimeStampGenerator;
import static util.Consts.WEBDRIVER;
import static util.Consts.SCENARIO;


import java.util.List;

public class Steps {

    Home home = new Home();
    SignUp signUp = new SignUp();
    SignIn signIn = new SignIn();
    PostAndComment postAndComment = new PostAndComment();
    ArticlePage articlePage = new ArticlePage();
    ViewProfile profile = new ViewProfile();
    Settings settings = new Settings();

    private String currentUsername;
    private String lastCreatedArticleTitle;
    private String lastAboutArticle;
    private String lastCreatedArticleMarkdown;
    private String lastCommentText;
    private String lastUpdatedPicURL = "URL of profile picture";
    private String lastUpdatedShortBio = "Short bio about you";
    private String lastUpdatedEmail = "Email";
    private String lastUpdatedPassword = "New Password";

    TimeStampGenerator ugen = new TimeStampGenerator();
    String timestamp = ugen.getTimestamp();

    @Given("^user is on homepage$")
    public void user_is_on_homepage(){
        WEBDRIVER.get("https://react-redux.realworld.io/");
        home.validateHomePageAnonymous(WEBDRIVER);
    }

    @And("^clicks on Sign up hypertext")
    public void clicks_on_sign_up_hypertext() {
        home.clickSignUpHypertext(WEBDRIVER);
    }

    @Then("^Sign Up page is loaded successfully$")
    public void sign_up_page_is_loaded_successfully()
    {
        signUp.validateSignUpPage(WEBDRIVER);
    }

    @When("^user inserts unique username and email, and a valid password$")
    public void user_inserts_unique_username_and_email_and_a_valid_password()
    {
        String username = "user_" + timestamp;
        String email = username + "@mailinator.com";
        String password = "Abcd1234";

        //Caching current Username and email
        currentUsername = username;
        lastUpdatedEmail = email;
        lastUpdatedPassword = password;

        signUp.enterUsername(WEBDRIVER, username);
        signUp.enterEmail(WEBDRIVER, email);
        signUp.enterPassword(WEBDRIVER, password);

        SCENARIO.log("username: " + username + "\t email: " + email + "\t password: " + password );
    }

    @When("^user enters (.+), (.+) and (.+) in Sign Up page$")
    public void user_enters_username_email_and_password_in_sign_up_page(String username, String email, String password)
    {
        System.out.print(" username: " + username);
        username = username.replace("[blank]", "");
        email = email.replace("[blank]", "");
        password = password.replace("[blank]", "");

        //Caching current user info
        currentUsername = username;
        lastUpdatedEmail = email;
        lastUpdatedPassword = password;

        signUp.enterUsername(WEBDRIVER, username);
        signUp.enterEmail(WEBDRIVER, email);
        signUp.enterPassword(WEBDRIVER, password);
    }

    @And("^click on Sign in button$")
    public void click_on_sign_in_button()
    {
        signUp.clickSignInBtn(WEBDRIVER);
    }

    @Then("^successfully signed up username hypertext will be displayed$")
    public void successfully_signed_up_username_hypertext_will_be_displayed()
    {
        home.validateLoginSuccessful(WEBDRIVER, currentUsername);
    }

    @Then("^user will be redirected back to home page$")
    public void user_will_be_redirected_back_to_home_page()
    {
        home.validateHomePageLoginUser(WEBDRIVER);
    }

    @Then("^sign up error message prompted \"(.+)\"$")
    public void sign_up_error_message_prompted(String errorMsg) {

        String errMsgsOnScreen = "";

        for(int i =0; i < signUp.getSignUpErrorMsgs(WEBDRIVER).size(); i++)
        {
            errMsgsOnScreen += "\n" + signUp.getSignUpErrorMsgs(WEBDRIVER).get(i);
        }

        try{
            Assert.assertEquals(signUp.getSignUpErrorMsgs(WEBDRIVER).contains(errorMsg), true);
        }
        catch (AssertionError ae)
        {
            byte[] screenshot = ((TakesScreenshot)WEBDRIVER).getScreenshotAs(OutputType.BYTES);
            SCENARIO.attach(screenshot, "image/png", "Page" + timestamp);

            String assertionError = "Expecting \"" + errorMsg + "\" but found \"" +
                    postAndComment.getNewPostErrorMsgsStr(WEBDRIVER);
            SCENARIO.log(assertionError);
            Assert.fail(assertionError);
        }
    }

    @And("^clicks on Sign in hypertext")
    public void clicks_on_sign_in_hypertext() {
        home.clickSignInHypertext(WEBDRIVER);
    }

    @Then("^Sign in page is loaded successfully$")
    public void sign_in_page_is_loaded_successfully()
    {
        signIn.validateSignInPage(WEBDRIVER);
    }

    @When("^(.+) enters (.+) and (.+) in Sign In page$")
    public void user_enters_email_and_password_in_sign_in_page(String user, String email, String password)
    {
        email = email.replace("[blank]", "");
        password = password.replace("[blank]", "");

        //Caching user info
        currentUsername = user;
        lastUpdatedEmail = email;
        lastUpdatedPassword = password;

        signIn.enterEmail(WEBDRIVER, email);
        signIn.enterPassword(WEBDRIVER, password);

        SCENARIO.log("username: " + user + "\t email: " + email + "\t password: " + password );
    }

    @Then("^sign in error message prompted \"(.+)\"$")
    public void sign_in_error_message_prompted(String errorMsg){
        try {
            Assert.assertEquals(signIn.getSignInErrorMsgs(WEBDRIVER).contains(errorMsg), true);
        }
        catch (AssertionError ae)
        {
            byte[] screenshot = ((TakesScreenshot)WEBDRIVER).getScreenshotAs(OutputType.BYTES);
            SCENARIO.attach(screenshot, "image/png", "Page" + timestamp);

            String assertionError = "Expecting \"" + errorMsg + "\" but found \"" +
                    postAndComment.getNewPostErrorMsgsStr(WEBDRIVER);
            SCENARIO.log(assertionError);
            Assert.fail(assertionError);
        }
    }

    @And("^clicks on New Post hypertext$")
    public void clicks_on_new_post_hypertext()
    {
        home.clickNewPostHypertext(WEBDRIVER);
    }

    @Then("^New Post page is loaded successfully$")
    public void new_post_page_is_loaded_successfully()
    {
        postAndComment.validateNewPostPage(WEBDRIVER);
    }

    @When("^user enters valid articleTitle, aboutArticle, articleMarkdown and tags in New Post page$")
    public void user_enters_valid_articleTitle_aboutArticle_articleMarkdown_and_tags_in_new_post_page()
    {
        lastCreatedArticleTitle = "title_" + timestamp;
        lastAboutArticle = "about_article: " + timestamp;
        lastCreatedArticleMarkdown = "article_markdown " + timestamp;
        String tags = "test_" + timestamp;

        postAndComment.enterArticleTitle(WEBDRIVER, lastCreatedArticleTitle);
        postAndComment.enterAboutThisArticle(WEBDRIVER, lastAboutArticle);
        postAndComment.enterWriteYourArticleInMarkDown(WEBDRIVER, lastCreatedArticleMarkdown);
        postAndComment.enterTags(WEBDRIVER, tags);
    }

    @And("^click on Publish Article button$")
    public void click_on_publish_article_button()
    {
        postAndComment.clickPublishArticleBtn(WEBDRIVER);
    }

    @Then("^target Author Article Page created and loaded successful$")
    public void target_article_page_created_and_loaded_successful()
    {
        articlePage.validateArticlePageUI(WEBDRIVER, true);

        Assert.assertEquals(articlePage.getArticleTitle(WEBDRIVER), lastCreatedArticleTitle);
        Assert.assertEquals(articlePage.getAuthorName(WEBDRIVER), currentUsername);
        Assert.assertEquals(articlePage.getArticleMarkdownParagraph(WEBDRIVER), lastCreatedArticleMarkdown);
    }

    @When("^user enters comment$")
    public void user_enters_comment() {
        lastCommentText = "Test comment 1 2 3 " + timestamp;
        articlePage.enterComment(WEBDRIVER, lastCommentText);

    }

    @And("^click on Post Comment button$")
    public void click_on_post_comment_button() throws InterruptedException {
        articlePage.clickPostCommentBtn(WEBDRIVER);
        Thread.sleep(2000);
    }

    @Then("^new comment card entry is created and displaying on Article page$")
    public void new_comment_card_entry_is_created_and_displaying_on_article_page()
    {
        Assert.assertEquals(articlePage.getLastCommentCardText(WEBDRIVER), lastCommentText);
        Assert.assertEquals(articlePage.getLastCommentAuthorHypertext(WEBDRIVER), currentUsername);
    }

    @When("^user navigates to Home page again$")
    public void user_navigates_to_home_page_again()
    {
        home.clickHome(WEBDRIVER);
    }

    @And("^click on Global Feed tab header$")
    public void click_on_global_feed_tab_header()
    {
        home.clickGlobalFeedTabHeader(WEBDRIVER);
    }

    @Then("^the top 10 latest feeds will be displayed on Global Feed listing$")
    public void the_top_10_latest_feeds_will_be_isplayed_on_global_feed_listing()
    {
        List<String> globalFeedTitlesList = home.getPage1GlobalFeedTitles(WEBDRIVER);
        Assert.assertEquals(globalFeedTitlesList.contains(lastCreatedArticleTitle),
                true);

        globalFeedTitlesList.forEach(s -> System.out.println("Global Feed: " + s));

        Assert.assertEquals(home.getPage1GlobalFeedAuthors(WEBDRIVER)
                .contains(currentUsername),
                true);

        Assert.assertEquals(globalFeedTitlesList.size(), 10);
    }

    @And("^click on the top Global Feed article title$")
    public void click_on_the_top_global_feed_article_title()
    {
        home.clickLastGlobalFeedTitle(WEBDRIVER);
    }

    @Then("^the target article page will be opened$")
    public void the_target_article_page_will_be_opened()
    {
        articlePage.validateArticlePageUI(WEBDRIVER, false);
    }

    @When("^user enters (.+), (.+), (.+) and (.+) in Post form$")
    public void user_enters_articleTitle_aboutArticle_articleMarkdown_and_tags_in_new_post_page(
            String articleTitle, String aboutArticle, String articleMarkdown, String tags)
    {
        articleTitle = articleTitle.replace("[blank]", "");
        aboutArticle = aboutArticle.replace("[blank]", "");
        articleMarkdown = articleMarkdown.replace("[blank]", "");
        tags = tags.replace("[blank]", "");

        postAndComment.enterArticleTitle(WEBDRIVER, articleTitle);
        postAndComment.enterAboutThisArticle(WEBDRIVER, aboutArticle);
        postAndComment.enterWriteYourArticleInMarkDown(WEBDRIVER, articleMarkdown);
        postAndComment.enterTags(WEBDRIVER, tags);
    }

    @Then("^validation message \"(.+)\" appears on new post page$")
    public void validation_message_appears_on_new_post_page(String errorMsg)
    {
        try {
            Assert.assertEquals(
                    postAndComment.getNewPostErrorMsgsList(WEBDRIVER)
                            .contains(errorMsg),
                    true);
        }
        catch (AssertionError ae)
        {
            byte[] screenshot = ((TakesScreenshot)WEBDRIVER).getScreenshotAs(OutputType.BYTES);
            SCENARIO.attach(screenshot, "image/png", "Page" + timestamp);

            String assertionError = "Expecting \"" + errorMsg + "\" but found \"" +
                    postAndComment.getNewPostErrorMsgsStr(WEBDRIVER);
            SCENARIO.log(assertionError);
            Assert.fail(assertionError);
        }
    }

    @And("^user click on own username hypertext on top right$")
    public void user_click_on_own_username_hypertext_on_top_right()
    {
        profile.clickOwnProfileHypertextByUsername(WEBDRIVER, currentUsername);
    }

    @Then("^the last Article title will displayed in My Articles listing on profile page$")
    public void the_last_article_title_will_displayed_in_my_articles_listing_on_profile_page()
    {
        Assert.assertEquals(profile.getLastCreatedArticleTitle(WEBDRIVER)
            , lastCreatedArticleTitle);
    }

    @And("^clicks on Settings hypertext$")
    public void clicks_on_update_settings_hypertext()
    {
        home.clickSettingsHypertext(WEBDRIVER);
    }

    @Then("^Update Settings page is loaded successfully$")
    public void update_settings_page_is_loaded_successfully()
    {
        settings.validateSettingsPage(WEBDRIVER);
    }

    @When("^user enters (.+), (.+), (.+), (.+) and (.+) in Settings page$")
    public void user_enters_picurl_username_shortbio_email_newpassword_in_settings_page(
            String picUrl, String username, String shortBio, String email, String newPassword)
    {
        picUrl = picUrl.replace("[blank]", "");
        username = username.replace("[blank]", "");
        shortBio = shortBio.replace("[blank]", "");
        email = email.replace("[blank]", "");
        newPassword = newPassword.replace("[blank]", "");

        //unique value handler
        username = username.replace("[unique username]", "user_" + timestamp);
        email = email.replace("[unique email]", "user_" + timestamp + "@mailinator.com");

        System.out.println("pic url : " + picUrl);
        System.out.println("username : " + username);
        System.out.println("shortBio : " + shortBio);
        System.out.println("email : " + email);
        System.out.println("newPassword : " + newPassword);

        //Skip or proceed form entry and caching latest user info
        if(!username.equals("[skip]"))
        {
            currentUsername = username;
            settings.enterUsernameTFXPath(WEBDRIVER, username);
        }

        if(!picUrl.equals("[skip]"))
        {
            lastUpdatedPicURL = picUrl;
            settings.enterProfilePicURLTFXPath(WEBDRIVER, picUrl);
        }

        if(!shortBio.equals("[skip]"))
        {
            lastUpdatedShortBio = shortBio;
            settings.enterShortBioTAXPath(WEBDRIVER, shortBio);
        }

        if(!email.equals("[skip]"))
        {
            lastUpdatedEmail = email;
            settings.enterEmail(WEBDRIVER, email);
        }

        if(!newPassword.equals("[skip]"))
        {
            lastUpdatedPassword = newPassword;
            settings.enterNewPasswordPFXPath(WEBDRIVER, newPassword);
        }
    }

    @And("^click on Update Settings button$")
    public void click_on_update_settings_button()
    {
        settings.clickUpdateSettingsBtn(WEBDRIVER);
    }

    @Then("^there will be no error message on Settings page$")
    public void new_profile_info_updated_successfully()
    {
        Assert.assertEquals(settings.getSettingsPageErrorMsgsCount(WEBDRIVER), 0);
    }

    @When("^user refresh page$")
    public void user_refresh_page()
    {
        WEBDRIVER.navigate().refresh();
    }

    @Then("^the latest profile info will be displayed on Settings page$")
    public void the_latest_profile_info_will_be_displayed_on_settings_page()
    {
        Assert.assertEquals(settings.getUsernameText(WEBDRIVER), currentUsername);
        Assert.assertEquals(settings.getProfilePicURLText(WEBDRIVER), lastUpdatedPicURL);
        Assert.assertEquals(settings.getShortBioText(WEBDRIVER), lastUpdatedShortBio);
        Assert.assertEquals(settings.getEmailText(WEBDRIVER), lastUpdatedEmail);
    }

}
