package com.pm.api;

import org.apache.log4j.Logger;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pm.utilities.RestUtilities;
import com.pm.utilities.TestBase;

public class login extends TestBase{

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
	public void loginToPostMii() throws Exception{
		urlSetUp();
		sResponse=oResUtil.ufPost(sURL, oConst.paramLogin);
		JSONObject oJsResdata=new JSONObject(sResponse.toString());
		oConst.sTokenOnLogin=oJsResdata.getString("token");
		log.info(oConst.sTokenOnLogin);
	}
	@Test (priority=1)
	public void loginAPI_ResponseChecking() throws Exception {
		sResponse=oResUtil.ufPost(sURL, oConst.paramLogin);
		log.info(sResponse);
	}
	@Test (priority=2)
	public void loginAPI_ErrorInDatas_ResponseCode400() throws Exception {
		sResponse=oResUtil.ufPost(sURL, oConst.paramLogin.replace("main", ""));
		if(RestUtilities.iStatusCode!=400)
			throw new Exception("There is error in login datas");
		log.info(sResponse);
	}

	@Test (priority=3)
	public void loginAPI_ResponseVerification_token_id_email() throws Exception {
		sResponse=oResUtil.ufPost(sURL, oConst.paramLogin);
		JSONObject oJsResdata=new JSONObject(sResponse.toString());
		log.info(sResponse);
		if(oJsResdata.getString("token").isEmpty())
			throw new Exception("Token is empty..");
		if(oJsResdata.getString("email").isEmpty())
			throw new Exception("email is empty..");	
	}
}
	