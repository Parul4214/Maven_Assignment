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

public class SecondQuestion {

	// Validate WebPages are opened when clicked on the DropDown

	WebDriverWait wdwait;
	WebDriver wd;
	SoftAssert sf;
	Actions action;
	String mainTabHandle;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "D:\\Web Drivers\\Chrome Driver\\chromedriver.exe");
		wd = new ChromeDriver();
		wdwait = new WebDriverWait(wd, Duration.ofSeconds(10));
		sf = new SoftAssert();
		wd.get("http://seleniumpractise.blogspot.com/2016/08/how-to-perform-mouse-hover-in-selenium.html");
		wd.manage().window().maximize();

		// instantiating action class and passing web driver to the instance of action
		// class
		action = new Actions(wd);
	}

	@Test
	public void valiateWebPages() {

		wdwait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h3[itemprop='name']")));
		WebElement titleOfPage = wd.findElement(By.cssSelector("h3[itemprop='name']"));
		sf.assertEquals(titleOfPage.getText(), "How to perform mouse hover in Selenium Webdriver",
				" *Title Mismatched for Selenium Page* ");
		sf.assertAll();
		System.out.println("1. Title of Page: " + titleOfPage.getText());

		// invoking mouse hover method
		verifyMouseHover();
		verifySeleniumTab();
		verifyTestNGTab();
		VerifyAppiumTab();

	}

	@AfterMethod
	public void tearDown() {

		wd.quit();
	}

	// Method to verify Mouse Hover Functions

	public void verifyMouseHover() {

		wdwait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.dropbtn")));
		WebElement mouseHover = wd.findElement(By.cssSelector("button.dropbtn"));
		action.moveToElement(mouseHover).perform();

	}

	// method to verify Selenium tab handle and Title
	public void verifySeleniumTab() {
		mainTabHandle = wd.getWindowHandle();
		System.out.println("2. Main tab Window Handle " + mainTabHandle);

		// performing click action
		action.moveToElement(wd.findElement(By.linkText("Selenium"))).click().perform();

		Set<String> allWindowHandles1 = wd.getWindowHandles();
		for (String handle1 : allWindowHandles1) {
			// to find child handle
			if (!(handle1.equals(mainTabHandle))) {
				// Switching to Selenium Tab to validate title
				wd.switchTo().window(handle1);
				System.out.println("3. Selenium Tab handle " + handle1);
			}
		}

		wdwait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#genesis-content>article>header>h1")));
		sf.assertEquals(wd.findElement(By.cssSelector("h1.entry-title")).getText(),
				"Selenium Webdriver tutorial for beginners", " *Title Mismatched for Selenium WebPage* ");
		sf.assertAll();

		System.out.println("4. Title of Selenium Page: " + wd.findElement(By.cssSelector("h1.entry-title")).getText());

		// Navigate back to original page

		wd.switchTo().window(mainTabHandle);

	}

	// method to validate TestNG webPage
	public void verifyTestNGTab() {

		verifyMouseHover();
		wd.findElement(By.linkText("TestNG")).click();
		wdwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("header.entry-header>h1")));

		WebElement titleOfTestNGPage = wd.findElement(By.cssSelector("header.entry-header>h1"));
		sf.assertEquals(titleOfTestNGPage.getText(), "TestNG Tutorials for Selenium Webdriver",
				"Title Mismatched for TestNG Webpage");
		sf.assertAll();

		System.out.println("5. Title of TestNG Page: " + titleOfTestNGPage.getText());

		// Navigating back to Main Page
		wd.navigate().back();

	}

	public void VerifyAppiumTab() {

		verifyMouseHover();
		wd.findElement(By.linkText("Appium")).click();

		wdwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("header.entry-header h1")));
		WebElement titleOfAppium = wd.findElement(By.cssSelector("header.entry-header h1"));
		sf.assertEquals(titleOfAppium.getText(), "Complete Ultimate Appium tutorial for beginners",
				" *Title Mismatch for Appium Page* ");
		sf.assertAll();

		System.out.println("6. Title of Appium Page: " + titleOfAppium.getText());

		wd.navigate().back();

	}

}
