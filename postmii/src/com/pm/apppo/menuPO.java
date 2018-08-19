package com.pm.apppo;


import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

import com.pm.utilities.TestBase;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class menuPO extends TestBase {
	Logger log = Logger.getLogger(getClass().getSimpleName());
	@FindBy(id = "cancelBtn")
	WebElement buttBack;
	@FindBy(id = "menu_langue")
	WebElement buttLanguage;
	@FindBy(id = "menu_modele")
	WebElement buttTemplate;
	@FindBy(id = "menu_photo")
	WebElement buttPhoto;
	@FindBy(id = "menu_edition")
	WebElement buttEdition;
	@FindBy(id = "menu_impression")
	WebElement buttPrint;
	@FindBy(id = "cancelPreview")
	WebElement cancelPreview;
	@FindBy(id = "validate")
	WebElement chooseCard;
	@FindBy(id = "previousGridBtn")
	WebElement previousGridBtn;
	@FindBy(id = "nextGridBtn")
	WebElement nextGridBtn;
	
	public menuPO(AppiumDriver<WebElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void menuItemsDisplay() throws Exception{
		
		String sLaunguageNotDisplayError=null;
		if(!buttBack.isDisplayed())
			sLaunguageNotDisplayError= "button Back Not Displayed ";
		if(!buttLanguage.isDisplayed())
			sLaunguageNotDisplayError= "button Language Not Displayed ";
		if(!buttPhoto.isDisplayed())
			sLaunguageNotDisplayError= "button Photo Not Displayed ";
		if(!buttEdition.isDisplayed())
			sLaunguageNotDisplayError= "button Edition Not Displayed ";
		if(!cancelPreview.isDisplayed())
			sLaunguageNotDisplayError= "button Preview Cancel Not Displayed ";
		if(!chooseCard.isDisplayed())
			sLaunguageNotDisplayError= "button choose Card message Not Displayed ";
		if(!previousGridBtn.isDisplayed())
			sLaunguageNotDisplayError= "button Previous Grid Not Displayed ";
		if(!nextGridBtn.isDisplayed())
			sLaunguageNotDisplayError= "button Next Grid Not Displayed ";
			
		if(sLaunguageNotDisplayError!=null)
			throw new Exception(sLaunguageNotDisplayError);
	}
	
}