package com.pm.utilities;


import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestUtilities extends TestBase{

	public static int iStatusCode=0;

	
	public Response ufPost(String sURL, String Parameter) throws Exception{
		log.info("setting Base URL as : "+sURL+"\nParams: "+Parameter);
		RestAssured.baseURI=sURL;
		Response res = RestAssured.given().contentType(Constants.sContentType).body(Parameter).post();
		log.info(iStatusCode=res.getStatusCode());
		log.info(res.asString());
		return res;
	}
	public Response ufPostheader(String sURL,String sHeaderKey,String sHeaderValue) throws Exception {
		log.info("setting Base URL as : "+sURL);
		RestAssured.baseURI=sURL;
		Response res = RestAssured.given().header(sHeaderKey,sHeaderValue).contentType(Constants.sContentType).post();
		log.info(iStatusCode=res.getStatusCode());
		log.info(res.asString());
		return res;
	}
	public Response ufPostheaderParamKey(String sURL,HashMap<String, String> map, String Parameter, String sHeaderKey,String sHeaderValue) throws Exception {
		log.info("setting Base URL as : "+sURL+"\nParams: "+Parameter);
		RestAssured.baseURI=sURL;
		Response res = RestAssured.given().headers(map).post();//.contentType(Constants.sContentType)
		//Response res = RestAssured.given().headers(Trial).contentType(Constants.sContentType).post();
		log.info(iStatusCode=res.getStatusCode());
		log.info(res.asString());
		return res;
	}
	public Response ufGetheader(String sURL,String sHeaderKey,String sHeaderValue) throws Exception {
		log.info("setting Base URL as : "+sURL);
		RestAssured.baseURI=sURL;
		Response res = RestAssured.given().header(sHeaderKey,sHeaderValue).contentType(Constants.sContentType).get();
		log.info(iStatusCode=res.getStatusCode());
		log.info(res.asString());
		return res;
	}
	public Response ufGetheader(String sURL) throws Exception {
		log.info("setting Base URL as : "+sURL);
		RestAssured.baseURI=sURL;
		Response res = RestAssured.given().contentType(Constants.sContentType).get();
		log.info(iStatusCode=res.getStatusCode());
		log.info(res.asString());
		return res;
	}
}
