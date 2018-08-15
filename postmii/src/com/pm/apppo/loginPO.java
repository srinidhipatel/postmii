package com.pm.apppo;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

import com.pm.utilities.TestBase;

import io.appium.java_client.AppiumDriver;

public class loginPO extends TestBase{
	
	JSONObject oJsonTestData;
	JSONObject oGetCredentials;
	private EventFiringWebDriver eventFiringWebDriver;
	
	@FindBy(id="auto")
	WebElement Auto;
	
	@FindBy(id="manual")
	WebElement Manual;
	
	@FindBy(id="employeeEmail")
	WebElement username;
	
	@FindBy(id="employeePassword")
	WebElement password;
	@FindBy(id="loginBtn")
	WebElement loginButton;
	
	public loginPO(EventFiringWebDriver driver){
		System.out.println("constructor called");
		try {
			oJsonTestData = oCommUtil.ReadJsonFileGetJsonObject(System.getProperty("user.dir")+"/src/TestData/TestDataInput.json");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oGetCredentials = oJsonTestData.getJSONObject("Credentials");
		eventFiringWebDriver=driver;
		//this.driver=driver;
		PageFactory.initElements(eventFiringWebDriver, this);
	}
	
	public void clickOnManual(){
		//driver.findElement(By.id("manual")).click();
		Manual.click();
	}
	
	public void login() {
		username.sendKeys(oGetCredentials.getString("username"));
		password.sendKeys(oGetCredentials.getString("password"));
		loginButton.click();
	}
}
