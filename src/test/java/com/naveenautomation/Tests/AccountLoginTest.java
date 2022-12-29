package com.naveenautomation.Tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.naveenautomation.Base.TestBase;
import com.naveenautomation.Pages.AccountLoginPage;
import com.naveenautomation.Pages.MyAccountPage;

public class AccountLoginTest extends TestBase {

	SoftAssert sf;
	MyAccountPage myAccountPage;

	@BeforeMethod
	public void setUp() {
		launchBrowser();
		sf = new SoftAssert();
		myAccountPage = new MyAccountPage();
	}

	@Test
	public void verifyUserLoginWithValidCred() {
		myAccountPage.clickLoginBtn();
		AccountLoginPage accountLogin = new AccountLoginPage();
		accountLogin.clickLogin();
		sleep(2);
		sf.assertEquals(driver.getTitle(), "My Account", "No match for E-Mail Address and/or Password");
		System.out.println("Login successful");
	}

	@AfterMethod
	public void tearDown() {
		 quitBrowser();

	}

}
