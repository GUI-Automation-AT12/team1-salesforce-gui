@PersonalInformation
Feature: Edit Personal Information

  Background:
    Given I log in to Salesforce with a valid Account

  @restorePersonalInformation
  Scenario: Update personal information with the minimum values
    When I go to settings
    And I verify that the title of the panel is "Personal Information"
    And I edit the form with the following data
      | First Name | Civil              |
      | Last Name  | Armando Guerra     |
      | Alias      | Guerra             |
      | Email      | armando@guerra.com |
      | Nickname   | user661user        |
    Then Verify if should be displayed "Your settings have been successfully saved."
    And Verify if the table was an updated
