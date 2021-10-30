Feature: Schedule a meeting

  @UserStory102
  Scenario: Navigate to the page for scheduling a meeting
    Given I am on the home page
    When I click the button for creating a meeting
    Then I am taken to the page for scheduling a meeting

  @UserStory102
  Scenario: Create a meeting
    Given I am on the create meeting page
    When I have entered the meeting ID "id"
    And I have entered the meeting title "title"
    And I have entered the meeting description "description"
    And I have set the start date
    And I have set the start time
    And I have set the end date
    And I have set the end time
    And I choose the group "group2"
    And I click the submit button
    Then I can see create group message

  @UserStory102
  Scenario: Enter the meeting ID
    Given I am on the create meeting page
    When I have entered the meeting ID "id"
    And I click the submit button
    Then I can see the meeting ID is set as "id"

  @UserStory102
  Scenario: Enter the meeting title
    Given I am on the create meeting page
    When I have entered the meeting title "title"
    And I click the submit button
    Then I can see the meeting title is set as "title"

  @UserStory102
  Scenario: Enter the meeting description
    Given I am on the create meeting page
    When I have entered the meeting description "description"
    And I click the submit button
    Then I can see the meeting description is set as "description"

  @UserStory102
  Scenario: Set meeting start date
    Given I am on the create meeting page
    When I have set the start date
    And I click the submit button
    Then I can see the meeting start date is set

  @UserStory102
  Scenario: Set meeting start time
    Given I am on the create meeting page
    When I have set the start time
    And I click the submit button
    Then I can see the meeting start time is set

  @UserStory102
  Scenario: Set meeting end date
    Given I am on the create meeting page
    When I have set the end date
    And I click the submit button
    Then I can see the meeting end date is set

  @UserStory102
  Scenario: Set meeting end time
    Given I am on the create meeting page
    When I have set the end time
    And I click the submit button
    Then I can see the meeting end time is set

  @UserStory102
  Scenario: Set group for a meeting
    Given I am on the create meeting page
    When I choose the group "group2"
    Then I can see the group is set as "group2"

  @UserStory102
  Scenario: Schedule the meeting as recurring
    Given I am on the create meeting page
    When I choose recurring as true
    Then I can see the recurring is set as true


  @UserStory102
  Scenario: Fail to create meeting due to invalid time
    Given I am on the create meeting page
    When I have entered the meeting ID "id"
    And I have entered the meeting title "title"
    And I have entered the meeting description "description"
    And I have set the start date
    And I have set the start time
    And I have set a invalid end date
    And I have set a invalid end time
    And I choose the group "group2"
    And I click the submit button
    Then I should see the invalid time message


  @UserStory102
  Scenario: Fail to create meeting due to empty meeting id
    Given I am on the create meeting page
    And I have entered the meeting title "title"
    And I have entered the meeting description "description"
    And I have set the start date
    And I have set the start time
    And I have set the end date
    And I have set the end time
    And I choose the group "group2"
    And I click the submit button
    Then I should see the invalid meeting id message

  @UserStory102
  Scenario: Fail to create meeting due to empty time
    Given I am on the create meeting page
    When I have entered the meeting ID "id"
    And I have entered the meeting title "title"
    And I have entered the meeting description "description"
    And I choose the group "group2"
    And I click the submit button
    Then I should see the empty meeting time message

