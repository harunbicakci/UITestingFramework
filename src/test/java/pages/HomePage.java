package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testBase.BaseClass;
import utility.CommonMethods;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomePage extends CommonMethods {

    @FindBy(xpath = "//div[@class='container']")
    public WebElement allProductsContainer;

    @FindBy(xpath = "//h5[@data-test='product-name']")
    public List<WebElement> productNames;

    @FindBy(xpath = "//span[@data-test='product-price']")
    public List<WebElement> productPrices;

    @FindBy(xpath = "//select[@data-test='sort']")
    public WebElement sortDropDown;

    public WebElement selectSortingfromDropdown(String sortingType){
        try{
            switch (sortingType.toLowerCase()) {
                case "name,asc":
                    return driver.findElement(By.xpath("//option[@value='name,asc']"));
                case "name,desc":
                    return driver.findElement(By.xpath("//option[text()='Name (Z to A)']"));
                case "price,desc":
                    return driver.findElement(By.xpath("//option[text()='Price (high to low)']"));
                case "price,asc":
                    return driver.findElement(By.xpath("//option[text()='Price (low to high)'"));
                default:
                    throw new IllegalArgumentException("Wrong sorting type ===> " + sortingType);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to select sorting type" + sortingType, e);
        }
    }

    @FindBy(xpath = "//span[@aria-label='ngx-slider']")
    public WebElement minPriceSlider;

    @FindBy(xpath = "//span[@aria-label='ngx-slider-max']")
    public WebElement maxPriceSlider;

    @FindBy(xpath = "//input[@data-test='search-query']")
    public WebElement searchBoxInput;

    @FindBy(xpath = "//button[@data-test='search-reset']")
    public WebElement resetButtonX;

    @FindBy(xpath = "//button[@data-test='search-submit']")
    public WebElement searchButton;

    @FindBy(xpath = "//a[@aria-label='Next']")
    public WebElement nextPageButton;

    @FindBy(xpath = "//a[contains(@aria-label, 'Page')]")
    public List<WebElement> pageNumbersOfProducts;


    // Constructor
    public HomePage(){
        PageFactory.initElements(driver, this);
    }

    public List<String> getAllProductNamesAcrossPages() {
        List<String> allNames = new ArrayList<>();
        boolean hasNext = true;
        while (hasNext) {
            // Re-locate product names to avoid stale references
            List<WebElement> currentProductNames = driver.findElements(By.xpath("//a[contains(@aria-label, 'Page')]"));
            for (WebElement name : currentProductNames) {
                allNames.add(name.getText().trim());
            }

            // Re-locate next page button
            List<WebElement> nextButtons = driver.findElements(By.xpath("//a[@data-test='pagination-next']"));
            if (nextButtons.size() > 0) {
                WebElement nextButton = nextButtons.get(0);
                if (nextButton.isDisplayed() && nextButton.isEnabled()) {
                    nextButton.click();
                    // Wait for the next page to load (adjust condition as per your application)
                    new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                            ExpectedConditions.stalenessOf(currentProductNames.get(0)) // Wait for old products to disappear
                    );
                } else {
                    hasNext = false;
                }
            } else {
                hasNext = false;
            }
        }
        return allNames;
    }

    public List<Double> getAllProductPricesAcrossPages() {
        List<Double> allPrices = new ArrayList<>();
        while (true) {
            for (WebElement price : productPrices){
                allPrices.add(Double.parseDouble(price.getText().replace("$", "").trim()));
            }
            if (nextPageButton.isEnabled()){
                click(nextPageButton);
            } else {
                break;
            }
        }
        return allPrices;
    }

    public void setPriceRange(double min, double max){
        sendText(minPriceSlider, String.valueOf(min));
        sendText(maxPriceSlider, String.valueOf(max));
        click(searchButton);
    }

    public boolean areProductsWithinPriceRange(double min, double max){
        List<Double> prices = getAllProductPricesAcrossPages();
        for (double price : prices){
            if (price < min || price > max){
                return false;
            }
        }
        return true;
    }

    public boolean areProductsSortedByNameAsc(){
        List<String> names = getAllProductNamesAcrossPages();
        List<String> sortedNames = new ArrayList<>(names);
        sortedNames.sort(String::compareToIgnoreCase);
        return names.equals(sortedNames);
    }

    public boolean areProductSortedByNameDesc(){
        List<String> names = getAllProductNamesAcrossPages();
        List<String> sortedNames = new ArrayList<>(names);
        sortedNames.sort(String::compareToIgnoreCase);
        Collections.reverse(sortedNames);
        return names.equals(sortedNames);
    }

    public boolean areProductsSortedByPriceLowToHigh(){
        List<Double> prices = getAllProductPricesAcrossPages();
        List<Double> sortedPrices = new ArrayList<>(prices);
        sortedPrices.sort(Double::compareTo);
        return prices.equals(sortedPrices);
    }

    public boolean areProductsSortedByPriceHighToLow(){
        List<Double> prices = getAllProductPricesAcrossPages();
        List<Double> sortedPrices = new ArrayList<>(prices);
        sortedPrices.sort(Collections.reverseOrder());
        return prices.equals(sortedPrices);
    }

    public boolean noProductsDisplayed(){
        return productNames.isEmpty();
    }

}
