package Day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
public class WaysToCreatePostRequestBody {
	//Using HashMap
	String  id;
	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@Test
	void testPostUsingHashMap() {
		HashMap data = new HashMap();
		data.put("name", "Apoorva");
		data.put("location", "France");
		data.put("number", "9876567865");
		String courseArr[]= {"java","Js"};
		data.put("course", courseArr);
		id = given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("http://localhost:3000/users")
			.jsonPath().getString("id");
	}
	
	//using JSON LIBRARY
	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@Test
	void testPostUsingjsonLibrary() {
		JSONObject data = new JSONObject();
		data.put("name", "Ashish");
		data.put("location", "Pune");
		data.put("number", "9876567865");
		String courseArr[]= {"java","Js"};
		data.put("course", courseArr); 
		
		id = given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("http://localhost:3000/users")
			.jsonPath().getString("id ");
//		.then()
//			.statusCode(201)
//			.log().all();
	}
	
	//@Test
	void testPostUsingPOJO() {
		POJO_PostRequest data = new POJO_PostRequest();
		
		data.setName("Asha");
		data.setLocation("Pune,Maharashtra");
		data.setPhone("908876567865");
		String[] course = {"English"," Maths"};
		data.setCourse(course);
		
		id = given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("http://localhost:3000/users")
			.jsonPath().getString("id ");
//		.then()
//			.statusCode(201)
//			.log().all();
	}
	@Test
	void testPostUsingExternalJSON() throws FileNotFoundException {
		File f = new File(".//body.json");
		
		FileReader fr = new FileReader(f);
		
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject data = new JSONObject(jt);
		
		id = given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("http://localhost:3000/users")
			.jsonPath().getString("id ");
//		.then()
//			.statusCode(201)
//			.log().all();
	}
	@Test
	void testDelete() {
		given()
		.when()
			.delete("http://localhost:3000/users/"+id)
		.then();
	}
}
		