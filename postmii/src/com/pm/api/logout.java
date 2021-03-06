package com.pm.api;

import org.apache.log4j.Logger;
import org.hamcrest.core.IsInstanceOf;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pm.utilities.Constants;
import com.pm.utilities.RestUtilities;
import com.pm.utilities.TestBase;

import io.restassured.response.Response;

public class logout extends TestBase{

	Response res;
	String sResponse,sParameters;
	String sURL;
	Logger log = Logger.getLogger(getClass().getSimpleName());
	login oLogin = new login();
	
@BeforeTest
public void urlSetUp() throws Exception{
	sParameters=oConst.paramLogin;
	sURL=System.getProperty("host");
	sURL=sURL+System.getProperty("basePath");
	sURL=sURL+System.getProperty("URI_logout");
	log.info(sURL);
}
	@Test (priority=1)
	public void logoutAPI_ResponseChecking() throws Exception {
		oLogin.loginToPostMii();
		res=oResUtil.ufPostheader(sURL,oConst.headerXTokenKey,Constants.sTokenOnLogin);
		if(res.statusCode()!=Constants.iHTTPCode200)
			throw new Exception("Expected status code 200 but found "+res.statusCode());
		
	}
	@Test (priority=2)
	public void logoutAPI_WrongToken_ResponseCode401() throws Exception {
		oLogin.loginToPostMii();
		res=oResUtil.ufPostheader(sURL,oConst.headerXTokenKey, Constants.sTokenOnLogin+"123");
		if(res.statusCode()!=Constants.iHTTPCode401)
			throw new Exception("Expected status code 401 but found "+res.statusCode());
		
	}
}
	