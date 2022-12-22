package secondAssignment;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ThirdQuestion {

	// http://seleniumpractise.blogspot.com/2017/07/multiple-window-examples.html
	// Validate the title of the pages opened in multiple tabs
	WebDriverWait wdwait;
	WebDriver wd;
	SoftAssert sf;
	Actions action;
	String parentTabHandle;
	String googleTabHandle;
	String facebookTabHandle;
	String yahooTabHandle;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "D:\\Web Drivers\\Chrome Driver\\chromedriver.exe");
		wd = new ChromeDriver();
		wdwait = new WebDriverWait(wd, Duration.ofSeconds(10));
		sf = new SoftAssert();
		wd.get("http://seleniumpractise.blogspot.com/2017/07/multiple-window-examples.html");
		wd.manage().window().maximize();

		// instantiating action class and passing web driver to the instance of action
		// class
		action = new Actions(wd);
	}

	@Test
	public void validateMultipleTabs() {

		WebElement titleOfPage = wd.findElement(By.cssSelector("h3[class='post-title entry-title']"));
		sf.assertEquals(titleOfPage.getText(), "Multiple window examples", " *Title mismatched for WebPage* ");
		sf.assertAll();
		System.out.println("1. Title of Main Page: " + titleOfPage.getText());

		// verify parent window handle
		parentTabHandle = wd.getWindowHandle();
		System.out.println("2. Main Tab Handle is: " + parentTabHandle);

		verifyGoogleTab();
		verifyFacebookTab();
		verifyYahooTab();
		verifySessionInSameTab();

	}

	public void verifyGoogleTab() {

		action.moveToElement(wd.findElement(By.cssSelector("#post-body-6170641642826198246 a:first-of-type"))).click()
				.perform();

		Set<String> firstTabHandles = wd.getWindowHandles();
		for (String handle1 : firstTabHandles) {
			if (!handle1.equals(parentTabHandle)) {
				googleTabHandle = handle1;
				System.out.println("3. Google Tab Handle is: " + googleTabHandle);
			}
		}
		// switch to that tab to find title
		wd.switchTo().window(googleTabHandle);
		System.out.println("4. Title of the Google Page: " + wd.getTitle());

		// validate by using assert method
		sf.assertEquals(wd.getTitle(), "Google", " *Title mismatched for Google* ");
		sf.assertAll();

		// switch back to main page
		wd.switchTo().window(parentTabHandle);

	}

	public void verifyFacebookTab() {

		wdwait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("#post-body-6170641642826198246 a:nth-of-type(2)")));
		action.moveToElement(wd.findElement(By.cssSelector("#post-body-6170641642826198246 a:nth-of-type(2)"))).click()
				.perform();

		Set<String> secondTabHandles = wd.getWindowHandles();
		for (String handle2 : secondTabHandles) {
			if (!(handle2.equals(parentTabHandle)) && !(handle2.equals(googleTabHandle))) {
				facebookTabHandle = handle2;
				System.out.println("5. Facebook Tab Handle is: " + facebookTabHandle);
			}
		}

		wd.switchTo().window(facebookTabHandle);
		System.out.println("6. Title of Facebook page: " + wd.getTitle());

		// validate by using assert method
		sf.assertEquals(wd.getTitle(), "Facebook - log in or sign up", " *Title mismatched for Facbook* ");
		sf.assertAll();

		// switch back to main page
		wd.switchTo().window(parentTabHandle);
	}

	public void verifyYahooTab() {

		wdwait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("#post-body-6170641642826198246 a:nth-of-type(3)")));
		action.moveToElement(wd.findElement(By.cssSelector("#post-body-6170641642826198246 a:nth-of-type(3)"))).click()
				.perform();
		Set<String> thirdTabHandles = wd.getWindowHandles();
		for (String handle3 : thirdTabHandles) {
			if (!(handle3.equals(parentTabHandle)) && !(handle3.equals(googleTabHandle))
					&& !(handle3.equals(facebookTabHandle))) {
				yahooTabHandle = handle3;
				System.out.println("7. Yahoo Tab Handle is: " + yahooTabHandle);
			}
		}

		wd.switchTo().window(yahooTabHandle);
		System.out.println("8. Title of Yahoo page: " + wd.getTitle());

		// validate by using assert method
		sf.assertEquals(wd.getTitle(),
				"Yahoo | Mail, Weather, Search, News, Finance, Sports, Shopping, Entertainment, Video",
				" *Title mismatched for Yahoo* ");
		sf.assertAll();

		// switch back to main page
		wd.switchTo().window(parentTabHandle);

	}

	public void verifySessionInSameTab() {

		wdwait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("#post-body-6170641642826198246 a:nth-of-type(4)")));
		action.moveToElement(wd.findElement(By.cssSelector("#post-body-6170641642826198246 a:nth-of-type(4)"))).click()
				.perform();
		// to get handle for the
		String sameTabHandle = wd.getWindowHandle();
		System.out.println("9. Session in same Page Handle: " + sameTabHandle);

		System.out.println("10. Title of session in same page: " + wd.getTitle());
		sf.assertEquals(wd.getTitle(), "Facebook - log in or sign up", " *Title Mismatched for this session* ");
		sf.assertAll();

		// navigating back to main window
		wd.navigate().back();

	}

	@AfterMethod
	public void tearDown() {
		wd.quit();
	}

}
