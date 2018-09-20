package com.qa.passionTea.shopping;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.passionTea.Const;
import com.qa.passionTea.IndexPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StackSteps {
	private WebDriver driver;

	public static ExtentReports report;
	public ExtentTest test;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", Const.DRIVER_PATH + "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Given("^the correct web address$")
	public void the_correct_web_address() throws Throwable {

		test = StackRunner.report.startTest("Correct web address");
		driver.get(Const.INDEX_URL);

		if (driver.getCurrentUrl().equals(Const.INDEX_URL)) {

			test.log(LogStatus.PASS, "Correct URL is passed - " + driver.getCurrentUrl());

		} else {

			test.log(LogStatus.FAIL,
					"Is not correct URL - " + driver.getCurrentUrl() + ". Expected - " + Const.INDEX_URL);
		}

		assertEquals("Is not correct URL - " + driver.getCurrentUrl() + ". Expected - " + Const.INDEX_URL,
				Const.INDEX_URL, driver.getCurrentUrl());
		StackRunner.report.endTest(test);
	}

	@When("^I navigate to the 'Menu' page$")
	public void i_navigate_to_the_Menu_page() throws Throwable {

		test = StackRunner.report.startTest("Navigate to the 'Menu' page");
		IndexPage index = PageFactory.initElements(driver, IndexPage.class);
		index.navigateToMenu();

		if (driver.getCurrentUrl().equals(Const.MENU_URL)) {

			test.log(LogStatus.PASS, "Menu button has been pressed - " + driver.getCurrentUrl());

		} else {
			test.log(LogStatus.FAIL, "" + Const.INDEX_URL);
			StackRunner.report.endTest(test);
		}

		assertEquals("Button transfers to a wrong URL - " + driver.getCurrentUrl() + ". Expected - "
				+ Const.MENU_URL, Const.MENU_URL, driver.getCurrentUrl());
		StackRunner.report.endTest(test);
	}

	@Then("^I can browse a list of the available products\\.$")
	public void i_can_browse_a_list_of_the_available_products() throws Throwable {

		test = StackRunner.report.startTest("Browse a list of the available products");
		IndexPage index = PageFactory.initElements(driver, IndexPage.class);
		index.navigateToMenu();

		if (driver.getCurrentUrl().equals(Const.MENU_URL)) {

			test.log(LogStatus.PASS, "Browsing products - " + driver.getCurrentUrl());

		} else {
			test.log(LogStatus.FAIL,
					"Can't browse products - " + driver.getCurrentUrl() + ". Expected - " + Const.MENU_URL);
			StackRunner.report.endTest(test);
		}

		assertEquals("Can't browse products - " + driver.getCurrentUrl() + ". Expected - " + Const.MENU_URL,
				Const.MENU_URL, driver.getCurrentUrl());
		StackRunner.report.endTest(test);
	}

	@When("^I click the checkout button$")
	public void i_click_the_checkout_button() throws Throwable {
		test = StackRunner.report.startTest("Checkout button functionality");

		IndexPage index = PageFactory.initElements(driver, IndexPage.class);
		index.navigateToCheckout();

		if (driver.getCurrentUrl().equals(Const.CHECKOUT_URL)) {

			test.log(LogStatus.PASS, "Checkout accessed - " + driver.getCurrentUrl());

		} else {
			test.log(LogStatus.FAIL,
					"Can't access checkout - " + driver.getCurrentUrl() + ". Expected - " + Const.CHECKOUT_URL);
			StackRunner.report.endTest(test);
		}

		assertEquals("Can't access checkout - ", Const.CHECKOUT_URL, driver.getCurrentUrl());
		StackRunner.report.endTest(test);
	}

	@Then("^I am taken to the checkout page$")
	public void i_am_taken_to_the_checkout_page() throws Throwable {
		test = StackRunner.report.startTest("Checkout page opened");

		IndexPage index = PageFactory.initElements(driver, IndexPage.class);
		index.navigateToCheckout();

		if (driver.getCurrentUrl().equals(Const.CHECKOUT_URL)) {

			test.log(LogStatus.PASS, "Taken to the checkout page - " + driver.getCurrentUrl());

		} else {
			test.log(LogStatus.FAIL,
					"Can't access checkout - " + driver.getCurrentUrl() + ". Expected - " + Const.CHECKOUT_URL);
			StackRunner.report.endTest(test);
		}

		assertEquals("Can't access checkout - ", Const.CHECKOUT_URL, driver.getCurrentUrl());
		StackRunner.report.endTest(test);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		StackRunner.report.flush();
	}

}