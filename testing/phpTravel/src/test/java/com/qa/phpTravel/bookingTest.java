package com.qa.phpTravel;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class bookingTest {
	
private WebDriver driver;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","C:/driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
	}

	@Test
	public void mainSearchBar() throws InterruptedException {
		driver.get("https://www.phptravels.net");
		SearchPage search = PageFactory.initElements(driver, SearchPage.class);
		search.bookHotel(driver, "London", "18/09/2018", "20/09/2018", "3 Adult 0 Child");
		SearchResult result = PageFactory.initElements(driver, SearchResult.class);
		System.out.println(result.results.size());
		assertTrue("Search doesn't work", result.results.size() > 0);
	}
	
	@After
	public void tearDown() {
		driver.quit();
		}

}
