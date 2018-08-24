package com.pm.apppo;


import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

import com.pm.utilities.TestBase;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class mailPO extends TestBase {
	Logger log = Logger.getLogger(getClass().getSimpleName());
	JSONObject oGetEmail;
	
	@FindBy(id="user")
	WebElement email;
	@FindBy(id="textView6")
	WebElement textGetYourCardByEmail;
	@FindBy(id="textView2")
	WebElement textPrintingIsInProgress;
	@FindBy(id="noBtn")
	WebElement buttonNo;
	@FindBy(id="send")
	WebElement buttonSend;
	@FindBy(xpath="//android.widget.TextView[@text='CONGRATULATION!']")
	WebElement congratulation;
	@FindBy(id="textView5")
	WebElement goTalkToPostmii;
	
	
	public mailPO(AppiumDriver<WebElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void photEmailPAgeVerification() throws Exception {
		String sLaunguageNotDisplayError=null;
		driver.hideKeyboard();
		Thread.sleep(1000);
		if(!email.isDisplayed())
			sLaunguageNotDisplayError= "email Field Not Displayed ";
		if(!textGetYourCardByEmail.isDisplayed())
			sLaunguageNotDisplayError= "textGetYourCardByEmail Not Displayed ";
		if(!textPrintingIsInProgress.isDisplayed())
			sLaunguageNotDisplayError= "textPrintingIsInProgress Field Not Displayed ";
		if(!buttonNo.isDisplayed())
			sLaunguageNotDisplayError= "buttonNo Field Not Displayed ";
		if(!buttonSend.isDisplayed())
			sLaunguageNotDisplayError= "buttonSend Field Not Displayed ";
		
		if(sLaunguageNotDisplayError!=null)
			throw new Exception(sLaunguageNotDisplayError);
		
		oAndUtil.ufLocalisationChecking(textGetYourCardByEmail, "GetYourCardByEmail");
		//oAndUtil.ufLocalisationChecking(textPrintingIsInProgress, "PrintingInProgress");
		driver.getKeyboard();
	}
	
public void sendEmail() throws Exception{
	//emailToSendPhoto
	driver.getKeyboard();
	oGetEmail = oJsonTestData.getJSONObject("Credentials");
	email.sendKeys(oGetEmail.getString("emailToSendPhoto"));
	driver.hideKeyboard();
	buttonSend.click();
	driver.getKeyboard();
	oAndUtil.ufWaitForElementDisplayed(congratulation, 2);
}
public void congratulation(boolean bLocalisation) throws Exception{
	String sLaunguageNotDisplayError=null;
	if(!congratulation.isDisplayed())
		sLaunguageNotDisplayError= "congratulation Not Displayed ";
	if(!goTalkToPostmii.isDisplayed())
		sLaunguageNotDisplayError= " go Talk to POSTMII message not Displayed ";
	
	if(sLaunguageNotDisplayError!=null)
		throw new Exception(sLaunguageNotDisplayError);
	if(bLocalisation)
	{
	oAndUtil.ufLocalisationChecking(congratulation, "CONGRATULATION");
	//oAndUtil.ufLocalisationChecking(goTalkToPostmii, "GoTalkToPostmii");
	}
}


}