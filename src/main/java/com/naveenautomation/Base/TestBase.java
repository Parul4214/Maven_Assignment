package com.naveenautomation.Base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	// making web driver static, once changed it remain same
	public static WebDriver driver;

	// to launch browser
	public void launchBrowser() {

		String browserForTesting = "chrome";
		// switch case to choose the web browser
		switch (browserForTesting.toLowerCase()) {
//		case "firefox":
//			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver();
//			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		default:
			System.out.println("Invalid Web Browser");
			break;
		}
		// to manage time
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// to maximize window size
		driver.manage().window().maximize();

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
		// launch web page
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=common/home");

	}

	// method to quit browser
	public void quitBrowser() {

		driver.quit();
	}

	public void sleep(int timer) {
		try {
			Thread.sleep(timer * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
