package com.qa.phpTravel;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

public class SearchBarTest {

	private WebDriver driver;

	public static ExtentReports report;
	public ExtentTest test;
	
	public static FileInputStream file = null;
	public static XSSFWorkbook workbook = null;

	@BeforeClass
	public static void initial() {
		report = new ExtentReports(Constants.REPORT_PATH + "searchBarTest.html", true);

		try {
			file = new FileInputStream(Constants.TDD_PATH + Constants.TDD_FILE);
		} catch (FileNotFoundException e) {
			
		}
		
		
		try {
			workbook = new XSSFWorkbook(file);
		} catch (IOException e) {
			
		}
				
	}

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", Constants.DRIVER_PATH);
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}

	@Test
	public void searchBarTest() throws Exception {
		
		XSSFSheet searchQuery = workbook.getSheetAt(0);
		XSSFCell city = searchQuery.getRow(1).getCell(0);
		XSSFCell dateIn = searchQuery.getRow(1).getCell(1);
		XSSFCell dateOut = searchQuery.getRow(1).getCell(2);
		XSSFCell guests = searchQuery.getRow(1).getCell(3);
		
		
		test = report.startTest("Search for Hotel");

		driver.get(Constants.INDEX_URL);
		test.log(LogStatus.INFO, "Page opened");

		SearchPage search = PageFactory.initElements(driver, SearchPage.class);
		search.searchForHotel(driver, city.getStringCellValue(), dateIn.toString(), dateOut.toString(), guests.getStringCellValue());

		test.log(LogStatus.INFO, "Search query" + test.addScreenCapture(Constants.SCREENSHOT_PATH + "searchQuery.png"));

		SearchResult result = PageFactory.initElements(driver, SearchResult.class);

		if (result.results.size() > 0) {
			test.log(LogStatus.PASS, "Search successfull"
					+ test.addScreenCapture(Helpers.takeScreenshot(driver, Constants.SCREENSHOT_PATH + "result.png")));
		} else {
			test.log(LogStatus.FAIL, "Search unsuccessfull"
					+ test.addScreenCapture(Helpers.takeScreenshot(driver, Constants.SCREENSHOT_PATH + "result.png")));
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
