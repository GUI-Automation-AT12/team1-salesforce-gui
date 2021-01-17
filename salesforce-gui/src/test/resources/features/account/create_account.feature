@Account
Feature: Create Account

  @deleteAccount
  Scenario: Create an Account from UI
    Given I log in to Salesforce with "Account Owner User" credentials
    When I go to ACCOUNTS tab
      And I create an new Account with the following data
        |  Name             |  New Account       |
        |  Parent account   |  Existent Account  |
        |  Site             |  New site          |
        |  Billing city     |  Capital City      |
        |  Phone            |  +591 77778888     |
    Then Account's new data should be displayed at details
      And Account's new data should be displayed in Accounts table
      And the gotten data about the Account should contain the new data