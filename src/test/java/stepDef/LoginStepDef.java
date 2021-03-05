package stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pageObjects.Home;
import pageObjects.SignIn;

public class LoginStepDef {

    WebDriver driver;
    Home home = new Home();
    SignIn signIn = new SignIn();
    String currentUsername;

//    @Given("^user is on homepage$")
//    public void user_is_on_homepage(){
//        driver = new DriverFactory().initBrowser();
//        driver.get("https://react-redux.realworld.io/");
//        home.validateHomePageAnonymous(driver);
//    }



//    @And("^click on Sign in button$")
//    public void click_on_sign_in_button()
//    {
//        signIn.clickSignInBtn(driver);
//    }

//    @Then("^successfully signed up username hypertext will be displayed$")
//    public void successfully_signed_up_username_hypertext_will_be_displayed()
//    {
//        home.validateLoginSuccessful(driver, currentUsername);
//    }

//    @Then("^user will be redirected back to home page$")
//    public void user_will_be_redirected_back_to_home_page()
//    {
//        home.validateHomePageLoginUser(driver);
//    }

//    @Then("^error message prompted \"(.+)\"$")
//    public void error_message_prompted(String errorMsg) throws InterruptedException {
//        Assert.assertEquals(signIn.getSignInErrorMsgs(driver).contains(errorMsg), true);
//    }
//
//    @After
//    public void tearDown()
//    {
//        driver.quit();
//    }
}
