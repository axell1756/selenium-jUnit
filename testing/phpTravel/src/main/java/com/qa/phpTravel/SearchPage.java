package com.qa.phpTravel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Hello world!
 *
 */
public class SearchPage 
{
    
	@FindBy(id = "s2id_autogen8")
	private WebElement searchMask;
	
	@FindBy(xpath = "//*[@id=\"select2-drop\"]/div/input")
	private WebElement searchBox;
	
	@FindBy(id = "dpd1")
	private WebElement dateArrive;
	
	@FindBy(id = "select2-drop-mask")
	private WebElement dateLeaveMask;
	
	@FindBy(id = "dpd2")
	private WebElement dateLeave;
	
	@FindBy(id = "travellersInput")
	private WebElement guests;
	
	public void bookHotel(WebDriver driver, String loc, String arrive, String leave, String g) {
		
		//Input location
		Actions action = new Actions(driver);
		action.moveToElement(searchMask);
		action.click();
		action.sendKeys(loc).perform();
		
		//Wait for dropdown to appear to confirm location
		WebElement searchDropdown = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"select2-drop\"]/ul/li/ul/li[1]/div")));
		action.moveToElement(searchDropdown);
		action.click();
		
		//Navigate to arrive date selection
		action.sendKeys(Keys.TAB).perform();
		action.sendKeys(arrive);
		
		//Navigate to leave date selection
		action.sendKeys(Keys.TAB).perform();
		action.sendKeys(leave);

		//Navigate to guest selection
		action.sendKeys(Keys.TAB).perform();
		action.sendKeys(g);
		
		//Search for criteria 
		action.sendKeys(Keys.TAB).perform();
		action.sendKeys(Keys.ENTER).perform();

		
	}
	
}
