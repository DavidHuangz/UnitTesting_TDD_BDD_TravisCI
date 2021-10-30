Feature: login

  @UserStory000
  Scenario Outline: User login with correct credentials
    Given I visit "/"
    When I enter '<username>' as user name field
    And I enter "123" as password field
    And I press the submit button
    Then I should see the home page

    Examples:
      | username |
      | abc123 |
      | abc123aucklanduni.ac.nz|

  @UserStory000
  Scenario Outline: login with incorrect username
    Given I visit "/"
    When I enter '<username>' as user name field
    And I enter "123" as password field
    And I press the submit button
    Then I should see the username error message

    Examples:
      | username |
      | abc1234  |
      | abc      |
      |          |

  @UserStory000
  Scenario Outline: login with incorrect password
    Given I visit "/"
    When I enter "abc123" as user name field
    And I enter '<password>' as password field
    And I press the submit button
    Then I should see the password error message

    Examples:
      | password |
      | 1234     |
      | 321      |
      |          |

  @UserStory000
  Scenario: User logout
    Given I visit "/"
    When I enter "abc123" as user name field
    And I enter "123" as password field
    And I press the submit button
    And I press the press the logout button
    Then I should be in the login page

  @UserStory000
  Scenario: User reset password
    Given I visit "/"
    When I enter "321" as new password field
    And I press the Reset button
    And I enter "abc123" as user name field
    And I enter "321" as password field
    And I press the submit button
    Then I should see the home page
