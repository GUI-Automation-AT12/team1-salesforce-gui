@Account
Feature: Import Accounts

  @deleteAccounts
  Scenario Outline: Import multiple Accounts using .csv files
    Given I log in to Salesforce with "Account Owner User" credentials
    When I go to "ACCOUNTS" page
      And I import a new Account matching by "<matchContactBy>" with the file "<filePath>" and the "<option>"
    Then the auto-mapped fields table should contain the next data
        | Mapped Salesforce Object | CSV Header         |
        | Account: Account Name    | AccountName        |
        | Contact: Last Name       | ContactLastName    |
        | Account: Website         | AccountWebsite     |
        | Account: Phone           | AccountPhone       |
        | Account: Description     | AccountDescription |
        | Contact: Email           | Email              |
      And the Mapped Fields value should be 6 and Unmapped Field should be 0
      And the Data Load Job Details should contain the following importation data
        | Content Type      | CSV    |
        | Operation         | Insert |
        | Progress          | 100%   |
        | Records Processed | 6      |
        | Status            | Closed |
    When I click the "View Result" link to download file
    Then the downloaded file should not contain false Success and any errors
    When I store the Id of imported Accounts from the downloaded file
    Then the new Accounts should be in New This Week view at Account page
    Examples:
        | option      | matchContactBy | filePath                 |
        | CSV         | Email          | importAccountCSV.csv     |
        | Outlook CSV | Email          | importAccountOutlook.csv |