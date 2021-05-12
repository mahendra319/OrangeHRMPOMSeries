package com.ohrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ohrm.base.BasePage;
import com.ohrm.utils.Constants;
import com.ohrm.utils.ElementUtil;

public class LoginPage extends BasePage{

	WebDriver driver;
	ElementUtil elementUtil;
	//By locators
	
	By forgotPassword = By.linkText("Forgot your password?");
	By userName = By.id("txtUsername");
	By password = By.id("txtPassword");
	By loginBtn = By.id("btnLogin");
	
	
	
	//constructor
	
	public LoginPage(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
		this.driver =driver;
	}
	
	//Actions
	
	public String getPageTitle() {
		//return driver.getTitle();
		return elementUtil.waitForTitlePresent(Constants.LOGIN_PAGE_TITLE, 10);
	}
	
	
	public boolean forgotPasswordLinkExists() {
		//return driver.findElement(forgotPassword).isDisplayed();
		return elementUtil.doIsdisplayed(forgotPassword);
	}
	
	public DashBoardPage doLogin(String uName, String pwd) {
//		driver.findElement(userName).sendKeys("Admin");
//		driver.findElement(password).sendKeys("admin123");
//		driver.findElement(loginBtn).click();
		
		elementUtil.doSendKeys(userName, uName);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginBtn);
		
		return new DashBoardPage(driver);
	}
	
	
	
	
	
	
	
	
	
	
	
}
