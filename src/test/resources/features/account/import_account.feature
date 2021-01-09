@Account
Feature: Import Accounts

  @deleteAccount @createAccount
  Scenario Outline: Import multiple Accounts using .csv files
    Given I log in to Salesforce with Account Owner User credentials
    When I go to "HOME" page
    And I search the following name
      | Armando Guerra |
    Then "Import your Data into Salesforce" should be displayed at title
    And I selected "Accounts and Contacts"
    And I selected "Add new records"
    And I selected "Email"
    And I import a new Account matching by "Email" with the following <filepath> as <option>
      | Option      | file path                  |
      | CSV         | config/account/csv.csv     |
      | Outlook CSV | config/account/outlook.csv |
      | ACT! CSV    | config/account/act.csv     |
      | GMail CSV   | config/account/gmail.csv   |
    Then The new record should be displayed at the table
 
