package com.techelevator.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePageObject {
	
	private WebDriver webDriver;
	
	public HomePageObject(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public RegistrationInputPage clickRegisterLink() {
		WebElement registerLink = webDriver.findElement(By.linkText("Register"));
		registerLink.click();
		return new RegistrationInputPage(webDriver);
	}

}
