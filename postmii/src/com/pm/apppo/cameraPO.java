package com.pm.apppo;


import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

import com.gargoylesoftware.htmlunit.OnbeforeunloadHandler;
import com.pm.utilities.TestBase;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class cameraPO extends TestBase {
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
	
	@FindBy (id="take")
	WebElement takePic;
	@FindBy (id="cameraChange")
	WebElement cameraChange;
	@FindBy (id="ar_filter")
	List<WebElement> camera_filter;
	@FindBy (id="back")
	WebElement cameraBack;
	@FindBy (id="filterBtn")
	WebElement adjust;
	@FindBy (id="paramBtn")
	WebElement brightness;
	@FindBy (id="nameFilter")
	List <WebElement> adjustFilters;
	@FindBy (id="nameFilter")
	WebElement webelementAdjustFilters;
	@FindBy (id="terminatedBtn")
	WebElement Print;
	@FindBy (id="brightnessBar")
	WebElement brightnessBar;
	@FindBy (id="saturationBar")
	WebElement saturationBar;
	@FindBy (xpath="//android.widget.TextView[@index=2]")
	WebElement textSaturation;
	
	@FindBy (id="menu_impression")
	WebElement menu_impression;
	@FindBy (id="user")
	WebElement emailBox;
	
	
	
	//locationLaunguageCountrySelectionPO poCountry;
	
	public cameraPO(AppiumDriver<WebElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
	public void takePhoto() throws Exception{
		
		String sLaunguageNotDisplayError=null;
		
		if(chooseCard.isDisplayed())
			chooseCard.click();
		oAndUtil.ufWaitForElementDisplayed(takePic, 5);
		
		if(!cameraChange.isDisplayed())
			sLaunguageNotDisplayError= "button change camera Not Displayed ";
		if(!cameraBack.isDisplayed())
			sLaunguageNotDisplayError= "button camera Back Not Displayed ";
		if(!cameraChange.isDisplayed())
			sLaunguageNotDisplayError= "button change camera Not Displayed ";
		if(camera_filter.size()!=3)
			sLaunguageNotDisplayError= "button Camera filter size is Wrong ";
		
		if(sLaunguageNotDisplayError!=null)
			throw new Exception(sLaunguageNotDisplayError);
		
		for(int i=0;i<camera_filter.size();i++)
		{
			log.info(camera_filter.get(i).getText());
			camera_filter.get(i).click();
			Thread.sleep(2000);
		}
		Thread.sleep(2000);
		takePic.click();
		oAndUtil.ufWaitForElementDisplayed(brightness,5);
	}
	
	public void adjustPic(boolean bLocalisation) throws Exception{
	
		int filterSize= adjustFilters.size();
		for(int i=0;i<filterSize;i++)
		{
			if(bLocalisation)
				oAndUtil.ufLocalisationChecking(adjustFilters.get(i), adjustFilters.get(i).getText());
			log.info(adjustFilters.get(i).getText());
			adjustFilters.get(i).click();
		}
		System.out.println(filterSize);
		oAndUtil.ufScrollUpToExpectedValue(webelementAdjustFilters, driver, "SEPIA", 15);
		
		for(int i=0;i<filterSize;i++)
		{	
			
			log.info(adjustFilters.get(i).getText());
			if(bLocalisation)
				oAndUtil.ufLocalisationChecking(adjustFilters.get(i), adjustFilters.get(i).getText());
			adjustFilters.get(i).click();
			Thread.sleep(2000);
		}
		System.out.println(filterSize);
		if(bLocalisation) {
		oAndUtil.ufLocalisationChecking(adjust,"ADJUST");
		oAndUtil.ufLocalisationChecking(brightness,"BRIGHTNESS");
		oAndUtil.ufLocalisationChecking(Print,"Print");
		}
		
	}
	
	public void adjustBrightness() throws Exception{		brightness.click();
		oAndUtil.ufWaitForElementDisplayed(brightnessBar, 5);
		brightnessBar.click();
		log.info(textSaturation.getText());
		/*oAndUtil.ufScrollUpToExpectedValue(saturationBar, driver,menu_impression.getText(), 2);
		Thread.sleep(2000);
		oAndUtil.ufScrollUpToExpectedValue(brightnessBar, driver,menu_impression.getText(), 2);
		Thread.sleep(2000);
		*/
		
		
	}
	public void takeItToEmailPage() throws Exception {
		Print.click();
		oAndUtil.ufWaitForElementDisplayed(emailBox, 5);
	}
}