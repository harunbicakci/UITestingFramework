Feature: Login

    @registerNewUser
    Scenario: New user registry with valid credentials
      Given user navigates to sign in page
      When user clicks to register new account
      And user enters valid credentials and data
      And click on register button
      Then I validate that user is registered
