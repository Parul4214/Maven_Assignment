package com.naveenautomation.Tests;

import java.util.Random;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.naveenautomation.Base.TestBase;
import com.naveenautomation.Pages.MyAccountPage;
import com.naveenautomation.Pages.RegisterPage;

public class RegisterTest extends TestBase {

	SoftAssert sf;
	MyAccountPage myAccountPage;

	@BeforeMethod
	public void setUp() {
		launchBrowser();
		sf = new SoftAssert();
		myAccountPage = new MyAccountPage();
	}

	@Test
	public void verifyUserIsAbleToRegisterToWebsite() {
		myAccountPage.getMyAccountText();
		myAccountPage.clickRegisterBtn();
		RegisterPage registerPage = new RegisterPage();
		registerPage.signUp("Parul", "Verma", randomEmail(), "9056167896", "registerPage", "registerPage");
		sleep(5);
		sf.assertEquals(driver.getTitle(), "Your Account Has Been Created!", " Title is invalid ");
		System.out.println("Registration successful");
	}

	@AfterMethod
	public void tearDown() {
		quitBrowser();
	}

	public String randomEmail() {

		Random random = new Random();
		int randomNum = random.nextInt(1000);
		String randomEmail = "test" + randomNum + "@test.com";
		return randomEmail;
	}

}
