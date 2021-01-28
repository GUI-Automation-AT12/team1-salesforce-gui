@Account
Feature: Search Accounts

  @createAccountsFromCsv @deleteAccountsFromCsv
  Scenario Outline: Account search using the search tool and compare with the account created
    Given I log in to Salesforce with "Account Owner User" credentials
    When I go to "HOME" page
      And I set the following text "<search>" in the searching tool without asterisk
    Then suggested results should contain the inputted text
    When I go to search results
    Then the result data in the "Accounts" section should match with the "<filePath>"
    #Examples:
    #  | search          | filePath                    |
    #  | Armando Lopez*  | searchByName.csv            |
    #  | *Lopez          | searchByLastName.csv        |
    #  | Ar*             | searchByFirstCharacters.csv |
    @FinalExample
    Examples:
      | search          | filePath                     |
      | Acc*            | searchByFirstCharacters.csv  |
