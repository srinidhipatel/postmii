package com.pm.utilities;

import java.util.List;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.android.AndroidDriver;

public class pageFunctions extends TestBase{

	public boolean TakeControlToOTPScreen(AppiumDriver<WebElement> driver) throws Exception {
		boolean bRes_flag = false;
		if (oAndUtil.ufElementDisplayed(driver, "WelcomeScreen_TextGetStarted"))
			oAndUtil.ufClickElement(driver, "WelcomeScreen_TextGetStarted");
		else if (oAndUtil.ufElementDisplayed(driver, "Pharmacy_WelcomeScreen_TextGetStarted"))
			oAndUtil.ufClickElement(driver, "Pharmacy_WelcomeScreen_TextGetStarted");

		oAndUtil.ufWaitForElementDisplayed(driver, "WelcomeScreen_OTP_EnterPhoneNumberField", 5);
		if (oAndUtil.ufElementDisplayed(driver, "WelcomeScreen_OTP_EnterPhoneNumberField"))
			bRes_flag = true;
		else
			sErrorMessage = sErrorMessage + "WelcomeScreen_OTP_EnterPhoneNumberField not displayed";

		return bRes_flag;
	}

	public boolean TakeControlToEnterOTPScreen(AppiumDriver<WebElement> driver) throws Exception {
		boolean bRes_flag = false;
		JSONObject oOTP = oAndUtil.oJsonTestData.getJSONObject("OTP Details");
		oAndUtil.ufSendKeysTextBox(driver, "WelcomeScreen_OTP_EnterPhoneNumberField",
				oOTP.get("Phone Number").toString());
		Thread.sleep(2000);
		oAndUtil.ufClickElement(driver, "WelcomeScreen_OTP_ButtonConfirm");
		oAndUtil.ufWaitForElementDisplayed(driver, "WelcomeScreen_OTP_OTPEnter_ExpiryTime", 10);
		if (oAndUtil.ufElementDisplayed(driver, "WelcomeScreen_OTP_OTPEnter_ExpiryTime"))
			bRes_flag = true;
		else
			sErrorMessage = sErrorMessage + "WelcomeScreen_OTP_OTPEnter_ExpiryTime not displayed";

		return bRes_flag;

	}

	public void ufEnterOTPtoScreen(AppiumDriver<WebElement> driver, String OTP) throws Exception {
		/*String[] splitOTP = OTP.split("");
		oAndUtil.ufSendKeysTextBox(driver, "WelcomeScreen_OTP_OTPEnter_digitBox1", splitOTP[0]);
		oAndUtil.ufSendKeysTextBox(driver, "WelcomeScreen_OTP_OTPEnter_digitBox2", splitOTP[1]);
		oAndUtil.ufSendKeysTextBox(driver, "WelcomeScreen_OTP_OTPEnter_digitBox3", splitOTP[2]);
		oAndUtil.ufSendKeysTextBox(driver, "WelcomeScreen_OTP_OTPEnter_digitBox4", splitOTP[3]);
		oAndUtil.ufSendKeysTextBox(driver, "WelcomeScreen_OTP_OTPEnter_digitBox5", splitOTP[4]);
		oAndUtil.ufSendKeysTextBox(driver, "WelcomeScreen_OTP_OTPEnter_digitBox6", splitOTP[5]);*/
		oAndUtil.ufSendKeysTextBox(driver, "WelcomeScreen_OTP_OTPEnter_digitBox1", OTP);
	}

	public boolean TakeControlToSpecifiedScreen(AppiumDriver<WebElement> driver, String slabelRequested)
			throws Exception {
		boolean bRes_flag = true;
		List<WebElement> oliLabel = oAndUtil.ufGetList(driver, "Pharmacy_Home_LabelList");
		if (slabelRequested.contains(oJsonLocalizationData.get("sLabelNew").toString()) 
				|| slabelRequested.contains(oJsonLocalizationData.get("sLabelInProgress").toString())
				|| slabelRequested.contains(oJsonLocalizationData.get("sLabelHistory").toString())) {
			bRes_flag = oCommUtil.ufClickWithList(driver, oliLabel, slabelRequested);
		} else {
			log.info("Element to click on Tab is not listed in Autlmation Repo (should be specified in Constants.java)");
		}
		return bRes_flag;
	}

	public boolean UserData(AppiumDriver<WebElement> driver) throws Exception {
		boolean bRes_flag = false;
		JSONObject oUserProfileDetails1 = oAndUtil.oJsonTestData.getJSONObject("UserProfileDetails_1");
try{
	
	System.out.println("inside try");
		if (oAndUtil.ufWaitForElementDisplayed(driver, "HaloDoc_Complete_Profile_FirstNameTextBox", 10)) {
			oAndUtil.ufSendKeysTextBox(driver, "HaloDoc_Complete_Profile_FirstNameTextBox",
					oUserProfileDetails1.get("FirstName").toString());
			oAndUtil.ufWait(2);
		} else
			sErrorMessage = sErrorMessage + "LastName not entered";

		if (oAndUtil.ufWaitForElementDisplayed(driver, "HaloDoc_Complete_Profile_LastNameTextBox", 10)) {
			oAndUtil.ufSendKeysTextBox(driver, "HaloDoc_Complete_Profile_LastNameTextBox",
					oUserProfileDetails1.get("LastName").toString());
			oAndUtil.ufWait(2);
		} else
			sErrorMessage = sErrorMessage + "LastName not entered";

		oCommUtil.mediumScrollUp(driver);
		oAndUtil.ufWait(2);

		if (oAndUtil.ufWaitForElementDisplayed(driver, "HaloDoc_Complete_Profile_EmailTextbox", 10)) {
			oAndUtil.ufSendKeysTextBox(driver, "HaloDoc_Complete_Profile_EmailTextbox",
					oUserProfileDetails1.get("Email").toString());
			oAndUtil.ufWait(2);
		} else
			sErrorMessage = sErrorMessage + "Email not entered";
		
		
		if(oAndUtil.ufWaitForElementDisplayed(driver, "HaloDoc_Complete_Profile_DOBTextbox", 2)){
		
		oAndUtil.ufClickElement(driver, "HaloDoc_Complete_Profile_DOBTextbox");

		List<WebElement> liCalendarDateList = oAndUtil.ufGetList(driver, "HaloDoc_Complete_Profile_Calendar");
		System.out.println(liCalendarDateList.size());

		WebElement element = liCalendarDateList.get(0);
		String sExpectedValue = "25";
		oAndUtil.ufScrollDownToExpectedValue(element, driver, sExpectedValue, 35);
		element = liCalendarDateList.get(1);
		sExpectedValue = "Aug";
		oAndUtil.ufScrollDownToExpectedValue(element, driver, sExpectedValue, 13);

		element = liCalendarDateList.get(2);
		sExpectedValue = "2007";
		oAndUtil.ufScrollDownToExpectedValue(element, driver, sExpectedValue, 40);
		oAndUtil.ufClickElement(driver, "HaloDoc_Complete_Profile_CalendarOkButton");
		}
		else
				sErrorMessage = sErrorMessage + "Date not entered";

		if (oAndUtil.ufElementDisplayed(driver, "HaloDoc_Complete_Profile_GenderPopUp")) {
			oAndUtil.ufClickElement(driver, "HaloDoc_Complete_Profile_GenderPopUp");
			oAndUtil.ufWait(2);
			oAndUtil.ufClickElement(driver, "HaloDoc_Complete_Profile_Gender_Female");
		} else
			sErrorMessage = sErrorMessage + "Gender Popup is not displayed";

		oCommUtil.scrollUp(driver);
		oAndUtil.ufWait(2);

		if (oAndUtil.ufWaitForElementDisplayed(driver, "HaloDoc_Complete_Profile_WeightTextBox", 10)) {
			oAndUtil.ufSendKeysTextBox(driver, "HaloDoc_Complete_Profile_WeightTextBox",
					oUserProfileDetails1.get("Weight").toString());
			oAndUtil.ufWait(2);
		} else
			sErrorMessage = sErrorMessage + "Weight not entered";

		if (oAndUtil.ufWaitForElementDisplayed(driver, "HaloDoc_Complete_Profile_HeightTextBox", 10)) {
			oAndUtil.ufSendKeysTextBox(driver, "HaloDoc_Complete_Profile_HeightTextBox",
					oUserProfileDetails1.get("Height").toString());
			oAndUtil.ufWait(2);
		} else
			sErrorMessage = sErrorMessage + "Height not entered";

		if (oAndUtil.ufWaitForElementDisplayed(driver, "HaloDoc_Complete_Profile_ProceedBtn", 10)) {
			oAndUtil.ufClickElement(driver, "HaloDoc_Complete_Profile_ProceedBtn");
			oAndUtil.ufWait(2);
		} else
			sErrorMessage = sErrorMessage + "Unabe to click on Proceed button";
		return bRes_flag;
}
	catch (Exception e){
		System.out.println(e);
		
	}
	return bRes_flag;
	}

	public boolean VerifyBuyingList(AppiumDriver<WebElement> driver) throws Exception {
		boolean bRes_flag = false;
		oAndUtil.ufClickElement(driver, "HaloDoc_CartStrip_BuyingListView");
		List<WebElement> BuyingForListView = oAndUtil.ufGetList(driver, "HaloDoc_CartStrip_BuyingListView");
		if (BuyingForListView != null) {
			oAndUtil.ufClickElement(driver, "HaloDoc_CartStrip_BuyingListView");
			for (int i = 0; i < BuyingForListView.size(); i++) {
				String sText = BuyingForListView.get(i).getText();
				System.out.println("The Buying List Elements are :" + sText);
			}

		}
		return bRes_flag;
	}

	public boolean TakeControlFromHomeConsumerToProfile(AppiumDriver<WebElement> driver) throws Exception {
		boolean bRes_flag = false;

		oAndUtil.ufClickElement(driver, "HaloDoc_ProfilePage");
		oAndUtil.ufWaitForElementDisplayed(driver, "HaloDoc_CompleteYourProfile", 5);
		oAndUtil.ufClickElement(driver, "HaloDoc_CompleteYourProfile");
		driver.hideKeyboard();
		oCommUtil.minScrollUp(driver);
		driver.getKeyboard();

		oAndUtil.ufClickElement(driver, "HaloDoc_Complete_Profile_DOBTextbox");

		List<WebElement> liCalendarDateList = oAndUtil.ufGetList(driver, "HaloDoc_Complete_Profile_Calendar");
		System.out.println(liCalendarDateList.size());

		WebElement element = liCalendarDateList.get(0);
		String sExpectedValue = "25";
		oAndUtil.ufScrollDownToExpectedValue(element, driver, sExpectedValue, 35);
		element = liCalendarDateList.get(1);
		sExpectedValue = "Aug";
		oAndUtil.ufScrollDownToExpectedValue(element, driver, sExpectedValue, 13);

		element = liCalendarDateList.get(2);
		sExpectedValue = "2007";
		oAndUtil.ufScrollDownToExpectedValue(element, driver, sExpectedValue, 40);
		oAndUtil.ufClickElement(driver, "HaloDoc_Complete_Profile_CalendarOkButton");
		return bRes_flag;
	}

	
	public boolean TakeControlToProfilePage(AppiumDriver<WebElement> driver) throws Exception{
		boolean bRes_flag = false;
		oAndUtil.ufClickElement(driver, "HaloDoc_ProfilePage");
		oAndUtil.ufWaitForElementDisplayed(driver, "HaloDoc_CompleteYourProfile", 5);
		oAndUtil.ufClickElement(driver, "HaloDoc_CompleteYourProfile");
		driver.hideKeyboard();
		return bRes_flag;
	}
	
	public void TakeControlToHomePage(AppiumDriver<WebElement> driver) throws Exception{
		oAndUtil.ufClickElement(driver, "CONSUMER_HOME");
	}
}
