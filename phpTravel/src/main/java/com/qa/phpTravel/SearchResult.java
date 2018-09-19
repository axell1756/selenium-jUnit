package com.qa.phpTravel;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResult {
	
	@FindBy(className = "table-striped")
	List<WebElement> results;

}
