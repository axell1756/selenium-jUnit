package com.qa.phpTravel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HotelBookingPage {

	@FindBy(name = "checkin")
	private WebElement checkInDate;

	@FindBy(name = "checkout")
	private WebElement checkOutDate;

	@FindBy(id = "adults")
	private WebElement adults;

	@FindBy(id = "child")
	private WebElement children;

	@FindBy(xpath = "//*[@id=\"body-section\"]/div[4]/div[2]/div/div[2]/form/div[5]/input[3]")
	private WebElement submit;

	public void bookHotel(WebDriver driver, String out, double a, double c) throws Exception {
		Actions action = new Actions(driver);

		// Input check out date
		action.moveToElement(checkOutDate);
		action.doubleClick().perform();
		action.doubleClick().perform();
		action.sendKeys(out);

		// Navigate to adult guest selection and select how many adults
		action.moveToElement(adults);
		WebElement selectAdults = (new WebDriverWait(driver, 10)).until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"adults\"]/option[" + (int) a + "]")));
		action.click();
		action.moveToElement(selectAdults);
		action.click();

		// Navigate to young guest selection and select how many children
		action.moveToElement(children);
		WebElement selectKids = (new WebDriverWait(driver, 10)).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[@id=\"child\"]/option[" + (int) (c + 1) + "]")));
		action.click();
		action.moveToElement(selectKids);
		action.click();

		// Navigate to Submit button and click it
		action.moveToElement(submit);
		Helpers.takeScreenshot(driver, Constants.SCREENSHOT_PATH + "modifyStayTime.png");
		action.click();

	}
}
