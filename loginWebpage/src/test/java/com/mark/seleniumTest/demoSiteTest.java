package com.mark.seleniumTest;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class demoSiteTest {
	private WebDriver driver;

	public static ExtentReports report;
	public ExtentTest test;

	public static FileInputStream file = null;
	public static XSSFWorkbook workbook = null;

	@BeforeClass
	public static void initial() {
		report = new ExtentReports(Const.REPORT_PATH + "loginWebpageTest.html", true);

		try {
			file = new FileInputStream(Const.TDD_PATH + "data.xlsx");
		} catch (FileNotFoundException e) {

		}

		try {
			workbook = new XSSFWorkbook(file);
		} catch (IOException e) {

		}

	}

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", Const.DRIVER_PATH + "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void createUser() throws Exception {

		XSSFSheet userDetails = workbook.getSheetAt(0);

		for (int i = 0; i < userDetails.getPhysicalNumberOfRows(); i++) {
			XSSFCell username = userDetails.getRow(i).getCell(0);
			XSSFCell password = userDetails.getRow(i).getCell(1);

			test = report.startTest("Test " + i + " - username: " + username.getStringCellValue() + ", password: "
					+ password.getStringCellValue());

			createNewUser(username.getStringCellValue(), password.getStringCellValue(), i);
			loginUser(username.getStringCellValue(), password.getStringCellValue(), i);

			report.endTest(test);
		}
	}

	private void createNewUser(String username, String password, int run) throws Exception {
		driver.get(Const.NEW_USER_URL);
		test.log(LogStatus.INFO, "Creating new user");

		CreateUserPage userPage = PageFactory.initElements(driver, CreateUserPage.class);
		userPage.createNewUser(username, password);
		test.log(LogStatus.INFO, "User created"
				+ test.addScreenCapture(Helpers.takeScreenshot(driver, Const.SCREENSHOT_PATH + "newUser" + run + ".png")));
	}

	private void loginUser(String username, String password, int run) throws Exception {

		driver.get(Const.USER_LOGIN_URL);
		test.log(LogStatus.INFO, "Logging in with new user credentials");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.login(username, password);

		if (loginPage.success.getText().equals("**Successful Login**")) {

			assertTrue("Login unsuccessful", loginPage.success.getText().equals("**Successful Login**"));

			test.log(LogStatus.PASS, "Login successfull"
					+ test.addScreenCapture(Helpers.takeScreenshot(driver, Const.SCREENSHOT_PATH + "result" + run + ".png")));
		} else {
			test.log(LogStatus.FAIL, "Login unsuccessfull"
					+ test.addScreenCapture(Helpers.takeScreenshot(driver, Const.SCREENSHOT_PATH + "result" + run + ".png")));
		}

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();

	}

	@AfterClass
	public static void end() {
		report.flush();

	}

}
