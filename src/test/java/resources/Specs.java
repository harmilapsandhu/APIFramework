package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specs {

	public static RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	
	
	public RequestSpecification getRequestSpecification() throws IOException
	{
		if (requestSpec == null) //Create this if statement to enable multiple data sets in .feature file to be logged in logging.txt file
		{                      //Also put requestSpec as public static otherwise it will be null every time and only the last date set will be logged
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		requestSpec = new RequestSpecBuilder().setBaseUri(getProperties("BaseURI")).log(LogDetail.ALL).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return requestSpec;
		}
		return requestSpec;
	}
	
	public ResponseSpecification getResponseSpecification()
	{
		responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).log(LogDetail.ALL).build();
		return responseSpec;
	}
	
	public static String getProperties(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\Harmilap\\REST Assured\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getJsonPath(Response response, String key)
	{
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}
}
