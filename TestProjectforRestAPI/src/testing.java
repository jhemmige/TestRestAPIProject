//This was created since Oauth failed to initialize for some reason.

import org.openqa.selenium.By;
import static io.restassured.RestAssured.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.path.json.JsonPath;

public class testing {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","/Users/jayashreehemmige/Downloads/chromedriver");
		//WebDriver driver =new ChromeDriver();
	
		/** As of 2020, google has prevented automation of  oauth. Hence, we have the manually input the URL that gets the code to continue
		 * automation. */
//		driver.get("https://finance.yahoo.com/");
//		driver.close();
		
//		driver.get("https://accounts.google.com/o/oauth2/v2/auth?auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss&scope=https://www.googleapis.com/auth/userinfo.email");
//		driver.findElement(By.xpath(" //input[@id='identifierId']")).sendKeys("testclient2121@gmail.com");
//		driver.findElement(By.xpath("//button[@type='button' and span='Next']")).click();	
//		driver.findElement(By.xpath("//*[@type='password']")).sendKeys("jhemmige123");
//		driver.findElement(By.xpath("//*[@class='VfPpkd-RLmnJb']")).click();
//		Thread.sleep(3000);
//		String CurrentURL=driver.getCurrentUrl();
//		System.out.println(CurrentURL);
		
//driver.get
//("https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2F0AY0e-g5Xh3dRgXr8PWHLTx5ouuEF-Npso6LsGqlkmAn2-h7j6uYLVSw2PQY7YtPW_-7rXw&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser="
//		+ "4&prompt=none#");

String CurrentURL = 
"https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2F0AY0e-g7smeZbpC4vQOzY4MaA2h4UUD2I6yJjxAqBuMBxPTgb7EWvEC9V3DW2aBjfX3tmGg&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=4&prompt=none#";
String[] partialURL= CurrentURL.split("&code=")	;
System.out.println(partialURL[0]);
System.out.println(partialURL[1]);

String[] ActualCode= partialURL[1].split("&scope=");
System.out.println(ActualCode[0]);
System.out.println(ActualCode[1]);
String AcTcode=ActualCode[0];
System.out.println("this is the Code"+ AcTcode);

		
//	String xchangeCodeAccesstoken=	given().urlEncodingEnabled(false).log().all().
//			queryParams("code", AcTcode)
//				.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
//				.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
//				.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
//				.queryParams("state", "verifyfjdss")
//				//. queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
//				.queryParams("grant_type", "authorization_code").when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
//	System.out.println(xchangeCodeAccesstoken);
	
//url encodingenabled is used cos by default, java encodes special characters, hence it is passed as false.
	String xchangeCodeAccesstoken=	given().urlEncodingEnabled(false).log().all().
			queryParams("code", AcTcode)
				.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParams("state", "verifyfjdss")
				//. queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
				.queryParams("grant_type", "authorization_code").when().post("https://www.googleapis.com/oauth2/v4/token").then().log().all().extract().response().asString();
	
	JsonPath jsp= new JsonPath(xchangeCodeAccesstoken);
String xchangeToken=	jsp.get("access_token");
	System.out.println(xchangeToken);
				
	String actualResponse=		given().log().all().queryParam("access_token", xchangeToken).when().get("https://rahulshettyacademy.com/getCourse.php")
	.then().log().all().extract().response().asString();
	
	
	
	}

}
