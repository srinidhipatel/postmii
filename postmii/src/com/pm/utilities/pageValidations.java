package com.pm.utilities;

import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class pageValidations extends TestBase {

	public boolean CheckGetStartedScreenOTP(AppiumDriver<WebElement> driver) throws Exception {
		boolean bRes_Flag = false, bOTP = true, bLogo = true, bWT1 = true, bWT2 = true, bWT3 = true, bWT4 = true,
				bWT5 = true, bWT6 = true;

		if (!oAndUtil.ufElementGetText(driver, "WelcomeScreen_TextGetStarted", true).contains(oJsonLocalizationData.get("sGetStarted").toString())) {
			bOTP = false;
			sErrorMessage = sErrorMessage + "WelcomeScreen_TextGetStarted get Text Fail ";
		}
		if (!oAndUtil.ufElementGetText(driver, "WelcomeScreen_TextShoppingDrugDeliveredQuickly ", true)
				.contains(oJsonLocalizationData.get("sShoppingDrugDeliveredQuickly").toString())) {
			bWT1 = false;
			sErrorMessage = sErrorMessage + "WelcomeScreen_TextShoppingDrugDeliveredQuickly get Text Fail";
		}
		if (!oAndUtil.ufElementGetText(driver, "WelcomeScreen_TextDiscoverGeneralPractitionersAndSpecialists_24", true)
				.contains(oJsonLocalizationData.get("sDiscoverGeneralPractitionersAndSpecialists_24").toString())) {
			bWT2 = false;
			sErrorMessage = sErrorMessage
					+ "WelcomeScreen_TextDiscoverGeneralPractitionersAndSpecialists_24 get Text Fail";
		}
		if (!oAndUtil.ufElementGetText(driver, "WelcomeScreen_TextReliableAnswersToYourMedicalNeeds", true)
				.contains(oJsonLocalizationData.get("sReliableAnswersToYourMedicalNeeds").toString())) {
			bWT3 = false;
			sErrorMessage = sErrorMessage + "WelcomeScreen_TextReliableAnswersToYourMedicalNeeds get Text Fail";
		}

		if (!oAndUtil.ufElementGetText(driver, "WelcomeScreen_TextByPressingTheButtonBelowIAgreeWith", true)
				.contains(oJsonLocalizationData.get("sByPressingTheButtonBelowIAgreeWith").toString())) {
			bWT4 = false;
			sErrorMessage = sErrorMessage + "WelcomeScreen_TextByPressingTheButtonBelowIAgreeWith get Text Fail";
		}
		if (!oAndUtil.ufElementGetText(driver, "WelcomeScreen_TextTermAndCondition", true)
				.contains(oJsonLocalizationData.get("sTermAndCondition").toString())) {
			bWT5 = false;
			sErrorMessage = sErrorMessage + "WelcomeScreen_TextTermAndCondition get Text Fail";
		}
		if (!oAndUtil.ufElementGetText(driver, "WelcomeScreen_TextsSetByHaloDoc", true)
				.contains(oJsonLocalizationData.get("sSetByHaloDoc").toString())) {
			bWT6 = false;
			sErrorMessage = sErrorMessage + "WelcomeScreen_TextsSetByHaloDoc get Text Fail";
		}
		if (bOTP && bLogo && bWT1 && bWT2 && bWT3 && bWT4 && bWT5 && bWT6)
			bRes_Flag = true;
		else
			bRes_Flag = false;

		return bRes_Flag;
	}

	public boolean CheckOTPScreenAllFields(AppiumDriver<WebElement> driver) throws Exception {
		boolean bRes_flag = true, bBackButton = true, bCFlag = true, bCCode = true, bPhNumSpace = true,
				bTextPaTitle = true, bTextEnMobNum = true;
		log.info(driver.findElement(By.id("tv_country_code")).getText());
		log.info(oJsonLocalizationData.get("sWelcomToHalodoc").toString());
		
		if (!oAndUtil.ufElementDisplayed(driver, "WelcomeScreen_OTP_CountryFlag")) {
			bCFlag = false;
			sErrorMessage = sErrorMessage + "WelcomeScreen_OTP_CountryFlag not Displayed";
		}
		if (!oAndUtil.ufElementDisplayed(driver, "WelcomeScreen_OTP_CountryCode")) {
			bCCode = false;
			sErrorMessage = sErrorMessage + "WelcomeScreen_OTP_CountryCode not Displayed";
		}
		if (!oAndUtil.ufElementDisplayed(driver, "WelcomeScreen_OTP_EnterPhoneNumberField")) {
			bPhNumSpace = false;
			sErrorMessage = sErrorMessage + "WelcomeScreen_OTP_EnterPhoneNumberField not Displayed";
		}
		
		if(TestBase.sLocalization.equalsIgnoreCase(TestBase.sLocalEnglish))
		{
			if (!oAndUtil.ufElementDisplayed(driver, "WelcomeScreen_OTP_NavigateBackButton")) {
				bBackButton = false;
				sErrorMessage = sErrorMessage + "WelcomeScreen_OTP_NavigateBackButton not Displayed";
			}
		}
		else if(TestBase.sLocalization.equalsIgnoreCase(TestBase.sLocalIndonasia))
			if (!oAndUtil.ufElementDisplayed(driver, "WelcomeScreen_OTP_NavigateBackButton_Indonasia")) {
				bBackButton = false;
				sErrorMessage = sErrorMessage + "WelcomeScreen_OTP_NavigateBackButton not Displayed";
			}
			//oAndUtil.ufClickElement(driver, "WelcomeScreen_OTP_NavigateBackButton_Indonasia");
		/*if (!oAndUtil.ufElementDisplayed(driver, "WelcomeScreen_OTP_NavigateBackButton")) {
			bBackButton = false;
			sErrorMessage = sErrorMessage + "WelcomeScreen_OTP_NavigateBackButton not Displayed";
		}*/
		
		
		if (!oAndUtil.ufElementGetText(driver, "WelcomeScreen_OTP_TextPageTitle", true).contains(oJsonLocalizationData.get("sWelcomToHalodoc").toString()))
				{
			bTextPaTitle = false;
			sErrorMessage = sErrorMessage + "WelcomeScreen_OTP_TextPageTitle not Displayed";
		}
		if (!oAndUtil.ufElementDisplayed(driver, "WelcomeScreen_OTP_TextEnterYourMobileNumber")) {
			bTextEnMobNum = false;
			sErrorMessage = sErrorMessage + "WelcomeScreen_OTP_TextEnterYourMobileNumber not Displayed";
		}
		if (!oAndUtil.ufElementGetText(driver, "WelcomeScreen_OTP_TextEnterYourMobileNumber", true)
				.contains(oJsonLocalizationData.get("sEnterYourPhoneNumber").toString())) {
			bTextEnMobNum = false;
			sErrorMessage = sErrorMessage + "WelcomeScreen_OTP_TextEnterYourMobileNumber not Displayed";
		}
		if (bBackButton && bCFlag && bCCode && bPhNumSpace && bTextPaTitle && bTextEnMobNum)
			bRes_flag = true;
		else
			bRes_flag = false;

		return bRes_flag;
	}

	public boolean CheckOTPEntryScreenAllFields(AppiumDriver<WebElement> driver) throws Exception {
		boolean bRes_flag = true, bBackButton = true, bDBox1 = true, bDBox2 = true, bDBox3 = true, bDBox4 = true,
				bDBox5 = true, bDBox6 = true, bExpiryTime = true, bTextPaTitle = true, bTextEnMobNum = true,
				bSixDigVer = true;
		if (!oAndUtil.ufElementDisplayed(driver, "WelcomeScreen_OTP_OTPEnter_digitBox1")) {
			bDBox1 = false;
			sErrorMessage = sErrorMessage + "WelcomeScreen_OTP_OTPEnter_digitBox1 not Displayed";
		}
		if (!oAndUtil.ufElementDisplayed(driver, "WelcomeScreen_OTP_OTPEnter_digitBox2")) {
			bDBox2 = false;
			sErrorMessage = sErrorMessage + "WelcomeScreen_OTP_OTPEnter_digitBox2 not Displayed";
		}
		if (!oAndUtil.ufElementDisplayed(driver, "WelcomeScreen_OTP_OTPEnter_digitBox3")) {
			bDBox3 = false;
			sErrorMessage = sErrorMessage + "WelcomeScreen_OTP_OTPEnter_digitBox3 not Displayed";
		}
		if (!oAndUtil.ufElementDisplayed(driver, "WelcomeScreen_OTP_OTPEnter_digitBox4")) {
			bDBox4 = false;
			sErrorMessage = sErrorMessage + "WelcomeScreen_OTP_OTPEnter_digitBox4 not Displayed";
		}
		if (!oAndUtil.ufElementDisplayed(driver, "WelcomeScreen_OTP_OTPEnter_digitBox5")) {
			bDBox5 = false;
			sErrorMessage = sErrorMessage + "WelcomeScreen_OTP_OTPEnter_digitBox5 not Displayed";
		}
		if (!oAndUtil.ufElementDisplayed(driver, "WelcomeScreen_OTP_OTPEnter_digitBox6")) {
			bDBox6 = false;
			sErrorMessage = sErrorMessage + "WelcomeScreen_OTP_OTPEnter_digitBox6 not Displayed";
		}

		if (!oAndUtil.ufElementDisplayed(driver, "WelcomeScreen_OTP_OTPEnter_ExpiryTime")) {
			bExpiryTime = false;
			sErrorMessage = sErrorMessage + "WelcomeScreen_OTP_OTPEnter_ExpiryTime not Displayed";
		}
		if (!oAndUtil.ufElementDisplayed(driver, "WelcomeScreen_OTP_OTPEnter_NavigateBackButton")) {
			bBackButton = false;
			sErrorMessage = sErrorMessage + "WelcomeScreen_OTP_OTPEnter_NavigateBackButton not Displayed";
		}
		if (!oAndUtil.ufElementGetText(driver, "WelcomeScreen_OTP_OTPEnter_TextPageTitle", true)
				.contains(oJsonLocalizationData.get("sWelcomToHalodoc").toString())) {
			bTextPaTitle = false;
			sErrorMessage = sErrorMessage + "WelcomeScreen_OTP_OTPEnter_TextPageTitle Get Text fail ";
		}
		if (!oAndUtil.ufElementDisplayed(driver, "WelcomeScreen_OTP_OTPEnter_MobileNumber")) {
			bTextEnMobNum = false;
			sErrorMessage = sErrorMessage + "WelcomeScreen_OTP_TextEnterYourMobileNumber not Displayed";
		}
		if (!oAndUtil.ufElementGetText(driver, "WelcomeScreen_OTP_OTPEnter_TextEnterThe6DigitVerification", true)
				.contains(oJsonLocalizationData.get("sEnterThe6DigitVerificationHasBeenSentToYourPhoneNumber").toString())) {
			bSixDigVer = false;
			sErrorMessage = sErrorMessage + "WelcomeScreen_OTP_OTPEnter_TextEnterThe6DigitVerification not Displayed";
		}

		if (bBackButton && bDBox1 && bDBox2 && bDBox3 && bDBox4 && bDBox5 && bDBox6 && bExpiryTime && bTextPaTitle
				&& bTextEnMobNum && bSixDigVer)
			bRes_flag = true;
		else
			bRes_flag = false;

		return bRes_flag;
	}

	public boolean VerifyInvalidOTPErrorScreen(AppiumDriver<WebElement> driver) throws Exception {

		boolean bRes_flag = false, bInScreTitl = false, bInOTPDia = false, bInOTPButOK = false;
		if (oAndUtil.ufElementDisplayed(driver, "WelcomeScreen_OTP_OTPEnter_InvalidOTPScreenTitle")) {
			if (oAndUtil.ufElementGetText(driver, "WelcomeScreen_OTP_OTPEnter_InvalidOTPScreenTitle", true)
					.contains(oJsonLocalizationData.get("sSorry").toString()))
				bInScreTitl = true;
			else
				sErrorMessage = sErrorMessage + "WelcomeScreen_OTP_OTPEnter_InvalidOTPScreenTitle get text Fail";
		} else
			sErrorMessage = sErrorMessage + "WelcomeScreen_OTP_OTPEnter_InvalidOTPScreenTitle not Displayed";

		if (oAndUtil.ufElementDisplayed(driver, "WelcomeScreen_OTP_OTPEnter_InvalidOTPDialogueMessage")) {
			if (oAndUtil.ufElementGetText(driver, "WelcomeScreen_OTP_OTPEnter_InvalidOTPDialogueMessage", true)
					.contains(oJsonLocalizationData.get("sIncorrectVerificationCode").toString()))
				bInOTPDia = true;
			else
				sErrorMessage = sErrorMessage + "WelcomeScreen_OTP_OTPEnter_InvalidOTPDialogueMessage get text Fail";
		} else
			sErrorMessage = sErrorMessage + "WelcomeScreen_OTP_OTPEnter_InvalidOTPDialogueMessage not Displayed";

		if (oAndUtil.ufElementDisplayed(driver, "WelcomeScreen_OTP_OTPEnter_InvalidOTPbuttonOK")) {
			if (oAndUtil.ufElementGetText(driver, "WelcomeScreen_OTP_OTPEnter_InvalidOTPbuttonOK", true)
					.contains(oJsonLocalizationData.get("sOK").toString()))
				bInOTPButOK = true;
			else
				sErrorMessage = sErrorMessage + "WelcomeScreen_OTP_OTPEnter_InvalidOTPbuttonOK get text Fail";
		} else
			sErrorMessage = sErrorMessage + "WelcomeScreen_OTP_OTPEnter_InvalidOTPDialogueMessage not Displayed";

		if (bInScreTitl && bInOTPDia && bInOTPButOK)
			bRes_flag = true;

		return bRes_flag;
	}

	public boolean VerifyConsumerHomeScreen(AppiumDriver<WebElement> driver) throws Exception {
		boolean bRes_flag = false, bTabHome = false, bTabProfile = false;
		if (oAndUtil.ufElementDisplayed(driver, "CONSUMER_HOME"))
			bTabHome = true;
		else
			sErrorMessage = sErrorMessage + "CONSUMER_HOME not Displayed";
		if (oAndUtil.ufElementDisplayed(driver, "CONSUMER_PROFILE"))
			bTabHome = true;
		else
			sErrorMessage = sErrorMessage + "CONSUMER_PROFILE not Displayed";

		if (bTabHome && bTabProfile)
			bRes_flag = true;

		return bRes_flag;
	}

	public boolean CheckPharmacyGetStartedScreenOTP(AppiumDriver<WebElement> driver) throws Exception {
		boolean bRes_Flag = false, bOTP = true, bLogo = true, bWT1 = true, bWT2 = true, bWT3 = true, bWT4 = true,
				bWT5 = true, bWT6 = true;

		if (!oAndUtil.ufElementGetText(driver, "Pharmacy_WelcomeScreen_TextGetStarted", true)
				.contains(oJsonLocalizationData.get("sGetStarted_Pharmacy").toString())) {
			bOTP = false;
			sErrorMessage = sErrorMessage + "Pharmacy_WelcomeScreen_TextGetStarted get Text Fail ";
		}
		if (!oAndUtil.ufElementGetText(driver, "Pharmacy_WelcomeScreen_TextReachMoreCustomers", true)
				.contains(oJsonLocalizationData.get("sReachMoreCustomers").toString())) {
			bWT1 = false;
			sErrorMessage = sErrorMessage + "Pharmacy_WelcomeScreen_TextReachMoreCustomers get Text Fail";
		}
		if (!oAndUtil.ufElementGetText(driver, "Pharmacy_WelcomeScreen_TextIncreaseSales", true)
				.contains(oJsonLocalizationData.get("sIncreaseSales").toString())) {
			bWT2 = false;
			sErrorMessage = sErrorMessage + "Pharmacy_WelcomeScreen_TextIncreaseSales get Text Fail";
		}
		if (!oAndUtil.ufElementGetText(driver, "Pharmacy_WelcomeScreen_TextFacilitateDeliveryOfOrders", true)
				.contains(oJsonLocalizationData.get("sFacilitateDeliveryOfOrders").toString())) {
			bWT3 = false;
			sErrorMessage = sErrorMessage + "Pharmacy_WelcomeScreen_TextFacilitateDeliveryOfOrders get Text Fail";
		}

		if (!oAndUtil.ufElementGetText(driver, "Pharmacy_WelcomeScreen_TextAgreeToTheTermsConditions", true)
				.contains(oJsonLocalizationData.get("sAgreeToTheTermsConditions").toString())) {
			bWT5 = false;
			sErrorMessage = sErrorMessage + "Pharmacy_WelcomeScreen_TextAgreeToTheTermsConditions get Text Fail";
		}

		if (bOTP && bLogo && bWT1 && bWT2 && bWT3 && bWT4 && bWT5)
			bRes_Flag = true;
		else
			bRes_Flag = false;

		return bRes_Flag;
	}

	public boolean checkPharmacyNewItemScreen(AppiumDriver<WebElement> driver) throws Exception {
		boolean bRes_flag = true, bNavBack = true, bPhName = true, bIcon = true, bIconBadge = true, bLabelNew = true,
				bLabelInprogress = true, bLabelHistory = true, bOrderID = true, bCusName = true, bAmount = true,
				bMinOrders = true;
		if (!oAndUtil.ufElementDisplayed(driver, "Pharmacy_Home_ButtonNavigateUp")) {
			bNavBack = false;
			sErrorMessage = sErrorMessage + "Pharmacy_Home_ButtonNavigateUp not Displayed";
		}
		if (!oAndUtil.ufElementDisplayed(driver, "Pharmacy_Home_NameOfPharmacy")) {
			bNavBack = false;
			sErrorMessage = sErrorMessage + "Pharmacy_Home_NameOfPharmacy not Displayed";
		}
		if (oAndUtil.ufGetList(driver, "Pharmacy_Home_Icon").size() != 3) {
			bIcon = false;
			sErrorMessage = sErrorMessage + "Pharmacy_Home_Icon length is "
					+ oAndUtil.ufGetList(driver, "Pharmacy_Home_Icon").size();
		}
		if (oAndUtil.ufGetList(driver, "Pharmacy_Home_TextNewMessageCountList").size() != 2) {
			bIconBadge = false;
			sErrorMessage = sErrorMessage + "Pharmacy_Home_TextNewMessageCountList length is "
					+ oAndUtil.ufGetList(driver, "Pharmacy_Home_TextNewMessageCountList").size();
		}
		List<WebElement> oliLabel = oAndUtil.ufGetList(driver, "Pharmacy_Home_LabelList");

		if (!oCommUtil.ufVerifyTextInsideList(oliLabel, oJsonLocalizationData.get("sLabelNew").toString())) {
			bLabelNew = false;
			sErrorMessage = sErrorMessage + "Pharmacy_Home_LabelList Not having New Tab";
		}
		if (!oCommUtil.ufVerifyTextInsideList(oliLabel, oJsonLocalizationData.get("sLabelInProgress").toString())) {
			bLabelInprogress = false;
			sErrorMessage = sErrorMessage + "Pharmacy_Home_LabelList Not having In Progress Tab";
		}
		if (!oCommUtil.ufVerifyTextInsideList(oliLabel, oJsonLocalizationData.get("sLabelHistory").toString())) {
			bLabelHistory = false;
			sErrorMessage = sErrorMessage + "Pharmacy_Home_LabelList Not having In History Tab";
		}

		// Minimum five order should be there in main screen
		List<WebElement> oliOrderID = oAndUtil.ufGetList(driver, "Pharmacy_Home_OrderID");
		if (oliOrderID.size() > 5) {
			bMinOrders = false;
			sErrorMessage = sErrorMessage + "(Minimum Five Order) More Orders in main screen ";
		}
		if (!oAndUtil.ufElementDisplayed(driver, "Pharmacy_Home_CustomerName")) {
			bCusName = false;
			sErrorMessage = sErrorMessage + "Pharmacy_Home_CustomerName not Displayed";
		}
		if (!oAndUtil.ufElementDisplayed(driver, "Pharmacy_Home_Amount")) {
			bAmount = false;
			sErrorMessage = sErrorMessage + "Pharmacy_Home_Amount not Displayed";
		}
		if (!oAndUtil.ufElementDisplayed(driver, "Pharmacy_Home_OrderID")) {
			bOrderID = false;
			sErrorMessage = sErrorMessage + "Pharmacy_Home_OrderID not Displayed";
		}

		if (bNavBack && bPhName && bIcon && bIconBadge && bLabelHistory && 
				bLabelNew && bLabelInprogress && bOrderID && bCusName && bAmount && bMinOrders)
			bRes_flag = true;
		else
			bRes_flag = false;

		return bRes_flag;
	}
	
	
	
		public boolean HalodocConsumerProfilePageverification(AppiumDriver<WebElement> driver) throws Exception{

			boolean bRes_flag = false, bBack_btn = false, bPageTitle =false, bWarningText =false, bFirstNameText = false,bFirstNameTextBox = false, bLastNameText = false,
					bLastNameTextBox =false, bPhoneNumberText = false, bPhoneNumber = false, bEmailText = false, bEmailTextbox = false, bDOBText =false, 
					 bDateSelected = false, bGenderText = false, bGenderPopUp = false, bWeightText = false, bWeightTextBox = false, bHeightText = false, bHeightTextBox=false, 
					 bProceedText = false;
try{
	
		System.out.println("Inside Try");
			if (oAndUtil.ufElementDisplayed(driver, "HaloDoc_Complete_Profile_BackButton")){
				oAndUtil.ufWait(2);					
				bBack_btn = true;
			}
			else{
				sErrorMessage =sErrorMessage + "Profile Page Back Button is not displayed";
			}
			
			if(oAndUtil.ufElementDisplayed(driver, "Halodoc_Complete_Profile_PageTitleText")){
				oAndUtil.ufWait(2);
			if(oAndUtil.ufElementGetText(driver, "Halodoc_Complete_Profile_PageTitleText", true).contains(oJsonLocalizationData.get("sPageTitle").toString()))
				bPageTitle = true;
			else 
				sErrorMessage = sErrorMessage  + "Page Title get text failed";
			}
			else
				sErrorMessage = sErrorMessage  + "Page Title element not displayed";
			
			if(oAndUtil.ufElementDisplayed(driver, "HaloDoc_Complete_Profile_WarningMessage")){
				oAndUtil.ufWait(2);
			if(oAndUtil.ufElementGetText(driver, "HaloDoc_Complete_Profile_WarningMessage", true).contains(oJsonLocalizationData.get("sWarningMessage").toString()))
				bWarningText = true;
			else 
				sErrorMessage = sErrorMessage  + "WarningText get text failed";
			}
			else
				sErrorMessage = sErrorMessage  + "Warning Text element not displayed";
		
			if (oAndUtil.ufElementDisplayed(driver, "HaloDoc_Complete_Profile_FirstNameTextBox")){
				oAndUtil.ufWait(2);					
				bFirstNameTextBox = true;
			}
			else{
				sErrorMessage =sErrorMessage + "FirstName Textbox is not displayed";
			}
			

			if(oAndUtil.ufElementDisplayed(driver, "HaloDoc_Complete_Profile_FirstNameText")){
				oAndUtil.ufWait(2);
			if(oAndUtil.ufElementGetText(driver, "HaloDoc_Complete_Profile_FirstNameText", true).contains(oJsonLocalizationData.get("sFirstNameText").toString()))
				bFirstNameText = true;
			else 
				sErrorMessage = sErrorMessage  + "First Name get text failed";
			}
			else
				sErrorMessage = sErrorMessage  + "FirstNameText element is not displayed";
			
			if (oAndUtil.ufElementDisplayed(driver, "HaloDoc_Complete_Profile_LastNameTextBox")){
				oAndUtil.ufWait(2);					
				bLastNameTextBox = true;
			}
			else{
				sErrorMessage =sErrorMessage + "LastName Textbox element is not displayed";
			}
			

			if(oAndUtil.ufElementDisplayed(driver, "HaloDoc_Complete_Profile_LastNameText")){
				oAndUtil.ufWait(2);
			if(oAndUtil.ufElementGetText(driver, "HaloDoc_Complete_Profile_LastNameText", true).contains(oJsonLocalizationData.get("sLastNameText").toString()))
				bLastNameText = true;
			else 
				sErrorMessage = sErrorMessage  + "Last Name get text failed";
			}
			else
				sErrorMessage = sErrorMessage  + "LastName Text element is not displayed";
			
			if(oAndUtil.ufElementDisplayed(driver, "HaloDoc_Complete_Profile_PhoneNumberText")){
				oAndUtil.ufWait(2);
			if(oAndUtil.ufElementGetText(driver, "HaloDoc_Complete_Profile_PhoneNumberText", true).contains(oJsonLocalizationData.get("sPhoneNumberText").toString()))
				bPhoneNumberText = true;
			else 
				sErrorMessage = sErrorMessage  + "Phone NumberText get text failed";
			}
			else
				sErrorMessage = sErrorMessage  + "PhoneNumber Text element is not displayed";
			
			if(oAndUtil.ufElementDisplayed(driver, "HaloDoc_Complete_Profile_PhoneNumber")){
				oAndUtil.ufWait(2);
			if(oAndUtil.ufElementGetText(driver, "HaloDoc_Complete_Profile_PhoneNumber", true).contains(oJsonLocalizationData.get("sPhoneNumber").toString()))
				bPhoneNumber = true;
			else 
				sErrorMessage = sErrorMessage  + "PhoneNumber get text failed";
			}
			else
				sErrorMessage = sErrorMessage  + "PhoneNumber element is not displayed";

			
			//driver.hideKeyboard();
			//oAndUtil.ufWait(2);
			
			oCommUtil.scrollUp(driver);
			oAndUtil.ufWait(2);
			
			//driver.getKeyboard();
		
				if(oAndUtil.ufElementDisplayed(driver, "HaloDoc_Complete_Profile_EmailTextbox")){
					bEmailTextbox = true;
					}
				else{
					sErrorMessage =sErrorMessage + "Email text box is not displayed";
				}
				if(oAndUtil.ufElementDisplayed(driver, "HaloDoc_Complete_Profile_EmailText")){
					oAndUtil.ufWait(4);
				if(oAndUtil.ufElementGetText(driver, "HaloDoc_Complete_Profile_EmailText", true).contains(oJsonLocalizationData.get("sEmailText").toString()))
					bEmailText = true;
				else 
					sErrorMessage = sErrorMessage  + "Email Text get text failed";
				}
				
				if(oAndUtil.ufElementDisplayed(driver, "HaloDoc_Complete_Profile_DOBText")){
					oAndUtil.ufWait(4);
				if(oAndUtil.ufElementGetText(driver, "HaloDoc_Complete_Profile_DOBText", true).contains(oJsonLocalizationData.get("sDOBText").toString()))
					bDOBText = true;
				else 
					sErrorMessage = sErrorMessage  + "DOB get text failed";
				}
				
				
				if(oAndUtil.ufElementDisplayed(driver, "HaloDoc_Complete_Profile_DOBTextbox")){
					oAndUtil.ufClickElement(driver, "HaloDoc_Complete_Profile_DOBTextbox");
					oAndUtil.ufWait(2);
				
					oAndUtil.ufWaitForElementDisplayed(driver, "HaloDoc_Complete_Profile_Calendar_Layout", 2);
					oAndUtil.ufClickElement(driver, "HaloDoc_Complete_Profile_CancelButton");
					bDateSelected = true;
				}
				else{
					sErrorMessage =sErrorMessage + "DOB pop element is not displayed";
				}
				
				oAndUtil.ufWait(2);
				
				if(oAndUtil.ufElementDisplayed(driver, "HaloDoc_Complete_Profile_GenderText")){
					oAndUtil.ufWait(2);
				if(oAndUtil.ufElementGetText(driver, "HaloDoc_Complete_Profile_GenderText", true).contains(oJsonLocalizationData.get("sGenderText").toString()))
					bGenderText = true;
				else 
					sErrorMessage = sErrorMessage  + "Gender get text failed";
				}
				else
					sErrorMessage = sErrorMessage  + "GenderText element not displayed";
				
				//GenderPop
				oAndUtil.ufClickElement(driver, "HaloDoc_Complete_Profile_GenderPopUp");
				if(oAndUtil.ufWaitForElementDisplayed(driver, "HaloDoc_Complete_Profile_GenderLayout", 2)){
				oAndUtil.ufElementGetText(driver, "HaloDoc_Complete_Profile_Gender_Male", true).contains(oJsonLocalizationData.get("sMale").toString());
				oAndUtil.ufClickElement(driver, "HaloDoc_Complete_Profile_Gender_Male");
				bGenderPopUp= true;
				}
				else
					sErrorMessage = sErrorMessage  + "Gender Popup element not displayed";
				
				
				//Verifying Weight Text
				if(oAndUtil.ufElementDisplayed(driver, "HaloDoc_Complete_Profile_WeightText")){
					oAndUtil.ufWait(2);
				if(oAndUtil.ufElementGetText(driver, "HaloDoc_Complete_Profile_WeightText", true).contains(oJsonLocalizationData.get("sWeight").toString()))
					bWeightText = true;
				else 
					sErrorMessage = sErrorMessage  + "Weight get text failed";
				}
				else
					sErrorMessage = sErrorMessage  + "Weight Text element not displayed";
				
				//Verifying Weight Textbox
				if(oAndUtil.ufElementDisplayed(driver, "HaloDoc_Complete_Profile_WeightTextBox")){
					bWeightTextBox = true;
				}
				else{
					sErrorMessage = sErrorMessage  + "Weight TextBox element not displayed";
				}
				
				//Verifying Height Text
				if(oAndUtil.ufElementDisplayed(driver, "HaloDoc_Complete_Profile_HeightText")){
					oAndUtil.ufWait(2);
				if(oAndUtil.ufElementGetText(driver, "HaloDoc_Complete_Profile_HeightText", true).contains(oJsonLocalizationData.get("sHeight").toString()))
					bHeightText = true;
				else 
					sErrorMessage = sErrorMessage  + "Height get text failed";
				}
				else
					sErrorMessage = sErrorMessage  + "Height Text element not displayed";
				
				//Verifying Height Textbox
				if(oAndUtil.ufElementDisplayed(driver, "HaloDoc_Complete_Profile_HeightTextBox")){
					bHeightTextBox = true;
				}
				else{
					sErrorMessage = sErrorMessage  + "Height TextBox element not displayed";
				}
				
				if(oAndUtil.ufElementDisplayed(driver, "HaloDoc_Complete_Profile_ProceedBtn")){
					oAndUtil.ufWait(2);
				if(oAndUtil.ufElementGetText(driver, "HaloDoc_Complete_Profile_ProceedBtn", true).contains(oJsonLocalizationData.get("sProceed").toString()))
					bProceedText = true;
				else 
					sErrorMessage = sErrorMessage  + "Proceed get text failed";
				}
				
					oAndUtil.ufClickElement(driver, "HaloDoc_Complete_Profile_ProceedBtn");
					oAndUtil.ufClickElement(driver, "HaloDoc_Complete_Profile_BackButton");
					oAndUtil.ufWait(2);
				
				
			if(bBack_btn && bDateSelected && bDOBText && bEmailText && bEmailTextbox && bFirstNameText && bFirstNameTextBox && bGenderPopUp && bGenderText
					&& bHeightText && bHeightTextBox && bLastNameText && bLastNameTextBox && bPageTitle && bPhoneNumber && bPhoneNumberText && bProceedText && bWarningText 
					&& bWeightText && bWeightTextBox == true){
				bRes_flag =true;
			}
				else 
					bRes_flag = false;
}
			catch(Exception e){
				System.out.println(e);
				
			}
			return bRes_flag;	

}


		public boolean ApotikPageVerification(AppiumDriver<WebElement> driver) throws Exception{
			boolean bRes_flag = false, bPartnerText = false, bSearchText = false, bSlideShowImage = false, bDeliveryAddress = false;
			if(oAndUtil.ufElementDisplayed(driver, "Apotik_Antar_ParternText")){
				oAndUtil.ufWait(2);
				oAndUtil.ufElementGetText(driver, "Apotik_Antar_ParternText", true).contains(oJsonLocalizationData.get("sPartnerText").toString());
				bPartnerText = true;
			}
			else
				sErrorMessage = sErrorMessage + "In Partnership with Go-Jek is not displayed";
			
			if(oAndUtil.ufElementDisplayed(driver, "Apotik_Antar_SearchboxText")){
				oAndUtil.ufWait(2);
				oAndUtil.ufElementGetText(driver, "Apotik_Antar_SearchboxText", true).contains(oJsonLocalizationData.get("sSearchboxText").toString());
				bSearchText = true;
			}
			else
				sErrorMessage = sErrorMessage + "Search medicine name or symptom is not displayed";
				
//			if(oAndUtil.ufElementDisplayed(driver, "Apotik_Antar_SlideShowImage")){
//			
//				oAndUtil.ufWait(2);
//				bSlideShowImage = true;
//			}
//			else
//				sErrorMessage = sErrorMessage + "SlideShowImage is not displayed";
//			
			oAndUtil.ufWait(4);
			
			if(oAndUtil.ufElementDisplayed(driver, "Apotik_Antar_DeliveryAddressBar")){
				oAndUtil.ufWait(2);
				oAndUtil.ufElementGetText(driver, "Apotik_Antar_DeliveryAddressBar", true).contains(oJsonLocalizationData.get("sDeliveryText").toString());
				bDeliveryAddress = true;
			}
			else
				sErrorMessage = sErrorMessage + "Delivery Address is not displayed";
			
				return bRes_flag;
		}
		
		public boolean GridElementVerification(AppiumDriver<WebElement> driver) throws Exception{
				boolean bRes_flag = true, result;
				SortedSet<String> Set1 = new TreeSet<String>();
				
				List<String> MainList = Arrays.asList("Alergi", "All categories", "Anti Nyeri", "Antibiotik", "Antiseptik","Asma",
					"Batuk dan Flu", "Consumer Goods", "Demam","Diabetes", "Diare", "Dietery", "Herbal", "Hipertensi", "Ibu dan Anak", "Jantung",
					"Kontrasepsi", "Kulit", "Maag dan Lambung", "Mata", "Otot dan Sendi");
			
			
				if(oAndUtil.ufWaitForElementDisplayed(driver, "Apotik_Antar_CategoryGridItems", 6)){
					
					List<WebElement> liHalodocCategory = driver.findElementsById("category_grid_item_name");
					for(int i =0; i<4;i++){
						String FirstString = liHalodocCategory.get(i).getText();
						Set1.add(FirstString);
					}
				System.out.println("=====Printing FirstSet===== " + Set1);
				
				oCommUtil.scrollUp(driver);
				oAndUtil.ufWait(2);
			
				List<WebElement> list2 = driver.findElementsById("category_grid_item_name");
				for(int i = 0; i<list2.size();i++){
					String SecondString = list2.get(i).getText();
					Set1.add(SecondString);
					}
				System.out.println("=====Printing SecondSet===== " + Set1);
			
//				FinalList.addAll(FirstList);
//				FinalList.addAll(SecondList);
				
					result = Set1.containsAll(MainList);
				
					if(!result){
						bRes_flag = false;
					}
			}
				
			else 
				sErrorMessage = sErrorMessage +"GridElement Verification failed";
				return bRes_flag;
			}	
		
		public boolean FluPageVerification(AppiumDriver<WebElement> driver) throws Exception{
			boolean bRes_flag = false, bFluBackBtn = false, bFluePageTitle = false, bSearchIcon = false, bRequirePrepositionText = false, bMedicineImage = false,
					bMedicineTitle = false, bMedicinePrice = false, bMedicineBuyBtn = false, bMedicineType = false,bMedicineBuyBtnText = false, bMedicineSize = false; ;
			
			if(oAndUtil.ufElementDisplayed(driver, "Apotik_Antar_AllCategories_FluBackBtn")){
				oAndUtil.ufWait(2);
				bFluBackBtn = true;
			}
			else
				sErrorMessage = sErrorMessage + "FluPage Back button is not displayed";
			
			if(oAndUtil.ufElementDisplayed(driver, "Apotik_Antar_AllCategories_FluPageTitle")){
				oAndUtil.ufWait(2);
				oAndUtil.ufElementGetText(driver, "Apotik_Antar_AllCategories_FluPageTitle", true).contains(oJsonLocalizationData.get("sFluPageTitle").toString());
				bFluePageTitle = true;
			}
			else
				sErrorMessage = sErrorMessage + "Flu Page Title Get text failed";
		
			if(oAndUtil.ufElementDisplayed(driver, "Apotik_Antar_AllCategories_MedicineSearchIcon")){
				oAndUtil.ufWait(2);
				bSearchIcon = true;
			}
			else
				sErrorMessage = sErrorMessage + "Search Icon is not displayed";
			
			
			if(oAndUtil.ufElementDisplayed(driver, "Apotik_Antar_AllCategories_RequirePreposition")){
				oAndUtil.ufWait(2);
				oAndUtil.ufElementGetText(driver, "Apotik_Antar_AllCategories_RequirePreposition", true).contains(oJsonLocalizationData.get("sRequirePrescriptionText").toString());
				bRequirePrepositionText = true;
			}
			else
				sErrorMessage = sErrorMessage + "Require Prescription get text failed";
			
				
				if (oAndUtil.ufGetList(driver, "Apotik_Antar_AllCategories_FluIndividualMedicine").size() == 2) {
					bMedicineSize = true;
					sErrorMessage = sErrorMessage + "Flue IndividualMedicine length is "
							+ oAndUtil.ufGetList(driver, "Apotik_Antar_AllCategories_FluIndividualMedicine").size();
				}
				
				
				if(oAndUtil.ufElementDisplayed(driver, "Apotik_Antar_AllCategories_FluIndividualMedicine")){
					oAndUtil.ufWait(2);
					oAndUtil.ufElementDisplayed(driver, "Apotik_Antar_AllCategories_FluMedicineImage");
					bMedicineImage = true;
				}
				else
					sErrorMessage = sErrorMessage + "Medicine image displayed failed";
				
				if(oAndUtil.ufElementDisplayed(driver, "Apotik_Antar_AllCategories_FluMedicineTitle")){
					oAndUtil.ufWait(2);
					oAndUtil.ufElementGetText(driver, "Apotik_Antar_AllCategories_FluMedicineTitle", true).contains(oJsonLocalizationData.get("sFluMedicineTitle").toString());
					bMedicineTitle = true;
				}
				else
					sErrorMessage = sErrorMessage + "Medicine Title verification failed";

				if(oAndUtil.ufElementDisplayed(driver, "Apotik_Antar_AllCategories_FluMedicineType")){
					oAndUtil.ufWait(2);
					oAndUtil.ufElementGetText(driver, "Apotik_Antar_AllCategories_FluMedicineType", true).contains(oJsonLocalizationData.get("sFluMedicineType").toString());
					bMedicineType = true;
				}
				else
					sErrorMessage = sErrorMessage + "Medicine Type get text verification failed";

				if(oAndUtil.ufElementDisplayed(driver, "Apotik_Antar_AllCategories_FluMedicinePrice")){
					oAndUtil.ufWait(2);
					oAndUtil.ufElementGetText(driver, "Apotik_Antar_AllCategories_FluMedicinePrice", true).contains(oJsonLocalizationData.get("sFluMedicinePrice").toString());
					bMedicinePrice = true;
				}
				else
					sErrorMessage = sErrorMessage + "Medicine Price get text verification failed";

				if(oAndUtil.ufElementDisplayed(driver, "Apotik_Antar_AllCategories_FluMedicineBuyBtn")){
					oAndUtil.ufWait(2);
					oAndUtil.ufElementDisplayed(driver, "Apotik_Antar_AllCategories_FluMedicineBuyBtn");
					bMedicineBuyBtn = true;
				}
				else
					sErrorMessage = sErrorMessage + "Medicine image displayed failed";
				
				if(oAndUtil.ufElementDisplayed(driver, "Apotik_Antar_AllCategories_FluMedicineBuyBtn")){
					oAndUtil.ufWait(2);
					oAndUtil.ufElementGetText(driver, "Apotik_Antar_AllCategories_FluMedicineBuyBtn", true).contains(oJsonLocalizationData.get("sFluMedicinebuyBtnText").toString());
					bMedicineBuyBtnText = true;
				}
				else
					
					sErrorMessage = sErrorMessage + "Medicine buy button get text verification failed";
				
				if(bFluBackBtn && bFluePageTitle && bMedicineBuyBtn && bMedicineBuyBtnText && bMedicineImage && bMedicinePrice  && bMedicineSize && bMedicineTitle 
						&& bMedicineType && bRequirePrepositionText && bSearchIcon == true)
					bRes_flag = true;
				else
					bRes_flag = false;
					return bRes_flag;
		}
		
		public boolean IbuDanAnakPageVerification(AppiumDriver<WebElement> driver) throws Exception{
			boolean bRes_flag = false, bIbuDanAnakBackBtn = false,bSearchIcon = false, bIbudanAnakPageTitle = false, bCouldntfindText = false; 
			
			if(oAndUtil.ufWaitForElementDisplayed(driver, "Apotik_Antar_AllCategories_IbuDanAnak_BackBtn", 2)){
				bIbuDanAnakBackBtn = true;
			}
			else
				sErrorMessage = sErrorMessage + "IbuDanAnak Page Back button is not displayed";
			
			if(oAndUtil.ufElementDisplayed(driver, "Apotik_Antar_AllCategories_IbuDanAnak_PageTitle")){
				oAndUtil.ufWait(2);
				oAndUtil.ufElementGetText(driver, "Apotik_Antar_AllCategories_IbuDanAnak_PageTitle", true).contains(oJsonLocalizationData.get("sIbudanAnakPageTitle").toString());
				bIbudanAnakPageTitle = true;
			}
			else
				sErrorMessage = sErrorMessage + "Flu Page Title Get text failed";
		
			if(oAndUtil.ufElementDisplayed(driver, "Apotik_Antar_AllCategories_MedicineSearchIcon")){
				oAndUtil.ufWait(2);
				bSearchIcon = true;
			}
			else
				sErrorMessage = sErrorMessage + "Search Icon is not displayed";
		
		if(oAndUtil.ufWaitForElementDisplayed(driver, "Apotik_Anta_AllCategories_Ibu_dan_AnakNoProducts", 4)){
			oAndUtil.ufWait(4);
			oAndUtil.ufElementGetText(driver, "Apotik_Anta_AllCategories_Ibu_dan_AnakNoProducts", true).contains(oJsonLocalizationData.get("sNoMedicine").toString());
			bCouldntfindText = true;
		}
		else
			sErrorMessage = sErrorMessage + "No medicine get text failed";
		
		if(bCouldntfindText && bIbuDanAnakBackBtn && bIbudanAnakPageTitle  && bSearchIcon == true)
			bRes_flag = true;
		else
			bRes_flag = false;
			return bRes_flag;	
			
		}
		
		public boolean CartStripVerification(AppiumDriver<WebElement> driver) throws Exception{
			boolean bRes_flag = false, bBuyingForText = false, bBuyingListView = false, bNoOfItems = false, bExtimatedPrice = false, bCheckOutBtn = false,
					bHaloDocSearchBox = false, bMedicineListed = false, bMedicineBuyBtn = false;
			String sSearchMedicine = "Crocin";
			
			if(oAndUtil.ufElementDisplayed(driver, "HaloDoc_SearchBox")){
				oAndUtil.ufWait(2);
				oAndUtil.ufSendKeysTextBox(driver, "HaloDoc_SearchBox", sSearchMedicine);
				oAndUtil.ufWait(2);
				oAndUtil.ufElementGetText(driver, "HaloDoc_MedicineListed", true).contains(oJsonLocalizationData.get("sMedicineListed").toString());
				bMedicineListed = true;
				
			}
			else
				sErrorMessage = sErrorMessage + "Medicine Listed Get text failed";
			
			if(oAndUtil.ufWaitForElementDisplayed(driver, "HaloDoc_CartStrip_BuyingforText", 2)){
			oAndUtil.ufElementGetText(driver, "HaloDoc_MedicineListed", true).contains(oJsonLocalizationData.get("sBuyingFor").toString());
			bBuyingForText = true;
			} else
				sErrorMessage = sErrorMessage + "Buying for Get text failed";
			
			if(oAndUtil.ufWaitForElementDisplayed(driver, "HaloDoc_CartStrip_NoOfItem", 2)){
				oAndUtil.ufElementGetText(driver, "HaloDoc_CartStrip_NoOfItem", true).contains(oJsonLocalizationData.get("sNoOfItem").toString());
				bNoOfItems = true;
				} else
					sErrorMessage = sErrorMessage + "No of Item Get text failed";
			
			if(oAndUtil.ufWaitForElementDisplayed(driver, "HaloDoc_CartStrip_ExtimatedPrice", 2)){
				oAndUtil.ufElementGetText(driver, "HaloDoc_CartStrip_ExtimatedPrice", true).contains(oJsonLocalizationData.get("sEstimatedPrice").toString());
				bExtimatedPrice = true;
				} else
					sErrorMessage = sErrorMessage + "Estimated Price get text failed";
			
			if(oAndUtil.ufWaitForElementDisplayed(driver, "HaloDoc_CartStrip_CheckOut", 2)){
				oAndUtil.ufElementGetText(driver, "HaloDoc_CartStrip_CheckOut", true).contains(oJsonLocalizationData.get("sCheckoutBtn").toString());
				bCheckOutBtn = true;
				} else
					sErrorMessage = sErrorMessage + "Estimated Price get text failed";
			
			bBuyingListView = oPageFunc.VerifyBuyingList(driver);
			
			if(bBuyingForText && bCheckOutBtn && bExtimatedPrice && bHaloDocSearchBox && bMedicineBuyBtn && bMedicineListed && bBuyingListView && bNoOfItems == true){
				bRes_flag = true;
			}
			else bRes_flag = false;
			return bRes_flag;
		}
		
}
		

		
	
		
