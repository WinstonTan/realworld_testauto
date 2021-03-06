@post
Feature: Test creating New Post in realworld website
  Background: Successful new Sign Up / Sign In as pre-requisite for Post testing
    Given user is on homepage
    And  clicks on Sign up hypertext
    Then Sign Up page is loaded successfully
    When user inserts unique username and email, and a valid password
    And click on Sign in button
    Then successfully signed up username hypertext will be displayed
    Then user will be redirected back to home page

  @smoke
  Scenario: Test creating new post successfully
    And clicks on New Post hypertext
    Then New Post page is loaded successfully
    When user enters valid articleTitle, aboutArticle, articleMarkdown and tags in New Post page
    And click on Publish Article button
    Then target Author Article Page created and loaded successful

    #Test enter new comment
    When user enters comment
    And click on Post Comment button
    Then new comment card entry is created and displaying on Article page

    #Validate created article on Home page Global Feed section (Last Post)
    When user navigates to Home page again
    And click on Global Feed tab header
    Then the top 10 latest feeds will be displayed on Global Feed listing

#  Scenario Outline: Validation test on Sign Up page
#    Given user is on homepage
#    And  clicks on Sign up hypertext
#    Then Sign Up page is loaded successfully
#    When user enters <username>, <email> and <password> in Sign Up page
#    And click on Sign in button
#    Then sign up error message prompted "<errorMsg>"
#
#    Examples:
#      | username              |  email                              | password                                                                  |  errorMsg                                       |
#      | auto_testuser_99000   | auto_testuser_00000@mailinator.com  | Abcd1234                                                                  | email has already been taken                    | # Email already been taken
#      | auto_testuser_00000   | auto_testuser_99900@mailinator.com  | Abcd1234                                                                  | username has already been taken                 | # Duplicated username
#      | [blank]               | auto_testuser_99900@mailinator.com  | Abcd1234                                                                  | username can't be blank (minimum is 1 character)| # Empty username
#      | auto_testuser_99000   | [blank]                             | Abcd1234                                                                  | email can't be blank                            | # Empty email
#      | auto_testuser_99000   | auto_testuser_99900@mailinator.com  | [blank]                                                                   | password can't be blank                         | # Empty password
#      | a12345678901234567890 | auto_testuser_99900@mailinator.com  | Abcd1234                                                                  | username is too long (maximum is 20 characters) | # Username length validation
#      | auto_testuser_99000   | auto_testuser_99900@mailinator.com  | 1234567890123456789012345678901234567890123456789012345678901234567890Abc | password is too long (maximum is 72 characters) | # Password length validation


