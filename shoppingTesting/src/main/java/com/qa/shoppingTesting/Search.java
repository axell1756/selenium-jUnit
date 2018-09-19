package com.qa.shoppingTesting;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Search 
{
    
	@FindBy(id = "search_query_top")
	private WebElement searchBox;
	
	@FindBy(xpath = "//*[@id=\"searchbox\"]/button")
	private WebElement searchButton;
	
	public void searchFor(String query) {
		searchBox.sendKeys(query);
		searchButton.submit();
	}
}
