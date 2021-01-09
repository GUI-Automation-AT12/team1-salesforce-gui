@Account
Feature: Search Account

  @deleteAccount @createAccount
  Scenario: Account search using the search tool and compare with the account created
    Given I log in to Salesforce with Account Owner User credentials
    When I go to "HOME" page
    And I search the following name
      | Armando Guerra |
    Then I search these user name in the drop down menu
    When I select the user name
    And The Account's information should be displayed at details
