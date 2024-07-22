Feature: Login

    @tag1
    Scenario: User login with valid credentials
      When user enters valid username
      And user enters valid password
      And click on login button
      Then I validate that user is logged in

    @tag1
    Scenario: empty password
      When user enters valid username
      And user leaves password empty
      And click on login button
      Then I validate that "Epic sadface: Password is required" message is displayed

    @tag1
    Scenario: invalid login
      When user enters invalid username as "random_username"
      And user enters invalid password as "random_password"
      And click on login button
      Then I validate that "Epic sadface: Username and password do not match any user in this service" message is displayed

#Try this scenario to test Scenario Outline, fill the examples table first
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