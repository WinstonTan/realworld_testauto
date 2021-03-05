package stepDef;

import driverFactory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.Home;
import pageObjects.SignIn;
import pageObjects.SignUp;
import util.UniqueKeyGenerator;

public class StepDef {

    WebDriver driver;
    Home home = new Home();
    SignUp signUp = new SignUp();
    String currentUsername;
    SignIn signIn = new SignIn();

    UniqueKeyGenerator ugen = new UniqueKeyGenerator();

    @Given("^user is on homepage$")
    public void user_is_on_homepage(){
        driver = new DriverFactory().initBrowser();
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

    @When("^user enters unique username and email, and a valid password$")
    public void user_enters_unique_username_and_email_and_a_valid_password()
    {
        String uniqueKey = ugen.getUniqueKey();
        String username = "user" + uniqueKey;
        String email = username + "@mailinator.com";

        //Caching current Username
        currentUsername = username;

        signUp.enterUsername(driver, username);
        signUp.enterEmail(driver, email);
        signUp.enterPassword(driver, "Abcd1234");
    }

    @When("^user enters (.+) , (.+) and (.+)$")
    public void user_enters_username_email_and_password(String username, String email, String password)
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

    @When("^user enters (.+) and (.+)$")
    public void user_enters_email_and_password(String email, String password)
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

    @After
    public void tearDown()
    {
        driver.quit();
    }
}
