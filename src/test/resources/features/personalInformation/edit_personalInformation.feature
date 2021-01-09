@PersonalInformation
Feature: Edit Personal Information

  Background:
    Given I log in to Salesforce with a valid Account

  @restorePersonalInformation
  Scenario: Update personal information with the minimum values
    When I go to "Personal Information page"
    And I verify that the title of the panel is "Personal Information"
    And I edit my personal information with the following data" to be more specific
      | First Name | Civil              |
      | Last Name  | Armando Guerra     |
      | Alias      | Guerra             |
      | Email      | armando@guerra.com |
      | Nickname   | user661user        |
    Then The personal information table should be updated
