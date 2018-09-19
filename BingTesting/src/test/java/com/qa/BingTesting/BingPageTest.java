package com.qa.BingTesting;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BingPageTest {

	public static ExtentReports report;
	public ExtentTest test;

	private WebDriver driver;

	@BeforeClass
	public static void initial() {
		report = new ExtentReports(
				"C:\\Users\\Admin\\Desktop\\workspace\\BingTesting\\src\\main\\reports\\BingWebpageTest.html", true);
	}

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:/driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void searchTest() {
		test = report.startTest("SearchText");

		driver.get("https://www.bing.com");
		test.log(LogStatus.INFO, "Bing opened");

		BingPage page = PageFactory.initElements(driver, BingPage.class);
		page.searchFor("Selenium");
		test.log(LogStatus.INFO, "Query input");

		WebElement checkElement = driver.findElement(By.id("sb_form_q"));
		
		if (checkElement.getAttribute("value").equals("Selenium")) {
			test.log(LogStatus.PASS, "Query was successfull");
		} else {
			test.log(LogStatus.FAIL, "Query was unsuccessfull");
		}

		assertEquals("Selenium", checkElement.getAttribute("value"));
		
		report.endTest(test);
	}

	@After
	public void tearDown() throws InterruptedException {
		driver.quit();
	}

	@AfterClass
	public static void end() {
		report.flush();
	}

}
