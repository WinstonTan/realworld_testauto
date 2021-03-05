@signup
Feature: Test Sign-Up in realworld website

  @smoke
  Scenario: Test signing up with unique username and email, and a valid password
    Given user is on homepage
    And  clicks on Sign up hypertext
    Then Sign Up page is loaded successfully
    When user inserts unique username and email, and a valid password
    And click on Sign in button
    Then successfully signed up username hypertext will be displayed
    Then user will be redirected back to home page


  Scenario Outline: Validation test on Sign Up page
    Given user is on homepage
    And  clicks on Sign up hypertext
    Then Sign Up page is loaded successfully
    When user enters <username>, <email> and <password> in Sign Up page
    And click on Sign in button
    Then sign up error message prompted "<errorMsg>"

    Examples:
      | username              |  email                              | password                                                                  |  errorMsg                                       |
      | auto_testuser_99000   | auto_testuser_00000@mailinator.com  | Abcd1234                                                                  | email has already been taken                    | # Email already been taken
      | auto_testuser_00000   | auto_testuser_99900@mailinator.com  | Abcd1234                                                                  | username has already been taken                 | # Duplicated username
      | [blank]               | auto_testuser_99900@mailinator.com  | Abcd1234                                                                  | username can't be blank (minimum is 1 character)| # Empty username
      | auto_testuser_99000   | [blank]                             | Abcd1234                                                                  | email can't be blank                            | # Empty email
      | auto_testuser_99000   | auto_testuser_99900@mailinator.com  | [blank]                                                                   | password can't be blank                         | # Empty password
      | a12345678901234567890 | auto_testuser_99900@mailinator.com  | Abcd1234                                                                  | username is too long (maximum is 20 characters) | # Username length validation
      | auto_testuser_99000   | auto_testuser_99900@mailinator.com  | 1234567890123456789012345678901234567890123456789012345678901234567890Abc | password is too long (maximum is 72 characters) | # Password length validation


