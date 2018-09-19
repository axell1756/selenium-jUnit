package com.mark.seleniumTest;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateUserPage 
{
    
	@FindBy(name = "username")
	private WebElement username;
	
	@FindBy(name = "password")
	private WebElement password;
	
	@FindBy(name = "FormsButton2")
	private WebElement submitButton;
		
	public void createNewUser(String u, String p) {
		username.sendKeys(u);
		password.sendKeys(p);
		submitButton.submit();
	}
	
}
