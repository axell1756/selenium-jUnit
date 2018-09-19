package com.qa.BingTesting;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BingPage 
{
    
	
	@FindBy(id = "sb_form_q")
	private WebElement searchBox;
	
	@FindBy(id = "sb_form_go")
	private WebElement searchBtn;
	
	public void searchFor(String query) {
		searchBox.sendKeys(query);
		searchBox.submit();
//		searchBtn.click();
	}
	
	
}
