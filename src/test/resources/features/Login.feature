Feature: Login

#Mapping Negative Scenarios
#  1- Valid username invalid password
#  2- Invalid username valid password
#  3- Empty username valid password
#  4- Valid username empty password
#  5- Valid username invalid password that not meet requirements

Background:
  Given user navigates to sign in page

  @smoke @positive @registerNewUser
  Scenario: New user registry with valid credentials
    When user clicks to register new account
    And user enters valid credentials and data
    And click on register button
    Then I validate that user is registered

  #1
  @smoke @negative
  Scenario: Valid username invalid password
    When user enters valid username
    And user enters invalid password
    And user clicks on login button
    Then I validate that error message appears

  #2
  @smoke @negative
  Scenario: Invalid username valid password
    When user enters invalid username
    And user enters valid password
    And user clicks on login button
    Then I validate that error message appears

  #3
  @smoke @negative
  Scenario: Empty username valid password
    When user enters empty username
    And user enters valid password
    And user clicks on login button
    Then I validate that error message appears

  #4
  @smoke @negative
  Scenario: Valid username empty password
    When user enters valid username
    And user enters empty password
    And user clicks on login button
    Then I validate that email is required error appears

  #5
  @smoke @negative
  Scenario: Valid username invalid password that not meet requirements
    When user enters valid username
    And user enters invalid password
    And user clicks on login button
    Then I validate that password is required error appears

