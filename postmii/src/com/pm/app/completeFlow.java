package com.pm.app;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.pm.apppo.locationLaunguageCountrySelectionPO;
import com.pm.apppo.loginPO;
import com.pm.utilities.TestBase;

public class completeFlow extends TestBase{

	loginPO poLogin;
	locationLaunguageCountrySelectionPO poCountry;

	@BeforeClass
	public void init() {
	System.out.println("Before Start driver Object:: "+driver);
	poLogin = new loginPO(driver);
	poCountry = new locationLaunguageCountrySelectionPO(driver);
	}
	@Test
	public void loginFlow() throws Exception{
		poLogin.gettingControl();
		poLogin.clickOnManual();
		poLogin.login();
	}
	@Test(dependsOnMethods = {"loginFlow"})
	public void countrySelection() throws Exception{
		poCountry.locationSelection();
		poCountry.wakeUpScreen();
	}
	@Test(dependsOnMethods = {"countrySelection"})
	public void launguageSelection() throws Exception{
		poCountry.launguageDisplayCheck();
		poCountry.launguageSelect();
	}
	
}
