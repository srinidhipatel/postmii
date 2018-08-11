package com.pm.api;

import org.apache.log4j.Logger;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pm.utilities.Constants;
import com.pm.utilities.TestBase;

import io.restassured.response.Response;

public class template extends TestBase{

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
	sURL=sURL+System.getProperty("URI_Template");
	log.info(sURL);
}
	
	@Test (priority=1)
	public void templateAPI_ResponseChecking() throws Exception {
		oLogin.loginToPostMii();
		sURL=sURL+Constants.sTemplateID;
		res=oResUtil.ufGetheader(sURL,oConst.headerXTokenKey, Constants.sTokenOnLogin);
		if(res.statusCode()!=Constants.iHTTPCode200)
			throw new Exception("Expected status code 201 but found "+res.statusCode());
		
	}

	@Test (priority=2)
	public void templateAPI_WrongToken_ResponseCode401() throws Exception {
		res=oResUtil.ufGetheader(sURL,oConst.headerXTokenKey, Constants.sTokenOnLogin+"123");
		if(res.statusCode()!=Constants.iHTTPCode401)
			throw new Exception("Expected status code 401 but found "+res.statusCode());
		
	}
	@Test (priority=3)	
	public void templateAPI_WrongfilterCode_ResponseCode405() throws Exception {
		res=oResUtil.ufGetheader(sURL+"123",oConst.headerXTokenKey, Constants.sTokenOnLogin);
		if(res.statusCode()!=Constants.iHTTPCode405)
			throw new Exception("Expected status code 405 but found "+res.statusCode());
		
	}
}
	