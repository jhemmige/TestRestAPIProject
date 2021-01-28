/** this script  is built to optimize the code written for serialization and deserialization. Here we are breaking the request 
 * using Request spec builder and the response obtained to a reponse spec builder*/

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import poJOclassesSerialize.Setlocation;
import poJOclassesSerialize.SettingBody;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import org.testng.annotations.Test;
public class RequestResponseSpecBuilder {
@Test
	public void ReqResSpecBuilder() {
	
	//Request Spec Builder
RequestSpecification quer=	new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri("https://rahulshettyacademy.com/")
	.addQueryParam("key", "qaclick123").build();

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


AddUpdateGetterSetter s= given().log().all().spec(quer).body(sb).when().post("maps/api/place/add/json").then().assertThat().log().all().statusCode(200).extract()
.as(AddUpdateGetterSetter.class);

String pID= s.getPlace_id();

System.out.println(pID);


//Response SPec Builder
ResponseSpecification resp=	new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
Response s1= given().log().all().spec(quer).body(sb).when().post("maps/api/place/add/json").then().spec(resp).extract().response();

String prettyString= s1.asPrettyString();
System.out.println("this is the" +prettyString);
JsonPath jp= new JsonPath(prettyString);

String status= jp.get("status");
String scope = jp.get("scope");
System.out.println(status  +  scope );
		

}
}