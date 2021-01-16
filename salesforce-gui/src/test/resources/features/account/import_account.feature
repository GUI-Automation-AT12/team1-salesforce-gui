@Account
Feature: Import Accounts

  @skipScenario @deleteAccount
  Scenario Outline: Import multiple Accounts using .csv files
    Given I log in to Salesforce with "Account Owner User" credentials
    When I go to "ACCOUNT" page
      And I go Import section
      And I import a new Account matching by "Email" with the following <filePath> as <Option>
    Then the Mapped Salesforce Object column should not contain "Unmapped" for any entry
      And the Mapped Fields value should be "7" and Unmapped Field should be "0"
      And the Data Load Job Details should contain correct importation data
      And the new records should be displayed at the Accounts table with "New This Week" option
    Examples:
      | Option      | filePath                   |
      | CSV         | config/account/csv.csv     |
      | Outlook CSV | config/account/outlook.csv |
      | ACT! CSV    | config/account/act.csv     |
      | GMail CSV   | config/account/gmail.csv   |
