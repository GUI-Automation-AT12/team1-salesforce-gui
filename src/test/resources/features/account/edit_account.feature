@Account
Feature: Edit Account

  @restoreAccount
  Scenario: Edit Account from UI
    Given I log in to Salesforce with Account Owner User credentials
    When I go to ACCOUNTS tab
      And I edit the Existent Account with the following data
        |  Name             |  Edited Account  |
        |  Parent account   |  Father Account  |
        |  Site             |  Another site    |
        |  Billing city     |  Other city      |
        |  Phone            |  +591 77777778   |
    Then Account's new data should be displayed at details
      And Account's new data should be displayed in Accounts table
      And the gotten data about the Opportunity should contain the new data

  @deleteAccount
  Scenario:User creates an Account, then edits it and verify creation and edition datetime
    Given I log in to Salesforce with Account Owner User credentials
    When I go to ACCOUNTS tab
      And I create an new Account with the following data
        |  Name             |  New Account     |
        |  Site             |  New site        |
        |  Billing city     |  Capital City    |
      And I edit the New Account with the following data
        |  Name             |  Edited Account  |
        |  Parent account   |  Father Account  |
        |  Site             |  Another site    |
        |  Billing city     |  Other city      |
        |  Phone            |  +591 77777778   |
    Then Account's new data should be displayed at details
      And the creation and edition datetime should be the correct
      And the gotten data about the Account should contain the correct creation and edition datetime
