package bdd.APIFramework;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import resources.ExcelData;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

public class LibraryHashMap {

	@Test
	public void addBook() throws IOException
	{
		//This will load data from an excel sheet and convert it into JSON using HashMap
		
		ExcelData xl = new ExcelData(); 
		ArrayList<String> array = xl.getData("Science", "REST"); //(pass (Row Name, Sheet Name)
		
		HashMap<String, Object> jsonAsMap = new HashMap<>();
		jsonAsMap.put("name", array.get(1));
		jsonAsMap.put("isbn", array.get(2));
		jsonAsMap.put("aisle", array.get(3));
		jsonAsMap.put("author", array.get(4));
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String addB = given().log().all().header("Content-Type", "application/json")
				.body(jsonAsMap)
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = ReusableMethods.rawToJson(addB);
		String id = js.get("ID");
		System.out.println(id);
	}
	
	/*@Test
	public void deleteBook()
	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String delB = given().log().all().header("Content-Type", "application/json")
				.body("{\r\n" +
				"\"ID\":\"skid4212\",\r\n" +
				"\"ID\":\"skju2251\"\r\n" + 
				"}")
		.when().delete("/Library/DeleteBook.php")
		.then().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js = ReusableMethods.rawToJson(delB);
		String mess = js.get("msg");
		System.out.println(mess);
		
	} */
	
	
}
