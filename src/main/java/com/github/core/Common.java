package com.github.core;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;

import com.framework.util.Utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Common extends Utilities {

//  ####  Get request   ####
	public Response getRequest(String url, String token) {
		System.out.println("####  GET Request   ####");
		Response response = null;
		try {
			if (token == null) {
				response = given().when().get(url);
			} else {
				response = given().header("Authorization", "token " + token).when().get(url);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

//  ####  Post request with parsing json data inside body   ####
	public Response postRequestWithJsonData(String url, String token, JSONObject jObj) {
		System.out.println("####  POST Request   ####");
		Response response = given().header("Authorization", "token " + token).contentType(ContentType.JSON)
				.body(jObj.toString()).post(url);
		return response;
	}

//  ####  Patch request with parsing json data inside body   ####
	public Response patchRequestWithJsonData(String url, String token, JSONObject jObj) {
		System.out.println("####  PATCH Request   ####");
		Response response = given().header("Authorization", "token " + token).contentType(ContentType.JSON)
				.body(jObj.toString()).patch(url);
		return response;
	}

//  ####  Delete request with parsing json data inside body   ####
	public Response deleteRequest(String url, String token) {
		System.out.println("####  DELETE Request   ####");
		Response response = given().header("Authorization", "token " + token).delete(url);
		return response;
	}

//  ####  Put request with parsing json data inside body   ####
	public Response putRequestWithJsonData(String url, String token, JSONObject jObj) {
		System.out.println("####  PUT Request   ####");
		Response response = given().header("Authorization", "token " + token).contentType(ContentType.JSON)
				.body(jObj.toString()).put(url);
		return response;
	}

//  ###   Verify Status Code     ####
	public void assertSuccessStatus(Response response, int expectedCode) {
		response.then().assertThat().statusCode(expectedCode);
		System.out.println("Response code : " + expectedCode + " || Status : Verified");
	}

}
