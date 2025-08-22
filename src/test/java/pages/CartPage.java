package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testBase.BaseClass;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static utility.CommonMethods.waitForVisibility;

public class CartPage extends BaseClass {

    @FindBy(xpath = "//li[@class='ng-star-inserted current']/a/div")
    public WebElement cartPageTitle;

    @FindBy(css = "table.table.table-hover")
    public WebElement cartTable;

    @FindBy(css = "tbody tr")
    public List<WebElement> productRows;

    @FindBy(xpath = "//tfoot//td[@data-test='cart-total']")
    public WebElement grandTotalElement;

    @FindBy(xpath = "//button[@data-test='proceed-1']")
    public WebElement checkoutButton1;

    @FindBy(xpath = "//button[@data-test='proceed-2']")
    public WebElement checkoutButton2;

    @FindBy(xpath = "//button[@data-test='proceed-3']")
    public WebElement checkoutButton3;

    @FindBy(xpath = "//p[@class='ng-star-inserted']")
    public WebElement checkoutMessage;

    @FindBy(xpath = "//h3[contains(text(), 'Billing Address')]")
    public WebElement billingAddressTitle;

    @FindBy(id = "street")
    public WebElement streetInput;

    @FindBy(id = "city")
    public WebElement cityInput;

    @FindBy(id = "state")
    public WebElement stateInput;

    @FindBy(id = "country")
    public WebElement countryInput;

    @FindBy(id = "postal_code")
    public WebElement postalCodeInput;

    @FindBy(xpath = "//h3[contains(text(), 'Payment')]")
    public WebElement paymentTitle;

    @FindBy(id = "payment-method")
    public WebElement paymentMethodDropdown;

    @FindBy(id = "credit_card_number")
    public WebElement creditCardNumberInput;

    @FindBy(id = "expiration_date")
    public WebElement expirationDateInput;

    @FindBy(id = "cvv")
    public WebElement cvvInput;

    @FindBy(id = "card_holder_name")
    public WebElement cardHolderNameInput;

    @FindBy(xpath = "//button[@data-test='finish']")
    public WebElement confirmButton;

    @FindBy(xpath = "//div[@data-test='payment-success-message']")
    public WebElement paymentSuccessfulMessage;

    public WebElement getValueFromRows(WebElement row, String columnName) {
        try {
            switch (columnName.toLowerCase()) {
                case "item":
                    // Explicitly target the product-title span within the current row
                    return row.findElement(By.xpath(".//span[@data-test='product-title']"));
                case "quantity":
                    // Target the input element for quantity within the current row
                    return row.findElement(By.xpath(".//input[@data-test='product-quantity']"));
                case "price":
                    // Target the product-price span within the current row
                    return row.findElement(By.xpath(".//span[@data-test='product-price']"));
                case "total":
                    // Target the line-price span within the current row
                    return row.findElement(By.xpath(".//span[@data-test='line-price']"));
                default:
                    throw new IllegalArgumentException("Unknown column name: " + columnName);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to find element for column " + columnName + " in row: " + row.getAttribute("outerHTML"), e);
        }
    }

    public CartPage() {
        PageFactory.initElements(driver, this);
    }

    public void waitForCartTable() {
        waitForVisibility(cartTable);
    }

    public List<Map<String, String>> getAllProducts() {
        waitForCartTable();
        List<Map<String, String>> products = new ArrayList<>();
        String[] columns = {"Item", "Quantity", "Price", "Total"};

        for (WebElement row : productRows) {
            Map<String, String> productData = new HashMap<>();
            for (String column : columns) {
                WebElement element = getValueFromRows(row, column);
                String value = column.equals("Quantity") ? element.getAttribute("value").trim() : element.getText().replace("\u00A0", "").trim();
                if (column.equals("Price") || column.equals("Total")) {
                    value = value.replace("$", "").trim();
                }
                productData.put(column.toLowerCase(), value);
                System.out.println("Row: " + row.getAttribute("outerHTML") + ", Column: " + column + ", Value: " + value);
            }
            products.add(productData);
        }
        System.out.println("Extracted Products: " + products);
        return products;
    }

    public Map<String, String> getProductDataByName(String productName) {
        List<Map<String, String>> allProducts = getAllProducts();
        return allProducts.stream()
                .filter(p -> p.get("item").equalsIgnoreCase(productName.trim()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found: " + productName));
    }

    public double getGrandTotal() {
        waitForVisibility(grandTotalElement);
        String totalText = grandTotalElement.getText().trim().replace("$", "");
        return Double.parseDouble(totalText);
    }

    public boolean isGrandTotalCorrect() {
        double calculatedTotal = getAllProducts().stream()
                .mapToDouble(p -> Double.parseDouble(p.get("total")))
                .sum();
        return Math.abs(calculatedTotal - getGrandTotal()) < 0.01;
    }
}