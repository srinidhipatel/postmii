package com.pm.utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.AppiumDriver;

import io.appium.java_client.android.AndroidDriver;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;



public class TestBase {
	// public static AppiumDriver<WebElement> driver;
	
	public static AppiumDriver<WebElement> driver=null;
	public static AndroidUtilities oAndUtil = new AndroidUtilities();
	public static CommonUtilities oCommUtil = new CommonUtilities();
	public static Constants oConst = new Constants();
	public static RestUtilities oResUtil = new RestUtilities();
	public static pageFunctions oPageFunc = new pageFunctions();
	public static pageValidations oPageVal = new pageValidations();
	public static String sErrorMessage = "";
	public static String sClassName = "";
	public static String sLocalization = "";
	public static String sLocalEnglish = "English";
	public static String sLocalIndonasia = "Indonesia";
	public static JSONObject oJsonLocalization = new JSONObject();
	public static JSONObject oJsonLocalizationData = new JSONObject();
	public static String AutomationRunning;
	Logger log = Logger.getLogger(getClass().getSimpleName());

	@BeforeSuite
	public void TriggerDependencies() throws Exception {
		// System.out.println(System.getProperty("os.name"));
		// System.out.println(System.getProperty("microedition.platform"));
		// System.out.println(Constants.sConstEnvironment);
		if (Constants.sConstEnvironment.equalsIgnoreCase("Stage"))
			oCommUtil.loadPropertyFiles(
					System.getProperty("user.dir") + "/src/Properties/ObjectRepositary_Stage.properties");
		else
			oCommUtil.loadPropertyFiles(System.getProperty("user.dir") + "/src/Properties/ObjectRepositary.properties");

		oCommUtil.loadPropertyFiles(System.getProperty("user.dir") + "/src/TestData/TestData.properties");
		oCommUtil.loadLog4jProperty(System.getProperty("user.dir") + "/src/Properties/log4j.properties");
		/*
		 * oJsonLocalization =
		 * oCommUtil.ReadJsonFileGetJsonObject(System.getProperty("user.dir")+
		 * "/src/TestData/LocalisationSupport.json");
		 * sLocalization=System.getProperty("localization");
		 * log.info("Localisation set to : "+sLocalization);
		 * if(sLocalization.equalsIgnoreCase(sLocalEnglish)) oJsonLocalizationData =
		 * oJsonLocalization.getJSONObject(sLocalEnglish); else { oJsonLocalizationData
		 * = oJsonLocalization.getJSONObject(sLocalIndonasia);
		 * sLocalization=sLocalIndonasia; }
		 * log.info(oJsonLocalizationData.get("sWelcomToHalodoc").toString());
		 */
		if(System.getProperty("AutomationRunning").equalsIgnoreCase(Constants.sAutomationMobile))
		{oCommUtil.ufdeletescreenshots();
		oAndUtil.launchApp();}

		// boolean bRes=oAndUtil.ufWaitForElementDisplayed(driver,
		// "Home_Contectualmenu", 15);
		// log.info("Launch Status : "+bRes);
		oAndUtil.ufLoadKeysMap();
	}

	@AfterSuite
	public void ShuttingDownAllDependencies() throws Exception {
		if(System.getProperty("AutomationRunning").equalsIgnoreCase(Constants.sAutomationMobile))
		{oAndUtil.shutDownApp();
		oCommUtil.ufzipscreenshots();}
		// oCommUtil.SendEmailAfterTestSuite();

	}
	
}
