package com.qa.api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import com.qa.api.payload.UserPOJO;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

//create for CRUD operations to the User API

public class UserEndPoints2 {

	public static Response createUser(UserPOJO payload) {

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)

				.when()
				.post(Routes.post_url);

		return response;

	}

	public static Response readUser(String userName) {

		Response response = given()
				.pathParam("username", userName)

				.when()
				.get(Routes.get_url);

		return response;

	}
	
	public static Response updateUser(String userName, UserPOJO payload) {

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)

				.when()
				.put(Routes.update_url);

		return response;

	}
	
	public static Response deleteUser(String userName) {

		Response response = given().pathParam("username", userName)

				.when()
				.delete(Routes.delete_url);

		return response;

	}
	

}
