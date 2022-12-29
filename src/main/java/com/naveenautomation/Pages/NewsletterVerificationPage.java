package com.naveenautomation.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.naveenautomation.Base.TestBase;

public class NewsletterVerificationPage extends TestBase {

	public NewsletterVerificationPage() {

		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//input[@value=\"1\"]")
	WebElement subscribeYes;

	@FindBy(xpath = "//input[@value=\"0\"]")
	WebElement subscribeNo;

	@FindBy(css = "input.btn-primary")
	WebElement clickContinueAfterVerifyingSubscription;

	@FindBy(css = "div.alert-success")
	WebElement successMsgForNewsletter;

	public void subscriptionVerification() {
		subscribeYes.click();
		sleep(2);
		subscribeNo.click();
		sleep(2);
		clickContinueAfterVerifyingSubscription.submit();

	}

	public String successMsg() {
		return successMsgForNewsletter.getText();
	}

}
