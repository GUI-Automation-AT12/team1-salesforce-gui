@PersonalInformation
Feature: Edit Personal Information

  Background:
    Given I log in to Salesforce with a valid Account

  @restorePersonalInformation
  Scenario: Update personal information with the minimum values
    When I go to "PERSONAL INFORMATION PAGE"
    And I edit my personal information with the following data
      | First Name | Civil              |
      | Last Name  | Armando Guerra     |
      | Alias      | Guerra             |
      | Email      | armando@guerra.com |
      | Nickname   | user661user        |
    Then The personal information table should be updated
