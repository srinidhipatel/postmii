package com.pm.api;

import java.util.HashMap;

import org.apache.log4j.Logger;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pm.utilities.Constants;
import com.pm.utilities.TestBase;

import io.restassured.response.Response;

public class emailcard extends TestBase{

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
	sURL=sURL+System.getProperty("URI_EmailCard");
	log.info(sURL);
	oLogin.loginToPostMii();
}


//@DataProvider(name = "language")

public static Object[][] credentials() {

    return new Object[][] {{"EN"},{"ES"},{"FR"},{"DE"},{"IT"},{"PT"},{"JA"},{"RU"},{"ZH"}};

}

	@Test //(priority=1 , dataProvider = "language")
	//public void emailCardAPI_ResponseChecking(String sLanguage) throws Exception {
		public void emailCardAPI_ResponseChecking() throws Exception {	
		//log.info("Language Running for "+sLanguage);
		HashMap< String , String> map = new HashMap<String, String>();
		map.put("location",Constants.sCountryCode);map.put(oConst.headerXTokenKey, Constants.sTokenOnLogin);
		map.put("email", "srinidhi.test@gmail.com");map.put("lang", "EN");
		map.put("content", "multipart/form-data");

		res=oResUtil.ufPostheaderParamKey(sURL,map,oConst.paramEmailCard.replace("changemelang", "EN").replace("changemecountry", Constants.sCountryCode),oConst.headerXTokenKey, Constants.sTokenOnLogin);
		if(res.statusCode()!=Constants.iHTTPCode200)
			throw new Exception("Expected status code 201 but found "+res.statusCode());
		
	}

	/*@Test (priority=2)
	public void emailCardAPI_WrongToken_ResponseCode401() throws Exception {
		res=oResUtil.ufPostheaderParamKey(sURL,oConst.paramEmailCard.replace("changemelang", "EN"),oConst.headerXTokenKey, Constants.sTokenOnLogin+"123");
		if(res.statusCode()!=Constants.iHTTPCode401)
			throw new Exception("Expected status code 401 but found "+res.statusCode());
		
	}
	@Test (priority=3)	
	public void emailCardAPI_WrongLanguageCode_ResponseCode405() throws Exception {
		res=oResUtil.ufPostheaderParamKey(sURL,oConst.paramEmailCard,oConst.headerXTokenKey, Constants.sTokenOnLogin);
		if(res.statusCode()!=Constants.iHTTPCode405)
			throw new Exception("Expected status code 405 but found "+res.statusCode());
		
	}*/
}
	