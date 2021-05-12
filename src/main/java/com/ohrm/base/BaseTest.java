package com.ohrm.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.ohrm.pages.DashBoardPage;
import com.ohrm.pages.LoginPage;


public class BaseTest {

	WebDriver driver;
	public BasePage basePage;
	public LoginPage loginPage;
	public Properties prop;
	public DashBoardPage dashboardPage;
	
	/**
	 * this method is useful when we run individual class execution. 
	 */
//	@BeforeTest
//	public void loginpageSetUp() {
//		basePage = new BasePage();
//		prop = basePage.intialize_Properties();
//		driver = basePage.initialize_Driver(prop);
//		loginPage = new LoginPage(driver);
//	}

	/**
	 * passing browser from testng.xml via parameter tag
	 */
	
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(String browserName) {
		basePage = new BasePage();
		prop = basePage.intialize_Properties();
		prop.setProperty("browser", browserName);
		driver = basePage.initialize_Driver(prop);
		loginPage = new LoginPage(driver);
	}
	

	@AfterTest
	public void tearDown() {
		driver.quit();
	
	
	}
	
	
	
}
