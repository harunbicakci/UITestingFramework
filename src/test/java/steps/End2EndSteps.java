package steps;

import com.zaxxer.sparsebits.SparseBitSet;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.CartPage;
import pages.MainPage;
import pages.ProductPage;
import testBase.TestData;
import utility.CommonMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class End2EndSteps extends CommonMethods {

    public List<Map<String, String>> productsAddedToCart = new ArrayList<>();
    TestData testData = new TestData();
    MainPage mainPage = new MainPage();
    ProductPage productPage = new ProductPage();
    CartPage cartPage = new CartPage();

    @When("user clicks on sign in button")
    public void user_clicks_on_sign_in_button() {
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
    public void user_is_navigated_to_login_page_and_user_logs_in() {
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
        WebElement product = productPage.getProductName(productName);
        System.out.println("Clicking product: " + product.getText());
        click(product);
        waitForClickability(productPage.addToCartButton);
        click(productPage.addToCartButton);
    }

    @Then("user validates the toaster message {string}")
    public void user_validates_the_toaster_message(String expectedToasterMessage) {
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
        Assert.assertEquals("====>>> Assertion Failed, Cart quantity does not match expected value", expectedQuantity, actualQuantity);
    }

    @When("user clicks add to cart button on the product page")
    public void user_clicks_add_to_cart_button_on_the_product_page() {
        click(productPage.cartButton);
        waitForVisibility(cartPage.cartPageTitle);
        Assert.assertEquals("CART", cartPage.cartPageTitle.getText());
        waitForVisibility(cartPage.cartTable);
    }

    @Then("user validates the cart products and totals")
    public void user_validates_the_cart_products_and_totals(DataTable dataTable) {
        List<Map<String, String>> expectedProducts = dataTable.asMaps(String.class, String.class);
        List<Map<String, String>> actualProducts = cartPage.getAllProducts();
        System.out.println("Expected Products: " + expectedProducts);
        System.out.println("Actual Products: " + actualProducts);
        for (Map<String, String> expected : expectedProducts) {
            String productName = expected.get("Item");
            if (!productName.equals("Grand Total")) {
                Map<String, String> actual = cartPage.getProductDataByName(productName);
                System.out.println("Validating Product: " + productName + ", Actual: " + actual);
                Assert.assertEquals("Product name mismatch", expected.get("Item"), actual.get("item"));
                Assert.assertEquals("Quantity mismatch for " + productName, expected.get("Quantity"), actual.get("quantity"));
                Assert.assertEquals("Price mismatch for " + productName, expected.get("Price"), actual.get("price"));
                Assert.assertEquals("Total mismatch for " + productName, expected.get("Total"), actual.get("total"));
            }
        }
        double expectedGrandTotal = Double.parseDouble(expectedProducts.stream()
                .filter(p -> p.get("Item").equals("Grand Total"))
                .findFirst().get().get("Total"));
        Assert.assertTrue("Grand total mismatch", cartPage.isGrandTotalCorrect());
        Assert.assertEquals("Grand total value mismatch", expectedGrandTotal, cartPage.getGrandTotal(), 0.01);
    }

    @When("user clicks checkout button")
    public void user_click_checkout_button(){
        click(cartPage.checkoutButton1);
    }

    @Then("user validates the checkout message and clicks to checkout button")
    public void user_validates_the_checkout_message_and_clicks_to_checkout_button(){
        waitForVisibility(cartPage.checkoutMessage);
        click(cartPage.checkoutButton2);
    }

    @Then("user validates the billing address and clicks to checkout button")
    public void user_validates_the_billing_address_and_clicks_to_checkout_button(){
        waitForVisibility(cartPage.billingAddressTitle);
        Assert.assertEquals("Street name mismatch", testData.testUser1Street, cartPage.streetInput.getAttribute("value"));
        Assert.assertEquals("City name mismatch", testData.testUser1City, cartPage.cityInput.getAttribute("value"));
        Assert.assertEquals("State name mismatch", testData.testUser1State, cartPage.stateInput.getAttribute("value"));
        Assert.assertEquals("Country name mismatch", testData.testUser1CountryCode, cartPage.countryInput.getAttribute("value"));
        Assert.assertEquals("Postal Code mismatch", testData.testUser1PostalCode, cartPage.postalCodeInput.getAttribute("value"));
        click(cartPage.checkoutButton3);
    }

    @When("user navigates to payment page")
    public void user_navigates_to_payment_page(){
        waitForVisibility(cartPage.paymentTitle);
    }

    @Then("user enters payment method information and clicks confirm")
    public void user_enters_payment_method_information_and_clicks_confirm(){
        selectDropdown(cartPage.paymentMethodDropdown, testData.ccPaymentMethod);
        sendText(cartPage.creditCardNumberInput, testData.ccNo);
        sendText(cartPage.expirationDateInput, testData.ccExpDate);
        sendText(cartPage.cvvInput, testData.ccCvv);
        sendText(cartPage.cardHolderNameInput, testData.ccFullName);
        click(cartPage.confirmButton);
    }

    @Then("user validates the {string} message")
    public void user_validates_the_message(String message){
        waitForVisibility(cartPage.paymentSuccessfulMessage);
        Assert.assertEquals(message, cartPage.paymentSuccessfulMessage.getText());
    }

    @Then("user navigates to home page")
    public void user_navigates_to_home_page(){
        click(mainPage.home);
        wait(4);
    }
}