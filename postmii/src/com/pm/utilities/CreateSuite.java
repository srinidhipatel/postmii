package com.pm.utilities;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author Crunchify.com
 */

public class CreateSuite {

	public static void main(String[] args) {
		CommonUtilities oCommon = new CommonUtilities();
		TestBase test = new TestBase();
		DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder icBuilder;
		String sPackageName,sFileName,sFilePath,sIgnoreTestCase;
		try {
			
			oCommon.loadPropertyFiles(System.getProperty("user.dir")+"/src/Properties/"+"/SuiteCreation.properties");

			icBuilder = icFactory.newDocumentBuilder();
			Document doc = icBuilder.newDocument();
			Element mainRootElement = doc.createElement("suite");
			doc.appendChild(mainRootElement);
			mainRootElement.setAttribute("name", System.getProperty("SuiteName"));
			mainRootElement.setAttribute("parallel", "tests");
			mainRootElement.setAttribute("thread-count", "10");
			try {
			if(System.getProperty("ListnerRequired").equalsIgnoreCase("true"))
			{
			mainRootElement.appendChild(addListnersToTag(doc,System.getProperty("ListnerPackagePath")));
			}}catch(Exception ea) {System.out.println("Listners not used");}
			// parallel="tests" thread-count="4"
			sPackageName = System.getProperty("Packages");
			sIgnoreTestCase = System.getProperty("ignoreCase");
			
			String[] sArrPackageNames=sPackageName.split(",");
			String[] sArrIgnoreTestCase=sIgnoreTestCase.split(",");
			for(int j=0;j<sArrPackageNames.length;j++)
			{
				System.out.println(sArrPackageNames[j]);
				sFilePath=System.getProperty("user.dir")+"/src/"+convertPackageIntoPath(sArrPackageNames[j]);
				sFileName=getFileNameInsidePackage(sFilePath);
				System.out.println(sFileName);
				String[]sArrFileNames = sFileName.split(",");
				boolean bIgnoreMeForSuite=true;
				for(int i=0;i<sArrFileNames.length;i++)
				{
					bIgnoreMeForSuite=true;
					//System.out.println("Required : "+sArrFileNames[i]);
					for (int l=0;l<sArrIgnoreTestCase.length;l++)
					{
						if(sArrFileNames[i].equals(sArrIgnoreTestCase[l]))
							bIgnoreMeForSuite=false;
						
					}
					if(bIgnoreMeForSuite)
						mainRootElement.appendChild(addValuesToTags(doc, sArrFileNames[i], sArrPackageNames[j]+"."+sArrFileNames[i]));
					else
						System.out.println("Ignoring Test Case : "+sArrFileNames[i]);
				}
				/*sFilePath=System.getProperty("user.dir")+"/src/"+convertPackageIntoPath(sPackageName);
				sFileName=getFileNameInsidePackage(sFilePath);
				System.out.println(sFileName);
				String[]sArrFileNames = sFileName.split(",");
				for(int i=0;i<sArrFileNames.length;i++)
				{
					mainRootElement.appendChild(addValuesToTags(doc, sArrFileNames[i], sPackageName+"."+sArrFileNames[i]));
				}*/
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMImplementation domImpl = doc.getImplementation();
			DocumentType doctype = domImpl.createDocumentType("doctype",
					"",
					"http://testng.org/testng-1.0.dtd");
			//transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, doctype.getPublicId());
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(System.getProperty("user.dir")+"/"+System.getProperty("SuiteName")+".xml"));
			transformer.transform(source, result);

			System.out.println("\nXML DOM Created Successfully..");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String convertPackageIntoPath(String sPackageName){
		String sFilePath;
		sFilePath=sPackageName.replaceAll("\\.", "/");
		return sFilePath;
	} 
	private static String getFileNameInsidePackage(String sFilePath){
		File folder = new File(sFilePath);
		File[] listOfFiles = folder.listFiles();
		String sArrFileName=null;
		String sDummy;

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("File " + listOfFiles[i].getName());
				if(i==0)
				{
					sDummy=(listOfFiles[i].getName()).replaceAll(".java", "");
					sArrFileName=sDummy;
				}
				else
				{
					sDummy=(listOfFiles[i].getName()).replaceAll(".java", "");
					sArrFileName=sArrFileName+","+sDummy;
				}

			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
			}
		}
		return sArrFileName;
	}

	private static Node addListnersToTag(Document doc, String sPackageName) {
		Element TagListners = doc.createElement("listeners");
		Element TagListnerPackage = doc.createElement("listener");
		TagListners.appendChild(TagListnerPackage);
		TagListnerPackage.setAttribute("class-name", sPackageName);
		return TagListners;
	}
	private static Node addValuesToTags(Document doc, String sTestName, String sPackageName){
		Element TagTest = doc.createElement("test");
		TagTest.setAttribute("name", sTestName);
		Element TagClasses = doc.createElement("classes");
		TagTest.appendChild(TagClasses);
		Element TagClass = doc.createElement("class");
		TagClasses.appendChild(TagClass);
		TagClass.setAttribute("name", sPackageName);

		return TagTest;
	}

	private static Node getCompany(Document doc, String id, String name, String age, String role) {
		Element company = doc.createElement("Company");
		company.setAttribute("id", id);
		company.appendChild(getCompanyElements(doc, company, "Name", name));
		company.appendChild(getCompanyElements(doc, company, "Type", age));
		company.appendChild(getCompanyElements(doc, company, "Employees", role));
		return company;
	}

	// utility method to create text node
	private static Node getCompanyElements(Document doc, Element element, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		node.appendChild(node);
		return node;
	}
}