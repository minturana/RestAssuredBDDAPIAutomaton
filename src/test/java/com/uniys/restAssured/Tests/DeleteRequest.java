package com.uniys.restAssured.Tests;

import java.util.HashMap;


import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class DeleteRequest {
	
	@Test
	public void deleteRequest() {
	RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
	RestAssured.basePath="/delete/10092";
	
	Response respone=
	given()
	.when().delete()
	.then()
	      .statusCode(200)
	      .log().all()
	      .extract().response();
	
	 String respnseSt=respone.asString();
	 System.out.println("The response is"+ respone.jsonPath().get("message"));
	 System.out.println("The response is"+respnseSt);
	 Assert.assertEquals(respnseSt.contains("deleted"),true);
	}

}
