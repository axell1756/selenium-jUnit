package com.qa.shoppingTesting;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class SearchTest {

private WebDriver driver;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","C:/driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
  
	@Test
	public void resultsFound() throws InterruptedException {
		driver.get("http://automationpractice.com/index.php");
		Search search = PageFactory.initElements(driver, Search.class);
		search.searchFor("dress");
		Thread.sleep(1000);
		SearchResult result = PageFactory.initElements(driver, SearchResult.class);
		System.out.println(result.allElements.size());
		assertTrue("Results are empty", result.allElements.size() > 0);
	}
	
	@Test
	public void resultsNotFound() throws InterruptedException {
		driver.get("http://automationpractice.com/index.php");
		Search search = PageFactory.initElements(driver, Search.class);
		search.searchFor("sasdads");
		Thread.sleep(1000);
		SearchResult result = PageFactory.initElements(driver, SearchResult.class);
		System.out.println(result.allElements.size());
		assertTrue("Has results", result.allElements.size() == 0);
	}
	
	@After
	public void tearDown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
		}

}
