Feature: End2EndTableValidation

#  User will login and add 3 products to the cart and validates the products are in the shopping cart list

  Background:
   # Given user navigates to homepage

  @endToEnd
  Scenario: User creates an account and adds products and pays with credit card and validates the success message
    When user clicks on sign in button
    Then user validates login page is present
    When user clicks to register new account button
    Then user validates customer registration page is present
    When user creates an account with test data and clicks to register button
    Then user is navigated to login page and user logs in
    # Login to application
    Then user logged in successfully
    # Adding first product to cart
    When user clicks on "Hand Tools" under categories tab
    When user clicks on "Combination Pliers" on the product list and adds to cart
    Then user validates the toaster message "Product added to shopping cart."
    Then user validates the shopping cart notification has the number "1"
    # Adding second product to cart
    When user clicks on "Hand Tools" under categories tab
    When user clicks on "Bolt Cutters" on the product list and adds to cart
    Then user validates the toaster message "Product added to shopping cart."
    Then user validates the shopping cart notification has the number "2"
    # Adding same second product to cart
    When user clicks on "Hand Tools" under categories tab
    When user clicks on "Pliers" on the product list and adds to cart
    Then user validates the toaster message "Product added to shopping cart."
    Then user validates the shopping cart notification has the number "3"
    # Cart Page Table Validation
    When user clicks add to cart button on the product page
    Then user validates the cart products and totals
      | Item                | Quantity  | Price | Total   |
      | Combination Pliers  | 1         | 14.15 | 14.15   |
      | Bolt Cutters        | 1         | 48.41 | 48.41   |
      | Pliers              | 1         | 12.01 | 12.01   |
      | Grand Total         |           |       | 74.57   |
    When user clicks checkout button
    Then user validates the checkout message and clicks to checkout button
    Then user validates the billing address and clicks to checkout button
    When user navigates to payment page
    Then user enters payment method information and clicks confirm
    Then user validates the "Payment was successful" message
    Then user navigates to home page

