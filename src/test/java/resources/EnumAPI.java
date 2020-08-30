package resources;

//Enum is a special class in Java which has collection of constants or methods
public enum EnumAPI {

	addPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");

	private String APIName;
	
	EnumAPI(String APIName) {
		// TODO Auto-generated constructor stub
		this.APIName = APIName;
	}
	
	public String getAPIName()
	{
		return APIName;
	}
}
