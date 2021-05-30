package com.swagger.restAssured.ApiBackground;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class ApiPlayground_ProductsApiTests {
	
	int productID;
	
public static HashMap hashMap=new HashMap();
	
	@BeforeClass
	public void postData() {
		hashMap.put("name","addproduct");
		hashMap.put("type","BadFood");
		hashMap.put("upc","890");
		hashMap.put("price",87);
		hashMap.put("description","This is a placeholder request for creating a new product.");
		hashMap.put("model","NP12345");
		
		RestAssured.baseURI="http://localhost:3030";
		RestAssured.basePath="/products";
	}
	
	@Test(priority=1)
	public void apiPlayBackgroup_GetAllProducts() {
		given()
		.when().get("http://localhost:3030/products")
		.then().statusCode(200)
		.assertThat().body("limit", equalTo(10))
		.header("Content-Type", "application/json; charset=utf-8");
		
	}
	@Test(priority=2)
	public void addAProduct() {
		
	Response response=	
	given().contentType("application/json").body(hashMap)
	.when().post()
	.then().statusCode(201)
	.log().all().extract().response();
	
	String jsonString=response.asString();
	System.out.println("Response is "+jsonString);
	Assert.assertEquals(jsonString.contains("addproduct"),true);
	Assert.assertEquals(jsonString.contains("BadFood"),true);
	Assert.assertEquals(jsonString.contains("This is a placeholder request for creating a new product."),true);
	System.out.println("The id value is"+response.jsonPath().get("id"));
	productID=response.jsonPath().get("id");
	
	}
	
	@Test(priority=3)
	public void getProductByID() {
		given().when().get("http://localhost:3030/products/"+productID)
		.then().statusCode(200)
		.and().body("id", equalTo(productID))
		.and().body("name", equalTo("addproduct"))
		.and().body("model", equalTo("NP12345"));
	}
	
	@Test(priority=4)
	public void patchProduct() {
		given().contentType("application/json").body(hashMap)
		.when().patch("http://localhost:3030/products/"+productID)
		.then().statusCode(200)
		.and().body("name", equalTo("addproduct"))
		.and().body("upc", equalTo("890"))
		.and().body("price", equalTo(87));
	}
	

	@Test(priority=5)
	public void findHigestProductPrice() {
		given().when().get("http://localhost:3030/products?$sort[price]=-1")
		.then().statusCode(200).log().all();
	}
	
	@Test(priority=6)
	public void getTheProductsLimitTo1() {
		given().when().get("http://localhost:3030/products?$limit=1")
		.then().statusCode(200).log().all();
	}
	
	@Test(priority=7)
	public void getTheProductsShowOnlyNameAndPrice() {
		given().when().get("http://localhost:3030/products?$select[]=name&$select[]=price")
		.then().statusCode(200).log().all();
	}
	
	@Test(priority=8)
	public void getTheProductsTypeBadFood() {
		given().when().get("http://localhost:3030/products?type=BadFood")
		.then().statusCode(200).log().all();
	}
	
	@Test(priority=9)
	public void deleteProduct() {
		given().when().delete("http://localhost:3030/products/"+productID)
		.then().statusCode(200);
	}
	
	
}
