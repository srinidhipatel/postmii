package com.pm.api;

import org.apache.log4j.Logger;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pm.utilities.Constants;
import com.pm.utilities.RestUtilities;
import com.pm.utilities.TestBase;

import io.restassured.response.Response;

public class info extends TestBase{

	Response res;
	String sResponse,sParameters;
	String sURL;
	Logger log = Logger.getLogger(getClass().getSimpleName());
	login oLogin = new login();
	
@BeforeClass
public void urlSetUp() throws Exception{
	sParameters=oConst.paramLogin;
	sURL=System.getProperty("host");
	sURL=sURL+System.getProperty("basePath");
	sURL=sURL+System.getProperty("URI_infos");
	log.info(sURL);
}
	
	@Test (priority=1)
	public void infosAPI_ResponseChecking() throws Exception {
		oLogin.loginToPostMii();
		sURL=sURL+Constants.sCountryCode;
		res=oResUtil.ufGetheader(sURL,oConst.headerXTokenKey, Constants.sTokenOnLogin);
		if(res.statusCode()!=Constants.iHTTPCode201)
			throw new Exception("Expected status code 201 but found "+res.statusCode());
		
	}

	@Test (priority=2)
	public void infosAPI_WrongToken_ResponseCode401() throws Exception {
		res=oResUtil.ufGetheader(sURL,oConst.headerXTokenKey, Constants.sTokenOnLogin+"123");
		if(res.statusCode()!=Constants.iHTTPCode401)
			throw new Exception("Expected status code 401 but found "+res.statusCode());
		
	}
	@Test (priority=3)	
	public void infosAPI_WrongCountryCode_ResponseCode418() throws Exception {
		res=oResUtil.ufGetheader(sURL+"123",oConst.headerXTokenKey, Constants.sTokenOnLogin);
		if(res.statusCode()!=Constants.iHTTPCode418)
			throw new Exception("Expected status code 418 but found "+res.statusCode());
		
	}
}
	