@Account
Feature: Import Accounts

  @deleteAccountImports
  Scenario: Import multiples accounts using csv files
    Given I log in to Salesforce with Account Owner User credentials
    When I go to ACCOUNTS tab
    And I selected "Import" option
    Then "Import your Data into Salesforce" should be displayed at title
    And I selected "Accounts and Contacts"
    And I selected "Add new records"
    And I selected "Email" option from "Match Contact by:" field
    And I selected the following options with a valid file
      | Option      | file path                  |
      | CSV         | config/account/csv.csv     |
      | Outlook CSV | config/account/outlook.csv |
      | ACT! CSV    | config/account/act.csv     |
      | GMail CSV   | config/account/gmail.csv   |
    And I selected "Next" option
    And I selected "Start Import" option
    And I selected "Ok" option
