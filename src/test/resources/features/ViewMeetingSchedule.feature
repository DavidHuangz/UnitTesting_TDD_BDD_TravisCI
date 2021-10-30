Feature: View meeting schedule

  @UserStory200
  Scenario: User can view meeting details
    Given I visit the home page for viewing meeting schedule
    When I choose view all the meetings
    Then I can see meeting ID
    And I can see meeting title
    And I can see meeting description
    And I can see meeting time
    And I can see user names


  @UserStory200
  Scenario: User can view all the meetings
    Given I visit the home page for viewing meeting schedule
    When I choose view all the meetings
    Then I should see the all meetings message

  @UserStory200
  Scenario: User can view past meetings
    Given I visit the home page for viewing meeting schedule
    When I choose view past meetings
    Then I should see the past meetings message

  @UserStory200
  Scenario: User can view upcoming meetings
    Given I visit the home page for viewing meeting schedule
    When I choose view upcoming meetings
    Then I should see the upcoming meetings message