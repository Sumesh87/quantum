package com.quantum.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;

import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebDriver;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.quantum.helper.BaseClass;
import com.quantum.utils.DeviceUtils;

//

@SuppressWarnings("static-access")
public class Nflows_MyTask_page extends WebDriverBaseTestPage<WebDriverTestPage> 
{
	@FindBy(locator = "myTask.loadingicon")
	private QAFExtendedWebElement loaderIcon;
	
	// Locators for User task	
	@FindBy(locator = "myTask.Search")
	private QAFExtendedWebElement myTaskSearch;
	
	@FindBy(locator = "myTask.GridtaskName")
	private QAFExtendedWebElement gridtaskName;

	@FindBy(locator = "Mytask.DelayedFilter")
	private QAFExtendedWebElement DelayedFilter;
	
	@FindBy(locator = "Mytask.On-TrackFilter")
	private QAFExtendedWebElement onTrackFilter;
	
	@FindBy(locator = "Mytask.Due-TodayFilter")
	private QAFExtendedWebElement DueTodayFilter;
	
	@FindBy(locator = "Mytask.AllFilter")
	private QAFExtendedWebElement AllFilter;
	
	@FindBy(locator = "myTask.gridData")
	private QAFExtendedWebElement UserTaskGriddata;
	
	@FindBy(locator = "myTask.gridHeader")
	private QAFExtendedWebElement myTaskGridHeader;
	
	@FindBy(locator = "myTask.gridAllRowData")
	private QAFExtendedWebElement allMyTaskRowData;	
	
	@FindBy(locator = "myTask.grid.nextPage")
	private QAFExtendedWebElement myTaskNextpage;	
	
	// Locators for Team task	
	@FindBy(locator = "TeamTask.Search")
	private QAFExtendedWebElement teamTaskSearch;
	
	@FindBy(locator = "TeamTask.gridHeader")
	private QAFExtendedWebElement TeamGrid;
	
	@FindBy(locator = "TeamTask.gridHeader")
	private QAFExtendedWebElement teamTaskGridHeader;
	
	@FindBy(locator = "TeamTask.gridAllRowData")
	private QAFExtendedWebElement allTeamTaskRowData;	
	
	@FindBy(locator = "TeamTask.grid.rowData")
	private QAFExtendedWebElement gridRowData;
	
	@FindBy(locator = "TeamTask.grid.nextPage")
	private QAFExtendedWebElement teamTaskNextpage;	
		
// ------------------------------       Locator Ends here !          ----------------------------------------------------
	
	public QAFExtendedWebElement getloaderIcon()
	{
		return loaderIcon;
	}
		
	public QAFExtendedWebElement getmyTaskSearch()
	{
		return myTaskSearch;
	}
	
	public QAFExtendedWebElement getGridtaskName() 
	{
		return gridtaskName;
	}
	
	public QAFExtendedWebElement getDelayedFilter() 
	{
		return DelayedFilter;
	}
	
	public QAFExtendedWebElement getonTrackFilter() 
	{
		return onTrackFilter;
	}
	
	public QAFExtendedWebElement getDueTodayFilter() 
	{
		return DueTodayFilter;
	}
	
	public QAFExtendedWebElement getAllFilter() 
	{
		return AllFilter;
	}
	
	public QAFExtendedWebElement getcheckUserTaskPresence()
	{
		return UserTaskGriddata;
	}
	
	public QAFExtendedWebElement getMyTaskGridHeader()
	{
		return myTaskGridHeader;
	}	
	
	public QAFExtendedWebElement getAllMyTaskRowData()
	{
		return allMyTaskRowData;
	}
	
	public QAFExtendedWebElement getmyTaskNextpage()
	{
		return myTaskNextpage;
	}
	
	// Team Task Getters ---------------------------------------------------
	
	public QAFExtendedWebElement getTeamTaskSearch()
	{
		return teamTaskSearch;
	}
	
	public QAFExtendedWebElement getcheckTeamTaskPresence(String astr_taskName)
	{	
		String loc = String.format(pageProps.getString("TeamTask.firstTaskName.data"), astr_taskName);	
		System.out.println(loc);
		return new QAFExtendedWebElement(loc);
	}
	
	public QAFExtendedWebElement getTeamTaskGridHeader()
	{
		return teamTaskGridHeader;
	}
	
	public QAFExtendedWebElement getAllTeamTaskRowData()
	{
		return allTeamTaskRowData;
	}
	
	public QAFExtendedWebElement getGridRowData(Integer rowNum,Integer colNum)
	{
		String loc = String.format(pageProps.getString("TeamTask.grid.rowData"), rowNum,colNum);
		System.out.println(loc);		
		return new QAFExtendedWebElement(loc);
	}	
	
	public QAFExtendedWebElement getTeamTaskNextpage()
	{
		return teamTaskNextpage;
	}
// ------------------------------         Getter method Ends here !          ----------------------------------------------------
	
	public void verifyDelayTaskFilter() throws InterruptedException 
	{
		CommonBean.lbase_baseClass.clickButton(getDelayedFilter());				
	}
	
	public void verifyonTrackFilter() throws InterruptedException 
	{
		CommonBean.lbase_baseClass.clickButton(getonTrackFilter());				
	}
	
	public void verifyDueTodayFilter() throws InterruptedException 
	{
		CommonBean.lbase_baseClass.clickButton(getDueTodayFilter());				
	}
	
	public void verifyAllFilter() throws InterruptedException 
	{
		CommonBean.lbase_baseClass.clickButton(getAllFilter());				
	}
	
	public Boolean searchTask(String astr_TaskName, String astr_userOrteam) throws InterruptedException 
	{
		System.out.println("Method Name: searchTask is called");
		if(astr_userOrteam.equalsIgnoreCase("Team")) 
		{	
			Thread.sleep(1000);	// 2500
			getTeamTaskSearch().clear();	
			CommonBean.lbase_baseClass.loadingWait(CommonBean.IMyRequest_page.getloadericon());
			CommonBean.lbase_baseClass.writeText(getTeamTaskSearch(), astr_TaskName);	
			CommonBean.lbase_baseClass.loadingWait(CommonBean.IMyRequest_page.getloadericon());
			getTeamTaskSearch().sendKeys(Keys.ENTER);	
			//Thread.sleep(1500);
		}
		else
		{		
			Thread.sleep(1000);	//2500
			getmyTaskSearch().clear();			
			CommonBean.lbase_baseClass.loadingWait(CommonBean.IMyRequest_page.getloadericon());
			CommonBean.lbase_baseClass.writeText(getmyTaskSearch(), astr_TaskName);	 
			CommonBean.lbase_baseClass.loadingWait(CommonBean.IMyRequest_page.getloadericon());
			getmyTaskSearch().sendKeys(Keys.ENTER);	
			//Thread.sleep(1500);
		}
		CommonBean.lbase_baseClass.loadingWait(CommonBean.IMyRequest_page.getloadericon());
		//Thread.sleep(3500);		
		Boolean isTaskpresent=false;
		if(driver.findElements(By.xpath("(//span[contains(text(),'" + astr_TaskName  + "')])[1]")).size()>0)
		{
			isTaskpresent =true;
			System.out.println(astr_TaskName + " - Task is present ");
			System.out.println("--------------BLOCK 5 ---------");
			return isTaskpresent;	
			
		}
		else if(driver.findElements(By.xpath("(//div[contains(text(),'" + astr_TaskName  + "')])[1]")).size()>0)
		{
			isTaskpresent =true;
			System.out.println(astr_TaskName + " - Task is present ");
			System.out.println("--------------BLOCK 6 ---------");
			return isTaskpresent;
		}
		else
		{
			System.out.println("No such task is present ");
			return false;	
		}				
	}
	
	public boolean editTask(int colIndex ,String astr_TaskName, String astr_userOrteam,String astr_gridData) throws InterruptedException, IOException 
	{	
		System.out.println("Method Name: editTask is called");
		Boolean istaskPresent=false;
		if(astr_userOrteam.equalsIgnoreCase("Team")) 
		{
			colIndex=CommonBean.IMyTask_Page.getHeaderIndex("Entity", CommonBean.IMyTask_Page.getTeamTaskGridHeader());										
		}
		else
		{
			colIndex=CommonBean.IMyTask_Page.getHeaderIndex("Entity", CommonBean.IMyTask_Page.getMyTaskGridHeader());	
		}
		
		// This code will edit tasks in my task Grid or Team Task Grid
		
		istaskPresent=editTaskBasedOnRow(astr_userOrteam,astr_gridData,colIndex); // slow 
		if(istaskPresent)
		{
			Thread.sleep(2000);		// 2500	
			BaseClass.getScreenshot(astr_TaskName + " - Task Edited");
			CommonBean.lbase_baseClass.scrollDown();
		}		
		return istaskPresent;	
	}
	
	public void completeTask(String astr_taskAction) throws InterruptedException
	{
		try 
		{				
			switch (astr_taskAction.toLowerCase())
			{
				case "submit":
					System.out.println("Submit the Task");
					try {
						//CommonBean.lbase_baseClass.highLightElement(CommonBean.ITask_Library.getTaskSubmitBtn());
						CommonBean.lbase_baseClass.clickButton(CommonBean.ITask_Library.getTaskSubmitBtn());
						Thread.sleep(2000);
						//CommonBean.lbase_baseClass.clickButton(CommonBean.ITask_Library.getTaskCancelBtn());
					} 
					catch (Exception e1) 
					{
						CommonBean.lbase_baseClass.clickButton(CommonBean.ITask_Library.getTaskCancelBtn());	
					}
					break;
				case "approve": case "approved":
					System.out.println("Approve the approval Task");
					//CommonBean.lbase_baseClass.highLightElement(CommonBean.ITask_Library.getApproveTaskBtn());
					CommonBean.lbase_baseClass.clickButton(CommonBean.ITask_Library.getApproveTaskBtn());
					Thread.sleep(2000);
					//CommonBean.lbase_baseClass.clickButton(CommonBean.ITask_Library.getTaskCancelBtn());
					break;
				case "reject": case "rejected":
					System.out.println("Reject the approval Task");
					//CommonBean.lbase_baseClass.highLightElement(CommonBean.ITask_Library.getRejectTaskBtn());
					CommonBean.lbase_baseClass.clickButton(CommonBean.ITask_Library.getRejectTaskBtn());
					Thread.sleep(2000);
					//CommonBean.lbase_baseClass.clickButton(CommonBean.ITask_Library.getTaskCancelBtn());
					break;
				case "save as draft":
					System.out.println("Do save as Draft");
					//CommonBean.lbase_baseClass.highLightElement(CommonBean.ITask_Library.getTaskSaveDraftBtn());
					CommonBean.lbase_baseClass.clickButton(CommonBean.ITask_Library.getTaskSaveDraftBtn());
					Thread.sleep(2000);
					CommonBean.lbase_baseClass.clickButton(CommonBean.ITask_Library.getTaskCancelBtn());
					break;
				case "cancel":
					System.out.println("Cancel the Task");					
					try 
					{
						//CommonBean.lbase_baseClass.highLightElement(CommonBean.ITask_Library.getTaskCancelBtn());
						CommonBean.lbase_baseClass.clickButton(CommonBean.ITask_Library.getTaskCancelBtn());
						Thread.sleep(2000);
					} 
					catch (Exception e)
					{		
						DeviceUtils.getQAFDriver().navigate().refresh();
						CommonBean.ITask_Library.getTaskCancelBtn().click();						
					}
						break;
				default : 
					System.out.println("Invalid Option");
					break;					
			}
			
			Thread.sleep(3000);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getHeaderIndex(String colName, QAFWebElement  gridHeader)
	{
		int colNum=0;
		//int noOfEmptyCol=6;

		List<WebElement> col=gridHeader.findElements(By.xpath("//div[@ng-class='col.colIndex()']"));
		
		for(int i=0;i<=col.size();i++)
		{
			//System.out.println("Col: " + i + " :" + col.get(i).getText());
			if(col.get(i).getText().trim().equalsIgnoreCase(colName.trim())){
				colNum=i;
				break;
			}
			else {
				System.out.println("Invalid column name !");
			}
		}
		return colNum ;		
	}
	
	public Boolean editTaskBasedOnRow(String astr_userOrteam,String astr_gridData,int colIndex) throws InterruptedException, IOException
	{
		System.out.println("Method Name: editTaskBasedOnRow is called");
		String astr_gridType="";
		Boolean isTaskExists=false;
		if(astr_userOrteam.equalsIgnoreCase("Team")) 
		{
			astr_gridType="myTeamTaskGrid";			
		}
		else
		{
			astr_gridType="taskGrid";
			//colIndex=colIndex-1;
		}
		
		colIndex=colIndex+1;
		
		// Get all rows from the Grid
		List<WebElement> row=DeviceUtils.getQAFDriver().findElementsByXPath("//div[@ng-grid='" + astr_gridType + "']//div[@ng-style='rowStyle(row)']");		
		
		for(int i=1;i<=row.size();i++)
		{					
			// To process each row data
			//QAFExtendedWebElement rowDataElement=DeviceUtils.getQAFDriver().findElementByXPath("((//div[@ng-grid='" + astr_gridType + "']"
					//+ "//div[@ng-style='rowStyle(row)'])["+ i + "]//div[@ng-class='col.colIndex()'])[" + colIndex + "]//span");
			
			QAFExtendedWebElement rowDataElement=DeviceUtils.getQAFDriver().findElementByXPath("(//div[@ng-grid='" + astr_gridType + "']"
					+ "//div[@ng-style='rowStyle(row)'])["+ i + "]//div[@ng-class='col.colIndex()'][" + colIndex + "]");
			
			
			String getrowData=rowDataElement.getText().trim().toLowerCase();
			
			// If entity data matches , Edit the task			
			if(astr_gridData != null && !astr_gridData.isEmpty() && getrowData.trim().equalsIgnoreCase(astr_gridData.trim().toLowerCase())) 
			{
				isTaskExists=true;
				System.out.println("The Entity data : " + getrowData);	
				Thread.sleep(500);
				BaseClass.getScreenshot("Task search success ");
				Thread.sleep(1500);
				CommonBean.lbase_baseClass.doubleClick(rowDataElement);
				Thread.sleep(5000);
				break;				
			}			
			if(i==6)
			{
				System.out.println(" Click next page when no matches are found in current page ");
				if(astr_userOrteam.equalsIgnoreCase("Team")) 
				{
					// Click next page under team task grid when no matches are found
					getTeamTaskNextpage().click();
				}
				else
				{
					// Click next page under My task grid when no matches are found
					getmyTaskNextpage().click();
				}
				CommonBean.lbase_baseClass.explicitInvisibilityOfElementLocated(CommonBean.IMyRequest_page.getloadericon());
				try 
				{
					row=DeviceUtils.getQAFDriver().findElementsByXPath("//div[@ng-grid='" + astr_gridType + "']//div[@ng-style='rowStyle(row)']");
					System.out.println(row.size());
				} 
				catch (Exception e) 
				{
					// TODO Auto-generated catch block
					System.out.println("Unable to find the row element in  " + astr_gridType );
					e.printStackTrace();
				}	
				i=0;
			}				
		}
		return isTaskExists;		
	}
	
	
	protected void openPage(PageLocator locator, Object... args) 
	{
		
	}	
}
	

