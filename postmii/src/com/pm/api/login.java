package com.pm.api;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pm.utilities.Constants;
import com.pm.utilities.TestBase;

import io.restassured.response.Response;

public class login extends TestBase{

	Response res;
	String sResponse,sParameters;
	String sURL;
	Logger log = Logger.getLogger(getClass().getSimpleName());
	
@BeforeClass
public void urlSetUp() throws Exception{
	sParameters=oConst.paramLogin;
	sURL=System.getProperty("host");
	sURL=sURL+System.getProperty("basePath");
	sURL=sURL+System.getProperty("URI_login");
	log.info(sURL);
}
	public String loginToPostMii() throws Exception{
		urlSetUp();
		res=oResUtil.ufPost(sURL, oConst.paramLogin);
		JSONObject oJsResdata=new JSONObject(res.asString());
		JSONObject oJsTemp;
		Constants.sTokenOnLogin=oJsResdata.getString("token");
		Constants.sTemplateID=Integer.toString(oJsResdata.getInt("id"));
		JSONArray oJsArrCountry = oJsResdata.getJSONArray("countries");
		log.info(oJsArrCountry);
		oJsTemp=oJsArrCountry.getJSONObject(0);
		Constants.sFilterID = Integer.toString(oJsTemp.getInt("id"));
		JSONArray oJsArrLocation = oJsTemp.getJSONArray("locations");
		log.info(oJsArrLocation);
		oJsTemp=oJsArrLocation.getJSONObject(0);
		Constants.sCountryCode=Integer.toString(oJsTemp.getInt("id"));
		
		log.info("Token: "+Constants.sTokenOnLogin);
		log.info("Country ID: "+Constants.sCountryCode);
		return res.asString(); 
	}
	@Test (priority=1)
	public void loginAPI_ResponseChecking() throws Exception {
		
		res=oResUtil.ufPost(sURL, oConst.paramLogin);
		if(res.statusCode()!=Constants.iHTTPCode201)
			throw new Exception("Expected status code 201 but found "+res.statusCode());
		
	}
	@Test (priority=2)
	public void loginAPI_ErrorInDatas_ResponseCode400() throws Exception {
		res=oResUtil.ufPost(sURL, oConst.paramLogin.replace("main", ""));
		if(res.statusCode()!=Constants.iHTTPCode400)
			throw new Exception("Expected status code 400 but found "+res.statusCode());
		
	}

	@Test (priority=3)
	public void loginAPI_ResponseVerification_token_id_email() throws Exception {
		res=oResUtil.ufPost(sURL, oConst.paramLogin);
		if(res.statusCode()!=Constants.iHTTPCode201)
			throw new Exception("Expected status code 201 but found "+res.statusCode());
		
		JSONObject oJsResdata=new JSONObject(res.asString());
		
		if(oJsResdata.getString("token").isEmpty())
			throw new Exception("Token is empty..");
		if(oJsResdata.getString("email").isEmpty())
			throw new Exception("email is empty..");	
	}
}
	