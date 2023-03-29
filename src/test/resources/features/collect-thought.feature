Feature: Capture Stage

  Scenario: Collect Thought
    When Thought "Send Birthday Wishes to Mike" is collected
    Then Inbox contains "Send Birthday Wishes to Mike"