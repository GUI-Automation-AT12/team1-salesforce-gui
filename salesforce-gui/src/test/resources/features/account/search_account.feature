@Account
Feature: Search Account

  @deleteAccount @createAccount
  Scenario Outline: Account search using the search tool and compare with the account created
    Given I log in to Salesforce with Account Owner User credentials
    When I go to "HOME" page
    And I set the following text <search> in the searching tool
      | search        |
      | Armando Lopez |
      | Lopez         |
      | Ar            |
    Then the results in the Account section should match with the <filePath>
      | filePath                     |
      | searchByName.json            |
      | searchByLastName.json        |
      | searchByFirstCharacters.json |
    And the details of each found account should contain the initial creation data
