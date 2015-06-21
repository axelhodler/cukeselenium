Feature: Todo

  Scenario: can manage todos
    Given i have added learn cucumber to the list
    When i mark the todo deletable
    Then i can clear the todo
