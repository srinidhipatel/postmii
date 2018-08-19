package com.pm.apppo;


import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

import com.pm.utilities.TestBase;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class locationLaunguageCountrySelectionPO extends TestBase {
	Logger log = Logger.getLogger(getClass().getSimpleName());
	JSONObject oGetlocation;
	// location Page
	@FindBy(id = "locationCountryTV")
	WebElement location;
	@FindBy(id = "locationCityEventTV")
	WebElement event;
	@FindBy(id = "saveCountryBtn")
	WebElement OK;

	@FindBy(id = "wakeupPicto")
	WebElement wakeupPicture;
	@FindBy(id = "movingText")
	WebElement wakeUpMovingText;
	
	@FindBy(xpath = "//android.widget.Button[@text='FRANÇAIS']")
	WebElement langFrance;
	@FindBy(xpath = "//android.widget.Button[@text='РУССКИЙ']")
	WebElement langRussian;
	@FindBy(xpath = "//android.widget.Button[@text='中文']")
	WebElement langChina;
	
	@FindBy(xpath = "//android.widget.Button[@text='DEUTSCH']")
	WebElement langDeutsch;
	@FindBy(xpath = "//android.widget.Button[@text='PORTUGUÊS']")
	WebElement langPortugues;
	@FindBy(xpath = "//android.widget.Button[@text='日本の']")
	WebElement langJapan;
	
	@FindBy(xpath = "//android.widget.Button[@text='DUTCH']")
	WebElement langDutch;
	@FindBy(xpath = "//android.widget.Button[@text='ENGLISH']")
	WebElement langEnglish;
	@FindBy(xpath = "//android.widget.Button[@text='ESPAÑOL']")
	WebElement langEspanol;
	
	public locationLaunguageCountrySelectionPO(AppiumDriver<WebElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void locationSelection() throws Exception {
		oGetlocation = oJsonTestData.getJSONObject("Credentials");
		oAndUtil.ufWaitForElementDisplayed(location, 5);
		location.clear();
		oAndUtil.ufLocalisationChecking(location, "selectCountry");
		location.sendKeys(oGetlocation.getString("country"));
		event.clear();
		oAndUtil.ufLocalisationChecking(event, "selectCity");
		event.sendKeys(oGetlocation.getString("event"));
		OK.click();
	}
	
	public void wakeUpScreen() throws Exception{
		oAndUtil.ufWaitForElementDisplayed(wakeupPicture, 5);
		oAndUtil.ufLocalisationChecking(wakeUpMovingText, "CreateYourPostcards");
		wakeupPicture.click();
	}
	
	public void launguageDisplayCheck() throws Exception{
		oAndUtil.ufWaitForElementDisplayed(langFrance, 5);
		String sLaunguageNotDisplayError=null;
		if(!langFrance.isDisplayed())
			sLaunguageNotDisplayError= "Language FRANCE Not Displayed ";
		if(!langRussian.isDisplayed())
			sLaunguageNotDisplayError="Language RUSSIA Not Displayed ";
		if(!langChina.isDisplayed())
			sLaunguageNotDisplayError= "Language CHINA Not Displayed ";
		if(!langDeutsch.isDisplayed())
			sLaunguageNotDisplayError= "Language DEUTSCH Not Displayed ";
		if(!langPortugues.isDisplayed())
			sLaunguageNotDisplayError= "Language PORTUGUES Not Displayed ";
		if(!langJapan.isDisplayed())
			sLaunguageNotDisplayError= "Language JAPAN Not Displayed ";
		if(!langDutch.isDisplayed())
			sLaunguageNotDisplayError= "Language DUTCH Not Displayed ";
		if(!langEnglish.isDisplayed())
			sLaunguageNotDisplayError= "Language ENGLISH Not Displayed ";
		if(!langEspanol.isDisplayed())
			sLaunguageNotDisplayError= "Language ESPANOL Not Displayed ";
		
		if(sLaunguageNotDisplayError!=null)
			throw new Exception(sLaunguageNotDisplayError);
		
	}
	public void launguageSelect() throws Exception{
		oAndUtil.ufWaitForElementDisplayed(langFrance, 5);
		langEnglish.click();  
	}
}