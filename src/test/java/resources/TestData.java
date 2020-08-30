package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.GooglePOJO;
import pojo.PojoLocation;

public class TestData {

	public GooglePOJO addPlacePayLoad(String Name, String Address, String Language)
	{
		GooglePOJO pojo = new GooglePOJO();
		
		PojoLocation loc = new PojoLocation();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		pojo.setLocation(loc);
		
		pojo.setAccuracy(50);
		pojo.setName(Name);
		pojo.setPhone_number("(+91) 983 893 3937");
		pojo.setAddress(Address);
		
		List<String> arrList = new ArrayList<String>();
		arrList.add("shoe park");
		arrList.add("shop");
		pojo.setTypes(arrList);
		
		pojo.setWebsite("http://google.com");
		pojo.setLanguage(Language);
		
		return pojo;
	}
	
	public String deletePlacePayLoad(String place_id)
	{
		return "{\r\n \"place_id\": \""+place_id+"\"\r\n}";
	}
}
