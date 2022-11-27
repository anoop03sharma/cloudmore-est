package com.cloudmore.api;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cloudmore.utils.ListenerUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class RestAssuredApiActions extends ListenerUtils{
	private static final Logger log = LogManager.getLogger(RestAssuredApiActions.class);
	
	//API calls which require json payloads - POST & PUT calls 
	
	/**
	 * This method will be invoked for POST or PUT calls {i.e  requires a JSON payload as @param }
	 * @param jsonPayload : required to create a 'new user' or 'Update' an existing user.
	 * @param method : "PUT" or "POST"
	 * @param baseURI
	 * @return instance of 'io.restassured.response.Response'
	 * @throws IOException
	 */
	
	public Response invokeAPI(String baseURI, String userName,String jsonPayload, String method) throws IOException {
		String url= baseURI+userName;
		try{
			RestAssured.baseURI =baseURI;
			Response response = null;
			RequestSpecification request = RestAssured.given();
			log.info("URL : "+baseURI+userName);
			test.info("URL : "+baseURI+userName);

			if(method.equalsIgnoreCase("POST")) {
				response= request
						.given()
						.accept(ContentType.JSON)
						.headers("accept","application/json","Content-Type","application/json")
						.body(jsonPayload)
						.when()
						.post(userName);
			}
			if(method.equalsIgnoreCase("PUT")) {

				response= request
						.given()
						.accept(ContentType.JSON)
						.headers("accept","application/json","Content-Type","application/json")
						.body(jsonPayload)
						.when()
						.put(userName);
			}

			// Check API response is not null
			if(response!=null) {
				log.info("\n\n API has returned HTTP Code\n"+response.getStatusLine());	
				test.info("HTTP Code returned by API is : "+response.getStatusLine());	

				return response;
			}
			else 
				return null;

		}
		catch(Exception e) {
			log.info("Call to API Call Failed, An exception has occured" +e);
			test.fail("Call to API Call Failed, An exception has occured" +e);
			e.printStackTrace();
			return null;
		}


	}
	
	
	/**
	 * This method will be invoked for 'GET' or 'DELETE' API calls
	 * @param userName : userName - for 'GET' or 'DELETE' operation 
	 * @param method : "GET" or "DELETE"
	 * @param baseURI
	 * @return instance of 'io.restassured.response.Response'
	 * @throws IOException
	 */
	
	public Response invokeAPI(String baseURI, String userName, String method) throws IOException {
		String url= baseURI+userName;

		try{
			RestAssured.baseURI =baseURI;
			Response response = null;
			RequestSpecification request = RestAssured.given();
			//GET calls
			if(method.equalsIgnoreCase("GET"))	{
				response= request
						.given()
						.headers("accept","application/json")
						.request(Method.GET, userName);
			}
			
			if(method.equalsIgnoreCase("DELETE"))	{
				response= request
						.given()
						.headers("accept","application/json")
						.when()
						.delete(userName);
			}
			// Check API response is not null
			if(response!=null) {
				log.info("\n\n API has returned HTTP Code\n"+response.getStatusLine());	
				test.info("HTTP Code returned by API is : "+response.getStatusLine());	
				return response;
			}
			else 
				return null;

		}
		catch(Exception e) {
			log.info("\n\nAPI Call Failed , An exception has occured \n"+e);
			test.fail("API Call Failed , An exception has occured "+e);

			e.printStackTrace();
			return null;
		}
	}
}




/*  Type details
{
id	integer($int64)
username	string
firstName	string
lastName	string
email	string
password	string
phone	string
userStatus	integer($int32) User Status

}

 */