package com.uniys.restAssured.Tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

// https://www.youtube.com/watch?v=x4BKSAKZ-Xg&list=PLUDwpEzHYYLskkglxoXd0L6DKu4uOfh-m&index=4
public class PostRequest {
	
	public static HashMap hashMap=new HashMap();
	
	@BeforeClass
	public void postData() {
		hashMap.put("FirstName","VIkas");
		hashMap.put("LastName","VIkas");
		hashMap.put("UserName","VIkas");
		hashMap.put("Password","VIkas");
		hashMap.put("Email","VIkas");
		
		RestAssured.baseURI="http://restapi.demoqa.com/customer";
		RestAssured.basePath="/register";
	}
	
	@Test
	public void testPost() {
	given()
		.contentType("application/json")
		.body(hashMap)
		
	.when()
		.post()
		
	.then()
		.statusCode(201)
		.and()
		.body("SucessCode", equalTo("OPERATION SUCCESS"))
		.and()
		.body("Message", equalTo("Operation Completed Sucessfully"));
		
		
	}

}
