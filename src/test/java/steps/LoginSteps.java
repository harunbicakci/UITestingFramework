package steps;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utility.CommonMethods;
import utility.ConfigsReader;

public class LoginSteps extends CommonMethods{

    @Given("user navigates to sign in page")
    public void user_navigates_to_sign_in_page(){
        click(loginPage.buttonSignIn);
    }

    @When("user clicks to register new account")
    public void user_clicks_to_register_new_account(){
        click(loginPage.buttonRegisterYourAccount);
    }

    @Given("user enters valid credentials and data")
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
        click(loginPage.countryRegister);
        sendText(loginPage.countryRegister, "United States of America");
        // selectDropdown(loginPage.countryRegister, "United States of America");
        wait(1);
        sendText(loginPage.phoneRegister, "5051234567");
        wait(1);
        sendText(loginPage.emailRegister, "test9911@gmail.com");
        wait(1);
        sendText(loginPage.passwordRegister, "CimBom!@2367");
        wait(1);
    }

    @Given("click on register button")
    public void click_on_register_button(){
        click(loginPage.buttonSubmitRegister);
    }

    @Given("I validate that user is registered")
    public void I_validate_that_user_is_registered(){
        String expMyAccount = "My account";
        Assert.assertEquals("ASSERTION COMPELETE = User Created Successfully",mainPage.actualMyAccount.getText(), expMyAccount);
        wait(3);
    }
}
