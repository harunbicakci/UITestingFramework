package steps;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utility.CommonMethods;
import utility.ConfigsReader;

public class LoginSteps extends CommonMethods{

    @When("user enters valid username")
    public void user_enters_valid_username(){
        sendText(loginPage.userName, ConfigsReader.getProperty("username"));
    }

    @When("user enters valid password")
    public void user_enters_valid_password(){
        sendText(loginPage.password, ConfigsReader.getProperty("password"));
    }

    @When("click on login button")
    public void click_on_login_button(){
        click(loginPage.loginBtn);
        wait(2);
    }

    @Then("I validate that user is logged in")
    public void I_validate_that_user_is_logged_in(){
        Assert.assertTrue(mainPage.appLogo.isDisplayed());
    }

    @When("user leaves password empty")
    public void user_leaves_password_empty() {
        sendText(loginPage.password, "");
    }

    @Then("I validate that {string} message is displayed")
    public void i_validate_that_message_is_displayed(String expectedMsg) {
        String actualMsg = loginPage.errorMsg.getText();
        Assert.assertEquals("The welcome message is as expected!", expectedMsg, actualMsg);
    }

    @When("user enters invalid username as {string}")
    public void user_enters_invalid_username_as(String username) {
        sendText(loginPage.userName, username);
    }

    @When("user enters invalid password as {string}")
    public void user_enters_invalid_password_as(String password) {
        sendText(loginPage.password, password);
    }

    // ---------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------

    @Given("user navigates to login screen")
    public void user_navigates_to_login_screen(){

    }

    @When("user enters valid credentials and clicks login button")
    public void user_enters_valid_credentials_and_clicks_login_button(){

    }

    @Then("user logs in to application")
    public void user_logs_in_to_applicstion(){

    }

    @When("user enters invalid combination of (.+) and (.+)")
    public void user_enters_invalid_combination_of_and(String str1, String str){

    }

    @Then("user gets the (.+)")
    public void user_gets_the(String str){

    }

}
