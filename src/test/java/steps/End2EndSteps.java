package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.CartPage;
import pages.MainPage;
import pages.ProductPage;
import testBase.TestData;
import utility.CommonMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class End2EndSteps extends CommonMethods {

    public List<Map<String, String>> productsAddedToCart = new ArrayList<>();
    TestData testData = new TestData();
    MainPage mainPage = new MainPage();
    ProductPage productPage = new ProductPage();
    CartPage cartPage = new CartPage();

    @When("user clicks on sign in button")
    public void user_clicks_on_sign_in_button(){
        click(loginPage.buttonSignIn);
    }

    @Then("user validates login page is present")
    public void user_validates_login_page_is_present() {
        waitForVisibility(loginPage.loginPageTitle);
        Assert.assertEquals("Login", loginPage.loginPageTitle.getText());
        System.out.println("The text here is =======================================> " + loginPage.loginPageTitle.getText());
    }

    @When("user clicks to register new account button")
    public void user_clicks_to_register_new_account_button() {
        click(loginPage.buttonRegisterYourAccount);
    }

    @Then("user validates customer registration page is present")
    public void user_validates_customer_registration_page_is_present() {
        waitForVisibility(loginPage.registrationPageTitle);
        Assert.assertEquals("Customer registration", loginPage.registrationPageTitle.getText());
        System.out.println("The text here is =======================================> " + loginPage.registrationPageTitle.getText());
    }

    @When("user creates an account with test data and clicks to register button")
    public void user_creates_an_account_with_test_data_and_clicks_to_register_button() {
        sendText(loginPage.firstNameRegister, testData.testUser1FirstName);
        sendText(loginPage.lastNameRegister, testData.testUser1LastName);
        sendText(loginPage.dobRegister, testData.testUser1DateOfBirth);
        sendText(loginPage.streetRegister, testData.testUser1Street);
        sendText(loginPage.postcodeRegister, testData.testUser1PostalCode);
        sendText(loginPage.cityRegister, testData.testUser1City);
        sendText(loginPage.stateRegister, testData.testUser1State);
        selectDropdown(loginPage.countryRegister, testData.testUser1Country);
        sendText(loginPage.phoneRegister, testData.testUser1Phone);
        sendText(loginPage.emailRegister, testData.testUser1Email);
        sendText(loginPage.passwordRegister, testData.testUser1Password);
        click(loginPage.buttonSubmitRegister);
    }

    @Then("user is navigated to login page and user logs in")
    public void user_is_navigated_to_login_page_and_user_logs_in(){
        waitForVisibility(loginPage.loginPageTitle);
        Assert.assertEquals("Login", loginPage.loginPageTitle.getText());
        sendText(loginPage.emailLogin, testData.testUser1Email);
        sendText(loginPage.passwordLogin, testData.testUser1Password);
        click(loginPage.buttonLogin);
    }

    @Then("user logged in successfully")
    public void user_logged_in_successfully() {
        waitForVisibility(mainPage.mainPageTitle);
        Assert.assertEquals("My account", mainPage.mainPageTitle.getText());
    }

    @When("user clicks on {string} under categories tab")
    public void user_clicks_on__under_categories_tab(String categoryName) {
        click(mainPage.categories);
        click((mainPage.getCategoryLink(categoryName)));
    }

    @When("user clicks on {string} on the product list and adds to cart")
    public void user_clicks_on_on_the_product_list_and_adds_to_cart(String productName) {
        click(productPage.getProductName(productName));
        waitForClickability(productPage.addToCartButton);
        click(productPage.addToCartButton);
    }

    @Then("user validates the toaster message {string}")
    public void user_validates_the_toaster_message(String expectedToasterMessage){
        waitForVisibility(productPage.toasterContainer);
        Assert.assertEquals(expectedToasterMessage, productPage.toastMessage.getText());
        getWaitObject().until(ExpectedConditions.invisibilityOf(productPage.toastMessage));
    }

    @Then("user validates the shopping cart notification has the number {string}")
    public void user_validates_the_shopping_cart_notification_has_the_number(String expectedCartQuantity) {
        int expectedQuantity;
        expectedQuantity = Integer.parseInt(expectedCartQuantity.trim());

        getWaitObject().until(ExpectedConditions.visibilityOf(productPage.cartQuantity));
        getWaitObject().until(d -> productPage.getCartQuantity() == expectedQuantity);

        int actualQuantity = productPage.getCartQuantity();
        Assert.assertEquals("====>>> Assertion Failed, Cart quantity does not match expected value",expectedQuantity, actualQuantity);
    }

    @When("user clicks add to cart button on the product page")
    public void user_clicks_add_to_cart_button_on_the_product_page() {
        click(productPage.cartButton);
        waitForVisibility(cartPage.cartPageTitle);
        Assert.assertEquals("CART",cartPage.cartPageTitle.getText());
        waitForVisibility(cartPage.cartTable);
        wait(4);
    }

    @Then("user validates the cart products and totals")
    public void user_validates_the_cart_products_and_totals(DataTable expectedProductsTable) {

//                // Convert DataTable to List<Map<String, String>> (columns: Name, Quantity, Price, Total)
//                List<Map<String, String>> expectedProducts = expectedProductsTable.asMaps(String.class, String.class);
//
//                // Get actual products from page
//                cartPage.waitForCartTable();
//                List<Map<String, String>> actualProducts = cartPage.getAllProductsData();
//
//                // Validate number of products
//                Assert.assertEquals("Number of products mismatch", expectedProducts.size(), actualProducts.size());
//
//                // Validate each product dynamically
//                for (Map<String, String> expected : expectedProducts) {
//                    String productName = expected.get("Name").trim();
//                    Map<String, String> actual = cartPage.getProductDataByName(productName);
//
//                    // Validate name
//                    Assert.assertEquals("Product name mismatch for " + productName, productName, actual.get("name"));
//
//                    // Validate quantity
//                    Assert.assertEquals("Quantity mismatch for " + productName, expected.get("Quantity").trim(), actual.get("quantity"));
//
//                    // Validate price (as double for precision)
//                    double expectedPrice = Double.parseDouble(expected.get("Price").trim());
//                    double actualPrice = Double.parseDouble(actual.get("price"));
//                    Assert.assertEquals("Price mismatch for " + productName, expectedPrice, actualPrice, 0.01);
//
//                    // Validate line total
//                    double expectedTotal = Double.parseDouble(expected.get("Total").trim());
//                    double actualTotal = Double.parseDouble(actual.get("total"));
//                    Assert.assertEquals("Line total mismatch for " + productName, expectedTotal, actualTotal, 0.01);
//                }
//
//                // Validate grand total
//                double expectedGrandTotal = expectedProducts.stream()
//                        .mapToDouble(p -> Double.parseDouble(p.get("Total").trim()))
//                        .sum();
//                double actualGrandTotal = cartPage.getGrandTotal();
//                Assert.assertEquals("Grand total mismatch", expectedGrandTotal, actualGrandTotal, 0.01);
//
//                // Optional: Validate calculated total matches displayed
//                Assert.assertTrue("Grand total integrity check failed", cartPage.isGrandTotalCorrect());
    }

}
