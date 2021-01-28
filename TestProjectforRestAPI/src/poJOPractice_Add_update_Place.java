//this class has both serialization and deserialization.

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import poJOclassesSerialize.SetUpdatedAddress;
import poJOclassesSerialize.Setlocation;
import poJOclassesSerialize.SettingBody;

public class poJOPractice_Add_update_Place {

	@Test

	public void AddUpdatePlace() {

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

		// THIS IS THE POST METHOD FOR ADDING A NEW ADDRESS. INSTEAD OF PARSING THE JSON
		// RESPONSE OBTAINED USING JSONPATH, WE USE
		// JAVA OBJECTS TO PARSE. SO, WE OBTAINED pID using Place_ID on
		// AddUpdateGetterSetter class.
		// We have also sent Payload body using Java objects of SettingBody.java.
		AddUpdateGetterSetter au = given().log().all().queryParam("key", "qaclick123")
				.header("Content-Type", "application/json").body(sb).expect().defaultParser(Parser.JSON).when()
				.post("maps/api/place/add/json").then().assertThat().log().all().statusCode(200).extract()
				.as(AddUpdateGetterSetter.class);

		String pID = au.getPlace_id();
		System.out.println("This is the: " + pID);
		
		

		// PUT METHOD OR UPDATING THE ADDRESS . We have used the parsed pID from above
		// and also created a separate pojo class SetAddress.java and called
		// java objects from there.
		
		
	
		
		SetUpdatedAddress sa1 = new SetUpdatedAddress();
		sa1.setPlace_id(pID);
		sa1.setAddress("70 Summer walk, USA");
		sa1.setKey("qaclick123");
		
	

		given().log().all().queryParams("key", "qaclick123").queryParams("place_id", pID)
				.header("Content-Type", "application/json").body(sa1).when().put("maps/api/place/update/json").then()
				.assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		

	}

}
