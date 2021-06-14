Feature: Users can view the data related to the sensors in the WebUI
    As a user I want to be able to view the historic of measurements of the kitchen's sensors in the WebUI

    Scenario: User access kitchen sensors measures without having already sensor ids
        Given the users does not have the sensor id that they need
        When the user requests sensors data
        Then the list with the measurements from all the sensors is returned
        And the api returns a success status 200

    Scenario: User access sensors measures having already sensor ids
            Given the users have the sensor id that they want
            When the user requests kitchen sensors data
            Then the list with the measurements from the sensor specified is returned
            And the api returns success status 200
