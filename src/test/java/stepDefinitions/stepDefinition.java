package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import java.io.IOException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.EnumAPI;
import resources.Specs;
import resources.TestData;

public class stepDefinition extends Specs {

	TestData td = new TestData();
	Specs speci = new Specs(); //We can also inherit the Specs class in which case we won't need to make an object for that class
	RequestSpecification reqSpec;
	//ResponseSpecification responseSpec;
	Response response;
	static String place_id;
	
	@Given("Add Place PayLoad with {string} {string} and {string}")
	public void add_place_pay_load_with_and(String Name, String Address, String Language) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		
		reqSpec = given().spec(speci.getRequestSpecification()).body(td.addPlacePayLoad(Name, Address, Language)); 
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String APIName, String method) {
	    // Write code here that turns the phrase above into concrete actions
		EnumAPI APIvalue = EnumAPI.valueOf(APIName);
		if (method.equalsIgnoreCase("POST"))
			response = reqSpec.when().post(APIvalue.getAPIName())
				.then().spec(speci.getResponseSpecification()).extract().response(); //this line will print response in console
		else if (method.equalsIgnoreCase("GET"))
			response = reqSpec.when().get(APIvalue.getAPIName())
				.then().spec(speci.getResponseSpecification()).extract().response();
	}
	@Then("API call succeeds with status code {int}")
	public void api_call_succeeds_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 200); //manually import static package for this
	}
	@And("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
	    // Write code here that turns the phrase above into concrete actions
	assertEquals(getJsonPath(response, key), value); //Needs to inherit class Specs for getJsonPath to work
	}
	@And("Verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String APIName) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		place_id = getJsonPath(response, "place_id");
		reqSpec = given().spec(speci.getRequestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(APIName, "GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);
	}
	@Given("deletePlace PayLoad")
	public void delete_place_pay_load() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	    reqSpec = given().spec(getRequestSpecification()).body(td.deletePlacePayLoad(place_id));
	}

}
