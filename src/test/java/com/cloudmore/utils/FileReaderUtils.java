package com.cloudmore.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cloudmore.api.UserDetailsJsonPojo;

import dev.failsafe.internal.util.Assert;

public class FileReaderUtils {
	//propertiesFilePath needs to be updated accordingly, when running on a different system.
	static final String requestRepoPropFilePath="\\resources\\requestRepository\\requestRepo.properties";
	static final String jsonFilePath="./resources/requestRepository/";
	
	static final String appPropertiesPath="\\resources\\application.properties";
	
	static Properties prop = new Properties();
	
	public static String getDataFromApplicationProp(String key) throws Exception
	{
		String filepath = System.getProperty("user.dir")+appPropertiesPath;
		
		FileInputStream fis = new FileInputStream(filepath);
		prop.load(fis);
		String value = prop.getProperty(key);
		if(value.isEmpty()) {
			throw new Exception("Value not specified in key:" +key+ "in properties file");
		}
		return value;
		
	}

	
	
	public static String getPropertyValue(String key){

		try {
			String filepath=System.getProperty("user.dir")+requestRepoPropFilePath;
			InputStream input= new FileInputStream(filepath);
			Properties prop= new Properties();
			prop.load(input);
			return prop.getProperty(key);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}


	//Place new json files here for APIs: << /resources/requestRepository/ >> and then provide the file name as a parameter to this method
	public static String getJsonAsString(String fileName) throws IOException {
		String file = jsonFilePath+fileName;
		String json = readFileAsString(file);
		return json;

	}
	
	/**
	 * This method will return userNames from a JSON array within a JSON file.
	 * @param fileName
	 * @return List <String>
	 * @throws IOException
	 Place json file here: << ./resources/requestRepository/ >> and then provide the file name as a parameter to this method
	 
	 */
	public static List<String> getValueFromJsonFile(String fileName,String key) throws IOException {
		String file = jsonFilePath+fileName;
		String json = readFileAsString(file);
		
		JSONArray jsonArray = new JSONArray(json);
		List<String> list = new ArrayList();
		
		for(int i=0; i < jsonArray.length();i++){
			JSONObject jo = jsonArray.getJSONObject(i); 
			list.add((jo.getString(key)));
		}
		
		
		return list;

	}
	

	public static String readFileAsString (String file) throws IOException {
		return new String(Files.readAllBytes(Paths.get(file)));
	}
	
	public static boolean verifyUpdatedUserDetails(String jsonFile, UserDetailsJsonPojo pojo) throws IOException {
		String file = jsonFilePath+jsonFile;
		String json = readFileAsString(file );
		JSONObject jo =  new JSONObject(json); 
		
		Assert.isTrue(jo.getString("username").equals(pojo.username), "userName is not matched");
		Assert.isTrue((jo.getInt("id"))==(pojo.id), "id is not matched");
		Assert.isTrue(jo.getString("firstName").equals(pojo.firstName), "firstName is not matched");
		Assert.isTrue(jo.getString("lastName").equals(pojo.lastName), " lastName is not matched");
		Assert.isTrue(jo.getString("email").equals(pojo.email), "email is not matched");
		Assert.isTrue(jo.getString("password").equals(pojo.password), "password is not matched");
		Assert.isTrue(jo.getString("phone").equals(pojo.phone), "phone is not matched");
	
		return true;

	
	}

}
