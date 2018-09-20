package com.qa.phpTravel;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;

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

public class HotelBookingTest {

	private WebDriver driver;

	public static ExtentReports report;
	public ExtentTest test;
	
	public static FileInputStream file = null;
	public static XSSFWorkbook workbook = null;

	@BeforeClass
	public static void initial() {
		report = new ExtentReports(Constants.REPORT_PATH + "hotelBookingTest.html", true);

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
	public void bookHotel() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		XSSFSheet searchQuery = workbook.getSheetAt(1);
		XSSFCell out = searchQuery.getRow(1).getCell(0);
		XSSFCell a = searchQuery.getRow(1).getCell(1);
		XSSFCell c = searchQuery.getRow(1).getCell(2);
		
		test = report.startTest("Book Hotel");

		driver.get(Constants.LONDON_HOTEL_URL);
		test.log(LogStatus.INFO, "Page with Hotel in London opened");
		
		HotelBookingPage booking = PageFactory.initElements(driver, HotelBookingPage.class);
		
		String checkOut = format.format(out.getDateCellValue());
		booking.bookHotel(driver, checkOut, a.getNumericCellValue(), c.getNumericCellValue());
		
		Thread.sleep(30000);
		
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
