@Opportunity
Feature: Create Opportunity

  @deleteOpportunity
  Scenario: Create an Opportunity that depends of an Account
    Given I log in to Salesforce with "Opportunity Owner User" credentials
    When I go to "OPPORTUNITIES" page
    When I create a new Opportunity with the following data
      |  Name         |  New Opportunity   |
      |  Account      |  Existent Account  |
      |  Close Date   |  TODAY             |
      |  Stage        |  Closed Won        |
    Then the new Opportunity should be present in Opportunities table
      And the new Opportunity should be present in Existent Account details
      And the gotten data about the Opportunity should contain the new data