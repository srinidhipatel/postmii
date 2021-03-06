package com.pm.app;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pm.apppo.cameraPO;
import com.pm.apppo.locationLaunguageCountrySelectionPO;
import com.pm.apppo.loginPO;
import com.pm.apppo.mailPO;
import com.pm.apppo.menuPO;
import com.pm.utilities.TestBase;

public class completeFlow extends TestBase{

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
	@Test(priority=1)
	public void loginFlow() throws Exception{
		poLogin.gettingControl();
		poLogin.clickOnManual();
		poLogin.login();
	}
	@Test(priority=2, dependsOnMethods = {"loginFlow"})
	public void countrySelection() throws Exception{
		poCountry.locationSelection();
		poCountry.wakeUpScreen();
		
	}
	@Test(priority=3,dependsOnMethods = {"countrySelection"})
	public void launguageSelection() throws Exception{
		poCountry.launguageDisplayCheck();
		poCountry.launguageSelect();
	}
	
	@Test(priority=4,dependsOnMethods = {"launguageSelection"})
	public void menuOptionChecking() throws Exception{
		poMenu.menuItemsDisplay();
	}
	@Test(priority=5,dependsOnMethods = {"menuOptionChecking"})
	public void backButtonInMenuFlow() throws Exception{
		poMenu.backButtonWorkingAsExpected();
		poMenu.cancelPreview();
	}

	@Test(priority=6,dependsOnMethods = {"menuOptionChecking"})
	public void TakePic() throws Exception{
		poCamera.takePhoto();
		//poCamera.adjustPic(true);
		poCamera.adjustBrightness();
		poCamera.takeItToEmailPage();
	}
	
	@Test(priority=6,dependsOnMethods = {"TakePic"})
	public void sendEmail() throws Exception{
		poMail.photEmailPAgeVerification();
		poMail.sendEmail();
		poMail.congratulation(true);
	}
}
