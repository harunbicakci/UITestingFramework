package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testBase.BaseClass;

import java.util.List;

public class MainPage extends BaseClass{

    @FindBy(xpath = "//div[@class='app_logo']")
    public WebElement appLogo;

    @FindBy(xpath = "//div[@class = 'inventory_item_name']")
    public List<WebElement> itemList;

    @FindBy(id = "mainElement")
    public WebElement mainDelete;

    //---------------------------------------------------------------

    @FindBy(xpath = "//h1[contains(text(), 'My account')]")
    public WebElement mainPageTitle;

    @FindBy(xpath = "//a[@routerlink='favorites']")
    public WebElement buttonFavorites;

    @FindBy(xpath = "//a[@routerlink='profile']")
    public WebElement buttonProfile;

    @FindBy(xpath = "//a[@routerlink='invoices']")
    public WebElement buttonInvoices;

    @FindBy(xpath = "//a[@routerlink='messages']")
    public WebElement buttonMessages;

    @FindBy(xpath = "//a[@data-test='nav-categories']")
    public WebElement categories;

//    @FindBy(xpath = "//a[text()='Hand Tools']")
//    public WebElement handToolsCategories;
//
//    @FindBy(xpath = "//a[text()='Power Tools']")
//    public WebElement powerToolsCategories;
//
//    @FindBy(xpath = "//a[text()='Other']")
//    public WebElement OtherCategories;
//
//    @FindBy(xpath = "//a[text()='Special Tools']")
//    public WebElement specialToolCategories;
//
//    @FindBy(xpath = "//a[text()='Rentals']")
//    public WebElement rentalCategories;

    public MainPage() {
        PageFactory.initElements(BaseClass.driver, this);
    }

    public WebElement getCategoryLink(String categoryName){
        switch (categoryName){
            case "Hand Tools":
                return driver.findElement(By.xpath("//a[text()='Hand Tools']"));
            case "Power Tools":
                return driver.findElement(By.xpath("//a[text()='Power Tools']"));
            case "Other":
                return driver.findElement(By.xpath("//a[text()='Other']"));
            case "Special Tools":
                driver.findElement(By.xpath("//a[text()='Special Tools']"));
            case "Rentals":
                driver.findElement(By.xpath("//a[text()='Rentals']"));
            default:
                throw new IllegalArgumentException("Unknown category ===> " + categoryName);
        }
    }

}