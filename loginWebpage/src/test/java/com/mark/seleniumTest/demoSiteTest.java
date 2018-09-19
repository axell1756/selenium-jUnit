package com.mark.seleniumTest;

import static org.junit.Assert.*;

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
	public void createUser() throws Exception {
		test = report.startTest("Create User");
		
		driver.get("http://thedemosite.co.uk/addauser.php");
		test.log(LogStatus.INFO, "Page opened");
		
		CreateUserPage userPage = PageFactory.initElements(driver, CreateUserPage.class);
		userPage.createNewUser("user", "pass");
		test.log(LogStatus.INFO, "User created (Username: user, Password: pass)" + test.addScreenCapture(helper.takeScreenshot(driver, "C:\\Users\\Admin\\Desktop\\workspace\\loginWebpage\\src\\main\\reports\\img\\newUser.png")));
		
		driver.get("http://thedemosite.co.uk/login.php");
		test.log(LogStatus.INFO, "Login page opened");
		
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.login("user", "pass");

		if (loginPage.success.getText().equals("**Successful Login**")) {
			test.log(LogStatus.PASS, "Login successfull" + test.addScreenCapture(helper.takeScreenshot(driver, "C:\\Users\\Admin\\Desktop\\workspace\\loginWebpage\\src\\main\\reports\\img\\result.png")));
		} else {
			test.log(LogStatus.FAIL, "Login unsuccessfull" + test.addScreenCapture(helper.takeScreenshot(driver, "C:\\Users\\Admin\\Desktop\\workspace\\loginWebpage\\src\\main\\reports\\img\\result.png")));
		}
		
		assertTrue("Login unsuccessful", loginPage.success.getText().equals("**Successful Login**"));
		report.endTest(test);
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
