package com.pm.api;

import org.apache.log4j.Logger;
import org.hamcrest.core.IsInstanceOf;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pm.utilities.RestUtilities;
import com.pm.utilities.TestBase;

public class logout extends TestBase{

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
		sResponse=oResUtil.ufPostheader(sURL,oConst.headerLogoutKey,oConst.sTokenOnLogin);
		log.info(sResponse);
	}
}
	