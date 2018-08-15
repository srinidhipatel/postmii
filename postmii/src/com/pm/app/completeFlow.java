package com.pm.app;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pm.apppo.loginPO;
import com.pm.utilities.TestBase;

public class completeFlow extends TestBase{

	EventFiringWebDriver eventFiringWebDriver;
	loginPO poLogin;
	@BeforeClass
	public void init() {
	
	poLogin = new loginPO(eventFiringWebDriver);
	}
	@Test
	public void completeFlow() throws Exception{
		poLogin.clickOnManual();
		poLogin.login();
	}
	
}
