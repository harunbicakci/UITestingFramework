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
    Then user logged in successfully
    When user searches for product "pliers" on the search box on side bar
    When user clicks on the "first" product from the list
    When user clicks add to cart button on the product page
