package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testBase.BaseClass;

public class LoginPage extends BaseClass {

    @FindBy(xpath = "//*[@id='navbarSupportedContent']/ul/li[4]/a")
    public WebElement buttonSignIn;

    @FindBy(id = "email")
    public WebElement emailLogin;

    @FindBy(id = "password")
    public WebElement passwordLogin;

    @FindBy(xpath = "//input[@type='submit']")
    public WebElement buttonLoginToolShop;

    @FindBy(xpath = "/html/body/app-root/div/app-login/div/div/div/form/div[6]/div")
    public WebElement errorInvalidEmailPassword;

    @FindBy(xpath = "//*[@id='email-error']/div")
    public WebElement errorEmailIsRequired;

    @FindBy(xpath = "//*[@id='password-error']/div")
    public WebElement errorPasswordIsRequired;

    @FindBy(xpath = "//*[contains(text(), 'Register your account')]")
    public WebElement buttonRegisterYourAccount;

    @FindBy(id = "first_name")
    public WebElement firstNameRegister;

    @FindBy(id = "last_name")
    public WebElement lastNameRegister;

    @FindBy(id = "dob")
    public WebElement dobRegister;

    @FindBy(id = "address")
    public WebElement addressRegister;

    @FindBy(id = "postcode")
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
