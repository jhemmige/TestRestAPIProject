//REference Json.
///Users/jayashreehemmige/Documents/rest_api/POJO/POJO_serialize.json

//what we are doing here is adding a new address POST . Refer to RSA_maps ADD Place on postman.
// we have converted the the body Json into different pojo classes with variables and we have called those classes and variables with getters and setters.

package poJOclassesSerialize;

import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;

public class Serialize_POJO1 {

	public static String pID;

	public static void main(String[] args) {

		// ADD OR POST
		RestAssured.baseURI = "https://rahulshettyacademy.com/";

		SettingBody sb = new SettingBody();
		sb.setAccuracy(50);
		sb.setName("Frontline house");
		sb.setPhone_number("(+91) 983 893 3937");
		sb.setAddress("29, side layout, cohen 09");
		sb.setWebsite("http://google.com");
		sb.setLanguage("French-IN");

		Setlocation sl = new Setlocation();

		sl.setLat(-38.383494);
		sl.setLng(33.427362);
		sb.setLocation(sl);

		// sb.getLocation().setLat(-38.383494);
		// sb.getLocation().setLng(33.427362);
		ArrayList<String> as = new ArrayList<String>();
		as.add("shoe park");
		as.add("shop");
		sb.setTypes(as);

		ExtractableResponse<io.restassured.response.Response> res = given().log().all().queryParam("key", "qaclick123")
				.header("Content-Type", "application/json").body(sb).when().post("maps/api/place/add/json").then()
				.assertThat().statusCode(200).extract();

		String responseString = res.asString();
		System.out.println("this is the response : " + responseString);

		JsonPath Jp = new JsonPath(responseString);
		pID = Jp.get("place_id");

		// PUT OR UPDATE METHOD

		SetUpdatedAddress sa = new SetUpdatedAddress();
		sa.setPlace_id(pID);
		sa.setAddress("70 Summer walk, USA");
		sa.setKey("qaclick123");

		given().log().all().queryParams("key", "qaclick123").queryParams("place_id", pID)
				.header("Content-Type", "application/json").body(sa).when().put("maps/api/place/update/json").then()
				.assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

	}

}
