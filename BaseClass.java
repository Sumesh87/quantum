package com.quantum.helper;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.itextpdf.text.DocumentException;
import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.qmetry.qaf.automation.util.Reporter;
import com.quantum.pages.CommonBean;
import com.quantum.utils.DeviceUtils;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

/**
 * @author sumeshm
 *
 */
public class BaseClass extends WebDriverBaseTestPage<WebDriverTestPage> 
{
	/**
	 * @author <Sumesh.M.S>
	 * Created On 	- <20th Sep 2019>
	 * Responsibility- <This class is used for using all Base class functions>
	 * Modified by <name> on <date> for Issue <Issue number (s)> for Integration <integration number>
	 */
	
	public static String istrScreenshot_folder_path = "";
	public static String istrPdf_folder_path = "";
	
	
	/**
	* Responsibility - <This method is get page title>
	 * @param 		- <Parameter descripition>
	 * @return String- lstr_pageTitle 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */
	
	public String getpageTitle() 
	{
		String lstr_pageTitle=null;
		try
		{
			lstr_pageTitle=new WebDriverTestBase().getDriver().getTitle();

		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstr_pageTitle;			
	}
	
	/**
	* Responsibility - <This method is check whether an object is present or not>
	 * @param aQAF_Element		- <Parameter descripition>
	 * @return boolean 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */
	
	public boolean isObjectPresent(WebElement aQAF_Element) 
	{
		try 
		{
			if (aQAF_Element.isDisplayed()) {
				System.out.println("Element displayed");
				return true;
			} else {
				System.out.println("Element is not displayed");
				return false;
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Element is not displayed");
			return false;
		}
	}

	/**
	* Responsibility - <This method is to check if the element is present on the DOM of a page and visible>
	 * @param aQAF_Element		- <Parameter descripition>
	 * @return void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */
	public void explicitVisibilityOf(QAFExtendedWebElement aQAF_Element) 
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.visibilityOf(aQAF_Element));
		} 
		catch (Exception e) {
			throw e;
		}
	}

	/**
	* Responsibility - <This method is for checking an element is visible and enabled such that we can click on the element.>
	 * @param aQAF_Element		- <Parameter descripition>
	 * @return void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */
	public void explicitElementToBeClickable(QAFExtendedWebElement aQAF_Element) 
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.elementToBeClickable(aQAF_Element));
		} 
		catch (Exception e) 
		{
			throw e;
		}
	}
	/**
	* Responsibility - <This method is for checking text present in the element.>
	 * @param aQAF_Element		- <Parameter descripition>
	 * @return void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */

	public void explicitTextToBePresentInElement(QAFExtendedWebElement aQAF_Element, String text) 
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.textToBePresentInElement(aQAF_Element, text));
		} 
		catch (Exception e) 
		{
			throw e;
		}
	}
	
	/**
	* Responsibility - <This method is for checking text present in the element.>
	 * @param aQAF_Element		- <Parameter descripition>
	 * @return void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */

	public void explicitInvisibilityOfElementLocated(QAFExtendedWebElement aQAF_Element) 
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.invisibilityOf(aQAF_Element));
		} 
		catch (Exception e) 
		{
			throw e;
		}
	}

	/**
	 * Sleep.
	 *
	 * @param seconds
	 *            the seconds
	 */
	
	public void fluentWait(QAFExtendedWebElement aQAF_Element) 
	{
		try 
		{
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		            .withTimeout(Duration.ofSeconds(30))
		            .pollingEvery(Duration.ofMillis(500))
		            .ignoring(NoSuchElementException.class);
			
			WebElement foo = wait.until(new Function<WebDriver, WebElement>() 
			{
			     public WebElement apply(WebDriver driver) 
			     {
			       return aQAF_Element;
			     }

			});
		          
		            
		} 
		catch (Exception e) 
		{
			throw e;
		}
	}
	
	public void loadingWait(QAFExtendedWebElement loader) 
	{		
	    WebDriverWait wait = new WebDriverWait(driver, 9 );
	    //wait.until(ExpectedConditions.visibilityOf(loader)); // wait for loader to appear
	    wait.until(ExpectedConditions.invisibilityOf(loader)); // wait for loader to disappear
	}
	
	public void sleep(int seconds) 
	{
		try 
		{
			TimeUnit.SECONDS.sleep(seconds);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	* Responsibility - <This method is for pageLoadTimeout.>
	 * @param aint_time		- <Parameter descripition>
	 * @return void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */
	public void waitForPageLoad(int aint_time) 
	{
		try
		{
			new WebDriverTestBase().getDriver().manage().timeouts().pageLoadTimeout(aint_time, TimeUnit.SECONDS);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	* Responsibility - <This method is for implicit wait.>
	 * @param aint_time		- <Parameter descripition>
	 * @return void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */
	public void waitForElementsToLoadInPage(int aint_time) 
	{
		try 
		{
			new WebDriverTestBase().getDriver().manage().timeouts().implicitlyWait(aint_time, TimeUnit.SECONDS);
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	* Responsibility - <This method is for  Selecting drop down by visible text>
	 * @param aQAF_Element	,itemToSelect	- <Parameter description>
	 * @return void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */
	public void selectFromDropdown(QAFExtendedWebElement aQAF_Element, String itemToSelect) 
	{
		try 
		{
			Select select = new Select(aQAF_Element);
			select.selectByVisibleText(itemToSelect);
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	* Responsibility - <This method is for  Selecting drop down >
	 * @param aQAF_Element	,itemToSelect	- <Parameter description>
	 * @return void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */
	public void selectFromDropdown(String astr_itemToSelect) 
	{
		try 
		{
			new WebDriverTestBase().getDriver().findElement(By.xpath("//div[contains(text(),'" + astr_itemToSelect + "')]"))
					.click();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	* Responsibility - <This method is for  Selecting drop down >
	 * @param aQAF_Element	,itemToSelect	- <Parameter description>
	 * @return void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */

	public void selectFromRule(String astr_itemToSelect) 
	{
		try 
		{
			new WebDriverTestBase().getDriver().findElement(By.xpath("//span[contains(text(),'" + astr_itemToSelect + "')]"))
					.click();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	* Responsibility - <This method is for  Selecting drop down >
	 * @param aQAF_Element	,itemToSelect	- <Parameter description>
	 * @return void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */
	
	public void selectExactRule(String astr_itemToSelect) 
	{
		try 
		{
			new WebDriverTestBase().getDriver().findElement(By.xpath("//span[text()='" + astr_itemToSelect + "']")).click();
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	* Responsibility - <This method is to check element exists >
	 * @param astr_itemToSelect- <Parameter description>
	 * @return boolean 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */
	public boolean findExactIgnorecase(String astr_itemToSelect) 
	{
		try 
		{
			System.out.println("//span[translate( text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='" + astr_itemToSelect.toLowerCase() + "']");
			return(new WebDriverTestBase().getDriver()
					.findElements(By
							.xpath("//span[translate( text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='"
									+ astr_itemToSelect.toLowerCase() + "']")).size()!=0);
		
					
		}
		catch (Exception e) 
		{		
			e.printStackTrace();
			return false;
		}
		
	}

	/**
	* Responsibility - <This method is to convert upper to  lowercase using the translate() function >
	 * @param aQAF_Element	,itemToSelect	- <Parameter description>
	 * @return void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */
	public void selectExactIgnorecase(String astr_itemToSelect) 
	{
		try 
		{
			new WebDriverTestBase().getDriver()
					.findElement(By
							.xpath("//span[translate( text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='"
									+ astr_itemToSelect + "']"))
					.click();
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void selectExactChildIgnorecase(String astr_itemToSelect) 
	{
		try 
		{
			new WebDriverTestBase().getDriver()
					.findElement(By
							.xpath("//span[text()='Entities']//following::span[translate( text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='"
									+ astr_itemToSelect + "']"))
					.click();
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		

	/**
	* Responsibility - <This method is to handle browser pop up >
	 * @param 	- <Parameter description>
	 * @return void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */

	public void handleBrowserPopup() {
		Robot r = null;
		try {
			r = new Robot();
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Press enter.
	 */
	public static void pressEnter() {
		Robot r = null;
		try {
			r = new Robot();
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			e.printStackTrace();
		}

	}

	/**
	* Responsibility - <This method is to click on element using Action class >
	 * @param aQAF_Element- <Parameter description>
	 * @return void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */
	public void actionClick(QAFExtendedWebElement aQAF_Element)
	{
		try 
		{
			Actions lact_actions = new Actions(driver);
			QAFExtendedWebElement lwebelement_Locator = aQAF_Element;
			lact_actions.moveToElement(lwebelement_Locator).click().perform();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	* Responsibility - <This method is to Double-click on element using Action class >
	 * @param aQAF_Element- <Parameter description>
	 * @return void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */
	public void doubleClick(QAFExtendedWebElement aQAF_Element) 
	{
		try 
		{
			explicitVisibilityOf(aQAF_Element);
			
			Actions lact_actions = new Actions(driver);
			WebElement lwebelement_Locator = aQAF_Element;
			lact_actions.doubleClick(lwebelement_Locator).perform();
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	* Responsibility - <This method is to move to particular element using Action class >
	 * @param aQAF_Element- <Parameter description>
	 * @return void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */
	public void moveTo(QAFExtendedWebElement aQAF_Element) 
	{
		try 
		{
			Actions lact_actions = new Actions(driver);
			WebElement lwebelement_Locator = aQAF_Element;
			lact_actions.moveToElement(lwebelement_Locator).perform();
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	* Responsibility - <This method is move and click on element using Action class >
	 * @param aQAF_Element- <Parameter description>
	 * @return void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */
	public void moveToAndClick(QAFExtendedWebElement aQAF_Element) 
	{
		try 
		{
			moveTo(aQAF_Element);
			Actions lact_actions = new Actions(driver);
			WebElement lwebelement_Locator = aQAF_Element;
			lact_actions.click(lwebelement_Locator).perform();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	* Responsibility - <This method is Scroll from Top to down >
	 * @param aQAF_Element- <Parameter description>
	 * @return void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */

	public void scrollTopToDown(QAFExtendedWebElement aQAF_Element) 
	{
		try 
		{
			JavascriptExecutor ljs_executor = (JavascriptExecutor) driver;
			// Find element by link text and store in variable "Element"
			WebElement lweb_Element = aQAF_Element;
			// This will scroll the page till the element is found
			ljs_executor.executeScript("arguments[0].scrollIntoView();", lweb_Element);
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	* Responsibility - <This method is Scroll from Top to down based on  >
	 * 
	 * @author Sumesh M.S.
	* */

	public  void scrollDown() 
	{
		try 
		{
			 //to perform Scroll on application using Selenium
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,350)", "");
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	* Responsibility - <This method is Scroll from Top to down >
	 * @param aQAF_Element- <Parameter description>
	 * @return void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */

	public boolean  isElementPresent(String astr_data) 
	{
		try 
		{
			if(driver.findElements(By.xpath("(//span[contains(text(),'" + astr_data  + "')])[1]")).size()>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	} 
			

	
	
	/**
	* Responsibility - <This method is Select a menu from Menu pane >
	 * @param astr_itemToSelect - <Parameter description>
	 * @return void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */

	public void selectFromMenu(String astr_itemToSelect) 
	{
		try 
		{
			QAFExtendedWebElement menuLink=new WebDriverTestBase().getDriver().findElement(By.xpath("//a[contains(text(),'" + astr_itemToSelect.trim() + "')]"));
			System.out.println("//a[contains(text(),'" + astr_itemToSelect.trim() + "')]");
			explicitVisibilityOf(menuLink);
			menuLink.click();
			Thread.sleep(2000);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	* Responsibility - <This method is to select a Field group data from Rule engine >
	 * @param astr_fieldGroupName,	astr_fieldName - <Parameter description>
	 * @return Void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */
	public void selectFieldGroupdata(String astr_fieldGroupName, String astr_fieldName) throws InterruptedException 
	{
		try 
		{
			System.out.println("//span[contains(text(),'" + astr_fieldGroupName + "')]//following::span[contains(text(),'"
					+ astr_fieldName + "')]");
			new WebDriverTestBase().getDriver().findElement(By.xpath("//span[contains(text(),'" + astr_fieldGroupName.trim()
					+ "')]//following::span[contains(text(),'" + astr_fieldName + "')]")).click();
			Thread.sleep(500);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * Responsibility - <This method is to perform highlight a particular field >
	 * @param aQAF_Element - {@link QAFExtendedWebElement} Web Element
	 * @author Sumesh M.S.
	 */
	public void highLightElement(QAFExtendedWebElement aQAF_Element) 
	{
		try
		{
			//explicitVisibilityOf(aQAF_Element);
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", aQAF_Element);
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Responsibility 				- <This method is get dynamic locator >
	 * @param astr_locatorKey		- {@link String} Locator Key
	 * @param astr_locatorValue		- {@link String} Locator Value
	 * @return {@link QAFExtendedWebElement}
	 */
	public QAFExtendedWebElement getDynamicLocator(String astr_locatorKey, String astr_locatorValue) 
	{
		String loc = String.format(pageProps.getString(astr_locatorKey), astr_locatorValue);
		return new QAFExtendedWebElement(loc);
	}

	public static void selectDatefromPicker() {

	}

	/**
	* Responsibility - <This method is to enter data in a field >
	 * @param aQAF_Element, astr_data	- <Parameter description>
	 * @return Void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */
	public void writeText(QAFExtendedWebElement aQAF_Element, String astr_data) throws InterruptedException 
	{
		try 
		{
			explicitVisibilityOf(aQAF_Element);
			aQAF_Element.sendKeys(astr_data);
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	* Responsibility - <This method is to Click a Button >
	 * @param aQAF_Element	- <Parameter description>
	 * @return Void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */
	public void clickButton(QAFExtendedWebElement aQAF_Element) throws InterruptedException
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(aQAF_Element));
			aQAF_Element.click();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("The Element : " + aQAF_Element + "is not clickable");
		}
	}
	
	/**
	* Responsibility - <This method is to get text from a specified attribute>
	 * @param aQAF_Element- <Parameter description>
	 * @return Void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */
	public String getText(QAFExtendedWebElement aQAF_Element) throws InterruptedException 
	{
		String lstrText=null;
		try 
		{
			explicitVisibilityOf(aQAF_Element);
			lstrText=aQAF_Element.getText();
			
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstrText;
	}
	
	/**
	* Responsibility - <This method is to verify the text is present in a specified attribute>
	 * @param aQAF_Element- <Parameter description>
	 * @return Boolean 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */
	public Boolean verifyText(QAFExtendedWebElement aQAF_Element, String astr_data) throws InterruptedException 
	{
		Boolean isPresent=null;
		String bodyText=null;
		try 
		{
			explicitVisibilityOf(aQAF_Element);
			bodyText=aQAF_Element.getText().trim();
			
			if(bodyText.equalsIgnoreCase(astr_data.trim()))
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isPresent;		
	}
	
	
	/**
	* Responsibility - <This method is to Select date from Date picker >
	 * @param 	- <Parameter description>
	 * @return Void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */

	public void selectDatePicker() 
	{
		try 
		{
			WebDriverWait lwebdriver_wait = new WebDriverWait(driver, 5);
			lwebdriver_wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Date']")));
			System.out.println("//span[text()='Date']");
			new WebDriverTestBase().getDriver().findElement(By.xpath("//span[text()='Date']")).click();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	* Responsibility - <This method is to Select date from Date picker >
	 * @param astr_itemToSelect	- <Parameter description>
	 * @return Void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */
	public void selectDate(String astr_itemToSelect) 
	{
		try 
		{
			WebDriverWait lwebdriver_wait = new WebDriverWait(driver, 5);
			lwebdriver_wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='" + astr_itemToSelect + "']")));
			System.out.println("//a[text()='" + astr_itemToSelect + "']");
			new WebDriverTestBase().getDriver().findElement(By.xpath("//a[text()='" + astr_itemToSelect + "']")).click();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	* Responsibility - <This method is to perform  click using java script >
	 * @param aQAF_Element	- <Parameter description>
	 * @return Void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */
	public void javascriptClick(QAFExtendedWebElement aQAF_Element)
	{
		try 
		{
			JavascriptExecutor ljs_executor = (JavascriptExecutor) driver;
			ljs_executor.executeScript("arguments[0].click();", aQAF_Element);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	* Responsibility - <This method is to perform Double click using java script >
	 * @param no parameter	- <Parameter description>
	 * @return Void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */
	public void javascriptDoubleClick(QAFExtendedWebElement aQAF_Element) 
	{
		try
		{
			JavascriptExecutor ljs_executor = (JavascriptExecutor) driver;
			ljs_executor.executeScript("arguments[0].dblclick;", aQAF_Element);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	* Responsibility - <This method is take screenshot >
	 * @param no parameter	- <Parameter description>
	 * @return Void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */
	@SuppressWarnings("finally")
	public static String getScreenshot(String ScreenshotName) throws IOException
	{
		String lstr_screenshotPath = null ;
		String lstr_randomData = null;
		try 
		{
			if(istrScreenshot_folder_path.trim().isEmpty()) 
			{
				createFolder("Screenshots", "Screenshots_");
			}
			
			lstr_screenshotPath = istrScreenshot_folder_path;
			System.out.println(lstr_screenshotPath);
			lstr_randomData = getRandomTimestampData();

			File scrFile = ((TakesScreenshot) DeviceUtils.getQAFDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(lstr_screenshotPath +  "\\" + ScreenshotName +"_"+ lstr_randomData+ ".jpg"));
			System.out.println("#--------Screenshot name created  : " +  ScreenshotName + ".jpg");
			
		} 
		catch (WebDriverException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			return lstr_screenshotPath + "\\Assert_" + lstr_randomData + ".jpg";
		}
		
	}
	
	/**
	* Responsibility - <This method is take screenshot >
	 * @param no parameter	- <Parameter description>
	 * @return Void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */
	@SuppressWarnings("finally")
	public static String captureParticularElement(String ScreenshotName,QAFExtendedWebElement aQAF_Element) throws IOException
	{
		String lstr_screenshotPath = null ;
		String lstr_randomData = null;
		try 
		{
			if(istrScreenshot_folder_path.trim().isEmpty()) 
			{
				createFolder("Screenshots", "Screenshots_");
			}
			
			lstr_screenshotPath = istrScreenshot_folder_path;
			System.out.println(lstr_screenshotPath);
			lstr_randomData = getRandomTimestampData();

			Screenshot screenshot= new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(DeviceUtils.getQAFDriver(),aQAF_Element);

			ImageIO.write(screenshot.getImage(), "jpg", new File(lstr_screenshotPath +  "\\" + ScreenshotName +"_"+ lstr_randomData+ ".jpg"));
			System.out.println(lstr_screenshotPath +  "\\" + ScreenshotName +"_"+ lstr_randomData+ ".jpg");
		} 
		catch (WebDriverException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			System.out.println(lstr_screenshotPath +  "\\" + ScreenshotName +"_"+ lstr_randomData+ ".jpg");
			
			return lstr_screenshotPath + "\\Assert_" + lstr_randomData + ".jpg";
		}
		
	}
	
	/**
	* Responsibility - <This method is create Folder based on folder name >
	 * @param no parameter	- <Parameter description>
	 * @return Void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */

	public static void createFolder(String astr_FolderName, String astr_FileName) 
	{
		try 
		{
			String lstr_ProjectPath = System.getProperty("user.dir");
			String lstr_getDate = getRandomTimestampData();

			File dir = new File(lstr_ProjectPath + "\\" + astr_FolderName + "\\" + astr_FileName + lstr_getDate);

			if (!dir.exists()) {
				dir.mkdirs();
				System.out.println("Folder is created succesfully : " + dir);
			}
			
			if(astr_FolderName.trim().equals("Screenshots")) {
				istrScreenshot_folder_path = lstr_ProjectPath + "\\" + astr_FolderName + "\\" + astr_FileName + lstr_getDate;
			}else if(astr_FolderName.trim().equals("PDF")) {
				istrPdf_folder_path = lstr_ProjectPath + "\\" + astr_FolderName + "\\" + astr_FileName + lstr_getDate;
			}
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	* Responsibility - <This method is add screenshot to a PDF file >
	 * @param no parameter	- <Parameter description>
	 * @return Void 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */

	public static void addScreenshotToPDF() throws IOException, DocumentException
	{
		try 
		{
			if(istrPdf_folder_path.trim().isEmpty()) 
			{
				createFolder("PDF", "PDFReport_");
			}			
			String lstr_pdfFolderPath = istrPdf_folder_path;
			String latestpath=lstr_pdfFolderPath.replace("\\", "~");			
			
			System.out.println(latestpath);	
			
			String pdf[]=latestpath.split("~");			
			System.out.println("Final Folder path " + istrPdf_folder_path + "\\" + pdf[4]);
			
			PDFManager.createPDF(istrPdf_folder_path + "\\" + pdf[4]);
			PDFManager.setFileAttribute();
			PDFManager.setBorder();
			PDFManager.creatCoverPage();				
			
			ArrayList<String> larl_list=BaseClass.getAllFilepath(istrScreenshot_folder_path);				
			//ArrayList<String> larl_list=BaseClass.getAllFilepath("E:\\Workspace\\Automation\\nFlows_Regression\\Screenshots\\Screenshots_02_Apr_2020_103647");
			if(!larl_list.isEmpty())
			{
				for(String file : larl_list)
				{
					String lstr_latestScreenPath= file.replace("\\", "~");
					String lstr_screenPath[]=lstr_latestScreenPath.split("~");
					String lstr_content[]=lstr_screenPath[lstr_screenPath.length-1].split("\\.");
					
					PDFManager.addImage(lstr_content[0], file);					
				}
			}
			else
			{
				System.out.println("No Screenshots are captured !");
			}
			
			//PDFManager.setBorder();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	* Responsibility - <This method is get timestamp data >
	 * @param no parameter	- <Parameter descripition>
	 * @return String 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */

	@SuppressWarnings("finally")
	public static String getRandomTimestampData() 
	{
		String lstr_randomData=null;
		try 
		{
			SimpleDateFormat lsdf_dateformat = new SimpleDateFormat("dd_MMM_yyyy_HHmmss");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			lstr_randomData = lsdf_dateformat.format(timestamp);
			return lstr_randomData;
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			return lstr_randomData;
		}

	}

	/**
	* Responsibility - <This method is get data from the Grid >
	 * @param astr_griddata	- <Parameter descripition>
	 * @return String 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */
	public String getGridData(String astr_griddata) 
	{
		System.out.println("The path : " + "(//span[@class='ng-binding' and text()='" + astr_griddata + "'])[2]");
			return new WebDriverTestBase().getDriver()
					.findElement(By.xpath("(//span[@class='ng-binding' and text()='" + astr_griddata + "'])[2]")).getText();
	}
	
	
	/**
	* Responsibility - <This method is get all file path from a specific folder>
	 * @param astrFolderpath	- <Parameter descripition>
	 * @return ArrayList 	- <return value description>
	 * @throws Exception
	 * @author Sumesh M.S.
	* */
	
	@SuppressWarnings("finally")
	public static  ArrayList<String> getAllFilepath(String astrFolderpath) throws IOException
	{
		ArrayList<String> larl_list=null;
		try 
		{
			larl_list=new ArrayList<String>();//Creating arraylist 
			
			File lfile_folder = new File(astrFolderpath);
			File[] listOfFiles = lfile_folder.listFiles();
			
			Arrays.sort(listOfFiles, new Comparator<File>()
			{
				public int compare(File f1, File f2)
				{
					return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
				} 
			});
			

			for (File file : listOfFiles) 
			{
			    if (file.isFile()) 
			    {		 
			    	larl_list.add(file.getAbsolutePath());
			        System.out.println("Aboslute path " + file.getAbsolutePath() );
			    }
			}
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		finally
		{
			return larl_list;
		}
	}

	/**
	 * Responsibility - <This method is get Header index for Grid>
	 * @param excelData
	 * @return
	 * @author Sumesh M.S.
	 */
	public static int getGridHeaderindex(String excelData)
	{
		int lint_headerIndex=-1;
		for(int i=0;i<=10;i++)
		{			
			String headerText=new WebDriverTestBase().getDriver().findElement(By.xpath("//div[@id='grid_scrn']//div[@class='ngHeaderText ng-binding colt"
		+ i +  "']")).getText();
			if(excelData.equalsIgnoreCase(headerText))
			{
				lint_headerIndex=i;
				break;
			}			
		}
		return lint_headerIndex;	
		
	}
	public void gridHandleTest(	String text_one,String text_two,String text_three,String text_four)
    {       
        List<WebElement> elements1=DeviceUtils.getQAFDriver().findElements(By.xpath("//div[@id='grid_scrn']"));
       
        for(int i=0;i<elements1.size();i++)
        {
            List<WebElement> totalData=elements1.get(i).findElements(By.tagName("div"));
            for(int j=0;j<totalData.size();j++) 
            {
            	System.out.println(" ----Text from the list of web elements: "+totalData.get(2).getText());
            	//	System.out.println(" ----Text from the list of web elements: "+totalData.get(1).getText());
            	//     System.out.println(" ----Text from the list of web elements: "+totalData.get(2).getText());
            	if((totalData.get(0).getText().equalsIgnoreCase(text_one))&& (totalData.get(1).getText().equalsIgnoreCase(text_two))&&(totalData.get(2).getText().equalsIgnoreCase(text_three))&&(totalData.get(3).getText().equalsIgnoreCase(text_three))) 
            	{
            			System.out.println(" ----Text from the list of web elements: "+totalData.get(0).getText());
            			System.out.println(" ----Text from the list of web elements: "+totalData.get(1).getText());
            			Actions actions = new Actions(DeviceUtils.getQAFDriver());
            			actions.doubleClick(totalData.get(2)).perform();
            			break;
            	}
            
        }
        }
        }
    
	
	/**
	 * Responsibility - <This method is perform scroll>
	 * @param excelData
	 * @return
	 * @author Sumesh M.S.
	 */
	public  void scrollintoView(WebElement aQAF_Element)
	{			
		JavascriptExecutor js = (JavascriptExecutor) driver;

		//This will scroll the page till the element is found		
		js.executeScript("arguments[0].scrollIntoView();", aQAF_Element);

	}
	
	/**
	 * Responsibility - <This method is for reporting purpose>
	 * @param excelData
	 * @return
	 * @author Sumesh M.S.
	 * @throws IOException 
	 */
	public void reporting(String astr_reportdata) throws IOException
	{			
		getScreenshot(astr_reportdata);
		Reporter.log(astr_reportdata);
	}
	
	/**
     * 
    * Responsibility : Function to get the data from property file and load the property file
    * @author  : Sumesh
    * @param astr_fileName-path of the property file,astr_loadvalue-values in property file
    * @return lprop_prop – property file 
    */
	public static String getdataFromProperty(String astr_fileName, String astr_loadvalue) throws IOException 
	{
		Properties lprop_prop = new Properties();
		FileInputStream linput_input = null;
		String lstr_fileWithPath = System.getProperty("user.dir") + "/src/main/resources/config/" + astr_fileName + ".properties";
		linput_input = new FileInputStream(lstr_fileWithPath);
		lprop_prop.load(linput_input);
		return lprop_prop.getProperty(astr_loadvalue);
	}
	public void selectFromDropdown(QAFWebElement element,String astr_ddValue,String dropdownType) 
	{
		try 
		{
			switch(dropdownType) {
			
			case "singleSelect":
				CommonBean.lbase_baseClass.clickButton((QAFExtendedWebElement) element);
				CommonBean.lbase_baseClass.scrollintoView(DeviceUtils.getQAFDriver().findElement(By.xpath("//div[(text()='" + astr_ddValue + "')]")));
				CommonBean.lbase_baseClass.clickButton(DeviceUtils.getQAFDriver().findElement(By.xpath("//div[(text()='" + astr_ddValue + "')]")));
				break;
			
			case "multiSelect":
			String[] ddArray=astr_ddValue.split(",");
			for(int i=0;i<ddArray.length;i++) {
				CommonBean.lbase_baseClass.clickButton((QAFExtendedWebElement) element);
				CommonBean.lbase_baseClass.scrollintoView(DeviceUtils.getQAFDriver().findElement(By.xpath("//div[(text()='" + ddArray[i]  + "')]")));
				CommonBean.lbase_baseClass.clickButton(DeviceUtils.getQAFDriver().findElement(By.xpath("//div[(text()='" + ddArray[i] + "')]")));
			}
				break;
			}
			
			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void fieldHandling(String astr_Action,QAFExtendedWebElement astr_Locator, String astr_fieldName, String astr_fieldType,String astr_Data) 
	{
		try 
		{
			switch (astr_Action.toLowerCase())
			{
				case "insert":
					System.out.println("Task Action : Insert");
					
					//CommonBean.projectLibrary.insertDisplaygroupFieldData(astr_Data, astr_Data, astr_Data, astr_Data);					
					performInsertAction(astr_fieldType, astr_Locator, astr_Data);	
					break;
				case "update":
					System.out.println("Task Action : Update");
					performUpdateAction(astr_fieldType, astr_Locator, astr_Data);	
					break;
				case "delete":
					System.out.println("Task Action : Delete");
					performDeleteAction(astr_fieldType, astr_Locator, astr_Data);	
					break;
				case "validate":
					System.out.println("Task Action : validate");
					break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void performInsertAction(String astr_fieldType,QAFExtendedWebElement astr_Locator,String astr_data ) throws InterruptedException
	{
		switch (astr_fieldType.toLowerCase())
		{
			case "singleline":
				System.out.println("Field Type : singleline");			
				writeText(astr_Locator, astr_data);	
				break;
			case "multiline":
				System.out.println("Field Type : multiline");
				writeText(astr_Locator, astr_data);
				break;
			case "date":
				System.out.println("Field Type : date");
				selectDate(astr_data);	
				break;
			case "dropdown":
				System.out.println("Field Type : dropdown");
				break;
			case "attachment":
				System.out.println("Field Type : dropdown");
				break;
		}
	}	
	
	public void performUpdateAction(String astr_fieldType,QAFExtendedWebElement astr_Locator,String astr_data ) throws InterruptedException
	{
		switch (astr_fieldType.toLowerCase())
		{
			case "singleline":
				System.out.println("Field Type : singleline");	
				astr_Locator.clear();
				writeText(astr_Locator, astr_data);	
				break;
			case "multiline":
				System.out.println("Field Type : multiline");
				astr_Locator.clear();
				writeText(astr_Locator, astr_data);
				break;
			case "date":
				System.out.println("Field Type : date");
				selectDate(astr_data);	
				break;
			case "dropdown":
				System.out.println("Field Type : dropdown");
				break;
			case "attachment":
				System.out.println("Field Type : dropdown");
				break;
		}
	}
	
	public void performDeleteAction(String astr_fieldType,QAFExtendedWebElement astr_Locator,String astr_data ) throws InterruptedException
	{
		switch (astr_fieldType.toLowerCase())
		{
			case "singleline":
				System.out.println("Field Type : singleline");	
				astr_Locator.clear();
				writeText(astr_Locator, astr_data);	
				break;
			case "multiline":
				System.out.println("Field Type : multiline");
				astr_Locator.clear();
				writeText(astr_Locator, astr_data);
				break;
			case "date":
				System.out.println("Field Type : date");
				selectDate(astr_data);	
				break;
			case "dropdown":
				System.out.println("Field Type : dropdown");
				break;
			case "attachment":
				System.out.println("Field Type : dropdown");
				break;
		}
	}
	
	
	@Override
	protected void openPage(PageLocator locator, Object... args) {
		// TODO Auto-generated method stub

	}

}
