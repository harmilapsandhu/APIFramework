Feature: Validate the Place API feature

@AddPlace @Regression
Scenario Outline: Verify the AddPlace functionality

Given Add Place PayLoad with "<Name>" "<Address>" and "<Language>"
When user calls "addPlaceAPI" with "POST" http request
Then API call succeeds with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And Verify place_id created maps to "<Name>" using "getPlaceAPI"

Examples:
|Name			|Address				|Language		|
|Magahales		|10 Park Lawn Road		|French			|
#|Partey			|Ghana					|Ghanian		|

@DeletePlace @Regression
Scenario: Verify the DeletePlace functionality

Given deletePlace PayLoad
When user calls "deletePlaceAPI" with "POST" http request
Then API call succeeds with status code 200
And "status" in response body is "OK"