package com.qa.shoppingTesting;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResult {

	@FindBy(className = "ajax_block_product")
	List<WebElement> allElements;
	
}
