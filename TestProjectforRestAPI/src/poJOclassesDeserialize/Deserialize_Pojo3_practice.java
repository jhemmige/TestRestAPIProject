/*this class was created for practicing an assignment suggested by RahulShetty. We are getting the courseTitles and price of WebAutomation course.
POJO Deserialization. Concept of arraylist shown here.*/

//Use the screenshot saved here for reference
//**/Users/jayashreehemmige/Documents/rest_api/POJO/Pojo_deserialize.png
//Users/jayashreehemmige/Documents/rest_api/POJO/pojodeserialize1.png */
///Users/jayashreehemmige/Documents/rest_api/POJO/Pojodeserialize.json
package poJOclassesDeserialize;

import org.openqa.selenium.By;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import ResourceFile.Payload;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class Deserialize_Pojo3_practice {

	public static void main(String[] args) throws InterruptedException {

		String[] WebAutocourseTitles = { "Selenium Webdriver Java", "Cypress", "Protractor" };// this array is created
																								// to compare the
																								// expected course title
																								// matches the actual
																								// courselist.

		List<String> expectedcourSeTitle = Arrays.asList(WebAutocourseTitles); // Above array is added to an arraylist

		System.setProperty("webdriver.chrome.driver", "/Users/jayashreehemmige/Downloads/chromedriver");

		String CurrentURL = "https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&"
				+ "code=4%2F0AY0e-g6ZN6FhORfYyATkQ5uF0m57LxSal5b21wOpNuE74gRDz53VVDKTRbw3iXGdxjHR5A"
				+ "&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=4&prompt=none#";
		String[] partialURL = CurrentURL.split("&code=");
		System.out.println(partialURL[0]);
		System.out.println(partialURL[1]);

		String[] ActualCode = partialURL[1].split("&scope=");
		System.out.println(ActualCode[0]);
		System.out.println(ActualCode[1]);
		String AcTcode = ActualCode[0];
		System.out.println("this is the Code" + AcTcode);

		String xchangeCodeAccesstoken = given().urlEncodingEnabled(false).log().all().queryParams("code", AcTcode)
				.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParams("state", "verifyfjdss")
				// . queryParams("session_state",
				// "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
				.queryParams("grant_type", "authorization_code").when()
				.post("https://www.googleapis.com/oauth2/v4/token").then().log().all().extract().response().asString();

		JsonPath jsp = new JsonPath(xchangeCodeAccesstoken);
		String xchangeToken = jsp.get("access_token");
		System.out.println(xchangeToken);

		System.out.println("from testing pojo");

		GetObjects go = given().log().all().queryParam("access_token", xchangeToken).expect().defaultParser(Parser.JSON)
				.when().get("https://rahulshettyacademy.com/getCourse.php").then().log().all().extract().response()
				.as(GetObjects.class);

		System.out.println(go.getLinkedIn());
		System.out.println(go.getInstructor());

		// This is the direct method to obtain the courseTitle using the index at which
		// the course title is present.Sometimes the index may be truly represent the
		// the course title we are looking for. So, we use a simple for loop.
		String courseTitle = go.getCourses().getApi().get(1).getCourseTitle();
		String coursePrice = go.getCourses().getApi().get(1).getPrice();
//System.out.println(courseTitle);
//System.out.println(coursePrice);

		List<WebAutomation> wa = go.getCourses().getWebAutomation();
		int courseSize = wa.size();
		System.out.println(courseSize);
		ArrayList<String> ar = new ArrayList<String>();

		for (int i = 0; i < courseSize; i++) {

			String webAutoCourseTitle = wa.get(i).getCourseTitle();
			String trimmedWACT = webAutoCourseTitle.trim();
			System.out.println(trimmedWACT);

			ar.add(trimmedWACT); // the printed out course title is added to an arrayList. Currently we know
									// there are 3 courses in WebAutomation, but tomorrow, we don't know
			// how many will get added to it, hence we add them to an arraylist
		}

		Assert.assertTrue(ar.equals(expectedcourSeTitle)); //

	}
}