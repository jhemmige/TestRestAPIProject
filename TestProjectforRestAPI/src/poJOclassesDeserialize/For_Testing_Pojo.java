/*This class is same as testing.java under default package. THis class was created specifically to cater to POJO class. 66. End to end Automation examples using
POJO Deserialization*/

package poJOclassesDeserialize;

import org.openqa.selenium.By;
import static io.restassured.RestAssured.*;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import ResourceFile.Payload;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class For_Testing_Pojo {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "/Users/jayashreehemmige/Downloads/chromedriver");

		String CurrentURL = "https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&"
				+ "code=4%2F0AY0e-g6ttSa02_P2Pskv1B_-ChJ6_EbQyW5mzxtrpZ9tQR8w7OSpxIpJYLVna_SZm5-CYQ"
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

		/*
		 * String actualResponse= given().log().all().queryParam("access_token",
		 * xchangeToken).when().get("https://rahulshettyacademy.com/getCourse.php")
		 * .then().log().all().extract().response().asString();
		 */ /**
			 * In testing.java, this line would give the response listing out the courses,
			 * prices etc. Since we are doing the deseriablization script is slightly
			 * changed
			 */

		System.out.println("from testing pojo");

		GetObjects go = given().log().all().queryParam("access_token", xchangeToken).expect().defaultParser(Parser.JSON)
				.when().get("https://rahulshettyacademy.com/getCourse.php").then().log().all().extract().response()
				.as(GetObjects.class); /**
										 * instead of as string, its changed to GetObjects.class and the response will
										 * be stored into a class obj. Hence the response is takeninto GetObjects go .
										 * Also the header has a content-type as Text/html. That needs to changed to
										 * JSON response. He we have included default parser with the additional steps
										 * expect.defaultparser(Parser.Json). If this is not added, we will end in an
										 * error that says response cannot be parsed.
										 */

		System.out.println(go.getLinkedIn());
		System.out.println(go.getInstructor());
		
		//This is the direct method to obtain the courseTitle using the index at which the course title is present.Sometimes the index may be truly represent the 
		//the course title we are looking for. So, we use a simple for loop. 
		String courseTitle = go.getCourses().getApi().get(1).getCourseTitle();
		String coursePrice = go.getCourses().getApi().get(1).getPrice();
//System.out.println(courseTitle);
//System.out.println(coursePrice);

	List<Api> courseT = go.getCourses().getApi();
		int courstTSize = courseT.size();

		for (int i = 0; i < courstTSize; i++) {

			String courseTitle1 = courseT.get(i).getCourseTitle();
			System.out.println("the course we are looking for  is :" + courseTitle1);

			if (courseTitle1.contains("SoapUI Webservices testing")) {

				String coursePrice1 = courseT.get(i).getPrice();
				System.out.println("course price of soapUI testing is : "+ coursePrice1);

				break;

			}

		}

	}

}

/** this is the response obtained */

//https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss
//4%2F0AY0e-g6HboQrkeGQC32Nr5MBOeE0FigJehlInJsbNPuGjYwfB2JB1jUQpYY0baZCoWs1RA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=4&prompt=none#
//4%2F0AY0e-g6HboQrkeGQC32Nr5MBOeE0FigJehlInJsbNPuGjYwfB2JB1jUQpYY0baZCoWs1RA
//email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=4&prompt=none#
//this is the Code4%2F0AY0e-g6HboQrkeGQC32Nr5MBOeE0FigJehlInJsbNPuGjYwfB2JB1jUQpYY0baZCoWs1RA
//SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
//SLF4J: Defaulting to no-operation (NOP) logger implementation
//SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
//Request method:	POST
//Request URI:	https://www.googleapis.com/oauth2/v4/token?code=4%2F0AY0e-g6HboQrkeGQC32Nr5MBOeE0FigJehlInJsbNPuGjYwfB2JB1jUQpYY0baZCoWs1RA&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&client_secret=erZOWM9g3UtwNRj340YYaK_W&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss&grant_type=authorization_code
//Proxy:			<none>
//Request params:	<none>
//Query params:	code=4%2F0AY0e-g6HboQrkeGQC32Nr5MBOeE0FigJehlInJsbNPuGjYwfB2JB1jUQpYY0baZCoWs1RA
//				client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com
//				client_secret=erZOWM9g3UtwNRj340YYaK_W
//				redirect_uri=https://rahulshettyacademy.com/getCourse.php
//				state=verifyfjdss
//				grant_type=authorization_code
//Form params:	<none>
//Path params:	<none>
//Headers:		Accept=*/*
//				Content-Type=application/x-www-form-urlencoded; charset=ISO-8859-1
//Cookies:		<none>
//Multiparts:		<none>
//Body:			<none>
//HTTP/1.1 200 OK
//Pragma: no-cache
//Expires: Mon, 01 Jan 1990 00:00:00 GMT
//Cache-Control: no-cache, no-store, max-age=0, must-revalidate
//Date: Wed, 06 Jan 2021 08:26:40 GMT
//Content-Type: application/json; charset=utf-8
//Vary: Origin
//Vary: X-Origin
//Vary: Referer
//Content-Encoding: gzip
//Server: scaffolding on HTTPServer2
//X-XSS-Protection: 0
//X-Frame-Options: SAMEORIGIN
//X-Content-Type-Options: nosniff
//Alt-Svc: h3-29=":443"; ma=2592000,h3-T051=":443"; ma=2592000,h3-Q050=":443"; ma=2592000,h3-Q046=":443"; ma=2592000,h3-Q043=":443"; ma=2592000,quic=":443"; ma=2592000; v="46,43"
//Transfer-Encoding: chunked
//
//{
//    "access_token": "ya29.a0AfH6SMBddLRyqwHDKZAP9ozbCU7IwcyBI2A9GjHqwtVjiRC88O3UGt-AyDQjLJ_uiZTA8xz-Ct9xTqewAeHIsPWcvSPtQ_y_Vphd3dDObYMyKXMG90L74dhB0OEFyEHrNNV5iBDozpLaH3jvXXH8m7LVBiDuduf4z5uuPxYEa9Nb",
//    "expires_in": 3529,
//    "scope": "openid https://www.googleapis.com/auth/userinfo.email",
//    "token_type": "Bearer",
//    "id_token": "eyJhbGciOiJSUzI1NiIsImtpZCI6IjI2MTI5YmE1NDNjNTZlOWZiZDUzZGZkY2I3Nzg5ZjhiZjhmMWExYTEiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI2OTIxODMxMDMxMDctcDBtN2VudDJoazdzdWd1djR2cTIyaGpjZmhjcjQzcGouYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI2OTIxODMxMDMxMDctcDBtN2VudDJoazdzdWd1djR2cTIyaGpjZmhjcjQzcGouYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMDQ4MjE0MzY0NDY4NjA2MDQ0MjEiLCJlbWFpbCI6InRlc3RjbGllbnQyMTIxQGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhdF9oYXNoIjoidm9UYlRORmQ1UE4tMVUxSDEzM0UzZyIsImlhdCI6MTYwOTkyMTYwMCwiZXhwIjoxNjA5OTI1MjAwfQ.T_8aqvaRHxfOyNlWzQOpn79pliQerNeCTf9K0GwfLqGW5fBkGylOyBedXYtDQXsah3GcazRcpMWwsZF69Hn6_SLdH2i4Mo827mmve-7mkX6exFGLv90b_BnFoZIMhgXhSoxrJ4Z9fuF1FR2oLyK7sShlBQ11qD1_p4it7CVBg4jKryhMiT6GGwoRzAUqd8cl9gqcKYxdmutesM561VYDo5TqlDL9YSu56LKkhV-V6s0OfY37gsGgZbk6BRJ4RLgDZdFKCO55OmkkB29iPL23SKCUa6Ya9imTaB7sSaxhQEgfocGH8lY0yb4T3XSj_F9KEa6GKxBiPYf-8-mlqqRG6A"
//}
//ya29.a0AfH6SMBddLRyqwHDKZAP9ozbCU7IwcyBI2A9GjHqwtVjiRC88O3UGt-AyDQjLJ_uiZTA8xz-Ct9xTqewAeHIsPWcvSPtQ_y_Vphd3dDObYMyKXMG90L74dhB0OEFyEHrNNV5iBDozpLaH3jvXXH8m7LVBiDuduf4z5uuPxYEa9Nb
//from testing pojo
//Request method:	GET
//Request URI:	https://rahulshettyacademy.com/getCourse.php?access_token=ya29.a0AfH6SMBddLRyqwHDKZAP9ozbCU7IwcyBI2A9GjHqwtVjiRC88O3UGt-AyDQjLJ_uiZTA8xz-Ct9xTqewAeHIsPWcvSPtQ_y_Vphd3dDObYMyKXMG90L74dhB0OEFyEHrNNV5iBDozpLaH3jvXXH8m7LVBiDuduf4z5uuPxYEa9Nb
//Proxy:			<none>
//Request params:	<none>
//Query params:	access_token=ya29.a0AfH6SMBddLRyqwHDKZAP9ozbCU7IwcyBI2A9GjHqwtVjiRC88O3UGt-AyDQjLJ_uiZTA8xz-Ct9xTqewAeHIsPWcvSPtQ_y_Vphd3dDObYMyKXMG90L74dhB0OEFyEHrNNV5iBDozpLaH3jvXXH8m7LVBiDuduf4z5uuPxYEa9Nb
//Form params:	<none>
//Path params:	<none>
//Headers:		Accept=*/*
//Cookies:		<none>
//Multiparts:		<none>
//Body:			<none>
//HTTP/1.1 200 OK
//Date: Wed, 06 Jan 2021 08:26:42 GMT
//Server: Apache/2.4.18 (Ubuntu)
//Vary: Accept-Encoding
//Content-Encoding: gzip
//Content-Length: 331
//Keep-Alive: timeout=5, max=100
//Connection: Keep-Alive
//Content-Type: text/html; charset=UTF-8
//
//<html>
//  <body>
//{ "instructor": "RahulShetty", "url": "rahulshettycademy.com", "services": "projectSupport", "expertise": "Automation", "courses": { "webAutomation": [ { "courseTitle": "Selenium Webdriver Java", "price": "50" }, { "courseTitle": "Cypress",
//                "price": "40"
//            },
//            {
//                "courseTitle": "Protractor",
//                "price": "40"
//            }
//        ],
//        "api": [
//            {
//                "courseTitle": "Rest Assured Automation using Java",
//                "price": "50"
//            },
//            {
//                "courseTitle": "SoapUI Webservices testing",
//                "price": "40"
//            }
//        ],
//        "mobile": [
//            {
//                "courseTitle": "Appium-Mobile Automation using Java",
//                "price": "50"
//            }
//        ]
//    },
//    "linkedIn": "https://www.linkedin.com/in/rahul-shetty-trainer/"
//}
//</body>
//</html>
//https://www.linkedin.com/in/rahul-shetty-trainer/
//RahulShetty
