package com.pm.utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumDriver;

import io.appium.java_client.android.AndroidDriver;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.Zip;
import org.openqa.selenium.remote.RemoteWebElement;
import org.json.JSONObject;

import com.google.gson.JsonObject;

public class CommonUtilities {
	public static AndroidUtilities oAndUtil = new AndroidUtilities();
	public static Constants oConst = new Constants();
	public static TestBase oTest = new TestBase();
	Logger log = Logger.getLogger(getClass().getSimpleName());
	String sError = "null";
	static Properties props = new Properties();
	static FileInputStream fileIn = null;
	AppiumDriver<WebElement> driver = null;

	public void loadPropertyFiles(String PropertiesFilePath) throws Exception {

		log.info("Current dir using System:" + PropertiesFilePath);
		fileIn = new FileInputStream(PropertiesFilePath);
		props.load(fileIn);
		try {
			oAndUtil.ufElementDisplayed(driver,
					"Home_Contextualmenu_SideMenu_NotificationCount");
			log.info("Notification Count Is Displayed On Notification Tab on Side Contextual Menu");
		} catch (Exception e) {
			log.info("Notification Count Is Not Displayed On Notification Tab on Side Contextual Menu");
		}
		System.getProperties().putAll(props);
		// log.info(util.props);
	}

	public void loadLog4jProperty(String PropertiesFilePath) throws Exception {
		log.info("Log4j Property file Path :" + PropertiesFilePath);
		fileIn = new FileInputStream(PropertiesFilePath);
		props.load(fileIn);
		PropertyConfigurator.configure(props);
	}

	public boolean ufVerifyTextInsideList(List<WebElement> liInput,
			String sVerifyingText) throws Exception {
		boolean bRes_Flag = false;
		for (int i = 0; i < liInput.size(); i++) {
			// log.info(liInput.get(i).getText());
			if (liInput.get(i).getText().equalsIgnoreCase(sVerifyingText)) {
				log.info("Actual : " + liInput.get(i).getText()
						+ "\nExpected: " + sVerifyingText);
				bRes_Flag = true;
				break;
			}
		}
		return bRes_Flag;
	}

	public void ufUserException(boolean bExpectedBoolean,
			boolean ActualBoolean, String strExceptionSummary) throws Exception {
		if (bExpectedBoolean != ActualBoolean)
			throw new Exception(strExceptionSummary);
	}

	public void ufUserErrorMessage(boolean bExpectedBoolean,
			boolean ActualBoolean, String strExceptionSummary) throws Exception {
		if (bExpectedBoolean != ActualBoolean) {
			oTest.sErrorMessage = oTest.sErrorMessage + ","
					+ strExceptionSummary;
			log.info("Inside Function : " + oTest.sErrorMessage);
		}
	}

	public String[] ufSplitMe(String ORValue) throws Exception {
		String[] sReturnValue;
		ORValue = System.getProperty(ORValue);
		sReturnValue = ORValue.split("::");
		return sReturnValue;
	}

	public void takeScreenShot(AppiumDriver<WebElement> driver,
			String className) throws IOException {
		String destDir = "screenshots";
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		String destFile = className + ".png";

		try {
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")
					+ "/test-output/" + destDir + "/" + destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getScreenshot(AppiumDriver<WebElement> driver) throws Exception
	{
		boolean b=true;
		String path = System.getProperty("user.dir")+"/Screenshot/"+System.currentTimeMillis()+".png";
		if(b) {
		 Robot r = new Robot();	 
         // Used to get ScreenSize and capture image
         Rectangle capture = 
         new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
         BufferedImage Image = r.createScreenCapture(capture);
         ImageIO.write(Image, "jpg", new File(path));
         System.out.println("Screenshot saved");
		}else {
         TakesScreenshot ts=(TakesScreenshot) driver;
 		File src=ts.getScreenshotAs(OutputType.FILE);
 		File destination=new File(path);
 		try 
 		{
 			FileUtils.copyFile(src, destination);
 		} catch (IOException e) 
 		{
 			System.out.println("Capture Failed "+e.getMessage());
 		}}
		return path;
	}
	
	public void deleteScreenShotDirectory() {
		String destDir = "screenshots";
		String SRC_FOLDER = System.getProperty("user.dir") + "/test-output/"
				+ destDir;
		File directory = new File(SRC_FOLDER);
		if (directory.exists()) {
			try {
				delete(directory);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		new File(System.getProperty("user.dir") + "/test-output/" + destDir)
		.mkdirs();

	}

	public void delete(File file) throws IOException {

		if (file.isDirectory()) {
			// directory is empty, then delete it
			if (file.list().length == 0) {
				file.delete();
				log.info("Directory is deleted : " + file.getAbsolutePath());

			} else {

				// list all the directory contents
				String files[] = file.list();

				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);
					// recursive delete
					delete(fileDelete);
				}
				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
					log.info("Directory is deleted : " + file.getAbsolutePath());
				}
			}

		} else {
			// if file, then delete it
			file.delete();
			log.info("File is deleted : " + file.getAbsolutePath());
		}
	}

	public void scrollUp(AppiumDriver<WebElement> driver)
			throws InterruptedException {

		Dimension dimensions = driver.manage().window().getSize();
		Double screenHeightStart = dimensions.getHeight() * 0.9;
		int scrollStart = screenHeightStart.intValue();
		log.info("s=" + scrollStart);
		Double screenHeightEnd = dimensions.getHeight() * 0.2;
		int scrollEnd = screenHeightEnd.intValue();

		driver.swipe(0, scrollStart, 0, scrollEnd, 2000);

	}

	public boolean ufClickWithList(AppiumDriver<WebElement> driver, List<WebElement> liSideMenuList, String sTextToClick)
			throws Exception {
		boolean bRes_flag=false;
		for (int i = 0; i < liSideMenuList.size(); i++) {
			log.info(liSideMenuList.get(i).getText());
			if (liSideMenuList.get(i).getText().equals(sTextToClick)) {
				log.info("Clicking on  : " + liSideMenuList.get(i).getText());
				liSideMenuList.get(i).click();
				log.info("Clicked On   : " + sTextToClick);
				bRes_flag=true;
				break;
			}
		}
		return bRes_flag;
	}

	public void ufBringingToHomePage(AppiumDriver<WebElement> driver) throws Exception {
		log.info("Bringing to Home Page");
		Thread.sleep(3000);

		if (TestBase.sClassName.contains("PH_")) {
			log.info("Filter Enabled for Pharmacy Check");
			if (oAndUtil.ufElementDisplayed(driver, "Pharmacy_Home_TabNew"))
				log.info("In Home Page..");
			else
				log.info("Not in Home Page");
		} else {

			if (oAndUtil.ufElementDisplayed(driver, "CONSUMER_HOME")) {
				log.info("In Home Page..");
			} else if (oAndUtil.ufElementDisplayed(driver, "WelcomeScreen_TextGetStarted")) {
				log.info("In Home Page..");
			} else if (oAndUtil.ufElementDisplayed(driver, "Pharmacy_WelcomeScreen_TextGetStarted")) {
				log.info("In Home Page..");
			} else if (oAndUtil.ufElementDisplayed(driver, "WelcomeScreen_OTP_CountryCode")) {
				
				log.info("Clicking on OTP Navigate Back");
				if(TestBase.sLocalization.equalsIgnoreCase(TestBase.sLocalEnglish))
					oAndUtil.ufClickElement(driver, "WelcomeScreen_OTP_NavigateBackButton");
				else if(TestBase.sLocalization.equalsIgnoreCase(TestBase.sLocalIndonasia))
					oAndUtil.ufClickElement(driver, "WelcomeScreen_OTP_NavigateBackButton_Indonasia");
				else 
					log.info("Launguage not found to handle");
			
				if (oAndUtil.ufWaitForElementDisplayed(driver, "WelcomeScreen_TextGetStarted", 5))
					log.info("In Home Page");
				else {
					oAndUtil.ufWaitForElementDisplayed(driver, "WelcomeScreen_TextGetStarted", 5);
					log.info("In Home Page");
				}

			} else 
				log.info("Not in Home Page");
		}
	}

	public boolean CheckAppPresence(AppiumDriver<WebElement> driver)
			throws Exception {
		boolean bRes_flag = false;
		if (TestBase.sClassName.contains("PH_")) {
			// log.info("Filtering enabled for Pharmacy check");
			if (oAndUtil.ufElementDisplayed(driver, "Pharmacy_Home_TabNew"))
				bRes_flag = true;
		} else {

			if (oAndUtil.ufElementDisplayed(driver, "WelcomeScreen_TextGetStarted"))
				bRes_flag = true;
			else if (oAndUtil.ufElementDisplayed(driver, "Pharmacy_WelcomeScreen_TextGetStarted"))
				bRes_flag = true;
			else if (oAndUtil.ufElementDisplayed(driver, "Pharmacy_Home_TabNew"))
				bRes_flag = true;
			else if (oAndUtil.ufElementDisplayed(driver, "CONSUMER_HOME"))
				bRes_flag = true;
		}
		// If false it Relaunches App once agin
		if (bRes_flag)
			return bRes_flag;
		else {
			oAndUtil.shutDownApp();
			Thread.sleep(5000);
			oAndUtil.launchApp();
			oAndUtil.ufWaitForElementDisplayed(driver, "WelcomeScreen_TextGetStarted",
					15);
		}

		return bRes_flag;
	}

	

	public void SendEmailAfterTestSuite() throws Exception {

		String from = "automation@halodoc.com";

		// Sender's email ID needs to be mentioned
		// String to =
		
		
		String to = "srinidhi.b@halodoc.com";
		final String username = "automation";// change accordingly
		final String password = "Auto@123";// change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "mail.halodoc.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "25");

		// Get the Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject("Automation: HaloDoc  Mobile Application Testing hourly status report");

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Now set the actual message
			messageBodyPart
			.setText(" Dear All, \n\n PFA of HaloDoc  Mobile Testing hourly status report.. \n\n Regards \n Srinidhi.B \n Mobile: +919964500275 \n ");

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			// String filename =
			// "/home/justdial/Documents/Files/Data-Result.xls";
			String filename = System.getProperty("user.dir")
					+ "\\test-output\\emailable-report.html";
			String zipfile = System.getProperty("user.dir")
					+ "/test-output/screenshots.zip";
			/*
			 * //System.get("curr.dir") DataSource source = new
			 * FileDataSource(filename); messageBodyPart.setDataHandler(new
			 * DataHandler(source)); messageBodyPart.setFileName(filename);
			 * multipart.addBodyPart(messageBodyPart);
			 */
			List<String> attachment_PathList = new ArrayList<String>();
			attachment_PathList.add(filename);
			attachment_PathList.add(zipfile);
			log.info(attachment_PathList);

			for (String str : attachment_PathList) {
				MimeBodyPart messageBodyPart2 = new MimeBodyPart();
				DataSource source = new FileDataSource(str);
				messageBodyPart2.setDataHandler(new DataHandler(source));
				messageBodyPart2.setFileName(source.getName());
				multipart.addBodyPart(messageBodyPart2);
			}

			// Send the complete message parts
			message.setContent(multipart);

			// Send message
			Transport.send(message);

			log.info("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public void ufdeletescreenshots() throws IOException {
		String destdir = "screenshots";
		String screenpath = System.getProperty("user.dir") + "/test-output/"
				+ destdir;
		File screenshotfolder = new File(screenpath);

		if (screenshotfolder.exists()) {
			try {
				FileUtils.cleanDirectory(screenshotfolder);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		new File(System.getProperty("user.dir") + "/test-output/" + destdir)
		.mkdirs();

	}

	public void ufzipscreenshots() {
		Zip zip = new Zip();

		try {

			File inputfolder = new File(System.getProperty("user.dir")
					+ "/test-output/screenshots");

			File output = new File(System.getProperty("user.dir")
					+ "/test-output/screenshots.zip");
			if (output.exists()) {
				output.delete();
			}

			zip.zip(inputfolder, output);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	public boolean ufVerifyContainsTextInsideList(List<WebElement> liInput,
			String sVerifyingText) throws Exception {
		boolean bRes_Flag = false;
		for (int i = 0; i < liInput.size(); i++) {
			String s = liInput.get(i).getText();

			if (StringUtils.containsIgnoreCase(s,sVerifyingText )) {
				log.info("Actual : " + liInput.get(i).getText()
						+ "\nExpected: " + sVerifyingText);
				bRes_Flag = true;
				break;
			}
		}
		return bRes_Flag;
	}
	public void ufClickWithContainsElementList(AppiumDriver<WebElement> driver,
			List<WebElement> liSideMenuList, String sTextToClick)
					throws Exception {
		for (int i = 0; i < liSideMenuList.size(); i++) {
			String s = liSideMenuList.get(i).getText();

			if (StringUtils.containsIgnoreCase(s,sTextToClick ))
			{
				log.info("Clicking on  : " + liSideMenuList.get(i).getText());
				liSideMenuList.get(i).click();
				log.info("Clicked On   : " + sTextToClick);
				break;
			}
		}

	}
	
	public void minScrollUp(AppiumDriver<WebElement> driver)
            throws InterruptedException {

        Dimension dimensions = driver.manage().window().getSize();
        Double screenHeightStart = dimensions.getHeight() * 0.3;
        int scrollStart = screenHeightStart.intValue();
        log.info("s=" + scrollStart);
        Double screenHeightEnd = dimensions.getHeight() * 0.2;
        int scrollEnd = screenHeightEnd.intValue();
        driver.swipe(0, scrollStart, 0, scrollEnd, 2000);

    } 
	
	public void mediumScrollUp(AppiumDriver<WebElement> driver)
			throws InterruptedException {

		Dimension dimensions = driver.manage().window().getSize();
		Double screenHeightStart = dimensions.getHeight() * 0.6;
		int scrollStart = screenHeightStart.intValue();
		log.info("s=" + scrollStart);
		Double screenHeightEnd = dimensions.getHeight() * 0.2;
		int scrollEnd = screenHeightEnd.intValue();

		driver.swipe(0, scrollStart, 0, scrollEnd, 2000);

	}
	
	
	
	public String convertToSeconds(String sActualTime) {
        String sTotalSec = null;
        String[] strSplit=sActualTime.split(":");
        if(strSplit.length==3)
        {
            int iHourToSec=Integer.parseInt(strSplit[0].toString())*60*60;
            //log.info(iHourToSec);
            int iMinuteToSec=Integer.parseInt(strSplit[1].toString())*60;
            //log.info(iMinuteToSec);
            double iSeconds = Double.parseDouble(strSplit[2].toString());
            //log.info(iSeconds);
            double iTotalSec = iHourToSec+iMinuteToSec+iSeconds;
            //log.info(iTotalSec);
            sTotalSec = Double.toString(iTotalSec);
        }

		return sTotalSec;

    }

	public JSONObject ReadJsonFileGetJsonObject(String sPathOfJson) throws Exception{
		System.out.println(sPathOfJson);
		String sJsonContent = readfileReturnInString(sPathOfJson);
		JSONObject jsonObject = new JSONObject();
		try{
			jsonObject = new JSONObject(sJsonContent.trim());
		}catch(Exception pj){
			try{
				jsonObject = new JSONObject(sPathOfJson.substring(sPathOfJson.indexOf('{')));
			}catch(Exception pja){
				
				log.error("Unable to parse "+pj);
			}
		}

		return jsonObject;
	}

	public JSONObject convertStringToJsonObject(String sJsonContent) throws Exception{
		JSONObject jsonObject = new JSONObject();
		try{
			jsonObject = new JSONObject(sJsonContent.substring(sJsonContent.indexOf('{')));
		}catch(Exception pj){
			try{
				jsonObject = new JSONObject(sJsonContent.trim());
				
			}catch(Exception pja){
				
				log.error("Unable to parse "+pj);
			}
		}

		return jsonObject;
	}

	public String readfileReturnInString(String sPathOfJson) throws IOException {
		
			  byte[] encoded = Files.readAllBytes(Paths.get(sPathOfJson));
			  return new String(encoded, StandardCharsets.UTF_8);

	}
	
	public boolean ufVerifyelementWithinList(AppiumDriver<WebElement> driver, List<WebElement> liSideMenuList, String sTextToVerify)
			throws Exception {
		boolean bRes_flag=false;
		for (int i = 0; i < liSideMenuList.size(); i++) {
			log.info(liSideMenuList.get(i).getText());
			if (liSideMenuList.get(i).getText().equals(sTextToVerify)) {
				log.info(liSideMenuList.get(i).getText() + " " + "Text verified");
				bRes_flag=true;
				break;
			}
		}
		return bRes_flag;
	}

	
}
