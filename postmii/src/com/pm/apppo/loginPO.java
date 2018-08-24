package com.pm.apppo;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;
//import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

import com.pm.utilities.TestBase;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class loginPO extends TestBase {
	Logger log = Logger.getLogger(getClass().getSimpleName());
	JSONObject oGetCredentials;

	@FindBy(id = "auto")
	WebElement Auto;

	@FindBy(id = "manual")
	WebElement Manual;

	@FindBy(id = "loginMessageTV")
	WebElement login;
	@FindBy(id = "employeeEmail")
	WebElement username;

	@FindBy(id = "employeePassword")
	WebElement password;
	@FindBy(id = "loginBtn")
	WebElement loginButton;

	// To take it to right page
	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.android.documentsui:id/icon_thumb']")
	WebElement photoIcon;
	@FindBy(xpath = "//android.widget.TextView[@resource-id='android:id/title']")
	WebElement pageTitle;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/title']")
	List<WebElement> liPageTitle;

	@FindBy(xpath = "//android.widget.Button[@resource-id='android:id/button1']")
	WebElement buttonSelect;
	// Country Page

	@FindBy(id = "locationCountryTV")
	WebElement country;
	@FindBy(id = "locationCityEventTV")
	WebElement event;
	@FindBy(id = "saveCountryBtn")
	WebElement OK;

	public loginPO(AppiumDriver<WebElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void clickOnManual() {
		Manual.click();
	}

	public void login() throws Exception {

		oGetCredentials = oJsonTestData.getJSONObject("Credentials");
		oAndUtil.ufLocalisationChecking(login, "login");
		username.clear();
		username.click();
		oAndUtil.ufLocalisationChecking(username, "email");
		username.sendKeys(oGetCredentials.getString("username"));
		password.clear();
		oAndUtil.ufLocalisationChecking(password, "password");
		password.sendKeys(oGetCredentials.getString("password"));
		
		driver.hideKeyboard();
		loginButton.click();
		driver.getKeyboard();
	}

	public void gettingControl() {
		for (int iter = 0; iter < 3; iter++) {
			if (Manual.isDisplayed())
				return;
			if (photoIcon.isDisplayed()) {
				photoIcon.click();
				String whichScreen = pageTitle.getText();
				System.out.println(whichScreen);
				pageTitle.click();
				enableSelectButton(whichScreen);
			}
			if (buttonSelect.isDisplayed() && buttonSelect.getText().equals("SELECT")) {
				buttonSelect.click();
				return;
			}
		}
	}

	private void enableSelectButton(String whichScreen) {
		for (int i = 0; i < liPageTitle.size(); i++) {
			System.out.println(liPageTitle.get(i).getText());
			if (liPageTitle.get(i).getText().equals(whichScreen))
				liPageTitle.get(i - 1).click();
		}
	}

}