Feature: If a sensor value violates a set configuration, send command to device
  As a user, I want an automatic command to be issued on a previously set condition

  Scenario: User receives the automatic command
    Given there is a configuration
    When the sensor value violates a condition
    Then an automatic command is sent