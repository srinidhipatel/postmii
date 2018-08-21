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


@DataProvider(name = "language")

public static Object[][] credentials() {

    return new Object[][] {{"EN"},{"ES"},{"FR"},{"DE"},{"IT"},{"PT"},{"JA"},{"RU"},{"ZH"}};

}

	@Test (priority=1 , dataProvider = "language")
	public void emailCardAPI_ResponseChecking(String sLanguage) throws Exception {
		log.info("Language Running for "+sLanguage);
		HashMap< String , String> map = new HashMap<String, String>();
		map.put("location",Constants.sCountryCode);map.put(oConst.headerXTokenKey, Constants.sTokenOnLogin);
		map.put("email", "srinidhi.test@gmail.com");map.put("lang", sLanguage);
		map.put("content", "multipart/form-data");

		res=oResUtil.ufPostheaderParamKey(sURL,map);
		if(res.statusCode()!=Constants.iHTTPCode200)
			throw new Exception("Expected status code 200 but found "+res.statusCode()+"Body : "+map.toString());
		
	}

	@Test (priority=2, dataProvider = "language")
	public void emailCardAPI_WrongToken_ResponseCode401(String sLanguage) throws Exception {
		log.info("Language Running for "+sLanguage);
		
		HashMap< String , String> map = new HashMap<String, String>();
		map.put("location",Constants.sCountryCode);map.put(oConst.headerXTokenKey, Constants.sTokenOnLogin+"123");
		map.put("email", "srinidhi.test@gmail.com");map.put("lang", sLanguage);
		map.put("content", "multipart/form-data");
		res=oResUtil.ufPostheaderParamKey(sURL,map);
		if(res.statusCode()!=Constants.iHTTPCode401)
			throw new Exception("Expected status code 401 but found "+res.statusCode()+"Body : "+map.toString());
		
	}
	@Test (priority=3)	
	public void emailCardAPI_WrongLanguageCode_ResponseCode200() throws Exception {
		HashMap< String , String> map = new HashMap<String, String>();
		map.put("location",Constants.sCountryCode);map.put(oConst.headerXTokenKey, Constants.sTokenOnLogin);
		map.put("email", "srinidhi.test@gmail.com");map.put("lang", "ENMB");
		map.put("content", "multipart/form-data");
		res=oResUtil.ufPostheaderParamKey(sURL,map);
		if(res.statusCode()!=Constants.iHTTPCode200)
			throw new Exception("Expected status code 200 but found "+res.statusCode()+"Body : "+map.toString());
		
	}
}
	