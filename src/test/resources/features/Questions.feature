Feature: Ask question in meetings

  @UserStory302
  Scenario: User can see the latest questions
    When I visit questions page
    Then I should see all questions sorted by time

  @UserStory302
  Scenario: User can ask questions
    Given I visit questions page
    When I enter "How long is this meeting?" as question field
    And I press the ask button
    Then I should see my question

  @UserStory302
  Scenario: User can upvote questions
    Given I visit questions page
    When I select question "1" to upvote
    Then I should see question one with one more upvote

  @UserStory302
  Scenario: User can answer questions
    Given I visit questions page
    When I enter "Go to science building" as answer field
    And I select question "1" to answer
    And I press the answer button
    Then I should see my answer for question one

  @UserStory302
  Scenario: Organiser can delete questions
    Given I visit questions page
    When I select question "1" to delete
    Then I should see question one removed

  @UserStory302
  Scenario: User can filter questions by upvote
    Given I visit questions page
    When I sort question by "upvote"
    Then I should see all questions sorted by Most Voted Questions

  @UserStory302
  Scenario: User can filter questions by answered
    Given I visit questions page
    When I sort question by "Answered"
    Then I should see all questions sorted by Answered Questions

  @UserStory302
  Scenario: Organiser can mark questions
    Given I visit questions page
    When I select question "1" to mark
    And I sort question by "Answered"
    Then I should see question one as resolved to true


