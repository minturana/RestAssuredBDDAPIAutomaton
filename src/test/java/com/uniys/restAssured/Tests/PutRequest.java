package com.uniys.restAssured.Tests;

import java.util.HashMap;


import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//https://dummy.restapiexample.com/public/api/v1/update/21

public class PutRequest {
	
	public static HashMap hashMap=new HashMap();
	
	@BeforeClass
	public void putData() {
		hashMap.put("name", "VikasTest");
		hashMap.put("salary", "10000");
		hashMap.put("age", "32");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		RestAssured.basePath="/update/10091";
	
	}
	
	@Test
	public void testPut() {
		given()
		.contentType("application/json")
		.body(hashMap)
	   .when()
	     .put()
	     .then().statusCode(200)
	     .log().all();
		
		
	}

}
