package steps;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utility.CommonMethods;
import utility.ConfigsReader;

public class LoginSteps extends CommonMethods{

    String newUserEmail = "test9902@gmail.com";
    String newUserPassword = "CimBom!@2467";
    String invalidEmail = "invalid_email@gmail.com";
    String invalidPassword = "invalidPassword?123";
    String emptyEmail = "";
    String emptyPassword = "";
    String expectedErrorMessageLoginPage = "Invalid email or password";
    String actualErrorMessageLoginPage;

    @Given("user navigates to sign in page")
    public void user_navigates_to_sign_in_page(){
        click(loginPage.buttonSignIn);
    }

    @When("user clicks to register new account")
    public void user_clicks_to_register_new_account(){
        click(loginPage.buttonRegisterYourAccount);
    }

    @When("user enters valid credentials and data")
    public void user_enters_valid_credentials_and_data(){
        sendText(loginPage.firstNameRegister, "TestFirstname99");
        wait(1);
        sendText(loginPage.lastNameRegister, "TestLastName89");
        wait(1);
        sendText(loginPage.dobRegister, "04121978");
        wait(1);
        sendText(loginPage.addressRegister, "9911 Poinciana Blv");
        wait(1);
        sendText(loginPage.postcodeRegister, "32999");
        wait(1);
        sendText(loginPage.cityRegister, "Chicago");
        wait(1);
        sendText(loginPage.stateRegister, "IL");
        wait(1);
        selectDropdown(loginPage.countryRegister, "United States of America (the)");
        wait(1);
        sendText(loginPage.phoneRegister, "5051234567");
        wait(1);
        sendText(loginPage.emailRegister, newUserEmail);
        wait(1);
        sendText(loginPage.passwordRegister, newUserPassword);
        wait(1);
    }

    @When("click on register button")
    public void click_on_register_button(){
        click(loginPage.buttonSubmitRegister);
        wait(1);
        sendText(loginPage.emailLogin, newUserEmail);
        wait(1);
        sendText(loginPage.passwordLogin, newUserPassword);
        wait(1);
        click(loginPage.buttonLoginToolShop);
    }

    @Then("I validate that user is registered")
    public void I_validate_that_user_is_registered(){
        String expMyAccount = "My account";
        Assert.assertEquals("ASSERTION COMPELETE = User Created Successfully",mainPage.actualMyAccount.getText(), expMyAccount);
        wait(3);

    }

    @When("user enters valid username")
    public void user_enters_valid_username() {
        sendText(loginPage.emailLogin, newUserEmail);
        wait(1);
    }

    @When("user enters invalid password")
    public void user_enters_invalid_password() {
        sendText(loginPage.passwordLogin, invalidPassword);
        wait(1);
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        click(loginPage.buttonLoginToolShop);
        wait(1);
    }

    @Then("I validate that error message appears")
    public void i_validate_that_error_message_appears() {
        actualErrorMessageLoginPage = loginPage.errorInvalidEmailPassword.getText();
        wait(1);
        Assert.assertEquals(expectedErrorMessageLoginPage, actualErrorMessageLoginPage);
        wait(1);
    }

    @When("user enters invalid username")
    public void user_enters_invalid_username() {
        sendText(loginPage.emailLogin, invalidEmail);
        wait(1);
    }

    @When("user enters valid password")
    public void user_enters_valid_password() {
        sendText(loginPage.passwordLogin, newUserPassword);
        wait(1);
    }

    @When("user enters empty username")
    public void user_enters_empty_username() {
        sendText(loginPage.passwordLogin, emptyEmail);
        wait(1);
    }

    @When("user enters empty password")
    public void user_enters_empty_password() {
        sendText(loginPage.passwordLogin, emptyPassword);
        wait(1);
    }

}
