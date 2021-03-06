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

    #Validate created article on My Articles section
    When user navigates to Home page again
    And user click on own username hypertext on top right
    Then the last Article title will displayed in My Articles listing on profile page


  Scenario: Posting comment on article that belongs to other user
    When user navigates to Home page again
    And click on Global Feed tab header
    And click on the top Global Feed article title
    Then the target article page will be opened
    When user enters comment
    And click on Post Comment button
    Then new comment card entry is created and displaying on Article page

  Scenario Outline: Test Validation in new post page form
    And clicks on New Post hypertext
    Then New Post page is loaded successfully
    When user enters <articleTitle>, <aboutArticle>, <articleMarkdown> and <tags> in Post form
    And click on Publish Article button
    Then validation message "<errorMsg>" appears on new post page

    Examples:
      | articleTitle  |  aboutArticle   | articleMarkdown                 | tags     | errorMsg                                            |
      | [blank]        | About 文章 456 | L49tKOXkRt3fC8fO9sA9dqgXSfboBj   | testTag1 | title can't be blank (minimum is 1 character)       | # Empty title
      | 测试标题789 HiJ | [blank]        |  L49tKOXkRt3fC8fO9sA9dqgXSfboBj  | testTag2 | description can't be blank (minimum is 1 character) | # Empty about article
      | 测试标题111 XyZ | About 文章 111  | [blank]                          | testTag3 |  body can't be blank                                | # Empty article markdown
