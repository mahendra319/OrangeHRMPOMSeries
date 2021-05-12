package com.ohrm.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ohrm.base.BaseTest;
import com.ohrm.pages.AddEmployeePage;
import com.ohrm.utils.Constants;
import com.ohrm.utils.ExcelUtil;

public class AddEmployeePageTest extends BaseTest{

	
	AddEmployeePage addEmployeePage;
	@BeforeClass
	public void addemployeePageSetup() {
		dashboardPage =loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		addEmployeePage=dashboardPage.goToAddEmployeePage();
	}

	@Test(priority=1)
	public void verifyAddEmployeePageUrlTest() {
		String url =addEmployeePage.getAddEmployeePageUrl();
		System.out.println("Add Employee Page url is: "+url);
		Assert.assertEquals(url, Constants.ADD_EMPLOYEE_PAGE_URL);
	}
	
	@Test(priority=2)
	public void verifyAddEmployeePageHeaderTest() {
		String headerText = addEmployeePage.getAddEmployeePageHeaderText();
		System.out.println("AddEmployee page header is: "+headerText);
		Assert.assertEquals(headerText, Constants.ADD_EMPLOYEE_PAGE_HEADER);
	}
	
	
	
	@DataProvider()
	public Object[][] getEmployeeData() {
		Object [][] data =ExcelUtil.getTestData(Constants.TEST_DATA_SHEET_NAME);
		return data;
	}
	
	@Test(dataProvider = "getEmployeeData", priority=3)
	public void verifyNewAddEmployeeTest(String firstName, String lastName, String userName, String password, String confirmPassword) {
		Assert.assertTrue(addEmployeePage.addEmployee(firstName, lastName, userName, password, confirmPassword));
		//Assert.assertTrue(addEmployeePage.addEmployee("Covaxin", "Bharath", "Covaxin", "Covaxin@Bharath", "Covaxin@Bharath"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
