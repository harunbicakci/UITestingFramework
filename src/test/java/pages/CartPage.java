package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testBase.BaseClass;

public class CartPage extends BaseClass {

    @FindBy(xpath = "//li[@class='ng-star-inserted current']/a/div")
    public WebElement cartPageTitle;

    @FindBy(css = "table.table.table-hover")  // Main table
    public WebElement cartTable;

    public CartPage(){
        PageFactory.initElements(driver, this);
    }

}

//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class CartPage {
//    private WebDriver driver;
//    private WebDriverWait wait;
//
//    // Locators
//
//    @FindBy(css = "tbody tr")  // All product rows
//    private List<WebElement> productRows;
//
//    @FindBy(xpath = "//tfoot//td[@data-test='cart-total']")  // Grand total
//    private WebElement grandTotalElement;
//
//    // Constructor
//    public CartPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        PageFactory.initElements(driver, this);
//    }
//
//    // Wait for table to load
//    public void waitForCartTable() {
//        wait.until(ExpectedConditions.visibilityOf(cartTable));
//    }
//
//    // Dynamic method: Get all product data as List<Map<String, String>>
//    public List<Map<String, String>> getAllProductsData() {
//        waitForCartTable();
//        List<Map<String, String>> products = new ArrayList<>();
//
//        for (WebElement row : productRows) {
//            Map<String, String> productData = new HashMap<>();
//
//            // Product Name (span with data-test="product-title")
//            WebElement nameElement = row.findElement(By.cssSelector("span[data-test='product-title']"));
//            productData.put("name", nameElement.getText().trim());
//
//            // Quantity (input with data-test="product-quantity")
//            WebElement qtyElement = row.findElement(By.cssSelector("input[data-test='product-quantity']"));
//            productData.put("quantity", qtyElement.getAttribute("value").trim());  // Use getAttribute for input value
//
//            // Price (span with data-test="product-price")
//            WebElement priceElement = row.findElement(By.cssSelector("span[data-test='product-price']"));
//            productData.put("price", priceElement.getText().trim().replace("$", ""));  // Remove $ for parsing
//
//            // Line Total (span with data-test="line-price")
//            WebElement totalElement = row.findElement(By.cssSelector("span[data-test='line-price']"));
//            productData.put("total", totalElement.getText().trim().replace("$", ""));
//
//            products.add(productData);
//        }
//        return products;
//    }
//
//    // Dynamic method: Get data for a specific product by name
//    public Map<String, String> getProductDataByName(String productName) {
//        List<Map<String, String>> allProducts = getAllProductsData();
//        return allProducts.stream()
//                .filter(p -> p.get("name").equalsIgnoreCase(productName.trim()))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Product not found: " + productName));
//    }
//
//    // Get grand total as double
//    public double getGrandTotal() {
//        wait.until(ExpectedConditions.visibilityOf(grandTotalElement));
//        String totalText = grandTotalElement.getText().trim().replace("$", "");
//        return Double.parseDouble(totalText);
//    }
//
//    // Validate calculated grand total matches displayed (for integrity check)
//    public boolean isGrandTotalCorrect() {
//        double calculatedTotal = getAllProductsData().stream()
//                .mapToDouble(p -> Double.parseDouble(p.get("total")))
//                .sum();
//        return Math.abs(calculatedTotal - getGrandTotal()) < 0.01;  // Allow for floating-point precision
//    }
//}