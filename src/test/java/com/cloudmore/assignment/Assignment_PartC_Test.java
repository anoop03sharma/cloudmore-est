package com.cloudmore.assignment;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.cloudmore.api.RestAssuredApiActions;
import com.cloudmore.api.UserDetailsJsonPojo;
import com.cloudmore.utils.FileReaderUtils;
import com.cloudmore.utils.ListenerUtils;

import dev.failsafe.internal.util.Assert;
import io.restassured.response.Response;


/**
 * @author Anoop
 * Test - APIs to check CRUD operations { POST,GET,PUT and DELETE }
 * baseURI : "https://petstore.swagger.io/v2/user/";
 */

public class Assignment_PartC_Test  extends ListenerUtils {
	private static final Logger log = LogManager.getLogger(Assignment_PartC_Test.class);
	static final String baseURI="https://petstore.swagger.io/v2/user/";

	//POST request
	@Test(groups = {"ALL","POST"})
	public void createWithArray_post() throws IOException {
		try {
			RestAssuredApiActions api=new RestAssuredApiActions();
			log.info("\n\n **************** POST -  API - CreateWithArray *******************\n\n");
			test.info(" **************** POST -  API - CreateWithArray *******************");

			final String method="POST";
			String endPoint=FileReaderUtils.getPropertyValue("createWithArray.endpoint");
			String requestBody=FileReaderUtils.getJsonAsString("createWithArray.json");

			log.info("\n\nSending json payload in Request:\n"+requestBody);
			test.info("Sending json payload in Request : "+requestBody);

			//Invoke API 
			Response response=api.invokeAPI(baseURI,endPoint, requestBody, method);

			log.info("\n\nAPI 'createWithArray' has returned response\n" +response.getBody().asString());
			test.info("API 'createWithArray' has returned response : " +response.getBody().asString());


			//Fails Test
			if (response.getStatusCode()==200) {
				log.info("\n\n POST Operation is successfully performed \n");
				test.info("POST Operation is successfully performed ");

			}

			//FAILS Test
			if (response.getStatusLine().contains("500")) {
				log.info("\n\n  UNKNOWN ERROR -  Technical exception\n"+response.getStatusLine());
				test.fail("UNKNOWN ERROR -  Technical exception : "+response.getStatusLine());

				Assert.isTrue(response.getStatusLine().contains("200"), "API returned HTTP error code - 500" );
			}

			/*					
			 *//** Optional 
			 * Invoke getUserByUserName calls to check the 'user/list of users' created via POST API call are successfully added 
			 **//*
			List<String> userNameList=FileReader.getValueFromJsonFile("createWithArray.json","username");
			 Iterator<String> it = userNameList.iterator();
		        while (it.hasNext()) {
		        	Assert.isTrue(getUserDetails(it.next()),"\n\n User verfication failed for userName + "+it.toString());
		        }

			  */	

		}catch(Exception e) {
			e.printStackTrace();
			log.info("\n\nAPI CreateWithArray :An exception has occured\n" +e);
			test.fail("API CreateWithArray :An exception has occured : "+e);;

		}
	}


	//GET Request - Optional
	@Test()
	public boolean getUserDetails(String username) throws IOException {
		try {
			RestAssuredApiActions api=new RestAssuredApiActions();

			log.info("\n**************** ***************************************\n");
			log.info("**************** OPTIONAL>> GET Call ***************************************\n");

			final String method="GET";

			//InvokeAPI
			Response response=api.invokeAPI(baseURI,username, method);
			log.info("\n\nAPI has returned response : "+response.getBody().asPrettyString());
			test.info("API has returned response : "+response.getBody().asPrettyString());

			UserDetailsJsonPojo apiRes = response.getBody().as(UserDetailsJsonPojo.class); 
			Assert.isTrue(apiRes!=null, "Json response successfully saved");		


			if (response.getStatusCode()==200) {
				log.info("\n\n GET Operation is successful\n");	
				log.info("GET Operation is successful");	

				log.info("\n\nAPI has returned userDetails -- \n"+
						"id : "+apiRes.id +"\n"+
						"username : "+apiRes.username +"\n"+
						"firstName : "+apiRes.firstName +"\n"+
						"lastName : "+apiRes.lastName +"\n"+
						"email : "+apiRes.lastName +"\n"+
						"password : "+apiRes.password +"\n"+
						"phone : "+apiRes.phone +"\n",
						"userStatus : "+apiRes.userStatus +"\n"
						);	
			}

			//Fails Test
			if (response.getStatusCode()==400) {
				log.info("\n\nAPI has returned response : "+response.getBody().asPrettyString());
				test.fail("API has returned response : "+response.getBody().asPrettyString());

				Assert.isTrue(response.getStatusLine().contains("200"), "API returned HTTP error code - 400" );

			}

			if(response.getStatusCode()==404) {
				log.info("\n\n USER NOT FOUND  :\n " +response.getStatusLine());
				test.info("USER NOT FOUND  " +response.getStatusLine());

			} 


		}
		catch(Exception e) {
			e.printStackTrace();
			log.error("\n\nAPI - getUserByUserName test Failed due to exception :\n"+e);	
			test.fail("API - getUserByUserName test Failed due to exception : "+e);


		}
		return false;

	}


	//GET Request
	@Test(dependsOnMethods={"createWithArray_post"}, groups = { "ALL","GET"})
	public void getUserByUserName() throws IOException {
		try {
			RestAssuredApiActions api=new RestAssuredApiActions();

			log.info("\n**************** GET - getUserByUserName [Run After Post call]***************************************\n");
			test.info("**************** GET - getUserByUserName [Run After Post call]***************************************");

			final String method="GET";
			String username=FileReaderUtils.getPropertyValue("getUserByUserName.username1");

			//InvokeAPI
			Response response=api.invokeAPI(baseURI,username, method);
			log.info("\n\nAPI has returned response : "+response.getBody().asPrettyString());
			test.info("\n\nAPI has returned response : "+response.getBody().asPrettyString());

			UserDetailsJsonPojo apiRes = response.getBody().as(UserDetailsJsonPojo.class); 
			Assert.isTrue(apiRes!=null, "Json response successfully saved");		


			if (response.getStatusCode()==200) {
				log.info("\n\n GET Operation is successful\n"+response.getStatusLine());
				test.info("\n\n GET Operation is successful\n"+response.getStatusLine());	

				log.info("\n\nAPI has returned userDetails -- \n"+
						"id : "+apiRes.id +"\n"+
						"username : "+apiRes.username +"\n"+
						"firstName : "+apiRes.firstName +"\n"+
						"lastName : "+apiRes.lastName +"\n"+
						"email : "+apiRes.email +"\n"+
						"password : "+apiRes.password +"\n"+
						"phone : "+apiRes.phone +"\n",
						"userStatus : "+apiRes.userStatus +"\n"
						);	


			}

			//Fails Test
			if (response.getStatusCode()==400) {
				log.info("\n\n INVALID USERNAME SUPPLIED \n"+response.getStatusLine());
				test.fail("\n\n INVALID USERNAME SUPPLIED \n"+response.getStatusLine());
				Assert.isTrue(response.getStatusLine().contains("200"), "API returned HTTP error code - 400" );


			}

			if(response.getStatusCode()==404) {
				log.info("\n\n USER NOT FOUND  :\n " +response.getStatusLine());
				test.info("\n\n USER NOT FOUND  :\n " +response.getStatusLine());

			} 



		}
		catch(Exception e) {
			e.printStackTrace();
			log.info("\n\nAPI - getUserByUserName test Failed due to exception :\n"+e);	
			test.fail("\n\nAPI - getUserByUserName test Failed due to exception :\n"+e);		}
	}





	//PUT request --> Followed by a Get call to verify update
	@Test(dependsOnMethods={"createWithArray_post"}, groups = { "ALL"," PUT"})
	public void updateUser_put() throws IOException {
		try {
			log.info("\n\n********************* PUT API -UpdateUser ***************************************\n");
			test.info("********************* PUT API -UpdateUser ***************************************");

			RestAssuredApiActions api=new RestAssuredApiActions();
			final String method="PUT";
			String userName=FileReaderUtils.getPropertyValue("getUserByUserName.username1");
			String updatedUserDetails=FileReaderUtils.getJsonAsString("updateUser.json");

			log.info("\n\nUpdate User : " +userName+" with new details : \n"+ updatedUserDetails);
			test.info("Update User : " +userName+" with new details : "+ updatedUserDetails);

			//Invoke API 
			Response response=api.invokeAPI(baseURI,userName, updatedUserDetails, method);
			log.info("\n\nAPI has returned response : *********\n"+response.getBody().asPrettyString());
			test.info("API has returned response : ********* "+response.getBody().asPrettyString());

			UserDetailsJsonPojo apiRes = response.getBody().as(UserDetailsJsonPojo.class); 
			Assert.isTrue(apiRes!=null, "Json response could not be saved");	

			if (response.getStatusCode()==200)
				log.info("\n\n PUT Operation is successful\n"+response.getStatusLine());		
			test.info("PUT Operation is successful "+response.getStatusLine());		 


			//Fails Test
			if (response.getStatusCode()==400) {
				Assert.isTrue(response.getStatusLine().contains("200"), "API returned HTTP error code - 400" );
				log.error("\n\n INVALID USERNAME SUPPLIED \n"+response.getStatusLine());
				test.fail("INVALID USERNAME SUPPLIED "+response.getStatusLine());

			}

			if(response.getStatusCode()==404) {
				log.info("\n\n USER NOT FOUND  :\n " +response.getStatusLine());
				test.info(" USER NOT FOUND " +response.getStatusLine());

			} 

			//Invoke GET API  and check User is Updated.
			Response getResp=api.invokeAPI(baseURI,userName, "GET");
			log.info("Call GET API to check the update is performed successfully : "+getResp.getBody().asPrettyString());
			test.info("Call GET API to check the update is performed successfully : "+getResp.getBody().asPrettyString());

			UserDetailsJsonPojo pojo2 = getResp.getBody().as(UserDetailsJsonPojo.class); 
			Assert.isTrue(getResp!=null, "Json response successfully saved");	
			Assert.isTrue(FileReaderUtils.verifyUpdatedUserDetails("updateUser.json", pojo2),"Updated user details not matched with Get API response");


			if (response.getStatusCode()==200) {
				log.info("\n\n PUT Operation is successful\n\nUpdated User Detais\n"+response.getStatusLine());	
				test.info("PUT Operation is successful , Updated User Details");	

				log.info("\n\nAPI has returned userDetails -- \n"+
						"id : "+pojo2.id +"\n"+
						"username : "+pojo2.username +"\n"+
						"firstName: "+pojo2.firstName +"\n"+
						"lastName : "+pojo2.lastName +"\n"+
						"email    : "+pojo2.email +"\n"+
						"password : "+pojo2.password +"\n"+
						"phone    : "+pojo2.phone +"\n",
						"userStatus : "+pojo2.userStatus +"\n"	);	

			}


		}catch(Exception e) {
			e.printStackTrace();
			log.info("\n\nAPI - createWithArray - An Exception has occured\n");
			test.fail("\n\nAPI - createWithArray - An Exception has occured\n");


		}
	}

	//DELETE Request -- > Followed by a GET call to verify user is deleted
	@Test( groups = { "ALL", "DELETE"})
	public void deleteUserByUserName() throws IOException {
		try {
			log.info("\n************************ DELETE API - deleteUser ***************************************\n");
			test.info("************************ DELETE API - deleteUser **************************************");

			RestAssuredApiActions api=new RestAssuredApiActions();
			final String method="DELETE";
			String username=FileReaderUtils.getPropertyValue("getUserByUserName.username2");
			//InvokeAPI
			Response response=api.invokeAPI(baseURI,username, method);
			log.info("\n\nAPI has returned response : "+response.getBody().asPrettyString());
			Assert.isTrue(response!=null, "Json response successfully saved");		


			if (response.getStatusCode()==200)
				log.info("\n\n DELETE Operation is successful\n"+response.getStatusLine());		 

			//Fails test
			if (response.getStatusCode()==400) {
				log.info("\n\n INVALID USERNAME SUPPLIED \n"+response.getStatusLine());
				test.fail("INVALID USERNAME SUPPLIED"+response.getStatusLine());

				Assert.isTrue(response.getStatusLine().contains("200"), "API returned HTTP error code - 400" );
			}

			if(response.getStatusCode()==404) {
				log.info("\n\n USER NOT FOUND  :\n " +response.getStatusLine());
				test.info("USER NOT FOUND" +response.getStatusLine());


			} 

			Assert.isTrue(!getUserDetails(username),"\n\n DELETE Operation failed for user :\n"+username);


		}
		catch(Exception e) {
			e.printStackTrace();
			log.info("\n\nAPI - DELETE API test Failed due to exception :\n"+e);		
			test.fail("API - DELETE API test Failed due to exception");		


		}
	}

}




