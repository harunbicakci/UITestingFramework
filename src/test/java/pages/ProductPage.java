package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testBase.BaseClass;

import static testBase.BaseClass.driver;

public class ProductPage extends BaseClass {

    @FindBy(id = "btn-add-to-cart")
    public WebElement addToCartButton;

    @FindBy(id = "btn-add-to-favorites")
    public WebElement addToFavouritesButton;

    @FindBy(className = "toast-message")
    public WebElement toastMessage;

    @FindBy(id = "toast-container")
    public WebElement toasterContainer;

    @FindBy(xpath = "//a[@data-test='nav-cart']")
    public WebElement cartButton;

    @FindBy(id = "lblCartCount")
    public WebElement cartQuantity;

    public WebElement getProductName(String productName){
        switch (productName){
            case "Combination Pliers":
                return driver.findElement(By.xpath("//h5[normalize-space(text())='Combination Pliers']"));
            case "Pliers":
                return driver.findElement(By.xpath("//h5[normalize-space(text())='Pliers']"));
            case "Bolt Cutters":
                return driver.findElement(By.xpath("//h5[normalize-space(text())='Bolt Cutters']"));
            case "Long Nose Pliers":
                return driver.findElement(By.xpath("//h5[normalize-space(text())='Long Nose Pliers']"));
            case "Slip Joint Pliers":
                return driver.findElement(By.xpath("//h5[normalize-space(text())='Slip Joint Pliers']"));
            case "Hammer":
                return driver.findElement(By.xpath("//h5[normalize-space(text())='Hammer']"));
            default:
                throw new IllegalArgumentException("Unknown product ===> " + productName);
        }
    }

    public int getCartQuantity(){
        String quantityText = cartQuantity.getText().trim();
        if(quantityText.isEmpty()){
            return 0;
        }else{
            return Integer.parseInt(quantityText);
        }
    }


    public ProductPage(){
        PageFactory.initElements(driver, this);

    }

}
