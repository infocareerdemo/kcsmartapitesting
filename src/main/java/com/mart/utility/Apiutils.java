package com.mart.utility;

import java.io.File;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Apiutils {
	
	protected Response getOTPByEmployeCode(String basePath, Map<String, Object> employee ) {
		
		return RestAssured.given()
				.contentType(ContentType.JSON)
				.body(employee)
				.log().all()
				.when()
				.post(basePath)
				.then()
				.log().all()
				.extract()
				.response();
	}
	
	
	protected Response loginRequest(String basePath, Map<String, Object> loginCredentials) {
		  
		  return RestAssured.given()
				  .contentType(ContentType.JSON)
				  .body(loginCredentials)
				  .when()
				  .post(basePath)
				  .then()		  
				  .extract()
				  .response();
	  }
	
	protected Response addProduct(String basePath, String token, 
	        File productImage, Map<String, Object> formParams) {
	    
	    return RestAssured.given()
	            .header("Authorization", token)               
	            .multiPart("productImg", productImage)         
	            .formParams(formParams)                       
	            .log().all()                                  
	            .when()
	            .post(basePath)                                
	            .then()
	            .log().all()                                   
	            .extract()
	            .response();                                  
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
