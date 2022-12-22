package com.Parul.MavenAssignment;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Assignment1 {

	WebDriver wd;

	@BeforeMethod
	public void setup() {

		System.setProperty("webdriver.chrome.driver", "D:\\Chrome Driver\\chromedriver.exe");
		// System.setProperty("webdriver.edge.driver", "D:\\Web Drivers\\EdgeDriver\\msedgedriver.exe");

		wd = new ChromeDriver();
		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wd.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		wd.manage().window().maximize();
		
	}

	@Test
	public void validateUserLogin() {
		WebElement email = wd.findElement(By.cssSelector("#input-email"));
		WebElement password = wd.findElement(By.cssSelector("#input-password"));
		WebElement login = wd.findElement(By.cssSelector("input[type='submit']"));
		email.sendKeys("parul.verma421@gmail.com");
		password.sendKeys("#elloWorld");
		login.click();
		verifyLogin();
		selectProduct();
		selectItem();
		validateCart();
		checkout();
		// userDetails();
		verifyBilling();
		isBtnSelectedForShipping();
		isBtnSelectedForRate();
		isbtnselectedForPayment();
		validateOrder();
		confirmOrder();

	}

	@AfterMethod
	public void tearDown() {

		// wd.close();

	}

	public void verifyLogin() {
		String title = wd.getTitle();
		Assert.assertEquals(title, "My Account", "Credentials doesn't match,try again");
		System.out.println(title);
	}

	public void selectProduct() {
		wd.findElement(By.cssSelector("#menu li:nth-of-type(6)>a")).click();
		String selectedItem = wd.getTitle();
		Assert.assertEquals(selectedItem, "Phones & PDAs", "Make right selection");
		System.out.println(selectedItem);
	}

	public void selectItem() {
		wd.findElement(By.linkText("Palm Treo Pro")).click();
		// WebElement quantity = wd.findElement(By.cssSelector("#input-quantity"));
		wd.findElement(By.cssSelector("#product:nth-of-type(2) button#button-cart")).click();
	}

	public void validateCart() {
		sleep(2);
		WebElement cartTotal = wd.findElement(By.cssSelector("div#cart span"));

		String itemTotal = cartTotal.getText();
		Assert.assertEquals(itemTotal, "1 item(s) - $279.99", "Invalid");

	}

	public void checkout() {
		wd.findElement(By.linkText("Checkout")).click();
	}

	public void userDetails() {
		WebElement firstName = wd.findElement(By.cssSelector("input[name='firstname']"));
		firstName.sendKeys("Parul");

		WebElement lastName = wd.findElement(By.cssSelector("input[name='lastname']"));
		lastName.sendKeys("Verma");

		WebElement address = wd.findElement(By.cssSelector("input[name='address_1']"));
		address.sendKeys("123 abcd drive");

		WebElement city = wd.findElement(By.cssSelector("input[name='city']"));
		city.sendKeys("Brampton");

		WebElement postCode = wd.findElement(By.cssSelector("input[name='postcode']"));
		postCode.sendKeys("A1B2C3");

		selectByValue(wd.findElement(By.cssSelector("select[name='country_id']")), "38");
		selectByValue(wd.findElement(By.cssSelector("select[name='zone_id']")), "609");

		wd.findElement(By.cssSelector("input[type='button']")).click();

	}

	public void selectByValue(WebElement element, String value) {
		Select sc = new Select(element);
		sc.selectByValue(value);

	}

//	private void selectStateByValue(WebElement element, String value) {
//		Select sc = new Select(element);
//		sc.selectByValue(value);
//
//	}

	public void verifyBilling() {
		wd.findElement(By.cssSelector("#collapse-payment-address div.radio input[value='existing']")).click();

		wd.findElement(By.cssSelector("div.pull-right>input#button-payment-address")).click();
	}

	public void isBtnSelectedForShipping() {
		WebElement radioBtn = wd
				.findElement(By.cssSelector("#collapse-shipping-address div.radio input[value='existing']"));
		boolean isBtnEnabled = radioBtn.isEnabled();

		Assert.assertTrue(isBtnEnabled);

		// radioBtn.click();
		wd.findElement(By.cssSelector("div.pull-right>input#button-shipping-address")).click();

	}

	public void isBtnSelectedForRate() {

		WebElement btnForShippingRate = wd.findElement(By.cssSelector("input[value='flat.flat']"));

		boolean isBtnEnabledForRate = btnForShippingRate.isEnabled();
		Assert.assertTrue(isBtnEnabledForRate);

		// btnForShippingRate.click();

		WebElement commentArea = wd.findElement(By.cssSelector("textarea[name='comment']"));
		commentArea.sendKeys("Leave package at front door");
		wd.findElement(By.cssSelector("div.pull-right>input#button-shipping-method")).click();

	}

	public void isbtnselectedForPayment() {

		WebElement btnForPayment = wd.findElement(By.cssSelector("input[value='cod']"));
		boolean isBtnEnabledforPayment = btnForPayment.isEnabled();
		Assert.assertTrue(isBtnEnabledforPayment);
		// btnForPayment.click();
		sleep(2);
		wd.findElement(By.cssSelector("input[type='checkbox']")).click();
		wd.findElement(By.cssSelector("div.pull-right>input#button-payment-method")).click();

	}

	public void validateOrder() {
		sleep(2);
		WebElement quantityCheck = wd
				.findElement(By.cssSelector("div[class='table-responsive'] tbody tr td:nth-child(3)"));
		sleep(2);
		// System.out.println("Quantity " + quantityCheck.getText());

		Assert.assertEquals(quantityCheck.getText(), "1", "invalid quantity");
		sleep(2);
		WebElement productCheck = wd.findElement(By.cssSelector("div.table-responsive td.text-left a:first-of-type"));
		sleep(2);
		Assert.assertEquals(productCheck.getText(), "Palm Treo Pro", "invalid");
		sleep(2);
		wd.findElement(By.cssSelector("#button-confirm:last-child")).click();
		sleep(2);
	}

	public void confirmOrder() {

		WebElement orderConfirmation = wd.findElement(By.cssSelector("#content h1"));

		Assert.assertEquals(orderConfirmation.getText(), "Your order has been placed!", "invalid title");

		// System.out.println("Message displayed " + isTextDisplayed);

	}

	public void sleep(int timer) {
		try {
			Thread.sleep(timer * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
