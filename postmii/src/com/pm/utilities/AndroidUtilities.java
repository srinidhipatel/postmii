package com.pm.utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.remote.DesiredCapabilities;


public class AndroidUtilities extends TestBase{
	//public static AppiumDriver<WebElement> driver;
	public static CommonUtilities oCommUtil=new CommonUtilities();
	public static Constants oConst= new Constants();
	Logger log = Logger.getLogger(getClass().getSimpleName());

	Map<String, String> KeysMap = new HashMap<String, String>() ;
	public JSONObject oJsonTestData = new JSONObject();
	//public JSONObject oJsonUserProfileData = new JSONObject();
	
	public void launchApp() throws Exception {

		TestBase.AutomationRunning=Constants.sAutomationMobile;
		oJsonTestData = oCommUtil.ReadJsonFileGetJsonObject(System.getProperty("user.dir")+"/src/TestData/TestDataInput.json");
		JSONObject oGetAppPackageActivity = oJsonTestData.getJSONObject(System.getProperty("AppName"));
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		log.info(System.getProperty("PLATFORM_NAME"));
		
		if(System.getProperty("PLATFORM_NAME").toLowerCase().contains("android"))
		{
			capabilities.setCapability("BROWSER_NAME", "Android");
			capabilities.setCapability("VERSION", System.getProperty("DeviceAndroidVersion")); 
			capabilities.setCapability("deviceName",System.getProperty("Android_DeviceName"));
			capabilities.setCapability("platformName","Android");
			capabilities.setCapability("appPackage",oGetAppPackageActivity.get("AppPackageName"));
			capabilities.setCapability("appActivity",oGetAppPackageActivity.get("AppActivity")); // This is Launcher activity of your app (you can get it from apk info app)
			capabilities.setCapability("newCommandTimeout", 60 * 5);
			capabilities.setCapability("noReset", true);
			//to avoid failures for ID 
			capabilities.setCapability("automationName","UiAutomator2");
			driver = new AndroidDriver<WebElement>(new URL("http://"+System.getProperty("AppiumServerIp")+"/wd/hub"), capabilities);
		}
		if(System.getProperty("PLATFORM_NAME").toLowerCase().contains("ios"))
		{
			//capabilities.setCapability("BROWSER_NAME", "ios");
			//capabilities.setCapability("VERSION", System.getProperty("DeviceIosVersion")); 
			//capabilities.setCapability("automationName","XCUITest");
			//capabilities.setCapability("useNewWDA",true);
			capabilities.setCapability("udid",System.getProperty("Ios_DeviceName"));
			capabilities.setCapability("platformName","ios");
			capabilities.setCapability("deviceName","Srinidhi’s iPhone");
			//capabilities.setCapability("PLATFORM_VERSION","10.2");
			capabilities.setCapability("xcodeConfigFile","/usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent/myconfig.xcconfig");
			
			//capabilities.setCapability("appPackage",oGetAppPackageActivity.get("ios_AppPackageName"));
			//capabilities.setCapability("appActivity",oGetAppPackageActivity.get("ios_AppActivity")); // This is Launcher activity of your app (you can get it from apk info app)
			capabilities.setCapability("bundleId",oGetAppPackageActivity.get("BundleId"));
			capabilities.setCapability("newCommandTimeout", 60 * 5);
			driver = new IOSDriver<WebElement>(new URL("http://"+System.getProperty("AppiumServerIp")+"/wd/hub"), capabilities);
		}
		
		Thread.sleep(5000);

	}

	public void shutDownApp() throws Exception{
		driver.quit();
	}

	public boolean ufElementDisplayed(AppiumDriver<WebElement> driver, String element) throws Exception {
		return true;//driver.findElement(element).isDisplayed();
	}
	
	public void ufLocalisationChecking(WebElement element,String sKey) throws Exception {
		oJsonLocalisationData=oJsonLocalisation.getJSONObject(System.getProperty("localisation"));
		log.info("Expected :: "+element.getText()+"\nActual :: "+oJsonLocalisationData.get(sKey));
		if(!element.getText().equals(oJsonLocalisationData.get(sKey)))
			throw new Exception("**********************Failed ***********************"
					+ "\nExpected :: "+element.getText()+"\nActual :: "+oJsonLocalisationData.get(sKey));
		
	}
	public boolean ufWait(int itimetowait) throws Exception {
		boolean bResultFlag=false;
		
		Thread.sleep(1000*itimetowait);
		bResultFlag=true;
		
		return bResultFlag; 

	}


	public boolean ufWaitForElementDisplayed(AppiumDriver<WebElement> driver, String ORValue, int itimetowait) throws Exception {
		
		boolean bResultFlag = false;
		for (int i=0; i<(itimetowait*2); i++)
		{
			bResultFlag = ufElementDisplayed(driver, ORValue);

			if (bResultFlag){
				log.info("Element Displayed");
				log.info(i);
				log.info("Exit Time "+ i/2);
				return bResultFlag;
			}
			else 
				Thread.sleep(500);
		}
		return bResultFlag; 

	}

	public void ufClickElement(AppiumDriver<WebElement> driver, String ORValue) throws Exception {
		
		String[] sArrReadyToServe = oCommUtil.ufSplitMe(ORValue);
		//log.info(ORValue+"="+sArrReadyToServe[0]+"::"+sArrReadyToServe[1]);
		switch(sArrReadyToServe[0].toUpperCase()) 
		{
		case "ID":
			driver.findElement(By.id(sArrReadyToServe[1].trim())).click();
			break;
		case "XPATH":
			driver.findElement(By.xpath(sArrReadyToServe[1].trim())).click();
			break;

		default :
			log.info("Locator not available to click");

		}

	}
	public void ufSendKeysTextBox(AppiumDriver<WebElement> driver, String ORValue, String sText) throws Exception
	{
		String[] sArrReadyToServe = oCommUtil.ufSplitMe(ORValue);
		switch(sArrReadyToServe[0].toUpperCase()) 
		{
		case "ID":
			//driver.findElement(By.id(sArrReadyToServe[1])).click();
			driver.findElement(By.id(sArrReadyToServe[1])).sendKeys(sText);
			break;
		case "XPATH":
			driver.findElement(By.xpath(sArrReadyToServe[1])).sendKeys(sText);
			break;

		default :
			log.info("Locator not available to click");

		}
	}
	
	public List<WebElement> ufGetList(AppiumDriver<WebElement> driver, String ORValue) throws Exception {
		
		List<WebElement> liResultingList = null;
		String[] sArrReadyToServe = oCommUtil.ufSplitMe(ORValue);

		switch(sArrReadyToServe[0].toUpperCase()) 
		{
		case "ID":
			liResultingList=driver.findElements(By.id(sArrReadyToServe[1]));
			break;
		case "XPATH":
			liResultingList=driver.findElements(By.xpath(sArrReadyToServe[1]));
			//loggerObj.info("Device type selected is Satellite.");
			break;

		default :
			log.info("Locator not available to getList");
		}
		return liResultingList;
	}
	
	public void ufLoadKeysMap()
	{
		KeysMap.put("a", "29");	KeysMap.put("b", "30");	KeysMap.put("c", "31");	KeysMap.put("d", "32");
		KeysMap.put("e", "33");	KeysMap.put("f", "34");	KeysMap.put("g", "35");	KeysMap.put("h", "36");
		KeysMap.put("i", "37");	KeysMap.put("j", "38");	KeysMap.put("k", "39");	KeysMap.put("l", "40");
		KeysMap.put("m", "41");	KeysMap.put("n", "42");	KeysMap.put("o", "43");	KeysMap.put("p", "44");
		KeysMap.put("q", "45");	KeysMap.put("r", "46");	KeysMap.put("s", "47");	KeysMap.put("t", "48");
		KeysMap.put("u", "49");	KeysMap.put("v", "50");	KeysMap.put("w", "51");	KeysMap.put("x", "52");
		KeysMap.put("y","53");	KeysMap.put("z", "54");	KeysMap.put("0","7");	KeysMap.put("1","8");
		KeysMap.put("2","9");	KeysMap.put("3","10");	KeysMap.put("4","11");	KeysMap.put("5","12");
		KeysMap.put("6","13");	KeysMap.put("7","14");	KeysMap.put("8","15");	KeysMap.put("9","16");
		KeysMap.put("SPACE","62");	KeysMap.put("ENTER","66");	KeysMap.put("BACKSPACE","67");
		KeysMap.put("@","77");	KeysMap.put("*","17");	KeysMap.put(",","159");	KeysMap.put("+","157");
	
	}
	
	/*public void ufKeyPressString(AppiumDriver<WebElement> driver,String stext)
	{
		try{
		
		driver.getKeyboard();
		String splitArr[]=stext.split(" ");
		
		for(int i=0;i<splitArr.length;i++)
		{
		if(splitArr[i].equals(" "))
		{
			 driver.pressKeyCode(Integer.parseInt(KeysMap.get("SPACE")));
		}
		else if(splitArr[i].equals("ENTER"))
		{
			driver.pressKeyCode(Integer.parseInt(KeysMap.get("ENTER")));
		}
		else if(splitArr[i].equals("BACKSPACE"))
		{
			 driver.pressKeyCode(Integer.parseInt(KeysMap.get("BACKSPACE")));
		}
		else
		{
			String [] sArr = splitArr[i].split("");
			for(int j=0;j<sArr.length;j++)
			{
				if(Character.isLowerCase(sArr[j].charAt(0)))
					driver.pressKeyCode(Integer.parseInt(KeysMap.get(sArr[j])));
				else if(Character.isDigit(sArr[j].charAt(0)))
					driver.pressKeyCode(Integer.parseInt(KeysMap.get(sArr[j])));
				else
				{
					
					driver.pressKeyCode((Integer.parseInt(KeysMap.get(sArr[j].toLowerCase()))),1);
			
				}
			}
			 driver.pressKeyCode(Integer.parseInt(KeysMap.get("SPACE")));
		}
		}
		}catch(Exception ea)
		{System.out.println(ea);}
		
	}
	*/
	public boolean ufElementIsSelected(AppiumDriver<WebElement> driver, String ORValue) throws Exception 
	{
		String[] sArrReadyToServe = oCommUtil.ufSplitMe(ORValue);
		boolean isSel = false;
		switch(sArrReadyToServe[0].toUpperCase()) 
		{
		case "ID":
			isSel = driver.findElement(By.id(sArrReadyToServe[1])).isSelected();
			break;
		case "XPATH":
			isSel= driver.findElement(By.xpath(sArrReadyToServe[1])).isSelected();
			break;

		default :
			log.info("Could not click");
			
		

	}
		return isSel;
	}
	
	public String ufElementGetText(AppiumDriver<WebElement> driver, String ORValue,boolean bPrintText) throws Exception 
	{
		String[] sArrReadyToServe = oCommUtil.ufSplitMe(ORValue);
		String text = null;
		switch(sArrReadyToServe[0].toUpperCase()) 
		{
		case "ID":
			text = driver.findElement(By.id(sArrReadyToServe[1])).getText();
			break;
		case "XPATH":
			text= driver.findElement(By.xpath(sArrReadyToServe[1])).getText();
			break;

		default :
			log.info("Could not click");	
	}
		if(bPrintText)
			log.info(ORValue+" == "+text);
			
		return text;
	}

	public boolean  ufScrollUpToExpectedValue(WebElement element, AppiumDriver<WebElement> driver, String sExpectedValue,int iExitCraiteria) {
		boolean bRes_flag=true;
		int exitCount=0;boolean bExit=false;
		while(bExit)
	    {
			if(element.getText().equals(sExpectedValue))
				bExit=true;
			else if(exitCount==iExitCraiteria)
				bExit=true;
			else{
			int topY = element.getLocation().getY();
		    int bottomY = topY + element.getSize().getHeight();
		    int centerX = element.getLocation().getX() + (element.getSize().getWidth()/2);
		    driver.swipe(centerX, bottomY, centerX, topY, 500);
			}
			exitCount++;
	    }
		return bRes_flag;
	}

	public boolean  ufScrollDownToExpectedValue(WebElement element, AppiumDriver<WebElement> driver, String sExpectedValue,int iExitCraiteria) throws InterruptedException {
		boolean bRes_flag=true;
		int exitCount=0;boolean bExit=true;
		while(bExit)
	    {
			Thread.sleep(200);
			if(element.getText().equals(sExpectedValue))
				bExit=false;
			else if(exitCount==iExitCraiteria)
				bExit=false;
			else{
			int topY = element.getLocation().getY();
		    int bottomY = topY + element.getSize().getHeight();
		    int centerX = element.getLocation().getX() + (element.getSize().getWidth()/2);
		    driver.swipe(centerX, topY, centerX, bottomY, 400);
			}
			exitCount++;
	    }
		return bRes_flag;
	}

	public boolean ufWaitForElementDisplayed(WebElement element, int itimetowait) throws Exception {
		boolean bResultFlag = false;
		for (int i=0; i<(itimetowait*2); i++)
		{
			try {
			bResultFlag = element.isDisplayed();

			if (bResultFlag){
				log.info("Element Displayed");
				log.info(i);
				log.info("Exit Time "+ i/2);
				return bResultFlag;
			}
			
			}catch(Exception ea) {
			log.info("page still not loaded");
			}
			Thread.sleep(500);
			
		}
		return bResultFlag; 

	}
	
//	public void ScrollToText() throws InterruptedException {
//		  //Scroll till element which contains "Views" text If It Is not visible on screen.
//		  driver.scrollTo("Views");
//		  // Click on Views/.
//		  driver.findElement(By.name("Views")).click();
//		  log.info("Scrolling has been started to find text -> Tabs.");
//		  // Scroll till element which contains Tabs text.
//		  driver.scrollTo("Tabs");
//		  log.info("Tabs text has been found and now clicking on It.");
//		  // Click on Tabs.
//		  driver.findElement(By.name("Tabs")).click();
//		 }
	

}
