Feature: Card Activity Game

   @UserStory100
   Scenario: Add player to game
    Given I have gone to the play game page
    When I type "Player 1" into the player box
    And I click the add player button
    Then "Player 1" appears in the player list

  @UserStory100
  Scenario: Start the game
    Given I have gone to the play game page
    And I type "p1" into the player box
    And I click the add player button
    And I type "p2" into the player box
    And I click the add player button
    When I click the play button
    Then I can see a card for "p1"
    And The card for "p1" has a card value
    And I can see a card for "p2"
    And The card for "p2" has a card value

  @UserStory100
  Scenario: Not enough players error
    Given I have gone to the play game page
    And I type "p1" into the player box
    And I click the add player button
    When I click the play button
    Then I should see an error message in the play page

  @UserStory100
  Scenario: Error for duplicate players
    Given I have gone to the play game page
    And I type "p1" into the player box
    And I click the add player button
    And I type "p1" into the player box
    When I click the add player button
    Then I should see an error message in the play page

  @UserStory100
  Scenario: Swap cards
    Given I have gone to the play game page
    And I type "p1" into the player box
    And I click the add player button
    And I type "p2" into the player box
    And I click the add player button
    And I click the play button
    When I click player "p1" card
    And I click player "p2" card
    Then "p1" card and "p2" card are swapped