package com.qa.api.tests;


import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.qa.api.endpoints.UserEndPoints;
import com.qa.api.payload.UserPOJO;

import groovyjarjarantlr4.v4.runtime.misc.LogManager;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	UserPOJO userPayload;
	public  Logger logger;

	@BeforeClass
	public void setup() {
		faker = new Faker();
		userPayload = new UserPOJO();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		//logger=LogManager.getLogger(this.getClass());

	}

	@Test(priority = 1)
	public void testPostUser() {

		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 2)
	public void testGetUserByName() {

		Response response = UserEndPoints.readUser(this.userPayload.getUsername());

		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);

	}

	@Test(priority = 3)
	public void testUpdateUserByName() {

		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());

		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);

		response.then().log().body();
		Assert.assertEquals(response.statusCode(), 200);
		
		//checking the data after update
		Response responseafterUpdate = UserEndPoints.createUser(userPayload);
		response.then().log().all();

	}
	
	@Test(priority = 4)
	public void testDeleteuserByName() {
		
	Response response =	UserEndPoints.deleteUser(this.userPayload.getUsername());
	Assert.assertEquals(response.statusCode(), 200);
		
		
		
	}

}
