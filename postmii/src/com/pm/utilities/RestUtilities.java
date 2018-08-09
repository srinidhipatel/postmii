package com.pm.utilities;


import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestUtilities extends TestBase{

	public static int iStatusCode=0;

	
	public String ufPost(String sURL, String Parameter) throws Exception{
		log.info("setting Base URL as : "+sURL);
		RestAssured.baseURI=sURL;
		Response res = RestAssured.given().contentType("application/json").body(Parameter).post();
		log.info(iStatusCode=res.getStatusCode());
		return res.asString();
	}
	public String ufPostheader(String sURL,String sHeaderKey,String sHeaderValue) throws Exception {
		log.info("setting Base URL as : "+sURL);
		RestAssured.baseURI=sURL;
		Response res = RestAssured.given().header(sHeaderKey,sHeaderValue).contentType("application/json").post();
		log.info(iStatusCode=res.getStatusCode());
		return res.asString();
	}
	
}
