package com.ohrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ohrm.base.BasePage;
import com.ohrm.utils.ElementUtil;

import io.qameta.allure.Step;

public class DashBoardPage extends BasePage{

	private WebDriver driver;
	ElementUtil elementUtil;
	
	By marketPlaceLink = By.id("MP_link");
	By subscribeLink = By.id("Subscriber_link");
	By accountName = By.id("welcome");
	By pim = By.id("menu_pim_viewPimModule");
	By addEmployee = By.linkText("Add Employee");
	By empList = By.linkText("Employee List");
		
	public DashBoardPage(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
		this.driver = driver;
	}
	
	@Step("getting dashboard page url")
	public String getDashboardPageUrl() {
		//return driver.getCurrentUrl();
		return elementUtil.doGetPageUrl();
	}
	
	@Step("checking account name ")
	public String accountNameExists() {
//		if(driver.findElement(accountName).isDisplayed()) {
//			return driver.findElement(accountName).getText();
//		}
//		return null;
		if(elementUtil.doIsdisplayed(accountName)) {
			return elementUtil.doGetText(accountName);
		}
		return null;
	}
	
	public boolean subscribeLinkExist() {
		//return driver.findElement(subscribeLink).isDisplayed();
		
		return elementUtil.doIsdisplayed(subscribeLink);
	}
	
	public boolean marketPlaceLinkExist() {
		//return driver.findElement(marketPlaceLink).isDisplayed();
		return elementUtil.doIsdisplayed(marketPlaceLink);
	}
	
	
	public AddEmployeePage goToAddEmployeePage() {
		clickAddEmployee();
		return new AddEmployeePage(driver);
	}
	
	
	private void clickAddEmployee() {
		elementUtil.moveToElement(pim);
		elementUtil.clickElementWhenReady(addEmployee, 10);
	}
	
//	public EmployeeListPage goToEmployeeListPage() {
//		clickEmployeeListLink();
//		return new EmployeeListPage(driver);
//	}
//	
//	private void clickEmployeeListLink() {
//		elementUtil.moveToElement(pim);
//		elementUtil.clickElementWhenReady(empList, 10);
//	}
//	
	
	
}
