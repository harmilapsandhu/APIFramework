package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	//Hooks are used to set pre and post conditions. e.g. DeletePlace Scenario will fail in the feature file if we run it stand alone as it will have 
	//null as it's place ID. So in that case we can define a Hook with @Before which will run prior to deletion and we can insert our place ID code.
	//Also make sure that this code is executed only when place_id = null.
	@Before ("@DeletePlace")
	public void getPlaceID() throws IOException
	{
		//We will use methods already defined in Step Definition package
		stepDefinition sd = new stepDefinition();
		if (stepDefinition.place_id==null) //because place_id is static, we can call it using class name, using object sd will give a warning
		{
		sd.add_place_pay_load_with_and("Messi", "Barcelona", "Spanish"); //Pass any random names as String values
		sd.user_calls_with_http_request("addPlaceAPI", "POST");
		sd.verify_place_id_created_maps_to_using("Messi", "getPlaceAPI");
		}
	}
}
