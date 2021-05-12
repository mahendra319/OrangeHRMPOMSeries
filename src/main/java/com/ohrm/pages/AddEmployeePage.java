package com.ohrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ohrm.base.BasePage;
import com.ohrm.utils.ElementUtil;

public class AddEmployeePage extends BasePage{

	// private WebDriver driver;
	ElementUtil elementUtil;

	By header = By.tagName("h1");
	By firstName = By.name("firstName");
	By lastName = By.name("lastName");
	By photograph = By.id("photofile");
	By createLgnDetailsChkbox = By.id("chkLogin");
	By userName = By.name("user_name");
	By password = By.id("user_password");
	By confirmPwd = By.id("re_password");
	By saveBtn = By.id("btnSave");
	By profileHeader = By.id("//div[@id='profile-pic']");
	By addEmployee = By.linkText("Add Employee");

	public AddEmployeePage(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
		// this.driver = driver;
	}

	public String getAddEmployeePageHeaderText() {
		return elementUtil.doGetText(header);
	}

	public String getAddEmployeePageUrl() {
		return elementUtil.doGetPageUrl();
	}

	public boolean addEmployee(String firstName, String lastName, String userName, String password, String confirmPwd) {
		elementUtil.doSendKeys(this.firstName, firstName);
		elementUtil.doSendKeys(this.lastName, lastName);
		// elementUtil.doSendKeys(this.photograph, photograph);
		elementUtil.clickElementWhenReady(createLgnDetailsChkbox, 10);
		elementUtil.doSendKeys(this.userName, userName);
		elementUtil.doSendKeys(this.password, password);
		elementUtil.doSendKeys(this.confirmPwd, confirmPwd);
		elementUtil.clickElementWhenReady(saveBtn, 20);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String fullName = firstName + " " + lastName;
		elementUtil.waitForElementToBeVisible(By.xpath("//h1[text()='" + fullName + "']"), 10);
		String empName =elementUtil.doGetText(By.xpath("//h1[text()='" + fullName + "']"));
		boolean flag = elementUtil.doIsdisplayed(By.xpath("//h1[text()='" + fullName + "']"));
		elementUtil.clickElementWhenReady(addEmployee, 20);
		System.out.println("Newly add employee name is: "+empName);
		return flag;
	}

}
