package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testBase.BaseClass;

import java.util.List;

public class MainPage {

    @FindBy(xpath = "//div[@class='app_logo']")
    public WebElement appLogo;

    @FindBy(xpath = "//div[@class = 'inventory_item_name']")
    public List<WebElement> itemList;

    @FindBy(id = "mainElement")
    public WebElement mainDelete;

    //---------------------------------------------------------------

    @FindBy(xpath = "//h1[contains(text(), 'My account')]")
    public WebElement actualMyAccount;

    @FindBy(xpath = "//a[@routerlink='favorites']")
    public WebElement buttonFavorites;

    @FindBy(xpath = "//a[@routerlink='profile']")
    public WebElement buttonProfile;

    @FindBy(xpath = "//a[@routerlink='invoices']")
    public WebElement buttonInvoices;

    @FindBy(xpath = "//a[@routerlink='messages']")
    public WebElement buttonMessages;


    public MainPage() {
        PageFactory.initElements(BaseClass.driver, this);
    }

}