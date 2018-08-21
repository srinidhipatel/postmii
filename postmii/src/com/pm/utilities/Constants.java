package com.pm.utilities;

public class Constants {
	
	//Environment and Running app (to avoid driver.quit error)
	public static final String sConstEnvironment="PROD",sAutomationMobile="App",sAutomationAPI="API";
	
	//HTTP Error codes for status return validation
	public static final int iHTTPCode201=201,iHTTPCode400=400,iHTTPCode200=200,iHTTPCode404=404,
			iHTTPCode401=401,iHTTPCode405=405,iHTTPCode418=418;
	public static final String sContentType="application/json";
	
	//Login API is setting up these values
	public static String sTokenOnLogin=null;
	public static String sCountryCode=null;
	public static String sFilterID=null;
	public static String sTemplateID=null;


	//Parameters Used far API utility
	public String paramLogin="{\"email\":\"maintec@postmii.com\"," + 
			"	\"password\":\"maintec\"," + 
			"	\"tablet_id\":\"asebwrel\"}";
	public String headerXTokenKey="X-token";
	public String paramLogout_1="{\"name\":\"",	paramLogout_2="\"}";
	public String paramEmailCard="{\"location\":\"changemecountry\","
			+ "\"email\":\"srinidhi.test@gmail.com\","
			+ "\"lang\":\"changemelang\","
			+ "\"content\":\"multipart/form-data\"}";

}
