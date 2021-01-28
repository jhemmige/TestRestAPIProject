import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class SNSapi {

	@Test
	public void SNSapipractice() {
		
		RestAssured.baseURI="https://api.ssactivewear.com/";
		
String res=		given().auth().basic("146411", "45d32a01-f35e-4952-86cf-4ce56aebdb68").log().all().when().get("v2/categories").then().assertThat().log().all()
		.statusCode(200).extract().response().asString();
		
		
		JsonPath js = new JsonPath (res);
ArrayList<String> cat=		js.get("categoryID");

System.out.println(cat);
		
	}
}
