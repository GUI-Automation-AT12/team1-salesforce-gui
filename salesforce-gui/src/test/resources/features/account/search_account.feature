@Account
Feature: Search Accounts

  @createAccountsFromCsv @deleteAccountsFromCsv
  Scenario Outline: Account search using the search tool and compare with the account created
    Given I log in to Salesforce with "Account Owner User" credentials
    When I go to "HOME" page
      And I set the following text "<search>" in the searching tool without asterisk
    Then suggested results should contain the inputted text
    When I go to search results
    Then the result data in the "Accounts" section should match with the "<fileName>"
    Examples:
      | search          | fileName                    |
      | Armando Lopez*  | searchByFullName.csv        |
      | *Lopez          | searchByLastName.csv        |
    @FinalExample
    Examples:
      | search          | fileName                    |
      | Ar*             | searchByFirstCharacters.csv |
