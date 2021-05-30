package com.swagger.restAssured.ApiBackground;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiPlayground_GetAllProducts {
	
	
	
	@Test(priority=2)
	public void apiPlayBackgroup_GetAllServices() {
		given()
		.when().get("http://localhost:3030/services")
		.then().statusCode(200)
		.assertThat().body("total", equalTo(21))
		.assertThat().body("limit", equalTo(10))
		.header("Content-Type", "application/json; charset=utf-8");
		
	}
	
	@Test(priority=3)
	public void apiPlayBackgroup_GetAllStores() {
		given()
		.when().get("http://localhost:3030/stores")
		.then().statusCode(200)
		.assertThat().body("total", equalTo(1561))
		.assertThat().body("limit", equalTo(10))
		.header("Content-Type", "application/json; charset=utf-8")
		.header("allow", "GET,POST,PUT,PATCH,DELETE")
		.header("connection", "keep-alive");
		
	}
	
	@Test(priority=4)
	public void apiPlayBackgroup_GetAllCategories() {
		given()
		.when().get("http://localhost:3030/categories")
		.then().statusCode(200)
		.assertThat().body("total", equalTo(4307))
		.assertThat().body("limit", equalTo(10))
		.header("Content-Type", "application/json; charset=utf-8")
		.header("allow", "GET,POST,PUT,PATCH,DELETE");
		
	}
	
	@Test(priority=5)
	public void apiPlayBackgroup_GetAllUtilities() {
		given()
		.when().get("http://localhost:3030/version")
		.then().statusCode(200)
		.assertThat().body("version", equalTo("1.1.0"))
		.header("Content-Type", "application/json; charset=utf-8");
		
	}

	
	@Test(priority=5)
	public void apiPlayBackgroup_GetProductByID() {
		 given()
		.when().get("http://localhost:3030/products/9999689")
		.then().statusCode(200)
		.assertThat().body("id", equalTo(9999689))
		.assertThat().body("name", equalTo("Newroductt"))
		.assertThat().body("type", equalTo("GoodFood"))
		.assertThat().body("description", equalTo("This is a placeholder request for creating a new product."))
		.assertThat().body("model", equalTo("NP12345"))
		.header("Content-Type", "application/json; charset=utf-8");
		
	}

}
