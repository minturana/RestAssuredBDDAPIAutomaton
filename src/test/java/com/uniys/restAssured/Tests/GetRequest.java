package com.uniys.restAssured.Tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetRequest {
	
	@Test
	public void getWeatheDetails() {
		given()
		.when().get("http://demoqa.com/utilities/weather/city/Hyderabad")
		.then().statusCode(200)
		.statusLine("HTTP/1.1 200 OK")
		.assertThat().body("City", equalTo("Hyderabad"))
		.header("Content-Type", "application/json; charset=utf-8");
		
	}

}
