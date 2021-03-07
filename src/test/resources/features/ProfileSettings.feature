@settings
Feature: Test profile settings in realworld website
  Background: Successful new Sign Up / Sign In, and open Update Settings page as pre-requisite for update Profile testing
    Given user is on homepage
    And clicks on Sign up hypertext
    Then Sign Up page is loaded successfully
    When user inserts unique username and email, and a valid password
    And click on Sign in button
    Then successfully signed up username hypertext will be displayed
    Then user will be redirected back to home page
    And clicks on Settings hypertext
    Then Update Settings page is loaded successfully

  @smoke
  Scenario Outline: Test updating Profile successfully
    When user enters <picURL>, <username>, <shortBio>, <email> and <newPassword> in Settings page
    And click on Update Settings button
    Then there will be no error message on Settings page
    When user refresh page
    And clicks on Settings hypertext
    Then the latest profile info will be displayed on Settings page

    Examples:
      | picURL                          |  username         | shortBio                  | email                             | newPassword |
      | https://i.imgur.com/RFk9hgO.gif | [unique username] | I'm a robot but a droid   | [unique email]  | Efgh56789   | # Update all fields
      | [blank]                         | [unique username] | [blank]                   | [unique email]  | [blank]     | # Update username and email
      | [blank]                         | [skip]            | [blank]                   | [skip]          | [blank]     | # Update with no changes
      | [blank]                         | [skip]            | [blank]                   | [unique email]  | [blank]     | # Update only email
      | [blank]                         | [unique username] | [blank]                   | [skip]          | [blank]     | # Update only username