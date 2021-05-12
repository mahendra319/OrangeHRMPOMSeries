package com.ohrm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ohrm.base.BaseTest;
import com.ohrm.utils.Constants;

public class LoginPageTest extends BaseTest{


	
	
	@Test (priority=1)
	public void verifyLoginPageTitleTest() {
		String title = loginPage.getPageTitle();
		System.out.println("LoginPage Title is: "+title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void verifyForgotpasswordLinkExistsTest() {
		Assert.assertTrue(loginPage.forgotPasswordLinkExists());
	}
	
	@Test(priority=3)
	public void verifyLoginTest() {
		loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	
	
	
}
