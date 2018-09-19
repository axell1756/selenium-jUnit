package com.qa.phpTravel;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class bookingTest {

	private WebDriver driver;

	public static ExtentReports report;
	public ExtentTest test;

	public Helpers helper;

	@BeforeClass
	public static void initial() {
		report = new ExtentReports(
				"C:\\Users\\Admin\\Desktop\\workspace\\loginWebpage\\src\\main\\reports\\loginWebpageTest.html", true);
	}

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:/driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}

	@Test
	public void searchBarTest() throws Exception {
		test = report.startTest("Create User");
		
		driver.get("https://www.phptravels.net");
		test.log(LogStatus.INFO, "Page opened");
		
		SearchPage search = PageFactory.initElements(driver, SearchPage.class);
		search.bookHotel(driver, "London", "19/09/2018", "21/09/2018", "3 Adult 0 Child");
		test.log(LogStatus.INFO, "Search query" + test.addScreenCapture(helper.takeScreenshot(driver, "C:\\Users\\Admin\\Desktop\\workspace\\shoppingTesting\\src\\main\\reports\\img\\searchQuery.png")));
		
		SearchResult result = PageFactory.initElements(driver, SearchResult.class);
		
		if (result.results.size() > 0) {
			test.log(LogStatus.PASS, "Search successfull" + test.addScreenCapture(helper.takeScreenshot(driver, "C:\\Users\\Admin\\Desktop\\workspace\\shoppingTesting\\src\\main\\reports\\img\\result.png")));
		} else {
			test.log(LogStatus.FAIL, "Search unsuccessfull" + test.addScreenCapture(helper.takeScreenshot(driver, "C:\\Users\\Admin\\Desktop\\workspace\\shoppingTesting\\src\\main\\reports\\img\\result.png")));
		}
		
		assertTrue("Search doesn't work", result.results.size() > 0);
		report.endTest(test);
	}

	@After
	public void tearDown() {
		driver.quit();
	}
	
	@AfterClass
	public static void end() {
		report.flush();
	}

}
