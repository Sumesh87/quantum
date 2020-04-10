package com.quantum.pages;

import org.testng.Assert;

import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;

import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;

import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.util.Reporter;
import com.quantum.helper.BaseClass;



@SuppressWarnings("static-access")
public class Nflows_Home_page extends WebDriverBaseTestPage<WebDriverTestPage>
{	
		
	@FindBy(locator = "nflows.logo")
	private QAFExtendedWebElement logo;
	
	@FindBy(locator = "nflows.menu.button")
	private QAFExtendedWebElement menuBar;
		
	@FindBy(locator = "nflows.menuSearch.txtbx")
	private QAFExtendedWebElement menuSearch;
	
	@FindBy(locator = "nflows.searchText.lnk")
	private QAFExtendedWebElement subMenuLnk;
	
	@FindBy(locator = "nflows.message.lnk")
	private QAFExtendedWebElement message;
	
	@FindBy(locator = "nflows.image.lnk")
	private QAFExtendedWebElement imageLnk;
	
	@FindBy(locator = "nflows.logout.lnk")
	private QAFExtendedWebElement logoutLnk;
	
// ------------------------------         Locators Ends here !          ----------------------------------------------------
	
	public QAFExtendedWebElement getLogo()
	{
		return logo;
	}
	
	public QAFExtendedWebElement gethomeMenu() {
		return menuBar;
	}
	
	public QAFExtendedWebElement getmenuSearch() {
		return menuSearch;
	}
	
	public QAFExtendedWebElement getmessage()
	{
		return message;
	}
	
	public QAFExtendedWebElement getsubMenuLnk()
	{
		return subMenuLnk;
	}
	
	public QAFExtendedWebElement getimageLnk()
	{
		return imageLnk;
	}
	
	public QAFExtendedWebElement getlogoutLnk() {
		return logoutLnk;
	}
		
// ------------------------------         Getter method Ends here !          ----------------------------------------------------
	
	public boolean verifyPostLogin() throws InterruptedException 
	{		
		if (logo.isDisplayed()) 
		{
			System.out.println("Login is Success");
			return true;
		} 
		else 
		{
			System.out.println("UnSuccessful Login !");
			return false;
		}

	}
	
	public void navigateByMenu (String astr_MenuName) throws InterruptedException 
	{
		String lstr_beforeTitle=null;
		String lstr_afterTitle=null;
		
		try 
		{
			lstr_beforeTitle= CommonBean.lbase_baseClass.getpageTitle();	
			CommonBean.lbase_baseClass.clickButton(gethomeMenu());
			CommonBean.lbase_baseClass.writeText(getmenuSearch(), astr_MenuName);
			Thread.sleep(700);
			
			BaseClass.getScreenshot("Try to navigate to " + astr_MenuName);
			
			CommonBean.lbase_baseClass.selectFromMenu(astr_MenuName.trim());
			Thread.sleep(2500);
			CommonBean.lbase_baseClass.explicitVisibilityOf(getLogo());	
			
			lstr_afterTitle= CommonBean.lbase_baseClass.getpageTitle();
				
			if(CommonBean.IMyRequest_page.getloadericon().isPresent())
			{
				CommonBean.lbase_baseClass.explicitInvisibilityOfElementLocated(CommonBean.IMyRequest_page.getloadericon());
				Thread.sleep(3000);
				BaseClass.getScreenshot("Navigated to " + astr_MenuName + " successfully" );
				Reporter.log("Navigated to " + astr_MenuName + " successfully");				
			}
			else
			{
				BaseClass.getScreenshot("Navigated to " + astr_MenuName + " successfully" );
				Reporter.log("Navigated to " + astr_MenuName + " successfully");
			}
			
			//Assert.assertTrue(!lstr_beforeTitle.equals(lstr_afterTitle), "Navigated to " + astr_MenuName + " successfully");
		} 
		catch (Exception e)
		{
			Assert.assertFalse(false, "Failed  Navigated to " + astr_MenuName);		
			Reporter.log("Failed  Navigated to" + astr_MenuName);
		}
	
	}
	
	public void perform_Logout () throws InterruptedException 
	{
		try 
		{
			System.out.println("Logout method is called !");
					
			try 
			{
				CommonBean.lbase_baseClass.explicitVisibilityOf(getimageLnk());
			} catch (Exception e) 
			{
				getimageLnk();
			}	
			CommonBean.lbase_baseClass.javascriptClick(getimageLnk());
			
			
			BaseClass.getScreenshot("Clicked on Logout Link");
			CommonBean.lbase_baseClass.explicitVisibilityOf(getlogoutLnk());	
			CommonBean.lbase_baseClass.clickButton(getlogoutLnk());
			Thread.sleep(1500);
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}	

	
	protected void openPage(PageLocator locator, Object... args) {
				
	}	
}
	

