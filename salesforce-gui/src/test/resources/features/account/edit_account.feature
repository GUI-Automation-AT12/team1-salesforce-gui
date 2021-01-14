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

  @createAccount @deleteAccount
  Scenario Outline: Edit the SLA Expiration Date should change in details
    Given I log in to Salesforce with Account Owner User credentials
    When I go to ACCOUNTS tab
      And I go to Existent Account details
      And I edit the Account's SLA Expiration Date with the following <date>
    Then Account's new SLA Expiration Date should be displayed at details
      And the edition datetime should be updated
      And the gotten data about the Account should contain the correct SLA Expiration and edition datetime
    Examples:
      |  date            |
      |  YESTERDAY       |
      |  TODAY           |
      |  TOMORROW        |
      |  NEXT WEEK       |
      |  NEXT MONTH      |
      |  NEXT YEAR       |
