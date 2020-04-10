package com.quantum.steps;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.sikuli.script.Screen;
import org.testng.Assert;

import com.itextpdf.text.DocumentException;
import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.util.Reporter;
import com.quantum.helper.BaseClass;
import com.quantum.helper.MyScreenRecorder;
import com.quantum.helper.PDFManager;
import com.quantum.helper.Xls_Reader;
import com.quantum.pages.CommonBean;
import com.quantum.pages.Nflows_Task_Library;
import com.quantum.utils.DeviceUtils;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@QAFTestStepProvider()
public class NFlowsStepDef extends WebDriverBaseTestPage<WebDriverTestPage>  {
	/**
	 * @author <Sumesh.M.S> Created On - <20th Sep 2019> Responsibility- <This class
	 *         is used for running Step definition files> Modified by <name> on
	 *         <date> for Issue <Issue number (s)> for Integration <integration
	 *         number>
	 */
	WebDriverTestBase driver = new WebDriverTestBase();	
	
	static HSSFWorkbook Iworkbook;
	static HSSFSheet Isheet;
	static Map<Integer, Object[]> Itestresultdata;
	static int imapKey=0;
	static int iTestcaseID=0;
		
	/******************************************************************************************************       
						Method-STARTS HERE ! Login functionality      
	******************************************************************************************************/
	
	@Then("^I launch Nflows application using \"([^\"]*)\"$")
	public void Iam_on_Nflows(String astr_url) throws Exception 
	{		
		
		Boolean isLaunch = null;
		try 
		{		
			isLaunch = CommonBean.lLogin_page.launch_application(astr_url);
			Itestresultdata=new LinkedHashMap<Integer, Object []>();
			Itestresultdata.put(++imapKey, new Object[] {"Test Step Id", "Action","Test Data", "Actual Result","STATUS","Exception message"});
						
			if(isLaunch.booleanValue())
			{
				BaseClass.getScreenshot("Site is launched successfully");				
				
				Itestresultdata.put(++imapKey, new Object[] {++iTestcaseID, "Navigate to Nflows site",astr_url, "Launch Nflows app is success","PASS"});
				Assert.assertEquals(isLaunch.booleanValue(), true, "Site is launched successfully");
			}
			else if(CommonBean.lLogin_page.getpageNotFound().isPresent())
			{				
				Itestresultdata.put(++imapKey, new Object[] {++iTestcaseID, "Navigate to Nflows site", astr_url,"nFlows - Page Not Found","FAIL"});
				//Assert.assertEquals(false, true);
				driver.getDriver().close();
				createTestResult("YES");
				System.exit(1);
				
			}
			else if(CommonBean.lLogin_page.getsiteUnavailable().isPresent())
			{				
				Itestresultdata.put(++imapKey, new Object[] {++iTestcaseID, "Navigate to Nflows site", astr_url,"This site can’t be reached","FAIL"});
				//Assert.assertEquals(false, true);
				driver.getDriver().close();
				createTestResult("YES");
				System.exit(1);
			}
			else 
			{			
				CommonBean.lbase_baseClass.reporting("Failure in launching site");
				
				Assert.assertEquals(isLaunch.booleanValue(), false, "Failure in launching site");		
				try
		        { 
		            throw new Exception("Site unreachable"); 
		        } 
		        catch(Exception e) 
		        { 
		        	Itestresultdata.put(++imapKey, new Object[] {++iTestcaseID, "Navigate to Nflows site", "","Launch Nflows app is failed","FAIL",e.getMessage()});
					e.printStackTrace();
					createTestResult("YES");
					System.exit(1);
		        }			
			}		
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();				
		}		
	}

	@When("^I login to Nflows with \"([^\"]*)\" and \"([^\"]*)\" credentials$")
	public void I_login_to_Nflows_with_userName_and_password(String astr_Username, String astr_Password)
			throws Throwable 
	{
		try 
		{
			Boolean lIsloginSuccess=CommonBean.lLogin_page.login_with_valid_username_and_password(astr_Username, astr_Password);
			if(lIsloginSuccess.booleanValue())
			{
				Itestresultdata.put(++imapKey, new Object[] {++iTestcaseID, "Login with valid Username and password ","User Name : " +astr_Username +",Password : " +astr_Password ,"User is successfully logged in","PASS"});
				Assert.assertEquals(lIsloginSuccess.booleanValue(), true);
				Reporter.log("Success - Login is Successful !");
			}			
			else
			{
				System.out.println("Failure - Login is Unsuccessful !");
			}				
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();	
			Itestresultdata.put(++imapKey, new Object[] {++iTestcaseID, "Login with valid Username and password ", "User login is failed","FAIL",e.getMessage()});
			CommonBean.lbase_baseClass.reporting("Failure - Login is Unsuccessful !");
			createTestResult("YES");
			System.exit(1);
		}
	}
	
	@Given("^I navigate to \"([^\\\"]*)\" page$")
	public void navigate_to_defined_menu(String astr_MenuName) throws Throwable 
	{	
		CommonBean.lHome_page_home.navigateByMenu(astr_MenuName);	
		Thread.sleep(1500); // 1500
		if(CommonBean.IMyRequest_page.getloadericon().isDisplayed())
		{
			CommonBean.lbase_baseClass.explicitInvisibilityOfElementLocated(CommonBean.IMyRequest_page.getloadericon());
		}	
		Itestresultdata.put(++imapKey, new Object[] {++iTestcaseID, "Navigate to " + astr_MenuName,"Menu Name : " +astr_MenuName ,"Navigated to " + astr_MenuName + " Successfully","PASS"});
	}	
	
	/******************************************************************************************************       
    					Method-STARTS HERE ! Entity Definition functionality      
	******************************************************************************************************/
	
	@When("^I create a new Entity Definition$")
	public void create_new_Entity_Definition() throws Throwable 
	{
		System.out.println("create_new_Entity_Definition");
	}

	@Then("^I Edit an Entity Definition for \"([^\"]*)\" Entity$")
	public void I_Edit_an_Entity_Definition(String astr_entityName) throws Throwable 
	{
		try 
		{
			CommonBean.lEntity_Definition_entityDef.editEntityfromgridView(astr_entityName);
			Thread.sleep(1500);	
			
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Method name : I_Edit_an_Entity_Definition - Failed");
			
		}
	}
	
	@When("^I create a new Entity record for \"([^\"]*)\"$")
	public void create_new_Entity_record(String astr_entityName) throws Throwable 
	{
		CommonBean.IEntity_Creation.navigateToEntityCreation(astr_entityName);
	}
	
	//I navigate to Workflow page from Entity definition page
	@Then("^I navigate to Workflow page \"([^\"]*)\" from Entity definition page$")
	public void navigate_to_Workflow_from_Entitydefinition(String astr_workflowName) throws Throwable 
	{
		try 
		{
			Thread.sleep(1000);
			CommonBean.lEntity_Definition_entityDef.navigate_to_Workflow(astr_workflowName); 
			Thread.sleep(1500);
			
			CommonBean.lbase_baseClass.reporting("Navigate to Workflow page from Entity successfully");			
			Itestresultdata.put(++imapKey, new Object[] {++iTestcaseID, "Navigate to Workflow page from Entity","Workflow Name : " +astr_workflowName ,"Navigate to Workflow page from Entity successfully","PASS"});
			
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@And("^I click on Grouping Rule tab$")
	public void clickGroupingRuleTab() throws Throwable 
	{
		try 
		{
			CommonBean.lbase_baseClass.javascriptClick(CommonBean.lEntity_Definition_entityDef.getClickGroupingRulesTab());		
			Thread.sleep(1500);			
			CommonBean.lbase_baseClass.reporting("Clicked on Grouping Rule tab successfully");
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();			
			Assert.assertFalse(false, "Failed  to click Grouping rule tab");		
			Reporter.log("Failed  to click Grouping rule tab");
		}
	}

	@And("^I create a grouping rule for entity with \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
	public void createGroupingRule(String astr_groupingRuleName, String astr_Description, String astr_RuleDefinition) throws Throwable 
	{
		try 
		{
			CommonBean.lEntity_Definition_entityDef.createNewGroupingRule(astr_groupingRuleName, astr_Description, astr_RuleDefinition);	
			Reporter.log("Create Grouping Rule ");
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block			
			System.out.println("Method name : createGroupingRule - Failed");					
			Reporter.log("Failed to new create Grouping rule ");
		}
	}
	
	public void edit_an_Entity_Definition (String astr_entityName) throws Throwable
	{
		try 
		{
			CommonBean.lEntity_Definition_entityDef.editEntityfromgridView(astr_entityName);
			Thread.sleep(1500);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void click_on_rules_tab () throws Throwable
	{
		try 
		{
			//Click on Rules Tab			
			CommonBean.lbase_baseClass.javascriptClick(CommonBean.lEntity_Definition_entityDef.getClickRulesTab());
			// [ Possibility of Execution stopping !]
			Thread.sleep(1500);
			BaseClass.getScreenshot("Clicked on Rules Tab successfully");
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Then("^I create a rule for the \"([^\"]*)\" entity with \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
	public void create_a_new_rule_definition(String astr_entityName, String RuleName, String RuleType, String Execution, String RuleDesc,
			String RuleDefinition, String Selectfield, String Selectmessage, String RuleEnrichment, String RuleFilter,String RuleGroup)
			throws Throwable 
	{
		try 		
		{	
			if(astr_entityName!=null) 
			{
				navigate_to_defined_menu("Entity Definition")	;			
			
				// Search an Entity and Edit entity from Grid view
				edit_an_Entity_Definition (astr_entityName);
					
				//Click on Rules Tab
				click_on_rules_tab();
			}
			
			CommonBean.lRule_Engine_Definition_ruleDefinition.clickNewRule();
			CommonBean.lRule_Engine_Definition_ruleDefinition.inputBasicRuleDetails(RuleName, RuleType, Execution, RuleDesc);

			if (RuleType.equalsIgnoreCase("validation")) {
				CommonBean.lRule_Engine_Definition_ruleDefinition.FillValidationDetails(RuleDefinition,RuleGroup, Selectfield, Selectmessage);
			} else if (RuleType.equalsIgnoreCase("enrichment")) {
				CommonBean.lRule_Engine_Definition_ruleDefinition.FillEnrichmentDetails(RuleDefinition,RuleGroup, RuleEnrichment);
			} else if (RuleType.equalsIgnoreCase("filter")) {
				CommonBean.lRule_Engine_Definition_ruleDefinition.FillFilterDetails(RuleDefinition,RuleGroup, RuleFilter);
			} else {
				System.out.println("This is an invalid Rule type");
			}

			BaseClass.getScreenshot("Enter data for " + RuleType + " rule .");
			//lRule_Engine_Definition_ruleDefinition.saveRule();
			Thread.sleep(2500);	
			
			CommonBean.lRule_Engine_Definition_ruleDefinition.cancelRule();
			Thread.sleep(2000);
			
			CommonBean.lRule_Engine_Definition_ruleDefinition.cancelWithoutSaveRule();
			Thread.sleep(2000);
		}
		
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@When("^I create a new record for \"([^\"]*)\" Entity$")
	public void create_Entity_Creation_record() throws Throwable 
	{

	}
	
	// I edit workflow
	@When("^I edit workflow page$")
	public void I_edit_workflow() throws Throwable {
		System.out.println("Sikuli method is called !");
		Screen s = new Screen();
		//s.rightClick("C:\\Users\\sumeshm\\Music\\sikuliImage\\Worklow Node.PNG");
		Thread.sleep(1500);
	}

	// I filter by <Filter> type
	@When("^I filter by \"([^\"]*)\" type$")
	public void filterTask_status() throws Throwable 
	{
		System.out.println("filterTask_status");
	}	

	/******************************************************************************************************       
						Method-STARTS HERE ! Forgot password functionality      
	******************************************************************************************************/

	@Given("^I click on forgot password link$")
	public void iClickOnForgotPasswordLink() throws InterruptedException {
		try {
			CommonBean.lbase_baseClass.clickButton(CommonBean.lLogin_page.getforgotPassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@And("^I enter the userName \"([^\"]*)\"$")
	public void iEnterTheUserName(String userName) throws InterruptedException {
		try {
			CommonBean.lbase_baseClass.writeText(CommonBean.lLogin_page.getTypeUsername(), userName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@And("^I click Submit for Forgot password$")
	public void iClickSubmitForForgotPassword() throws InterruptedException 
	{
		try {
			CommonBean.lbase_baseClass.clickButton(CommonBean.lLogin_page.getClickForgotSubmit());
			Thread.sleep(2500);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("^I got email for forgot paasword successfully$")
	public void iGotEmailForForgotPaaswordSuccessfully() 
	{

	}

	//Method-ENDS HERE! 

	/*******************************************************************************************************       
	                    Method-STARTS HERE ! My Request functionality      
	*******************************************************************************************************/


	@And("^I wait for proper page loading for myRequest page$")
	public void checkMyRequestLoad() throws InterruptedException, IOException 
	{	
		try {
			CommonBean.lbase_baseClass.explicitInvisibilityOfElementLocated(CommonBean.IMyRequest_page.getloadericon());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@And("^I check Search functionality for \"([^\"]*)\"$")
	public void performSearchinMyrequest(String astr_searchName) throws InterruptedException, IOException 
	{
		try {
			CommonBean.IMyRequest_page.searchRequest(astr_searchName);	
			BaseClass.getScreenshot("Search for My request is done successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@And("^I check recalculate functionality$")
	public void checkRecalculate()
	{
		
	}
	
	@And("^I check show Other Request functionality$")
	public void checkShowOtherRequest() throws InterruptedException, IOException 
	{
		try 
		{
			CommonBean.IMyRequest_page.clickShowOtherRequest();
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@And("^I navigate to new Request page$")
	public void navigatetoNewRequest() throws InterruptedException, IOException
	{
		try 
		{
			CommonBean.IMyRequest_page.createNewRequest();
			String lStr_expected="nFlows - Request Manual Trigger";
			String lStr_actual=driver.getDriver().getTitle();
			if(lStr_actual.equals(lStr_expected))
			{
				BaseClass.getScreenshot("Navigate to New  Request page");
				Itestresultdata.put(++imapKey, new Object[] {++iTestcaseID, "Navigate to new Request page", "Navigate to new Request page successfully","PASS"});
			}
			else
			{
				BaseClass.getScreenshot("Failed to Navigate to New Request page");
				Itestresultdata.put(++imapKey, new Object[] {++iTestcaseID, "Navigate to new Request page", "Failed to Navigate to New Request page","FAIL"});
			}
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
	}
	
	@And("^I click on \"([^\"]*)\" and click on entityRecordLookup button$")
	public void clickWorkflow_and_clickEntityRecordLookup(String astr_workflowName) throws InterruptedException, IOException
	{
		try 
		{
			CommonBean.IMyRequest_page.clickWorkflowName(astr_workflowName);
			BaseClass.getScreenshot("Click on workflow Name : " + astr_workflowName);
			
			CommonBean.IMyRequest_page.clickentityRecordLookup();			
			Itestresultdata.put(++imapKey, new Object[] {++iTestcaseID, "Click Workflow and click EntityRecordLookup", "Successfully Clicked Workflow and EntityRecordLookup","PASS"});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@And("^I select a \"([^\"]*)\" request from request pop up based on \"([^\"]*)\" initiation fields$")
	public void selectRequest(String astr_searchInitiation,String gridData) throws InterruptedException, IOException
	{		
		CommonBean.IMyRequest_page.selectRequest(astr_searchInitiation,gridData);
	}
	

	@Then("^\"([^\"]*)\" Request is successfully triggered and validated in my Request page$")
	public void validateNewRequest(String astr_initiationField) throws InterruptedException, IOException
	{		
		try {
			CommonBean.IMyRequest_page.searchRequest(astr_initiationField);	
			BaseClass.getScreenshot("New Request is triggered successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Method-ENDS HERE! 	

	/*******************************************************************************************************       
      						Method-STARTS HERE ! My Task functionality     
	 * @throws Throwable 
	*******************************************************************************************************/

	@And("^I search for a particular task \"([^\"]*)\" of \"([^\"]*)\" in \"([^\"]*)\" section and edit based on \"([^\"]*)\" in \"([^\"]*)\"$")
	public void searchAndEditTask(String astr_TaskName, String astr_taskType, String astr_UserOrTeam,String astr_gridData,String menuName) throws Throwable 
	{	
			try 
			{		
				Boolean isTaskexists=null;
				if(menuName!=null) 
				{
					// To navigate to my task page
					navigate_to_defined_menu("My Task");
					
					// To check page is loaded properly
					//checkMyRequestLoad();		// commented			
				}				
				int colIndex=0;
				if(CommonBean.IMyTask_Page.getloaderIcon().isDisplayed())
				{					
					isTaskexists=CommonBean.IMyTask_Page.searchTask(astr_TaskName, astr_UserOrTeam);
					System.out.println("--------------BLOCK 1 ---------");
				}
				else
				{					
					isTaskexists=CommonBean.IMyTask_Page.searchTask(astr_TaskName, astr_UserOrTeam);
					System.out.println("--------------BLOCK 2 ---------");
				}			
				
				// Check whether task is present and edit based on task's availability
				if(isTaskexists)					
				{										
					Thread.sleep(1500);										
					Boolean isTaskAvailable=CommonBean.IMyTask_Page.editTask(colIndex, astr_TaskName, astr_UserOrTeam, astr_gridData);
					if(isTaskAvailable)
					{
						Itestresultdata.put(++imapKey, new Object[] 
								{++iTestcaseID, "Edit the task based on entity data" ,astr_TaskName, "Task search is success","PASS"});						
					} else
					{
						Itestresultdata.put(++imapKey, new Object[] 
							{++iTestcaseID, "Edit the task based on entity data" ,astr_TaskName, "Task is not available to Edit","FAIL"});
						BaseClass.getScreenshot("'"+ astr_TaskName + "'" + " - Task is not available to Edit-Failed ");
					}
				}
				else
				{					
					System.out.println("No Task is available to Edit");
					Itestresultdata.put(++imapKey, new Object[] 
							{++iTestcaseID, "Search for a task" ,astr_TaskName, "No such Task is present","FAIL"});
					BaseClass.getScreenshot("'"+ astr_TaskName + "'" + " - Task search Failed ");
					Thread.sleep(1500);

				}				
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@And("^I perform action for \"([^\"]*)\"$")	
	public void performAction(String astr_task_ID) throws IOException
	{		
		LinkedHashMap<String, Map<String, String>> map=CommonBean.xls_reader.getAllExcelData("Task_Details",astr_task_ID);	
		
		// Get keys and values
		for (Map.Entry<String, Map<String, String>> entry : map.entrySet()) 
		{	
			System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());	
			switch(entry.getKey().toLowerCase())
			{
				case "insert":
					System.out.println("insert : ");
					//String arr[]=entry.getValue().toString().split(",");
					//arr[0]=
					
					//CommonBean.lbase_baseClass.performInsertAction(astr_fieldType, astr_Locator, astr_data);
					break;
				case "update":
					System.out.println("update : ");
					break;
				case "delete":
					System.out.println("delete : ");
					break;
				case "validate":
					System.out.println("validate : ");
					break;
			}
		}			
	}

	@And("^I completed the \"([^\"]*)\" task based on \"([^\"]*)\"$")
	public void completeTask(String astr_TaskName,String astr_taskAction) throws InterruptedException, IOException 
	{	
			try 
			{	
				Boolean isTaskPresent=Nflows_Task_Library.getTaskCancelBtn().isPresent();
				if(isTaskPresent)
				{
					BaseClass.getScreenshot("Do " + astr_taskAction+ " - " + astr_TaskName);						
					Thread.sleep(2000);					
					
					CommonBean.IMyTask_Page.completeTask(astr_taskAction);
					
					Itestresultdata.put(++imapKey, new Object[] 
							{++iTestcaseID, "Complete the task" ,"Task Name : " + astr_TaskName, "Task is completed by action : " + astr_taskAction,"PASS"});
					
					Thread.sleep(2500);
					
					if(CommonBean.IMyRequest_page.getloadericon().isDisplayed())
					{
						CommonBean.lbase_baseClass.explicitInvisibilityOfElementLocated(CommonBean.IMyRequest_page.getloadericon());
					}
				}
				else
				{
					System.out.println("No task is present to complete !");
					Thread.sleep(1500);
					BaseClass.getScreenshot("No task is present to complete : " + astr_TaskName);					
					Itestresultdata.put(++imapKey, new Object[] 
							{++iTestcaseID, "Complete the task" ,"Task Name : " + astr_TaskName, "Task completion failed for action : " + astr_taskAction ,"FAIL"});
					
				}				
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}	
	
	@Then("^I check whether current task belongs to same user with \"([^\"]*)\" and \"([^\"]*)\" credentials$")
	public void check_task_for_another_user(String astr_userName,String astr_Password) throws Throwable 
	{	
			try 
			{					
				if(astr_userName!=null) 
				{	
					Thread.sleep(2000);
					CommonBean.lbase_baseClass.loadingWait(CommonBean.IMyRequest_page.getloadericon());
					
					//Logout as current user
					click_Logout();
					Thread.sleep(3500);	// old 6000
					System.out.println("The User whom the task is alloted : " + astr_userName );
					Itestresultdata.put(++imapKey, new Object[] {++iTestcaseID, "Logout as current User " ,"","Logout is Successful","PASS"});
										
					//Login as next user
					CommonBean.lLogin_page.login_with_valid_username_and_password(astr_userName, astr_Password);
					Thread.sleep(3500);	//
					
					Itestresultdata.put(++imapKey, new Object[] {++iTestcaseID, "Login as new User to perform the task ","User Name : " +astr_userName +",Password : " +astr_Password ,"User is successfully logged in","PASS"});
				}
				else
				{
					System.out.println("This task is performed by same user");
				}
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	//Method-ENDS HERE! 
	
	// ----    Method-STARTS HERE ! Widget Definition functionality    -------- 
	
	@Then("^I validate back update process$")	
	public void validate_backupdate() throws Throwable 
	{				
		
	}
	
	
	
	//Method-ENDS HERE! 
	
	// ----    Method-STARTS HERE ! Widget Definition functionality    -------- 
	
	@Then("^I Edit an Widget Definition for \"([^\"]*)\" widget$")	
	public void edit_WidgetDefinition(String astr_widgetName) throws Throwable 
	{
		System.out.println("edit_WidgetDefinition method is called !");		
		CommonBean.IWidget_Definition.editWidgetDefinition(astr_widgetName);
	}

	@Then("^add columns to the widgets \"([^\"]*)\"$")	
	public void addfields(String astr_addFields) throws Throwable 
	{
		try 
		{
			System.out.println("addfields method is called !");		
			CommonBean.IWidget_Definition.addFieldToWidget(astr_addFields);
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Then("^I create a \"([^\"]*)\" rule for Widget Definition$")	
	public void createWidgetRule(String astr_ruleDefinition) throws Throwable 
	{
		try 
		{
			System.out.println("createWidgetRule method is called !");		
			CommonBean.IWidget_Definition.createWidgetRule(astr_ruleDefinition);
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	/*******************************************************************************************************       
    					Method-STARTS HERE ! Document manager Functionality     
	*******************************************************************************************************/
	
	@And("^I validate \"([^\"]*)\" entity attachment for the \"([^\"]*)\" in \"([^\"]*)\"$")
	public void validateEntityLevelattachment(String astr_entityfield, String astr_record, String astr_entity) throws Throwable 
	{
		try 
		{
			Boolean lIsDocumetExists=CommonBean.IDocument_Manager.ValidateDocument(astr_entityfield, astr_record, astr_entity);
			if(lIsDocumetExists)
			{
				CommonBean.lbase_baseClass.reporting("Validated " + astr_entityfield + " entity attachment for Entityname" + astr_entity);			
				Itestresultdata.put(++imapKey, new Object[] {++iTestcaseID, "Validate entity level attachment in document manager",
						"Validated entity level attachment for Entityname - " + astr_entity + " successfully","PASS"});
			}
			else
			{ 
				CommonBean.lbase_baseClass.reporting("Validated " + astr_entityfield + " entity attachment for Entityname" + astr_entity +" is failed");			
				Itestresultdata.put(++imapKey, new Object[] {++iTestcaseID, "Validate entity level attachment in document manager",
						"Unable to Validate entity level attachment for Entityname - " + astr_entity ,"FAIL"});
			}
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@And("^I validate \"([^\"]*)\" workflow attachment for the \"([^\"]*)\" in \"([^\"]*)\"$")
	public void validateWorkflowLevelattachment() throws Throwable 
	{
		try 
		{
			Reporter.log("I click on Logout Button successfully");
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//Method-ENDS HERE! 
	
	/*******************************************************************************************************       
							Method-STARTS HERE ! Logout Functionality     
	*******************************************************************************************************/
	
	@Given("^I click on Logout Button$")
	public void click_Logout() throws Throwable 
	{
		try 
		{
			CommonBean.lbase_baseClass.loadingWait(CommonBean.IMyRequest_page.getloadericon());
			CommonBean.lHome_page_home.perform_Logout();
			BaseClass.getScreenshot("Successfully Logout of nFlows ");
			Reporter.log("I click on Logout Button successfully");
			Itestresultdata.put(++imapKey, new Object[] {++iTestcaseID, "Clicked Logout button", "","Successfully clicked Logout button","PASS"});	
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("^I logged out of Nflows successfully$")
	public void check_logout(String astr_isRecordingenabled) throws Throwable 
	{
		try 
		{
			CommonBean.lLogin_page.validate_Logout();
			Reporter.log("I logged out of Nflows successfully");
			Itestresultdata.put(++imapKey, new Object[] {++iTestcaseID, "Logout of nFlows app", "Logout is successful","PASS"});	
					
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Itestresultdata.put(++imapKey, new Object[] {++iTestcaseID, "Logout is Failed ", "Logout is Failed","FAIL"});
		}

	}
	
	//Method-ENDS HERE !
	
	/*******************************************************************************************************       
							Method-STARTS HERE ! Video Recording Functionality     
	*******************************************************************************************************/
	
	@Then("^I start recording automation execution based on \"([^\"]*)\"$")
	public void recordingStart(String astr_isRecordingenabled) throws Exception 
	{
		if(astr_isRecordingenabled.equalsIgnoreCase("YES"))
		{
			MyScreenRecorder.startRecording("NflowsRegression");
		}
		else
		{
			System.out.println("No recording is performed !");
		}		
	}
	
	@Then("^I stop recording automation execution based on \"([^\"]*)\"$")
	public void recordingStop(String astr_isRecordingenabled) throws Exception 
	{
		try {
			if(astr_isRecordingenabled.equalsIgnoreCase("YES"))
			{
				MyScreenRecorder.stopRecording();
				System.out.println("Recording is Stopped !");
			}
			else
			{
				System.out.println("No recording is performed !");
			}
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	/*******************************************************************************************************       
							Method-STARTS HERE ! PDF Report Functionality     
	*******************************************************************************************************/
	
	@When("^I create a PDF report based on \"([^\"]*)\"$")
	public void createPDFReport(String astr_isPdfenabled) throws DocumentException, MalformedURLException, IOException 
	{
		try 
		{
			if(astr_isPdfenabled.equalsIgnoreCase("YES"))
			{
				BaseClass.addScreenshotToPDF();
				PDFManager.closePDF();
			}
			else
			{
				System.out.println("No Pdf report is generated !");
			}
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	
	/*******************************************************************************************************       
							Method-STARTS HERE ! Test excel Report Functionality     
	*******************************************************************************************************/	
	
	
	@When ("^I create a Test Result file based on \"([^\"]*)\"$")
	public void createTestResult(String astr_isenabled) throws FileNotFoundException, DocumentException 
	{
		try 
		{
			if(astr_isenabled.trim().equalsIgnoreCase("YES"))
			{
				String lstr_TestResultPath=System.getProperty("user.dir") 
					+ "\\TestResult_" + BaseClass.getRandomTimestampData();
				System.out.println(lstr_TestResultPath);
				
				//create a new work book
				Iworkbook = new HSSFWorkbook();
		      
				// Create a new work sheet
				Isheet=Iworkbook.createSheet("TestResult");				
				
				CellStyle passStyle = Iworkbook.createCellStyle();
				Font greenfont = Iworkbook.createFont();
				greenfont.setColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());
				greenfont.setBold(true);
			    passStyle.setFont(greenfont);
			
				CellStyle failStyle = Iworkbook.createCellStyle();
				Font redfont = Iworkbook.createFont();
				redfont.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
				redfont.setBold(true);
				failStyle.setFont(redfont);	
				
				if(Itestresultdata.size()>1)
				{
					// Write data into the excel file
					Set<Integer> keySet=Itestresultdata.keySet();
					int rownum=0;
					for(Integer key: keySet)
					{
						Row row=Isheet.createRow(rownum++);					
						Object [] objArr=Itestresultdata.get(key);
						int cellnum=0;
						
						CellStyle style = Iworkbook.createCellStyle(); //Create new style
		                style.setWrapText(true); //Set wordwrap	                
						
						for(Object obj:objArr)
						{						
							Cell cell=row.createCell(cellnum ++);
							if(obj instanceof Date) 
				                cell.setCellValue((Date)obj);
							 else if(obj instanceof Boolean)
					                cell.setCellValue((Boolean)obj);
					         else if(obj instanceof String)
					                cell.setCellValue((String)obj);
					         else if(obj instanceof Double)
					              cell.setCellValue((Double)obj);
					         else if(obj instanceof Integer)
					              cell.setCellValue((Integer)obj);
						
							cell.setCellStyle(style);
							
							if(obj.toString().equals("PASS"))
							{
								cell.setCellStyle(passStyle);
							}												 
							else if(obj.toString().equals("FAIL"))
							{
								cell.setCellStyle(failStyle);
							}					
						}						
					}
					
					for (int i=0; i<10; i++)
					{
						Isheet.autoSizeColumn(i);
					}
				}
				try 
				{
					System.out.println("File path : "  + lstr_TestResultPath + "\\TestResult_"+ BaseClass.getRandomTimestampData()+ ".xls");
					File file = new File(lstr_TestResultPath);
					file.mkdir();
			        FileOutputStream out =new FileOutputStream(new File(lstr_TestResultPath + "\\TestResult_"+ BaseClass.getRandomTimestampData()+ ".xls"));
			        Iworkbook.write(out);
			        out.close();
			        System.out.println("Excel written successfully..");
			         
			    } 
				catch (FileNotFoundException e) {
			        e.printStackTrace();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			}
			else
			{
				System.out.println("No Test excel report is generated !");
			}
		} 
		catch (Exception e) 
		{
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
	@When("^Insert Field group data$")
	public void Insert_Field_group_data() throws InterruptedException
	{
		CommonBean.projectLibrary.insertDisplaygroupFieldData("Section","input","DT31","NA");
		CommonBean.projectLibrary.insertDisplaygroupFieldData("Manual Owners","button","Joanne;Lee","singleSelect");
	//	CommonBean.projectLibrary.addFieldgroupRows(DeviceUtils.getQAFDriver().findElement(By.xpath("//span[@ng-click='addGridRow(1440)']")),4);
	/*	projectLibrary.getFieldgroupHeaderColumnNumber("S No", DeviceUtils.getQAFDriver().findElements(By.xpath("//div[@col-index='renderIndex']")));
		projectLibrary.insertFieldgroupData(4, "Designation", DeviceUtils.getQAFDriver().findElements(By.xpath("//div[@col-index='renderIndex']")), "button","abc,Manual Owner,MO and G Head,LRC Member,LRC Chairman");
		*/
	//	CommonBean.projectLibrary.insertFieldgroupData(4, "S No", DeviceUtils.getQAFDriver().findElements(By.xpath("//div[@col-index='renderIndex']")),"input", "0,1,2,3,4");
	/*	projectLibrary.modifyFieldgroupData("Designation","S No",DeviceUtils.getQAFDriver().findElements(By.xpath("//div[@col-index='renderIndex']")),"Manual Owner","9","input");
		projectLibrary.modifyFieldgroupData("Designation","Action by",DeviceUtils.getQAFDriver().findElements(By.xpath("//div[@col-index='renderIndex']")),"Manual Owner","Ahamed;R","button");
	*/
	//	CommonBean.projectLibrary.deleteFieldgroupRow(2);
	}
	
	@Override
	protected void openPage(PageLocator locator, Object... args) {
		// TODO Auto-generated method stub
		
	}

	//Method-ENDS HERE !
}
