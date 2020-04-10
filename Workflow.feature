@Workflow
Feature: Entity Definition 

@WF_00_LaunchNflows
  	Scenario Outline: Launching Nflows application

    Given I launch Nflows application using "<Url_Testing>"
    When I start recording automation execution based on "<recording_enabled>"
    
    #for csv, txt, xls files specify datafile location
    Examples: {'datafile' : 'src/main/resources/data/testData.xls','sheetName':'Environment'}

@WF_01_NflowsLogin
  	Scenario Outline: Login to Nflows 
  	Given I login to Nflows with "<username>" and "<password>" credentials   
            
    #for csv, txt, xls files specify datafile location
    Examples: {'datafile' : 'src/main/resources/data/testData.xls','sheetName':'Nflows_Login'} 
    
@WF_02_MyRequest
  	Scenario Outline: To check my-Request page related functionalities
    	Given I navigate to "My Request" page
    	#And I wait for proper page loading for myRequest page
    	And I navigate to new Request page
    	And I click on "<workflowName>" and click on entityRecordLookup button
    	And I select a "<searchInitiation>" request from request pop up based on "<gridData>" initiation fields
    	#Then "<validateNewRequest>" Request is successfully triggered and validated in my Request page
    	
        Examples: {'datafile' : 'src/main/resources/data/testData.xls','sheetName':'My_Request'}
                
@WF_03_MyTask_approval
  	Scenario Outline: To submit the respective task   		
  		Given I check whether current task belongs to same user with "<username>" and "<password>" credentials	
    	#When I navigate to "My Task" page 
    	#And I wait for proper page loading for myRequest page
    	And I search for a particular task "<taskName>" of "<taskType>" in "<user/team>" section and edit based on "<gridData>" in "<myTask>"
    	#And I perform action for "<task_ID>"
    	And I completed the "<taskName>" task based on "<taskAction>"
    	
    	Examples: {'datafile' : 'src/main/resources/data/testData.xls','sheetName':'task_Approval'} 
    	
@WF_04_MyTask_rejection
  	Scenario Outline: To submit the respective task   		
  		Given I check whether current task belongs to same user with "<username>" and "<password>" credentials	
    	#When I navigate to "My Task" page 
    	#And I wait for proper page loading for myRequest page
    	And I search for a particular task "<taskName>" of "<taskType>" in "<user/team>" section and edit based on "<gridData>" in "<myTask>"
    	#And I perform action for "<task_ID>"
    	And I completed the "<taskName>" task based on "<taskAction>"   	
    	
    	Examples: {'datafile' : 'src/main/resources/data/testData.xls','sheetName':'task_Rejection'} 
    	
@WF_05_ValidateRequestCompletion
Scenario Outline: To submit the respective task   		
  			
    	#Given I navigate to "My Request" page 
    	Given I navigate to "UOBAM Manuals" page 
    	#Then I validate back update process
    	#When I select completed filter 
    	#Then I verify "<entityRecord>" request is completed for the "<workflow>"    	
    	
    	Examples: {'datafile' : 'src/main/resources/data/testData.xls','sheetName':'myTask'}

    
@WF_06_NflowsLogout
  	Scenario Outline: Logout of Nflows
    Given I click on Logout Button
    #Then I logged out of Nflows successfully
    Then I stop recording automation execution based on "<recording_enabled>"
    
    #for csv, txt, xls files specify datafile location
    Examples: {'datafile' : 'src/main/resources/data/testData.xls','sheetName':'Environment'}
    
@WF_07_CreateTestResult
Scenario Outline: To Create Test Result file
	When I create a Test Result file based on "<isExcelReport_enabled>"
	
	#for csv, txt, xls files specify datafile location
    Examples: {'datafile' : 'src/main/resources/data/testData.xls','sheetName':'Environment'}
	
	
@WF_08_CreatePDFReport
Scenario Outline: To Start PDF Report 
  	When I create a PDF report based on "<pdfReport_enabled>"
  	
  	#for csv, txt, xls files specify datafile location
    Examples: {'datafile' : 'src/main/resources/data/testData.xls','sheetName':'Environment'}
    
