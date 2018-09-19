package com.mark.seleniumTest;

import static org.junit.Assert.*;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class demoSiteTest {
	private WebDriver driver;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","C:/driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void createUser() throws InterruptedException {
		driver.get("http://thedemosite.co.uk/addauser.php");
		CreateUserPage userPage = PageFactory.initElements(driver, CreateUserPage.class);
	    userPage.createNewUser("user", "pass");
	    
	    driver.get("http://thedemosite.co.uk/login.php");
	    LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	    loginPage.login("user", "pass");
	    
	    assertTrue("Login unsuccessful", loginPage.success.getText().equals("**Successful Login**"));
	}
	@After
	public void tearDown() throws Exception {
		driver.quit();
		
	}


}
