package com.pm.apppo;


import javax.swing.ScrollPaneConstants;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

import com.pm.utilities.Constants;
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
	@FindBy(id="title")
	WebElement chooseTemplateTitle;
	@FindBy(id="rowOneColumnTwo")
	WebElement templateOne;
	@FindBy(id="rowTwoColumnTwo")
	WebElement templateTwo;
	locationLaunguageCountrySelectionPO poCountry;
	
	public menuPO(AppiumDriver<WebElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		poCountry = new locationLaunguageCountrySelectionPO(driver);
	}

	public void setLocalisationLaunguage() {
		if(buttBack.getText().contains("BACK"))
			sLocalisation=Constants.langEnglish;
		else if(buttBack.getText().contains("返回"))
			sLocalisation=Constants.langChina;
		else if(buttBack.getText().contains("RETOUR"))
			sLocalisation=Constants.langFrance;
		else if(buttBack.getText().contains("НАЗАД"))
			sLocalisation=Constants.langRussian;
		else if(buttBack.getText().contains("VOLVER"))
			sLocalisation=Constants.langEspanol;
		else if(buttBack.getText().contains("ZURÜCK"))
			sLocalisation=Constants.langDeutsch;
		else if(buttBack.getText().contains("戻る"))
			sLocalisation=Constants.langJapan;
		else if(buttBack.getText().contains("VOLTAR"))
			sLocalisation=Constants.langPortugues;
		else if(buttBack.getText().contains("TERUG"))
			sLocalisation=Constants.langDutch;
		
	}
	public void menuItemsDisplay() throws Exception{
		
		String sLaunguageNotDisplayError=null;
		oAndUtil.ufWaitForElementDisplayed(buttPhoto, 5);
		oCommUtil.takeScreenShot(driver, getClass().getSimpleName()+oCommUtil.unixTimeStampInString());
		if(!buttBack.isDisplayed())
			sLaunguageNotDisplayError= "button Back Not Displayed  ";
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
	
	public void backButtonWorkingAsExpected() throws Exception{
		buttBack.click();
		poCountry.launguageSelect();
		oAndUtil.ufWaitForElementDisplayed(buttPhoto, 5);
		oCommUtil.takeScreenShot(driver, getClass().getSimpleName()+oCommUtil.unixTimeStampInString());
		if(!buttPhoto.isDisplayed())
			throw new Exception("problem mwith back button in menu screen ");
	}
	public void cancelPreview() throws Exception{
		String sLaunguageNotDisplayError=null;
		cancelPreview.click();
		Thread.sleep(1000);
		oAndUtil.ufLocalisationChecking(chooseTemplateTitle, "CHOOSE_A_TEMPLATE");
		if(!templateOne.isDisplayed())
			sLaunguageNotDisplayError= "Template One Not Displayed ";
		if(!templateTwo.isDisplayed())
			sLaunguageNotDisplayError= "Template Two Not Displayed ";
		
		if(sLaunguageNotDisplayError!=null)
			throw new Exception(sLaunguageNotDisplayError);
		templateOne.click();
		Thread.sleep(1000);
		if(!chooseCard.isDisplayed())
			sLaunguageNotDisplayError= "button choose Card message Not Displayed ";
		cancelPreview.click();
		Thread.sleep(1000);
		templateTwo.click();
		Thread.sleep(1000);
		if(!chooseCard.isDisplayed())
			sLaunguageNotDisplayError= "button choose Card message Not Displayed ";
	}
	
}