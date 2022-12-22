package secondAssignment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

//Validate purchase of item

public class FirstQuestion {
	WebDriverWait wdwait;
	WebDriver wd;
	SoftAssert sf;
	String name = "Parul";
	String amount = "820";
	String cardNumber = "1234123412341234";

	@BeforeMethod
	public void setUp() {

		System.setProperty("webdriver.chrome.driver", "D:\\Web Drivers\\Chrome Driver\\chromedriver.exe");
		wd = new ChromeDriver();

		wdwait = new WebDriverWait(wd, Duration.ofSeconds(10));

		sf = new SoftAssert();
		wd.get("https://www.demoblaze.com/index.html");
		wd.manage().window().maximize();

	}

	@Test
	public void validateItemPurchase() {

		// validate title of the page
		wdwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#nava:last-of-type")));
		WebElement titleOfPage = wd.findElement(By.cssSelector("#nava:last-of-type"));
		sf.assertEquals(titleOfPage.getText(), "PRODUCT STORE", "Title doesn't match");

		System.out.println("1. " + titleOfPage.getText());

		// click item on the page

		wdwait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Nokia lumia 1520")));
		wd.findElement(By.linkText("Nokia lumia 1520")).click();

		// Validate product name
		wdwait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("h2.name"), "Nokia lumia 1520"));
		WebElement productName = wd.findElement(By.cssSelector("h2.name"));
		sf.assertEquals(productName.getText(), "Nokia lumia 1520", "Invalid product Selection");

		System.out.println("2. " + productName.getText());

		// Validate Price of Product
		WebElement priceOfProduct = wd.findElement(By.cssSelector("h3.price-container"));
		sf.assertEquals(priceOfProduct.getText(), "$820 *includes tax", "Price Mismatched");

		sf.assertAll();

		System.out.println("3. " + priceOfProduct.getText());

		// Add to cart
		wdwait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[onclick='addToCart(2)']")));
		wd.findElement(By.cssSelector("a[onclick='addToCart(2)']")).click();

		// Alert Validation
		wdwait.until(ExpectedConditions.alertIsPresent());
		wd.switchTo().alert().accept();

		// click on cart
		wd.findElement(By.cssSelector("#cartur")).click();

		// invoking method to validate cart fields
		validateCartField();

		wdwait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-target='#orderModal']")));
		wd.findElement(By.cssSelector("button[data-target='#orderModal']")).click();

		// invoking method to place an order
		placeOrder();

		// validate the data entered
		validateDataEntered();

	}

	@AfterMethod
	public void tearDown() {

		wd.quit();
	}

	// using separate method to validate texts present in cart field
	public void validateCartField() {

		wdwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("tr.success td:nth-of-type(2)")));
		WebElement validateTiteField = wd.findElement(By.cssSelector("tr.success td:nth-of-type(2)"));
		sf.assertEquals(validateTiteField.getText(), "Nokia lumia 1520", "Invalid Title");

		WebElement validatePricefield = wd.findElement(By.cssSelector("tr.success td:nth-of-type(3)"));
		sf.assertEquals(validatePricefield.getText(), amount, " Invalid Price text");

		WebElement totalPriceField = wd.findElement(By.cssSelector("#totalp"));
		sf.assertEquals(totalPriceField.getText(), amount, "Invalid total text");

		System.out.println("4. " + validateTiteField.getText() + "\n" + "5. " + validatePricefield.getText() + "\n"
				+ "6. " + totalPriceField.getText());

		sf.assertAll();

	}

	public void placeOrder() {

		wdwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#orderModalLabel")));

		// locate personal details field
		WebElement nameField = wd.findElement(By.cssSelector("div.form-group #name"));
		nameField.sendKeys(name);

		WebElement country = wd.findElement(By.cssSelector("div.form-group #country"));
		country.sendKeys("Canada");

		WebElement city = wd.findElement(By.cssSelector("div.form-group #city"));
		city.sendKeys("Brampton");

		WebElement card = wd.findElement(By.cssSelector("div.form-group #card"));
		card.sendKeys(cardNumber);

		WebElement month = wd.findElement(By.cssSelector("div.form-group #month"));
		month.sendKeys("February");

		WebElement year = wd.findElement(By.cssSelector("div.form-group #year"));
		year.sendKeys("2022");

		wdwait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick='purchaseOrder()']")));
		wd.findElement(By.cssSelector("button[onclick='purchaseOrder()']")).click();

	}

	public void validateDataEntered() {

		wdwait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("body > div.sweet-alert.showSweetAlert.visible > h2")));
		WebElement confirmationMessage = wd
				.findElement(By.cssSelector("body > div.sweet-alert.showSweetAlert.visible > h2"));

		System.out.println("7. " + confirmationMessage.getText());

		WebElement dataCheck = wd.findElement(By.cssSelector("p[style='display: block;']"));
		if (dataCheck.getText().contains(cardNumber) && (dataCheck.getText().contains(name))
				&& (dataCheck.getText().contains(amount))) {
			System.out.println("8. All details matched");
		}

		wd.findElement(By.cssSelector("div.sa-button-container>div>button")).click();

		wdwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#nava:last-of-type")));
		WebElement backToHomePage = wd.findElement(By.cssSelector("#nava:last-of-type"));
		sf.assertEquals(backToHomePage.getText(), "PRODUCT STORE", "Title doesn't match");

		System.out.println("9. Redirected to Home Page: " + backToHomePage.getText());

	}

}
