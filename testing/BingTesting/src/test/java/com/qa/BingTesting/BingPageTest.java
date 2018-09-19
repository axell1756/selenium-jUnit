package com.qa.BingTesting;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class BingPageTest {

private WebDriver driver;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","C:/driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
  
	@Test
	public void searchTest() {
		driver.get("https://www.bing.com");
		BingPage page = PageFactory.initElements(driver, BingPage.class);
		page.searchFor("Selenium");
		WebElement checkElement = driver.findElement(By.id("sb_form_q"));
		assertEquals("Selenium", checkElement.getAttribute("value"));
	}
	
	@After
	public void tearDown() throws InterruptedException {
		Thread.sleep(6000);
		driver.quit();
		}

}
