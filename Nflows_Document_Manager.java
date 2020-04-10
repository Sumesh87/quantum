package com.quantum.pages;

import java.util.Set;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
public class Nflows_Document_Manager extends WebDriverBaseTestPage<WebDriverTestPage>
{
	
	/**
	 * @author <Sumesh.M.S>
	 * Created On 	- <12th Feb 2020>
	 * Responsibility- <This class is used for Document manager>
	 * Modified by <name> on <date> for Issue <Issue number (s)> for Integration <integration number>
	 */
	
	// WebDriver driver;
	public WebDriverTestBase driver;
	//BaseClass lbase_baseClass = new BaseClass();	
	
	
	@FindBy(locator = "nflows.documentmanager.treeSearchBx")
	private QAFExtendedWebElement treeSearchBx;
	
	@FindBy(locator = "nflows.documentmanager.EntityType")
	private QAFExtendedWebElement EntityType;
	
	@FindBy(locator = "nflows.documentmanager.Entitylevelfields")
	private QAFExtendedWebElement Entitylevelfields;
	
	@FindBy(locator = "nflows.documentmanager.Workflowlevelfields")
	private QAFExtendedWebElement Workflowlevelfields;
	
	@FindBy(locator = "nflows.documentmanager.searchBxGridData")
	private QAFExtendedWebElement searchBxGridData;
	
	@FindBy(locator = "nflows.documentmanager.searchVersionGrid")
	private QAFExtendedWebElement searchVersionGrid;
	
	@FindBy(locator = "nflows.documentmanager.downloadParentfile")
	private QAFExtendedWebElement downloadParentfile;
	
	@FindBy(locator = "nflows.documentmanager.downloadAllfile")
	private QAFExtendedWebElement downloadAllfile;
	

	// ------------------------------         Locators Ends here !          ----------------------------------------------------
	
	public QAFExtendedWebElement gettreeSearchBx() {
		return treeSearchBx;
	}
	
	public QAFExtendedWebElement getEntityType() {
		return EntityType;
	}
	
	public QAFExtendedWebElement getEntitylevelfields() {
		return Entitylevelfields;
	}
	
	public QAFExtendedWebElement getWorkflowlevelfields() {
		return Workflowlevelfields;
	}
	
	public QAFExtendedWebElement getsearchBxGridData() {
		return searchBxGridData;
	}
	
	public QAFExtendedWebElement getentityRowRecord(String astr_EntityRecord)
	{
		String loc = String.format(pageProps.getString("nflows.documentmanager.entityRowRecord"), astr_EntityRecord);
		return new QAFExtendedWebElement(loc);
	}
	
	public QAFExtendedWebElement getFieldNameInEntityRow(String astr_FieldName)
	{
		String loc = String.format(pageProps.getString("nflows.documentmanager.fieldentityRowRecord"), astr_FieldName);
		return new QAFExtendedWebElement(loc);
	}
	
	public QAFExtendedWebElement getsearchVersionGrid() {
		return searchVersionGrid;
	}
	
	public QAFExtendedWebElement getDownloadParentfile() {
		return downloadParentfile;
	}
	
	public QAFExtendedWebElement getDownloadAllfile() {
		return downloadAllfile;
	}
	
	// ------------------------------         Getter method Ends here !          ----------------------------------------------------
	
	// Type Entity Type in search tree
	public void searchEntityType(String astr_EntityType) throws InterruptedException
	{
		CommonBean.lbase_baseClass.writeText(treeSearchBx, astr_EntityType);
	}
	
	// Click on Entity level field under search tree
	public void clickEntitylevelfields() throws InterruptedException
	{
		CommonBean.lbase_baseClass.clickButton(getEntitylevelfields());
	}
	
	// Click on Workflow level field under search tree
	public void clickWorkflowlevelfields() throws InterruptedException
	{
		CommonBean.lbase_baseClass.clickButton(getWorkflowlevelfields());
	}
	
	// Type Entity record in search tree
	public void searchEntityRecord(String astr_EntityRecord) throws InterruptedException
	{
		CommonBean.lbase_baseClass.writeText(treeSearchBx, astr_EntityRecord);
	}	
	
	// Click on Entity record under Grid
	public void clickOnEntityRecord(String astr_EntityRecord) throws InterruptedException
	{
		CommonBean.lbase_baseClass.doubleClick(getentityRowRecord(astr_EntityRecord));
	}
	
	// Click on field name in a particular Entity record under Grid
	public void clickOnAttachmentField(String astr_attachmentfield) throws InterruptedException
	{
		CommonBean.lbase_baseClass.clickButton(getFieldNameInEntityRow(astr_attachmentfield));
	}
	
//	// Click on field name in a particular Entity record under Grid
//	public void clickOnAttachmentField(String astr_attachmentfield) throws InterruptedException
//	{
//		CommonBean.lbase_baseClass.clickButton(getFieldNameInEntityRow(astr_attachmentfield));
//	}
	
	
	public Boolean ValidateDocument(String astr_entityfield, String astr_record, String astr_entity) throws InterruptedException
	{
		searchEntityType(astr_entity);
		Thread.sleep(2000);
		
		if(getEntityType().isPresent())
		{
			clickEntitylevelfields();
			Thread.sleep(2000);
			if(getentityRowRecord(astr_record).isPresent())
			{
				clickOnEntityRecord(astr_record);
				Thread.sleep(1500);
				
				clickOnAttachmentField(astr_entityfield);
				Thread.sleep(1500);
			}
			else
			{
				System.out.println("No such entity record exists");
			}		
			
			Thread.sleep(1500);
			return true;
		}
		else
		{
			System.out.println("No such entity exists");
			return false;
		}		
	}
	
	
	
	protected void openPage(PageLocator locator, Object... args) {
				
	}
	
	
}
	

