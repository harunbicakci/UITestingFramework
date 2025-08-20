package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testBase.BaseClass;

public class LoginPage extends BaseClass {

    @FindBy(xpath = "//a[@data-test='nav-sign-in']")
    public WebElement buttonSignIn;

    @FindBy(id = "email")
    public WebElement emailLogin;

    @FindBy(id = "password")
    public WebElement passwordLogin;

    @FindBy(xpath = "//input[@type='submit']")
    public WebElement buttonLogin;

    @FindBy(xpath = "/html/body/app-root/div/app-login/div/div/div/h3")
    public WebElement loginPageTitle;

    @FindBy(xpath = "/html/body/app-root/div/app-register/div/div/div/h3")
    public WebElement registrationPageTitle;

    @FindBy(xpath = "/html/body/app-root/div/app-login/div/div/div/form/div[6]/div")
    public WebElement errorInvalidEmailPassword;

    @FindBy(xpath = "//*[@id='email-error']/div")
    public WebElement errorEmailIsRequired;

    @FindBy(xpath = "//*[@id='password-error']/div")
    public WebElement errorPasswordIsRequired;

    @FindBy(xpath = "//a[@data-test='register-link']")
    public WebElement buttonRegisterYourAccount;

    @FindBy(id = "first_name")
    public WebElement firstNameRegister;

    @FindBy(id = "last_name")
    public WebElement lastNameRegister;

    @FindBy(id = "dob")
    public WebElement dobRegister;

    @FindBy(id = "street")
    public WebElement streetRegister;

    @FindBy(id = "postal_code")
    public WebElement postcodeRegister;

    @FindBy(id = "city")
    public WebElement cityRegister;

    @FindBy(id = "state")
    public WebElement stateRegister;

    @FindBy(id = "country")
    public WebElement countryRegister;
    //option[@value='US']
    //*[@id="country"]/option[237] United States
    //*[@id="country"]/option[228] Turkey

    @FindBy(id = "phone")
    public WebElement phoneRegister;

    @FindBy(id = "email")
    public WebElement emailRegister;

    @FindBy(id = "password")
    public WebElement passwordRegister;

    @FindBy(xpath = "//button[@type = 'submit']")
    public WebElement buttonSubmitRegister;


    public LoginPage(){
        PageFactory.initElements(driver, this);
    }

    public void enterNewUserData(){
        // I may add the steps here for clear code view on LoginSteps class
    }

}
