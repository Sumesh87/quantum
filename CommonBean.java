package com.quantum.pages;

import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.quantum.helper.BaseClass;
import com.quantum.helper.Xls_Reader;
import com.quantum.helper.nFlowsLibrary;
import com.quantum.uobam.ReviewUOBAMManual_UserTask;

public class CommonBean extends WebDriverBaseTestPage<WebDriverTestPage>
{	
	public static  BaseClass lbase_baseClass = new BaseClass();
	public static Nflows_Login_page lLogin_page = new Nflows_Login_page();
	public static Nflows_Home_page lHome_page_home = new Nflows_Home_page();
	public static Nflows_Entity_Definition lEntity_Definition_entityDef = new Nflows_Entity_Definition();
	public static Nflows_RuleEngineDefinition_page lRule_Engine_Definition_ruleDefinition = new Nflows_RuleEngineDefinition_page();
	public static Nflows_MyRequest_page IMyRequest_page=new Nflows_MyRequest_page();
	public static Nflows_Widget_Definition IWidget_Definition=new Nflows_Widget_Definition();
	public static Nflows_MyTask_page IMyTask_Page=new Nflows_MyTask_page();
	public static Nflows_Document_Manager IDocument_Manager=new Nflows_Document_Manager();
	public static Nflows_Entity_Creation IEntity_Creation=new Nflows_Entity_Creation();
	public static Nflows_Task_Library ITask_Library=new Nflows_Task_Library();
	public static  nFlowsLibrary projectLibrary = new nFlowsLibrary();
	public static Xls_Reader xls_reader=new Xls_Reader();
	public static ReviewUOBAMManual_UserTask reviewUOBAMManualTsk=new ReviewUOBAMManual_UserTask()
;
	@Override
	protected void openPage(PageLocator locator, Object... args) 
	{
		// TODO Auto-generated method stub
		
	}
	

}
