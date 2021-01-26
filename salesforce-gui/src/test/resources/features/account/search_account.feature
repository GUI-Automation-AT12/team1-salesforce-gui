@Account
Feature: Search Accounts

  @createAccounts @deleteAccounts
  Scenario Outline: Account search using the search tool and compare with the account created
    Given I log in to Salesforce with "Account Owner User" credentials
    When I go to "HOME" page
      And I set the following text <search> in the searching tool
    Then suggested results should contain the input text
    When I go to the search results
      Then the results in the Account section should match with the <filePath>
      And the details of each found Account should contain the initial creation data
    Examples:
      | search         | filePath                     |
      | Armando Lopez  | searchByName.json            |
      | *Lopez         | searchByLastName.json        |
      | Ar*            | searchByFirstCharacters.json |
