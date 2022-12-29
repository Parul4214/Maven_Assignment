package com.naveenautomation.Tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.naveenautomation.Base.TestBase;
import com.naveenautomation.Pages.MyAccountPage;

public class MyAccountTest extends TestBase {
	MyAccountPage accountlogin;

	SoftAssert sf;

	@BeforeMethod
	public void setUp() {
		launchBrowser();
		accountlogin = new MyAccountPage();
		sf = new SoftAssert();
	}

	@Test
	public void verifyRegisterBtnIsClicked() {
		accountlogin.getMyAccountText();
		accountlogin.clickRegisterBtn();
		System.out.println(accountlogin.getMyAccountText());
		// asserting the title of the page
		sf.assertEquals(driver.getTitle(), "Register Account", "title doesn't match");
		System.out.println(driver.getTitle());
		sf.assertAll();
	}

	@AfterMethod
	public void tearDown() {
		quitBrowser();
	}

}
