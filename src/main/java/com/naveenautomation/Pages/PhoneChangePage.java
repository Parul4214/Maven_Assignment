package com.naveenautomation.Pages;

import java.util.Random;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.naveenautomation.Base.TestBase;

public class PhoneChangePage extends TestBase {

	public PhoneChangePage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div.list-group a:first-of-type")
	WebElement myAccount;

	@FindBy(xpath = "//a[text()=\"Edit your account information\"]")
	WebElement editAccInfo;

	@FindBy(id = "input-telephone")
	WebElement phoneInput;

	@FindBy(css = "input[type='submit']")
	WebElement clickcontinueAfterPhoneChange;

	@FindBy(css = "div.alert-success")
	WebElement successAlert;

	@FindBy(xpath = "//a[text()=\"Subscribe / unsubscribe to newsletter\"]")
	WebElement newsletterSubscription;

	public void clickMyAccount() {
		myAccount.click();

	}

	public String editAccountInfo(String newPhoneNum) {
		clickMyAccount();
		editAccInfo.click();
		sleep(2);
		phoneInput.clear();
		sleep(2);
		phoneInput.sendKeys(newPhoneNum);

		return newPhoneNum;

	}

	public void clickContinue() {
		clickcontinueAfterPhoneChange.submit();

	}

	public String getSuccessMsgFromAlertBanner() {
		return successAlert.getText();

	}

	public NewsletterVerificationPage newsletter() {
		newsletterSubscription.click();
		return new NewsletterVerificationPage();

	}

	public LogoutPage logoutFromAccount() {
		return new LogoutPage();

	}

}
