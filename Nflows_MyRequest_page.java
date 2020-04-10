package com.quantum.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.quantum.helper.ExtentManager;
import com.quantum.utils.DeviceUtils;


@SuppressWarnings("static-access")
public class Nflows_MyRequest_page extends WebDriverBaseTestPage<WebDriverTestPage>
{			
	//locator repository for My Request page	
	@FindBy (locator = "nflows.myrequest.searchbx.txtbx")
	private QAFExtendedWebElement searchbxRequest;
	
	@FindBy (locator = "nflows.myrequest.recalculate.btn")
	private QAFExtendedWebElement recalculate;
	
	@FindBy (locator = "nflows.myrequest.newRequest.btn")
	private QAFExtendedWebElement newRequest;
	
	@FindBy (locator = "nflows.myrequest.graphnode.lnk")
	private QAFExtendedWebElement graphnode;
	
	@FindBy (locator = "nflows.myrequest.parentplusBtn.btn")
	private QAFExtendedWebElement parentplusBtn;
	
	@FindBy (locator = "nflows.myrequest.showOtherRequest.chkbx")
	private QAFExtendedWebElement showOtherRequest;
	
	@FindBy (locator = "nflows.myrequest.loader")
	private QAFExtendedWebElement loadericon;
	
	//locator repository for New Request page	
	@FindBy (locator = "nflows.myrequest.newRequest.workflowName")
	private QAFExtendedWebElement workflowName;
		
	@FindBy (locator = "nflows.myrequest.newRequest.entityName")
	private QAFExtendedWebElement entityName;
	
	@FindBy (locator = "nflows.myrequest.newRequest.searchRecord")
	private QAFExtendedWebElement searchRecord;
	
	@FindBy (locator = "nflows.myrequest.newRequest.back")
	private QAFExtendedWebElement back;
	
	@FindBy (locator = "nflows.myrequest.newRequest.entityRecordLookupNewReq")
	private QAFExtendedWebElement entityRecordLookupNewReq;
	
	@FindBy (locator = "nflows.myrequest.newRequest.start")
	private QAFExtendedWebElement startBtn;	
	
	@FindBy (locator="nflows.myrequest.newRequest.checkIniation")
	private QAFExtendedWebElement checkInitiation;
	
	//locator repository for New Request pop up
	@FindBy (locator = "nflows.myrequest.newRequest.searchInitiation")
	private QAFExtendedWebElement searchInitiation;
	
	@FindBy (locator = "nflows.myrequest.newRequest.addInitiation")
	private QAFExtendedWebElement addInitiation;
	
	@FindBy (locator = "nflows.myrequest.newRequest.closeInitiation")
	private QAFExtendedWebElement closeInitiation;
	
	//locator repository for New Request grid elements (rows and column)
	@FindBy (locator = "nflows.myrequest.newRequest.datagrid")
	private List<QAFExtendedWebElement>  datagrid;
	
	@FindBy (locator = "nflows.myrequest.newRequest.gridHeader")
	private QAFExtendedWebElement gridHeader;
	
	@FindBy (locator = "nflows.myrequest.newRequest.dataRows")
	private QAFExtendedWebElement dataRows;
	
	@FindBy (locator = "nflows.myrequest.newRequest.singledataRow")
	private QAFExtendedWebElement singledataRow;
		
	
// ------------------------------         Locators Ends here !          ----------------------------------------------------	

	public QAFExtendedWebElement getsearchbxRequest() {
		return searchbxRequest;
	}
	
	public QAFExtendedWebElement getrecalculate() {
		return recalculate;
	}
	public QAFExtendedWebElement getnewRequest() {
		return newRequest;
	}
	
	public QAFExtendedWebElement getgraphnode() {
		return graphnode;
	}
	
	public QAFExtendedWebElement getparentplusBtn() {
		return parentplusBtn;
	}
	
	public QAFExtendedWebElement getshowOtherRequest() {
		return showOtherRequest;
	}	
	
	public QAFExtendedWebElement getloadericon() {
		return loadericon;
	}
	
	public QAFExtendedWebElement getClickWorkflowName(String astr_workflowName)
	{
		String loc = String.format(pageProps.getString("nflows.myrequest.newRequest.workflowName"), astr_workflowName);
		return new QAFExtendedWebElement(loc);
	}
	
	public QAFExtendedWebElement getClickEntityName(String astr_entityName)
	{
		String loc = String.format(pageProps.getString("nflows.myrequest.newRequest.entityName"), astr_entityName);
		return new QAFExtendedWebElement(loc);
	}
	
	public QAFExtendedWebElement getClickEntityName() {
		return entityName;
	}
	
	public QAFExtendedWebElement getSearchRecord() {
		return searchRecord;
	}
	public QAFExtendedWebElement getBack() {
		return back;
	}
	
	public QAFExtendedWebElement getStart() 
	{
		return 	startBtn;
	}
	
	public QAFExtendedWebElement getCheckInitiation() 
	{		
		return checkInitiation;
	}
	
	public QAFExtendedWebElement getClickentityRecordLookup() {
		return entityRecordLookupNewReq;
	}
	
	public QAFExtendedWebElement getSearchInitiation() {
		return searchInitiation;
	}
	
	public QAFExtendedWebElement getAddInitiation() {
		return addInitiation;
	}
	
	public QAFExtendedWebElement getCloseInitiation() {
		return closeInitiation;
	}
	
	public List<QAFExtendedWebElement>  getDatagrid() {
		return datagrid;
	}
	
	public QAFExtendedWebElement getGridHeader() {
		return gridHeader;
	}
	
	public QAFExtendedWebElement getDataRows() {
		return dataRows;
	}
	
	public QAFExtendedWebElement getSingledataRow() {
		return singledataRow;
	}
	
		
	@Override
	protected void openPage(PageLocator locator, Object... args) {
		// TODO Auto-generated method stub
		
	}
	
	// ------------------------------         Getter method Ends here !          ----------------------------------------------------
	
	
	public void searchRequest(String astr_searchCriteria ) throws InterruptedException 
	{
		CommonBean.lbase_baseClass.explicitElementToBeClickable(getnewRequest());
		if(getsearchbxRequest().isDisplayed())
		{
			CommonBean.lbase_baseClass.writeText(getsearchbxRequest(),astr_searchCriteria);		
			Thread.sleep(500);
			CommonBean.lbase_baseClass.pressEnter();
			
			CommonBean.lbase_baseClass.explicitInvisibilityOfElementLocated(getloadericon());			
			//CommonBean.lbase_baseClass.explicitElementToBeClickable(getnewRequest());
			
		}		
	}
	
	public void clickRecalculate() throws InterruptedException, IOException 
	{				
		CommonBean.lbase_baseClass.clickButton(getrecalculate());		
		Thread.sleep(1500);
	}	
	
	public void createNewRequest() throws InterruptedException 
	{		
		CommonBean.lbase_baseClass.clickButton(getnewRequest());		
		Thread.sleep(1500);			
	}
	
	public void clickWorkflowName(String astr_workflowName) throws InterruptedException 
	{		
		CommonBean.lbase_baseClass.clickButton(getClickWorkflowName(astr_workflowName));
	}
	
	public void clickentityRecordLookup() throws InterruptedException 
	{				
		CommonBean.lbase_baseClass.clickButton(getClickentityRecordLookup());
	}
	
	public void selectRequest(String astr_searchInitiation,String gridData) throws InterruptedException, IOException
	{
		Thread.sleep(2000);		
		if(getDataRows().isPresent())
		{
			if(astr_searchInitiation!=null)
			{
				CommonBean.lbase_baseClass.writeText(getSearchInitiation(), astr_searchInitiation);
				gridHandle(gridData);
			}
			else
			{
				System.out.println(" - -  No Search is performed  --");
				gridHandle(gridData);
			}
		}
		else
		{
			System.out.println(" - -  There are no records for request  --");
		}		
	}	
	
	public void clickShowOtherRequest() throws InterruptedException, IOException 
	{				
		CommonBean.lbase_baseClass.clickButton(getshowOtherRequest());		
		
		//CommonBean.lbase_baseClass.explicitVisibilityOf(getloadericon());		
		CommonBean.lbase_baseClass.explicitInvisibilityOfElementLocated(getloadericon());
		if(getshowOtherRequest().isEnabled())
		{
			CommonBean.lbase_baseClass.getScreenshot("Click on Other request is successful");	
		}
		else
		{
			CommonBean.lbase_baseClass.getScreenshot("Failure on - Other request");	
			System.out.println("Show Other request is not selected ");
		}
	}
	public void clickGraphNode() throws InterruptedException 
	{		 
			
	}
	public void expandParentGrid() throws InterruptedException 
	{		
			
	}
	
	public void gridHandle(String astr_gridData) throws InterruptedException, IOException
	{	
		List<String> larl_gridData = new ArrayList<String>();
		larl_gridData=Arrays.asList(astr_gridData.split(";"));
        
        int int_size=larl_gridData.size();        
        	
		WebElement datagrid=DeviceUtils.getQAFDriver().findElement(By.xpath("//div[@id='grid_scrn']"));
		List<WebElement> dataRows=datagrid.findElements(By.xpath("//div[@ng-style='rowStyle(row)']"));
					
		for(int j=1;j<dataRows.size();j++)	
		{			
			List<WebElement> singleRow =dataRows.get(j).findElements(By.xpath("(//div[@id='grid_scrn']//div[@ng-style='rowStyle(row)'][" + j +"]//div[contains(@class,'ngCellText')])"));				
			switch(int_size)
			{	
			
			case 2:
				if(singleRow.get(0).getText().trim().toLowerCase().equalsIgnoreCase(larl_gridData.get(0).trim().toLowerCase()) && singleRow.get(1).getText().trim().toLowerCase().equalsIgnoreCase(larl_gridData.get(1).trim().toLowerCase()))
				{
					System.out.println("Request record is matching !");	
					QAFExtendedWebElement aQAF_Element=new WebDriverTestBase().getDriver().findElement(By.xpath("((//div[@id='grid_scrn']//div[@ng-style='rowStyle(row)'])[" + j +"]//div[contains(@class,'ngCellText')])[1]"));
					
					CommonBean.lbase_baseClass.highLightElement(aQAF_Element);
					Thread.sleep(2500);
					
					singleRow.get(0).findElement(By.xpath("((//div[@id='grid_scrn']//div[@ng-style='rowStyle(row)'])[" + j +"]//div[contains(@class,'ngCellText')])[1]")).click();
					j=dataRows.size()+1;					
				}
				break;
			case 4:
				if(singleRow.get(0).getText().trim().toLowerCase().equalsIgnoreCase(larl_gridData.get(0).trim().toLowerCase()) && singleRow.get(1).getText().trim().toLowerCase().equalsIgnoreCase(larl_gridData.get(1).trim().toLowerCase()) && 
					singleRow.get(2).getText().trim().toLowerCase().equalsIgnoreCase(larl_gridData.get(2).trim().toLowerCase()) && singleRow.get(3).getText().trim().toLowerCase().equalsIgnoreCase(larl_gridData.get(3).trim().toLowerCase()))
				{
					System.out.println("Request record is matching !");	
					QAFExtendedWebElement aQAF_Element=new WebDriverTestBase().getDriver().findElement(By.xpath("((//div[@id='grid_scrn']//div[@ng-style='rowStyle(row)'])[" + j +"]//div[contains(@class,'ngCellText')])[1]"));
					
					CommonBean.lbase_baseClass.highLightElement(aQAF_Element);
					Thread.sleep(2500);
					
					singleRow.get(0).findElement(By.xpath("((//div[@id='grid_scrn']//div[@ng-style='rowStyle(row)'])[" + j +"]//div[contains(@class,'ngCellText')])[1]")).click();
					j=dataRows.size()+1;				
				}
				break;	
			}
		}
			
		CommonBean.lbase_baseClass.getScreenshot("Click on request record");		
		CommonBean.lbase_baseClass.clickButton(getAddInitiation());	
		Thread.sleep(1500);	
		
		CommonBean.lbase_baseClass.getScreenshot("Check data before starting request");
		
		CommonBean.lbase_baseClass.clickButton(getStart());
		Thread.sleep(2500);				
		
		CommonBean.lbase_baseClass.clickButton(getBack());			
		CommonBean.lbase_baseClass.explicitVisibilityOf(getloadericon());		
		CommonBean.lbase_baseClass.explicitInvisibilityOfElementLocated(getloadericon());
	}
	
}
