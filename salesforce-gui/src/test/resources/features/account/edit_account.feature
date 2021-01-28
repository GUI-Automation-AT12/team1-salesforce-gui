@Account
Feature: Edit Account

  @skipScenario @restoreAccount
  Scenario: Edit Account from UI
    Given I log in to Salesforce with "Account Owner User" credentials
    When I go to "ACCOUNTS" page
    And I edit the Existent Account with the following data
      |  Name             |  Edited Account  |
      |  Parent account   |  Father Account  |
      |  Site             |  Another site    |
      |  Billing city     |  Other city      |
      |  Phone            |  +591 77777778   |
    Then Account's new data should be displayed at details
    And Account's new data should be displayed in Accounts table
    And the gotten data about the Opportunity should contain the new data

  @createAccount @deleteAccounts
  Scenario Outline: Edit the SLA Expiration Date should change in details
    Given I log in to Salesforce with "Account Owner User" credentials
    When I go to "ACCOUNTS" page
      And I go to Existent Account details
      And I edit the Account's SLA Expiration Date with the following "<date>"
    Then Account's new SLA Expiration "<date>" should be displayed at details
      And the edition datetime should be updated with the "TODAY" date
    Examples:
      | date       |
      | YESTERDAY  |
      | TODAY      |
      | TOMORROW   |
      | NEXT WEEK  |
      | NEXT MONTH |
      | NEXT YEAR  |
