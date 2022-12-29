package com.naveenautomation.Tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.naveenautomation.Base.TestBase;
import com.naveenautomation.Pages.AccountLoginPage;
import com.naveenautomation.Pages.LogoutPage;
import com.naveenautomation.Pages.MyAccountPage;
import com.naveenautomation.Pages.PhoneChangePage;

public class LogoutTest extends TestBase {

	SoftAssert sf;
	MyAccountPage myAccountPage;
	PhoneChangePage phoneNumUpdate;
	AccountLoginPage accountLogin;

	@BeforeMethod
	public void setUp() {
		launchBrowser();
		sf = new SoftAssert();
		myAccountPage = new MyAccountPage();
		phoneNumUpdate = new PhoneChangePage();
		accountLogin = new AccountLoginPage();
	}

	@Test
	public void verifyUserISAbleToLogoutSuccessfully() {
		myAccountPage.clickLoginBtn();
		sleep(2);
		accountLogin.clickLogin();
		sleep(2);
		phoneNumUpdate.clickMyAccount();
		// page chaining
		LogoutPage logout = phoneNumUpdate.logoutFromAccount();
		logout.clickLogout();
		sf.assertEquals(logout.logOutSuccessMsg(), "Account Logout", "Something went wrong");
		logout.clickContinueAfterAccountLogout();
		sf.assertEquals(driver.getTitle(), "Your Store", "Try Again");
		System.out.println("Succesfully landed back to Home Page");

	}

	@AfterMethod
	public void tearDown() {
		 quitBrowser();
	}

}
