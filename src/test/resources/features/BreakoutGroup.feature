Feature: Breakout Groups

  @UserStory300
  Scenario: Hide config when no selection
    When I have gone to the breakout group config
    Then I cannot see the config parameters

  @UserStory300
  Scenario: Choose to make groups with number of users
    Given I have gone to the breakout group config
    When I choose to make groups for number of users
    Then I can see the user config parameters

  @UserStory300
  Scenario: Choose to make groups by number of groups
    Given I have gone to the breakout group config
    When I choose to make groups by number of groups
    Then I can see the group config parameters

  @UserStory300
  Scenario: Specify number of users
    Given I have gone to the breakout group config
    And I choose to make groups for number of users
    When I type 5 into the user input
    Then The user input has value 5

  @UserStory300
  Scenario: Specify number of groups
    Given I have gone to the breakout group config
    And I choose to make groups by number of groups
    When I type 5 into the group input
    Then the group input has value 5

  @UserStory300
  Scenario: Set random allocation
    Given I have gone to the breakout group config
    And I choose to make groups for number of users
    When I click the random user allocation option
    Then Random allocation format should be selected

  @UserStory300
  Scenario: Set user choice
    Given I have gone to the breakout group config
    And I choose to make groups for number of users
    When I click the user choice allocation option
    Then User choice format should be selected

  @UserStory300
  Scenario: Error for no number of users
    Given I have gone to the breakout group config
    And I choose to make groups for number of users
    When I click to make groups
    Then I can see an error message for "Must specify number of users"

  @UserStory300
  Scenario: Error for no number of groups
    Given I have gone to the breakout group config
    And I choose to make groups by number of groups
    When I click to make groups
    Then I can see an error message for "Must specify number of groups"

  @UserStory300
  Scenario: Error for no allocation format
    Given I have gone to the breakout group config
    And I choose to make groups by number of groups
    And I type 5 into the group input
    When I click to make groups
    Then I can see an error message for "Must choose allocation format"

  @UserStory300
  Scenario Outline: View empty groups
    Given I have gone to the breakout group config
    And I choose to make groups for number of users
    And I add <usersInMeeting> users to the meeting
    And I type <groupUsers> into the user input
    And I click the user choice allocation option
    When I click to make groups
    Then I can see <numGroups> groups to choose from

    Examples:
      | usersInMeeting | groupUsers | numGroups |
      | 10             | 1          | 10        |
      | 10             | 3          | 4         |
      | 10             | 10         | 1         |
      | 10             | 11         | 1         |

  @UserStory300
  Scenario Outline: View user allocation to breakout groups
    Given I have gone to the breakout group config
    And I choose to make groups by number of groups
    And I add <usersInMeeting> users to the meeting
    And I type <numGroupsInput> into the group input
    And I click the random user allocation option
    When I click to make groups
    Then Each group has at least <minGroupUsers> users
    And Each group has at most <maxGroupUsers> users

    Examples:
      | usersInMeeting | numGroupsInput | minGroupUsers | maxGroupUsers |
      | 10             | 1              | 10            | 10            |
      | 10             | 5              | 2             | 2             |
      | 10             | 10             | 1             | 1             |

  @UserStory300
  Scenario: Join breakout group
    Given I have gone to the breakout group config
    And I choose to make groups by number of groups
    And I add 1 users to the meeting
    And I type 1 into the group input
    And I click the user choice allocation option
    And I click to make groups
    When I click to join group 0
    Then I should see 1 user video stream

  @UserStory300
  Scenario: Error when breakout group is full
    Given I have gone to the breakout group config
    And I choose to make groups for number of users
    And I add 10 users to the meeting
    And I type 5 into the user input
    And I click the random user allocation option
    And I click to make groups
    When I click to join group 0
    Then I can see an error message for "Breakout group is full"

  @UserStory300
  Scenario: Disband breakout group
    Given I have gone to the breakout group config
    And I choose to make groups by number of groups
    And I add 10 users to the meeting
    And I type 2 into the group input
    And I click the user choice allocation option
    And I click to make groups
    And I click to join group 0
    When I click to disband the group
    Then I can see 1 groups to choose from