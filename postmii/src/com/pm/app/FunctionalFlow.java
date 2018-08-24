package com.pm.app;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pm.apppo.cameraPO;
import com.pm.apppo.locationLaunguageCountrySelectionPO;
import com.pm.apppo.loginPO;
import com.pm.apppo.mailPO;
import com.pm.apppo.menuPO;
import com.pm.utilities.TestBase;

public class FunctionalFlow extends TestBase{

	loginPO poLogin;
	locationLaunguageCountrySelectionPO poCountry;
	menuPO poMenu;
	cameraPO poCamera;
	mailPO poMail;
	@BeforeClass
	public void init() {
	System.out.println("Before Start driver Object:: "+driver);
	poLogin = new loginPO(driver);
	poCountry = new locationLaunguageCountrySelectionPO(driver);
	poMenu = new menuPO(driver);
	poCamera = new cameraPO(driver);
	poMail = new mailPO(driver);
	}
	
	@DataProvider(name = "language")
	public static Object[][] credentials() {
	    return new Object[][] {{"langEnglish"},{"langFrance"},{"langRussian"},{"langChina"},{"langDeutsch"},{"langPortugues"},{"langJapan"},{"langDutch"},{"langEspanol"}};
	}
	@Test(priority=1)
	public void loginFlow() throws Exception{
		poLogin.gettingControl();
		poLogin.clickOnManual();
		poLogin.login();
	}
	@Test(priority=2, dependsOnMethods = {"loginFlow"})
	public void countrySelection() throws Exception{
		poCountry.locationSelection();
	}
	
	@Test(priority=3,dependsOnMethods = {"countrySelection"},dataProvider = "language")
	public void EntireFlowAsPerlaunguageSelection(String sLanguage) throws Exception{
		poCountry.wakeUpScreen();
		poCountry.launguageSelect(sLanguage);
		//poMenu.cancelPreview();
		poCamera.takePhoto();
		poCamera.adjustPic(false);
		poCamera.adjustBrightness();
		poCamera.takeItToEmailPage();
		poMail.photEmailPAgeVerification();
		poMail.sendEmail();
		poMail.congratulation(false);
	}
}
