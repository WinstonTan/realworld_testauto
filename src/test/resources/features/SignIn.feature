@signin
Feature: Test Sign in on realworld website
#
  @smoke
  Scenario Outline: Test signing in with valid email and password
    Given user is on homepage
    And clicks on Sign in hypertext
    Then Sign in page is loaded successfully
    When <user> enters <email> and <password> in Sign In page
    And click on Sign in button
    Then user will be redirected back to home page

    Examples:
      | user                | email                               | password  |
      | auto_testuser_00000 | auto_testuser_00000@mailinator.com  | Abcd1234  |


  Scenario Outline: Validation test on Sign In page
    Given user is on homepage
    And  clicks on Sign in hypertext
    Then Sign in page is loaded successfully
    When user enters <email> and <password> in Sign In page
    And click on Sign in button
    Then sign in error message prompted "<errorMsg>"

    Examples:
      | email                               | password  |  errorMsg                    |
      | auto_testuser_0000z@mailinator.com  | Abcd1234  | email or password is invalid | # Invalid email id
      | auto_testuser_00000@mailinator.com  | Abcd1235  | email or password is invalid | # Invalid password
      | [blank]                             | Abcd1234  | email or password is invalid | # Blank email
      | auto_testuser_00000@mailinator.com  | [blank]   | email or password is invalid | # Blank password
