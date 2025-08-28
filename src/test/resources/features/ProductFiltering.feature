Feature: Product Filtering and Sorting on Home Page

  As a user
  I want to filter and sort products on the home page
  So that I can find relevant products efficiently

  Background: User is logged in and on the home page
    Given user navigates to sign in page
    Then user is navigated to login page and user logs in
    Then user navigates to home page

  @regression
  Scenario: Validate product search returns correct results across all pages
    When user enters "Pliers" in the search box
    Then all products across all pages contain "Pliers" in their name

  @regression
  Scenario: Validate sorting by name ascending across all pages
    When user selects sorting by "name,asc"
    Then products across all pages are sorted by name ascending

  @regression
  Scenario: Validate sorting by name descending across all pages
    When user selects sorting by "name,desc"
    Then products across all pages are sorted by name descending

  @regression
  Scenario: Validate sorting by price low to high across all pages
    When user selects sorting by "price,desc"
    Then products across all pages are sorted by price high to low

  @regression
  Scenario: Validate sorting by price low to high across all pages
    When user selects sorting by "price,asc"
    Then products across all pages are sorted by price low to high

  @regression
  Scenario: Validate price range filtering shows correct products across all pages
    When user sets price range from "10" to "50"
    Then all products across all pages have prices between "10" and "50"

  @regression
  @negative
  Scenario: Validate no results for invalid search
    When user enters "NoExistentProduct123" in the search box
    Then no products are displayed



