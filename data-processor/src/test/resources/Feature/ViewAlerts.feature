Feature: Users can view alerts in the WebUI
    As a user I want to be able to view the alerts of the last minute in the WebUI

    Scenario: User access alerts
        Given the alarm is generated
        When the alert is consumed
        Then the alert should be available in the /alerts endpoint