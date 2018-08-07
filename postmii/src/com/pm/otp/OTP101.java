package com.pm.otp;

import org.apache.log4j.Logger;
import org.testng.annotations.*;

import com.pm.utilities.TestBase;

public class OTP101 extends TestBase{
	String sTestCaseName="Verify Get Started Screen is coming OR Not and check all the content on the screen";
	boolean bTestStatus=false;
	Logger log = Logger.getLogger(getClass().getSimpleName());

	@BeforeTest
	public void SettingDependencies() throws Exception{
		log.info("Currently Running TestCase: "+sTestCaseName);
		oCommUtil.CheckAppPresence(driver);
	}

	@Test
	public void CheckGetStartedScreenPresentORnot() throws Exception{
		bTestStatus=oPageVal.CheckGetStartedScreenOTP(driver);
		if(!bTestStatus)
			throw new Exception("Failed while verifying screen");
	}

	@AfterTest
	public void BringBackToHomePage() throws Exception{
		if(!bTestStatus)
		{
			oCommUtil.takeScreenShot(driver, getClass().getSimpleName());
			log.info("Failure Reason : "+sErrorMessage);
		}
		oCommUtil.ufBringingToHomePage(driver);
	}

	
}
