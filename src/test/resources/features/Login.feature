@tag
  Feature: Login

    @tag1
    Scenario: User login with valid credentials
      Given user navigates to login screen
      When user enters valid credentials and clicks login button
      Then user logs in to application

    @tag2
    Scenario Outline: User tries with invalid credentials
      Given user navigates to login screen
      When user enters invalid combination of <username> and <password>
      Then user gets the <errorMessage>

      Examples:
      |username|password|errorMessage|
      |aaa@mail.com|aaa|            |
      |aaa@mail.com|   |            |
      |            |aaa|            |
#
#Feature: Sauce Demo login
#
#  Scenario: valid login
#    When user enters valid username
#    And user enters valid password
#    And click on login button
#    Then I validate that user is logged in
#
#  Scenario: empty password
#    When user enters valid username
#    And user leaves password empty
#    And click on login button
#    Then I validate that "Epic sadface: Password is required..." message is displayed
#
#  Scenario: invalid login
#    When user enters invalid username as "random_username"
#    And user enters invalid password as "random_password"
#    And click on login button
#    Then I validate that "Epic sadface: Username and password do not match any user in this service" message is displayed