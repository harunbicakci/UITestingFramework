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

    public MainPage() {
        PageFactory.initElements(BaseClass.driver, this);
    }

}