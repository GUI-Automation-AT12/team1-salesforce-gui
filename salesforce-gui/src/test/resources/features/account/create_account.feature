@Account
Feature: Create Account

  @deleteAccount
  Scenario: Create an Account from UI
    Given I log in to Salesforce with "Account Owner User" credentials
    When I go to "ACCOUNTS" page
      And I create an Account with the following data
        |  Name             |  New Account                                 |
        |  Parent Account   |  Existent Account                            |
        |  Site             |  New site                                    |
        |  Billing City     |  Capital City                                |
        |  Rating           |  Warm                                        |
        |  Phone            |  +591 77778888                               |
        |  Description      |  An important account recorded by the user!  |
    Then Account's new data should be displayed at details
      And Account's new data should be displayed in Accounts table
      And the gotten data about the Account should contain the new data