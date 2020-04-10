package com.quantum.pages;

import java.util.Set;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
 

import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebDriver;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.quantum.helper.BaseClass;


@SuppressWarnings("static-access")
public class Nflows_Entity_Creation extends WebDriverBaseTestPage<WebDriverTestPage>
{
	
	/**
	 * @author <Sumesh.M.S>
	 * Created On 	- <20th Sep 2019>
	 * Responsibility- <This class is used for Entity Definition>
	 * Modified by <name> on <date> for Issue <Issue number (s)> for Integration <integration number>
	 */			
	
	// Locators in Entity creation page
	@FindBy(locator = "nflows.entitycreation.newEntityCreation.button")
	private QAFExtendedWebElement newEntityCreation;
		
	@FindBy(locator = "nflows.entitycreation.downloadXML.link")
	private QAFExtendedWebElement downloadXML;
	
	@FindBy(locator = "nflows.entitycreation.downloadJSON.link")
	private QAFExtendedWebElement downloadJSON;
	
	
	@FindBy(locator = "nflows.entitycreation.downloadExcel.link")
	private QAFExtendedWebElement downloadExcel;
	
	@FindBy(locator = "nflows.entitycreation.search.textBox")
	private QAFExtendedWebElement search;
	
	@FindBy(locator = "nflows.entitycreation.entitydatatable.grid")
	private QAFExtendedWebElement entitydatatable;
	

	//  Getters 
	
	public QAFExtendedWebElement getNewEntityCreation(String astr_entityName)
	{
		String loc = String.format(pageProps.getString("nflows.entitycreation.newEntityCreation.button"), astr_entityName);
		return new QAFExtendedWebElement(loc);
	}
	
	public QAFExtendedWebElement getDownloadXML() {
		return downloadXML;
	}
	
	public QAFExtendedWebElement getDownloadJSON() {
		return downloadJSON;
	}
	public QAFExtendedWebElement getDownloadExcel() {
		return downloadExcel;
	}
	public QAFExtendedWebElement getEntitycreationSearch() {
		return search;
	}
	public QAFExtendedWebElement getentitydatatable() {
		return entitydatatable;
	}
		
	// Functionalities related to Entity Creation
	
	public void navigateToEntityCreation(String astr_entityName) throws InterruptedException 
	{		
		CommonBean.lbase_baseClass.clickButton(getNewEntityCreation(astr_entityName));		
		Thread.sleep(4000);		
	}
	
	public void editEntityRecord(String astr_entityName) throws InterruptedException 
	{		
		CommonBean.lbase_baseClass.writeText(getEntitycreationSearch(), astr_entityName);
		getEntitycreationSearch().sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		
		
	}
	
	protected void openPage(PageLocator locator, Object... args) {
				
	}
	
	
	
	
}
	

