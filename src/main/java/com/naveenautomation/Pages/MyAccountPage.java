package com.naveenautomation.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.naveenautomation.Base.TestBase;

public class MyAccountPage extends TestBase {
	// create constructor to initialize the web elements
	public MyAccountPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()=\"My Account\"]")
	WebElement myAccountText;

	@FindBy(xpath = "//a[text()=\"Register\"]")
	WebElement registerOption;

	@FindBy(xpath = "//a[text()=\"Login\"]")
	WebElement loginBtn;

	public String getMyAccountText() {
		myAccountText.click();
		return myAccountText.getText();

	}

	public void clickLoginBtn() {
		getMyAccountText();
		loginBtn.click();

	}

	public void clickRegisterBtn() {

		registerOption.click();

	}

}
