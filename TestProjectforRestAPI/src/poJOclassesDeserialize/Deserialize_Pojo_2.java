/*this class was created to  practice  defaultpackage. complexJsonParse*/

package poJOclassesDeserialize;

import org.openqa.selenium.By;
import static io.restassured.RestAssured.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import ResourceFile.Payload;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class Deserialize_Pojo_2 {
	
	
		public static void main(String[] args) throws InterruptedException {
			
			
	JsonPath jp = new JsonPath(Payload.payLoadFile());
	
	int courseSize = jp.get("courses.size()");
	
	System.out.println(courseSize);
	
	int size= jp.get("courses.webAutomation.size()");
	
	for (int i=0; i<size; i++) {
		
		String courseTitle= jp.get("courses.webAutomation ["+ i +"].courseTitle");
		String pRice= jp.get("courses.webAutomation ["+ i +"].price");
		
		System.out.println(courseTitle);
		System.out.println(pRice);
		
		
	}
	
	
		
		}
		
		

	}

