/**
	 * @author <Shayina>
	 * Created On 	- <26th Feb 2020>
	 * Responsibility- <This class consists of Reusable common methods specific for nFlows application >
	 * Modified by <name> on <date> for Issue <Issue number (s)> for Integration <integration number>
	 */
package com.quantum.helper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.quantum.pages.CommonBean;
import com.quantum.utils.DeviceUtils;

public class nFlowsLibrary extends WebDriverBaseTestPage<WebDriverTestPage> {

	public WebElement getWebElement(int rowNum, int colNum, String dataType) {
		WebElement element = DeviceUtils.getQAFDriver().findElement(By.xpath("((//div[@class='ui-grid-row ng-scope'])["
				+ rowNum + "]//div[@role='gridcell'])[" + colNum + "]//" + dataType + ""));
		return element;
	}

	public WebElement getWebElement(String fieldName, String dataType) {
		WebElement element = DeviceUtils.getQAFDriver()
				.findElement(By.xpath("//label[text()='" + fieldName + "']/following-sibling::div//" + dataType + ""));
		return element;
	}

	public WebElement getWebElement(String loc) {
		WebElement element = DeviceUtils.getQAFDriver().findElement(By.xpath("//span[text()='" + loc + "']"));
		return element;
	}

	public WebElement getWebElement(int rowNum) {
		WebElement element = DeviceUtils.getQAFDriver()
				.findElement(By.xpath("((//div[@class='ui-grid-row ng-scope'])[" + rowNum + "]"));
		return element;
	}

	/**
	 * Responsibility - <This method is to get Header column numbers of Fieldgroup>
	 * 
	 * @param -
	 *            < >
	 * @return Integer - aint_colnum - <return Header Column Numbers>
	 * @throws Exception
	 * @author Shayina
	 * @return
	 */

	public int getFieldgroupHeaderColumnNumber(String astr_fieldName, List<WebElement> aweb_header) {
		int colNum = 1;
		for (int k = 1; k < aweb_header.size(); k++) {
			if (aweb_header.get(k).getText().trim().equalsIgnoreCase(astr_fieldName)) {
				colNum = k + 1;
				break;
			}
		}
		System.out.println("*************************" + colNum);
		return colNum;

	}

	public void addFieldgroupRows(QAFExtendedWebElement aweb_add, int aint_Rows) throws InterruptedException {
		CommonBean.lbase_baseClass.scrollintoView(aweb_add);
		for (int i = 1; i <= 4; i++) {
			CommonBean.lbase_baseClass.clickButton(aweb_add);
		}
	}

	public void insertFieldgroupData(int aint_Rows, String astr_fieldName, List<WebElement> aweb_header,
			String dataType, String data) throws InterruptedException {
		String[] dataArray = data.split(",");
		for (int i = 1; i <= aint_Rows; i++) {
			WebElement Cell = getWebElement(i, getFieldgroupHeaderColumnNumber(astr_fieldName, aweb_header), dataType);
			if (dataType.equalsIgnoreCase("input")) {
				CommonBean.lbase_baseClass.writeText((QAFExtendedWebElement) Cell, dataArray[i]);
			}
			if (dataType.equalsIgnoreCase("button")) {
				CommonBean.lbase_baseClass.clickButton((QAFExtendedWebElement) Cell);
				Thread.sleep(3000);
				WebElement aweb_dropdown = getWebElement(dataArray[i]);
				CommonBean.lbase_baseClass.clickButton((QAFExtendedWebElement) aweb_dropdown);
				Thread.sleep(3000);
			}
		}
	}

	public void modifyFieldgroupData(String astr_checkFieldName, String astr_ModifyFieldName,
			List<WebElement> aweb_header, String astr_checkData, String astr_modifyData, String dataType)
			throws InterruptedException {
		// Column no for the field to satisfy the condition
		int checkColumn = getFieldgroupHeaderColumnNumber(astr_checkFieldName, aweb_header);

		// Column number for the field to be modified
		int modifyColumn = getFieldgroupHeaderColumnNumber(astr_ModifyFieldName, aweb_header);

		List<WebElement> Rows = DeviceUtils.getQAFDriver()
				.findElements(By.xpath("//div[@class='ui-grid-row ng-scope']"));

		for (int i = 1; i <= Rows.size(); i++) {
			WebElement RowElement = getWebElement(i);
			List<WebElement> Cells = RowElement.findElements(By.xpath("//div[@role='gridcell']"));

			if (Cells.get(checkColumn - 1).getText().trim().equalsIgnoreCase(astr_checkData)) {
				WebElement modifyCell = getWebElement(i, modifyColumn, dataType);
				if (dataType.equalsIgnoreCase("input")) {
					modifyCell.clear();
					CommonBean.lbase_baseClass.writeText((QAFExtendedWebElement) modifyCell, astr_modifyData);
				}
				if (dataType.equalsIgnoreCase("button")) {
					CommonBean.lbase_baseClass.clickButton((QAFExtendedWebElement) modifyCell);
					Thread.sleep(3000);
					WebElement aweb_dropdown = getWebElement(astr_modifyData);
					CommonBean.lbase_baseClass.clickButton((QAFExtendedWebElement) aweb_dropdown);
					Thread.sleep(3000);
				}
				break;
			}
		}
	}

	public void deleteFieldgroupRow(int rowNum) {
		DeviceUtils.getQAFDriver().findElement(By.xpath("(//div[@class='remove ng-scope'])[" + rowNum + "]")).click();
		DeviceUtils.getQAFDriver().findElement(By.xpath("//button[@id='yesbtn']")).click();
		List<WebElement> Rows = DeviceUtils.getQAFDriver()
				.findElements(By.xpath("//div[@class='ui-grid-row ng-scope']"));
		System.out.println("Row Number: " + Rows.size());
	}

	public void insertDisplaygroupFieldData(String astr_fieldName, String astr_dataType, String astr_data,
			String astr_dropdownType) throws InterruptedException
	{
		String[] arrDt=astr_data.split(";");
		astr_dataType=arrDt[0];
		String astr_fieldData=arrDt[1];
		
		switch (astr_dataType) 
		{
			case "input":
				CommonBean.lbase_baseClass.clickButton((QAFExtendedWebElement) getWebElement(astr_fieldName, astr_dataType));
				CommonBean.lbase_baseClass.writeText((QAFExtendedWebElement) getWebElement(astr_fieldName, astr_dataType),
					astr_fieldData);
				break;

			case "button":
				if (astr_dropdownType.equalsIgnoreCase("singleSelect")) 
				{
			
					CommonBean.lbase_baseClass.selectFromDropdown((QAFExtendedWebElement) getWebElement(astr_fieldName, astr_dataType), astr_data,"singleSelect");
				}

				if (astr_dropdownType.equalsIgnoreCase("multiSelect")) {
				CommonBean.lbase_baseClass.selectFromDropdown(
						(QAFExtendedWebElement) getWebElement(astr_fieldName, astr_dataType), astr_fieldData,
						"multiSelect");
		}
			break;
		}
	}
	
	public void getElement(String astr_data) 
	{
		//WebElement aQAF_Element=CommonBean.ITask_Library.getDynamicSingleLine(astr_data);
		if(CommonBean.ITask_Library.getDynamicSingleLine(astr_data).isDisplayed())
		{
			String typeValue=CommonBean.ITask_Library.getDynamicSingleLine(astr_data).getAttribute("abc");
		}
		else if(CommonBean.ITask_Library.getDynamicMultiLine(astr_data).isDisplayed())
		{
			String typeValue=CommonBean.ITask_Library.getDynamicMultiLine(astr_data).getAttribute("abc");
		}
		else if(CommonBean.ITask_Library.getDynamicDate(astr_data).isDisplayed())
		{
			String typeValue=CommonBean.ITask_Library.getDynamicDate(astr_data).getAttribute("abc");
		}
		else if(CommonBean.ITask_Library.getDynamicDropdown(astr_data).isDisplayed())
		{
			String typeValue=CommonBean.ITask_Library.getDynamicDropdown(astr_data).getAttribute("abc");
		}
		else if(CommonBean.ITask_Library.getDynamicAttachment(astr_data).isDisplayed())
		{
			String typeValue=CommonBean.ITask_Library.getDynamicAttachment(astr_data).getAttribute("abc");
		}
		else
		{
			
		}
		
	}


	
	@Override
	protected void openPage(PageLocator locator, Object... args) {
		// TODO Auto-generated method stub

	}

}
