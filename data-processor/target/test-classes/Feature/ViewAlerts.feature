Feature: Users can view alerts in the WebUI
    As a user I want to be able to view the alerts of the last minute in the WebUI

    Scenario: User access alerts
        Given the kitchen luminosity sensor is configured to detect when the room is darker then a threshold parameter
        When the alert is consumed by the system
        Then the alert should be available in the /alerts endpoint with the description that the kitchen is too dark
