@Account
Feature: Search Account

  @deleteAccount @createAccount
  Scenario: Account search using the search tool and compare with the account created
    Given I log in to Salesforce with Account Owner User credentials
    When I go to "HOME" page
    And I search the following values <search> and compare the result with the <filePath> file
      | Search        | Type of search | filePath                     |
      | Armando Lopez | Account        | searchByName.json            |
      | Lopez         | Account        | searchByLastName.json        |
      | Ar            | Account        | searchByFirstCharacters.json |
    Then I search the account name in the drop down menu
    When I select the account name
    And The Account's information should be displayed at details
