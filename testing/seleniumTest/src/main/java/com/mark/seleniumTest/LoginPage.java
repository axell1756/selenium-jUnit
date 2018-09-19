package com.mark.seleniumTest;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	@FindBy(name = "username")
	private WebElement username;
	
	@FindBy(name = "password")
	private WebElement password;
	
	@FindBy(name = "FormsButton2")
	private WebElement submitButton;
	
	@FindBy(xpath= "/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")
	WebElement success;
	
	public void login(String u, String p) {
		username.sendKeys(u);
		password.sendKeys(p);
		submitButton.submit();
	}
	
}
