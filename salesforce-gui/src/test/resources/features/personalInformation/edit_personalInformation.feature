@PersonalInformation
Feature: Edit Personal Information

  Background:
    Given I log in to Salesforce with "Account Owner User" credentials

  @restorePersonalInformation
  Scenario: Update personal information with the minimum values
    When I go to "PERSONAL INFORMATION" page
    And I edit my personal information with the following data
      | firstName | Civil          |
      | lastName  | Armando Guerra |
      | alias     | Guerra         |
    Then The personal information table should be updated
