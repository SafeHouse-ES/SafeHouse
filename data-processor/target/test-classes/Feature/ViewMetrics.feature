Feature: Users can view the metrics available in the WebUI
    As a user I want to be able to view the metrics that i can access in each sensors in the WebUI

    Scenario: User access metrics
        When users want to get the data about the metrics
        Then the list with the three metrics keys, temperature, luminosity, movement
