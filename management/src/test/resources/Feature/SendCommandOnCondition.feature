Feature: If a sensor value violates a set configuration, send command to device
  As a user, I want an automatic command to be issued on a previously set condition

  Scenario: User receives the automatic command
    Given there is a configuration for when temperature in Room1 is higher than forty
    When temperature in Room1 is higher than forty
    Then an automatic command is sent to lower the temperature in Room1 to 25