@Nflows
Feature: Entity Definition 

@TC_00_LaunchNflows
  	Scenario Outline: Launching Nflows application

    Given I launch Nflows application using "<Url_Testing>"
    When I start recording automation execution based on "<recording_enabled>"
    
    #for csv, txt, xls files specify datafile location
    Examples: {'datafile' : 'src/main/resources/data/testData.xls','sheetName':'Environment'}

@TC_01_NflowsLogin
  	Scenario Outline: Login to Nflows 
  	Given I login to Nflows with "<username>" and "<password>" credentials   
            
    #for csv, txt, xls files specify datafile location
    Examples: {'datafile' : 'src/main/resources/data/testData.xls','sheetName':'Nflows_Login'} 

@TC_02_CreateEntityDefinition
  	Scenario Outline: Create a Entity Definition 	
    	Given I navigate to "<menuName>" page
    	When I create a new Entity Definition 
    	#Then I can see new Entity template in landing page
            
    	#for csv, txt, xls files specify datafile location
   		Examples: {'datafile' : 'src/main/resources/data/testData.xls','sheetName':'Entity_Definition'}  

@TC_03_EntityCreation
	Scenario Outline: Create Entity Creation record
	    Given I navigate to "<entityType>" page
	    When I create a new Entity record for "<entityType>"    
	    When Insert Field group data 
    	#Then I can see my new record for "<entityname>" Entity in landing page

   		#for csv, txt, xls files specify datafile location
   		Examples: {'datafile' : 'src/main/resources/data/entity.xls','sheetName':'Entity_Creation'}    		
   		
@TC_04_EditEntityDefinition
  	Scenario Outline: Edit an Entity Definition
    	Given I navigate to "<menuName>" page
    	Then I Edit an Entity Definition for "<Entityname>" Entity
    	Then I navigate to Workflow page "<workflowName>" from Entity definition page
    	#And I click on Grouping Rule tab
    	#And I create a grouping rule for entity with "<groupingRuleName>" and "<Description>" and "<RuleDefinition>"
    	
   		Examples: {'datafile' : 'src/main/resources/data/entity.xls','sheetName':'Edit_Entity_Definition'} 
 
@TC_05_CreateRuleDefinition  		
   Scenario Outline: Create a new Rule Definition
   		Given I navigate to "Entity Definition" page  	  	
    	Then I create a rule for the "<entityName>" entity with "<ruleName>" and "<ruletype>" and "<ruleExecution>" and "<description>" and "<ruleDefinition>" and "<selectfield>" and "<selectmessage>" and "<ruleEnrichment>" and "<ruleFilter>" and "<ruleGroup>" 

   		Examples: {'datafile' : 'src/main/resources/data/testData.xls','sheetName':'Rule_Definition', "key":"YES"} 		
	
@TC_06_EditWorkflow
  	Scenario Outline: Login into nFlows and Edit a Workflow definition
    	Given I navigate to "<menuName>" page
    	When I edit workflow page
    	
   		Examples: {'datafile' : 'src/main/resources/data/testData.xls','sheetName':'PROD'} 
   		
@TC_07_FilterinMyTask 
  	Scenario Outline: Submit a Task
    	Given I navigate to "<menuName>" page
    	When I filter by "<Filter>" type
       #Then I verify the filtered task Type 	
   		
   		Examples: {'datafile' : 'src/main/resources/data/testData.xls','sheetName':'myTask'}    		

@TC_08_MyRequest
  	Scenario Outline: To check my-Request page related functionalities
    	Given I navigate to "<menuName>" page
    	And I wait for proper page loading for myRequest page
    	#And I check Search functionality for "<searchRequest>"
    	#And I check recalculate functionality
    	#And I check show Other Request functionality
    	And I navigate to new Request page
    	And I click on "<workflowName>" and click on entityRecordLookup button
    	And I select a "<searchInitiation>" request from request pop up based on "<gridData>" initiation fields
    	Then "<validateNewRequest>" Request is successfully triggered and validated in my Request page
    	
        Examples: {'datafile' : 'src/main/resources/data/testData.xls','sheetName':'My_Request'}

@TC_09_WidgetDefinition
Scenario Outline: To create a new Widget
    	Given I navigate to "<menuName>" page
    	Then I Edit an Widget Definition for "<widgetName>" widget
    	#Then I create a "<ruleDefinition>" rule for Widget Definition
    	Then add columns to the widgets "<colName>"
   		
   		Examples: {'datafile' : 'src/main/resources/data/testData.xls','sheetName':'widget'}    
   		
@TC_10_MyTask
  	Scenario Outline: To submit the respective task
    	Given I navigate to "<menuName>" page
    	And I wait for proper page loading for myRequest page
    	And I search for a particular task "<taskName>" of "<taskType>" in "<user/team>" section and edit based on "<gridData>"
    	
    	Examples: {'datafile' : 'src/main/resources/data/testData.xls','sheetName':'myTask'}    
    
@TC_11_DocumentManager
Scenario Outline: To validate documents under document manager
    	Given I navigate to "<menuName>" page 
    	And I validate "<EntityField>" entity attachment for the "<RecordName>" in "<Entityname>"
    	#And I validate "<WorkflowField>" workflow attachment for the "<RecordName>" in "<Entityname>" 	  	
    	
    	Examples: {'datafile' : 'src/main/resources/data/entity.xls','sheetName':'DocumentManager'}     

@TC_12_NflowsLogout
  	Scenario Outline: Logout of Nflows
    Given I click on Logout Button
    #Then I logged out of Nflows successfully
    Then I stop recording automation execution based on "<recording_enabled>"
    
    #for csv, txt, xls files specify datafile location
    Examples: {'datafile' : 'src/main/resources/data/testData.xls','sheetName':'Environment'}
    
@TC_13_ForgotPassword
  	Scenario Outline: Check Forgot password functionality
  	
    And I click on forgot password link
    And I enter the userName "<username>"
    And I click Submit for Forgot password
    Then I got email for forgot paasword successfully
    
    #for csv, txt, xls files specify datafile location
    Examples: {'datafile' : 'src/main/resources/data/testData.xls','sheetName':'Nflows_Login'}
        
@TC_14_CreatePDFReport
Scenario Outline: To Start PDF Report 
  	Given I create a PDF report based on "<pdfReport_enabled>"
  	
  	#for csv, txt, xls files specify datafile location
    Examples: {'datafile' : 'src/main/resources/data/testData.xls','sheetName':'Environment'}

  	
@TC_15_CreateTestResult
	Scenario Outline: To Create Test Result file
	When I create a Test Result file based on "<isExcelReport_enabled>"
	
  	#for csv, txt, xls files specify datafile location
    Examples: {'datafile' : 'src/main/resources/data/testData.xls','sheetName':'Environment'}
	 	  
