package com.naveenautomation.Tests;

import java.util.Random;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.naveenautomation.Base.TestBase;
import com.naveenautomation.Pages.AccountLoginPage;
import com.naveenautomation.Pages.MyAccountPage;
import com.naveenautomation.Pages.PhoneChangePage;

public class PhoneChangeTest extends TestBase {

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

	public void verifyUserCanChangePhoneNum() {
		myAccountPage.clickLoginBtn();
		accountLogin.clickLogin();
		phoneNumUpdate.editAccountInfo(randomPhoneNum());
		phoneNumUpdate.clickContinue();
		sf.assertEquals(phoneNumUpdate.getSuccessMsgFromAlertBanner(),
				"Success: Your account has been successfully updated.", "Error message");

		System.out.println("Phone number updated successfully");

		sf.assertEquals(driver.getTitle(), "My Account", "Something went wrong");
		System.out.println("Landed to page : " + driver.getTitle());
		sf.assertAll();
	}

	@AfterMethod
	public void tearDown() {
		quitBrowser();
	}

	public String randomPhoneNum() {

		Random random = new Random();
		int randomNum = random.nextInt(100000);
		String randomPhoneNumber = "28945" + randomNum;
		return randomPhoneNumber;

	}

}
