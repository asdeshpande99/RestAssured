package Day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
public class HTTPRequest {
//	@Test(priority =1)
	void getUser() {
		given()
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("page", equalTo(2))
			.log().all();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	void createUser() {
		HashMap data = new HashMap();
		data.put("name", "Apoorva");
		data.put("job", "Software Tester");
		given()
			.header("User-Agent", "Mozilla/5.0") 
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")			
		.then()
			.statusCode(201)
			.log().all();
	}
}
