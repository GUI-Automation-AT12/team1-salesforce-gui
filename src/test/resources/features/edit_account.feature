Feature: Edit Account

  @restoreAccount
  Scenario: Edit an Account from UI
    Given I log in to Salesforce with Account Owner User credentials
    When I go to ACCOUNTS tab
      And I edit the Existing Account with the following data
        |  Name             |  Edited Account  |
        |  Parent account   |  Father Account  |
        |  Site             |  Another site    |
        |  Billing city     |  Other city      |
        |  Phone            |  +591 77777778   |
    Then edited data should be displayed at Account details
      And edited data should be displayed in Accounts table
    When I get the Account info via API
    Then the response should contain the edited data
