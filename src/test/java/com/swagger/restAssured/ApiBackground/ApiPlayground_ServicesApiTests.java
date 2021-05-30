package com.swagger.restAssured.ApiBackground;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiPlayground_ServicesApiTests {
	
	public static HashMap hashMap=new HashMap();
	int service;
	
	@BeforeClass
	public void postData() {
		hashMap.put("name","ApacheTomcatService");
		
		RestAssured.baseURI="http://localhost:3030";
		RestAssured.basePath="/services";
	}
	
	
	@Test(priority=1)
	public void apiPlayBackgroup_GetAllServices() {
		given()
		.when().get("http://localhost:3030/services")
		.then().statusCode(200).log().all()
		.assertThat().body("total", equalTo(22))
		.assertThat().body("limit", equalTo(10))
		.header("Content-Type", "application/json; charset=utf-8");
	}
	

	@Test(priority=2)
	public void apiPlayBackgroup_GetServiceByID() {
		
		Response response= 
		given()
		.when().get("http://localhost:3030/services/1")
		.then().statusCode(200).log().all().extract().response();
		 System.out.println("The id is"+response.jsonPath().get("id"));
		 System.out.println("The name is"+response.jsonPath().get("name"));
         String name=response.jsonPath().get("name").toString();
         Assert.assertTrue(name.contains("Geek Squad Services"));
	
}
	@Test(priority=3)
	public void apiPlayBackgroup_AddService() {
		
		 Response response=
		 given().contentType("application/json").body(hashMap)
		.when().post()
		.then().statusCode(201).log().all().extract().response();
		 
		 System.out.println("The new id is"+response.jsonPath().get("id"));
		 System.out.println("The name is"+response.jsonPath().get("name"));
		 Assert.assertTrue(response.jsonPath().get("name").toString().contains("ApacheTomcatService"));
         service=response.jsonPath().get("id");
	}
	
	
	@Test(priority=4)
	public void apiPlayBackgroup_PatchService() {
		hashMap.put("name","ApacheTomcatService");
		
		given().contentType("application/json").body(hashMap)
	    .when().patch("http://localhost:3030/services/"+service)
	    .then().statusCode(200)
	    .and().body("name", equalTo("ApacheTomcatService"));
	}
		
	
	
	@Test(priority=5)
	public void apiPlayBackgroup_DeleteService() {
		
	      given()
	     .when().delete("http://localhost:3030/services/"+service)
	     .then().statusCode(200)
	     .and().body("id", equalTo(service))
	     .and().body("name",equalTo("ApacheTomcatService"));
		}
		
	

}
