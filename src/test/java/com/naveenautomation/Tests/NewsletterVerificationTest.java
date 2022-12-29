package com.naveenautomation.Tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.naveenautomation.Base.TestBase;
import com.naveenautomation.Pages.AccountLoginPage;
import com.naveenautomation.Pages.MyAccountPage;
import com.naveenautomation.Pages.NewsletterVerificationPage;
import com.naveenautomation.Pages.PhoneChangePage;

public class NewsletterVerificationTest extends TestBase {
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
	public void verifySubUnsubNewletter() {
		myAccountPage.clickLoginBtn();
		sleep(2);
		accountLogin.clickLogin();
		sleep(2);
		phoneNumUpdate.clickMyAccount();
		// page chaining
		NewsletterVerificationPage newsletterValidation = phoneNumUpdate.newsletter();
		newsletterValidation.subscriptionVerification();
		sf.assertEquals(newsletterValidation.successMsg(),
				" Success: Your newsletter subscription has been successfully updated!", "Wrong Error Message");
		System.out.println("Page Navigated back with success message ");
	}

	@AfterMethod
	public void tearDown() {
		 quitBrowser();
	}

}
