package com.naveenautomation.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.naveenautomation.Base.TestBase;

public class LogoutPage extends TestBase {

	public LogoutPage() {
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(css = "div.list-group a:last-of-type")
	WebElement logoutOption;
	
	@FindBy(css = "div#content h1")
	WebElement logoutMsg;
	
	@FindBy(xpath = "//a[text()=\"Continue\"]")
	WebElement clickContinueAfterLogout;
	
	
	public void clickLogout() {
		logoutOption.click();
		
	}
	
	public String logOutSuccessMsg() {
		return logoutMsg.getText();
		
		
	}
	
	public void clickContinueAfterAccountLogout() {
		clickContinueAfterLogout.click();
		
		
	}
	
	
	

}
