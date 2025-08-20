package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.MainPage;
import testBase.TestData;
import utility.CommonMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class End2EndSteps extends CommonMethods {

    public List<Map<String, String>> productsAddedToCart = new ArrayList<>();
    TestData testData = new TestData();
    MainPage mainPage = new MainPage();
//    @Given("user navigates to homepage")
//    public void user_navigates_to_homepage(){
//
//    }

    @When("user clicks on sign in button")
    public void user_clicks_on_sign_in_button(){
        click(loginPage.buttonSignIn);
    }

    @Then("user validates login page is present")
    public void user_validates_login_page_is_present() {
        waitForVisibility(loginPage.loginPageTitle);
        Assert.assertEquals("Login", loginPage.loginPageTitle.getText());
        System.out.println("The text here is =======================================> " + loginPage.loginPageTitle.getText());
    }
    @When("user clicks to register new account button")
    public void user_clicks_to_register_new_account_button() {
        click(loginPage.buttonRegisterYourAccount);
    }
    @Then("user validates customer registration page is present")
    public void user_validates_customer_registration_page_is_present() {
        waitForVisibility(loginPage.registrationPageTitle);
        Assert.assertEquals("Customer registration", loginPage.registrationPageTitle.getText());
        System.out.println("The text here is =======================================> " + loginPage.registrationPageTitle.getText());
    }
    @When("user creates an account with test data and clicks to register button")
    public void user_creates_an_account_with_test_data_and_clicks_to_register_button() {
        sendText(loginPage.firstNameRegister, testData.testUser1FirstName);
        sendText(loginPage.lastNameRegister, testData.testUser1LastName);
        sendText(loginPage.dobRegister, testData.testUser1DateOfBirth);
        sendText(loginPage.streetRegister, testData.testUser1Street);
        sendText(loginPage.postcodeRegister, testData.testUser1PostalCode);
        sendText(loginPage.cityRegister, testData.testUser1City);
        sendText(loginPage.stateRegister, testData.testUser1State);
        selectDropdown(loginPage.countryRegister, testData.testUser1Country);
        sendText(loginPage.phoneRegister, testData.testUser1Phone);
        sendText(loginPage.emailRegister, testData.testUser1Email);
        sendText(loginPage.passwordRegister, testData.testUser1Password);
        click(loginPage.buttonSubmitRegister);
    }

    @Then("user is navigated to login page and user logs in")
    public void user_is_navigated_to_login_page_and_user_logs_in(){
        waitForVisibility(loginPage.loginPageTitle);
        Assert.assertEquals("Login", loginPage.loginPageTitle.getText());
        sendText(loginPage.emailLogin, testData.testUser1Email);
        sendText(loginPage.passwordLogin, testData.testUser1Password);
        click(loginPage.buttonLogin);
        wait(4);
    }

    @Then("user logged in successfully")
    public void user_logged_in_successfully() {
        waitForVisibility(mainPage.mainPageTitle);
        Assert.assertEquals("My account", mainPage.mainPageTitle.getText());
        wait(4);
    }

    @When("user searches for product {string} on the search box on side bar")
    public void user_searches_for_product_on_the_search_box_on_side_bar(String string) {

    }

    @When("user clicks on the {string} product from the list")
    public void user_clicks_on_the_product_from_the_list(String string) {

    }

    @When("user clicks add to cart button on the product page")
    public void user_clicks_add_to_cart_button_on_the_product_page() {

    }
}
