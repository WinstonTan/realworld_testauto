@excel
  Feature: Excel import and export test data
    Scenario Outline: Test Excel import and export test data
    Given user is on google home page
    When user fills the search field from given sheetname "<SheetName>" and rownumber <RowNumber>
    And user clicks on search button
    Then it shows search result
#
      Examples:
      | SheetName | RowNumber |
      | Sheet1    | 1         |
      | Sheet1    | 2         |
      | Sheet1    | 3         |
      | Sheet1    | 4         |
