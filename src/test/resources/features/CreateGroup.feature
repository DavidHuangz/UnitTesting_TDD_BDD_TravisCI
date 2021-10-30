Feature: create a group

  @UserStory104
  Scenario: Create a group successfully
    Given I visit the group page
    When I enter "groupName" as group name field
    And I enter "userName" as user name field for adding group members
    And I press the create button
    Then I should see the group is created successfully message

  @UserStory104
  Scenario: Create a group without groupName
    Given I visit the group page
    When I enter "userName" as user name field for adding group members
    And I press the create button
    Then I should see an error message for invalid group name

  @UserStory104
  Scenario: Create a group without a user
    Given I visit the group page
    When I enter "groupName" as group name field
    And I press the create button
    Then I should see an error message for invalid user

  @UserStory104
  Scenario: Add a user successfully
    Given I visit the group configuration page
    When I enter "userName" as user name field for adding group members
    And I press the add button
    Then I should see a message for adding user successfully

  @UserStory104
  Scenario: Add a user without username
    Given I visit the group configuration page
    When I press the add button
    Then I should see an error message for invalid user name

  @UserStory104
  Scenario: Remove a user successfully
    Given I visit the group configuration page
    When I enter "userName" as user name field for adding group members
    And I press the remove button
    Then I should see a message for removing user successfully

  @UserStory104
  Scenario: Remove a user without username
    Given I visit the group configuration page
    When I press the remove button
    Then I should see an error message for invalid user name