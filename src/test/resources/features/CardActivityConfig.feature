Feature: Card Activity Config

  @UserStory100
  Scenario: View default activity config
    Given I have gone to the new activity page
    Then I should see the card values that will be used
    And I should see the card suits that will be used

  @UserStory100
  Scenario: Delete card values
    Given I have gone to the new activity page
    When I click on value "A"
    Then value "A" is removed

  @UserStory100
  Scenario: Delete card suits
    Given I have gone to the new activity page
    When I click on suit "Hearts"
    Then suit "Hearts" is removed

  @UserStory100
  Scenario: Add card values
    Given I have gone to the new activity page
    When I type in value "Egg"
    And I press the add value button
    Then Value "Egg" is added to the values display

  @UserStory100
  Scenario: Add card suits
    Given I have gone to the new activity page
    When I type in suit "Potatoes"
    And I press the add suit button
    Then Suit "Potatoes" is added to the suits display

  @UserStory100
  Scenario: Prevent no card values
    Given I have gone to the new activity page
    When I click on value "A"
    And I click on value "2"
    And I click on value "3"
    And I click on value "4"
    And I click on value "5"
    And I click on value "6"
    And I click on value "7"
    And I click on value "8"
    And I click on value "9"
    And I click on value "10"
    And I click on value "J"
    And I click on value "Q"
    And I click on value "K"
    Then I should see an error message in the config page
    And I should see value "K"

  @UserStory100
  Scenario: Prevent no card suits
    Given I have gone to the new activity page
    When I click on suit "Spades"
    And I click on suit "Hearts"
    And I click on suit "Clubs"
    And I click on suit "Diamonds"
    Then I should see an error message in the config page
    And I should see suit "Diamonds"