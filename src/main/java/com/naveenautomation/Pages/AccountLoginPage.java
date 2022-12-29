package com.naveenautomation.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.naveenautomation.Base.TestBase;

public class AccountLoginPage extends TestBase {

	public AccountLoginPage() {
		// re-initializing elements to avoid stale element reference exception
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@name=\"email\"]")
	WebElement emailInput;

	@FindBy(id = "input-password")
	WebElement passwordInput;

	@FindBy(xpath = "//input[@type=\"submit\"]")
	WebElement clickLogin;

	public void loginWithValidCred() {
		emailInput.sendKeys("parul.verma421@gmail.com");
		passwordInput.sendKeys("#elloWorld");
		sleep(2);
	}

	public void clickLogin() {
		loginWithValidCred();

		clickLogin.submit();
	}
}
