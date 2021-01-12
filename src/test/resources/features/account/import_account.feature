@Account
Feature: Import Accounts

  @deleteAccount @createAccount
  Scenario Outline: Import multiple Accounts using .csv files as Standard objects

    Given I log in to Salesforce with Account Owner User credentials
    When I go to "ACCOUNT" page
    And I select the "import" option
    Then "Import your Data into Salesforce" should be displayed at title
    And I import a new Account matching by "Email" with the following <filepath> as <option>
      | Option      | file path                  |
      | CSV         | config/account/csv.csv     |
      | Outlook CSV | config/account/outlook.csv |
      | ACT! CSV    | config/account/act.csv     |
      | GMail CSV   | config/account/gmail.csv   |
    Then The new record should be displayed at the table
