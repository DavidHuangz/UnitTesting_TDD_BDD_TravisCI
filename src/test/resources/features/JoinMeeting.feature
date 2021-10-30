Feature: Join a meeting

  @UserStory201
  Scenario: View current meetings
    When I visit the page for joining a meeting
    Then I should be able to see all active meetings

  @UserStory201
  Scenario Outline: Join an active meeting
    Given I visit the page for joining a meeting
    When I enter '<meeting ID>' as meeting ID field to join a meeting
    And I press the join button
    Then I should see a message for joining the meeting
    Examples:
      | meeting ID |
      | 001        |
      | 003        |

  @UserStory201
  Scenario Outline: Join an inactive meeting
    Given I visit the page for joining a meeting
    When I enter '<meeting ID>' as meeting ID field to join a meeting
    And I press the join button
    Then I should see an error message for inactive meeting
    Examples:
      | meeting ID |
      | 101        |
      | 008        |

  @UserStory201
  Scenario: Join a meeting without entering a meeting id
    Given I visit the page for joining a meeting
    When I press the join button
    Then I should see an error message for not entering a meeting id
