package com.ohrm.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ohrm.base.BaseTest;
import com.ohrm.listeners.TestAllureListener;
import com.ohrm.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;



//@Listeners(ExtentReportListener.class)
@Listeners(TestAllureListener.class)
@Epic("Epic 100: Dashboard feature ")
@Story("user story 99: Dashboard feature testing")
public class DashBoardPageTest extends BaseTest{

	
	
	@BeforeClass
	public void dashboardPageSetup() {
		dashboardPage =loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@Description("Validating dashboard page url")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=1)
	public void verifyDashboardPageUrlTest() {
		
		String url =dashboardPage.getDashboardPageUrl();
		Assert.assertEquals(url, Constants.DASHBOARD_PAGE_URL);
	}
	
	@Description("Validating Marketplace link exists")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void verifyMarketplaceLinkTest() {
		Assert.assertTrue(dashboardPage.marketPlaceLinkExist());
	}
	@Description("Validating Subscrib link exists")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	public void verifySubscribeLinkTest() {
		Assert.assertTrue(dashboardPage.subscribeLinkExist());
	}
	
	@Description("Validating accountName")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=4)
	public void verifyAccountNameTest() {
		String acctName = dashboardPage.accountNameExists();
		System.out.println("Account Name is: "+acctName);
		Assert.assertEquals(acctName, prop.getProperty("accountName").trim());
	}
	
//	@Test(priority=5)
//	public void navigateToAddEmployeePageTest() {
//		dashboardPage.goToAddEmployeePage();
//	}
	
}
