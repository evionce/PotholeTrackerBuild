package com.techelevator.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.techelevator.pageobject.LogInPage;

public class RegistrationInputPage {
	
	private WebDriver webDriver;
	
	public RegistrationInputPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public RegistrationInputPage enterEmailAddress(String email) {
		WebElement emailField = webDriver.findElement(By.name("email"));
		emailField.sendKeys(email);
		return this;
		
	}

	public RegistrationInputPage enterFirstName(String firstName) {
		WebElement nameField = webDriver.findElement(By.name("first_name"));
		nameField.sendKeys(firstName);
		return this;
	}
	
	public RegistrationInputPage enterLastName(String firstName ) {
		WebElement lNameField = webDriver.findElement(By.name("last_name"));
		lNameField.sendKeys(firstName);
		return this;
	}
	
	public RegistrationInputPage enterPhoneNumber(String number) {
		WebElement phoneNumber = webDriver.findElement(By.name("phoneNumber"));
		phoneNumber.sendKeys(number);
		return this;
	}
	
	public RegistrationInputPage enterPassword(String pass) {
		WebElement password = webDriver.findElement(By.name("password"));
		password.sendKeys(pass);
		return this;
	}
	
	public RegistrationInputPage enterConfirmPassword(String passConfirm) {
		WebElement confirmPassword = webDriver.findElement(By.name("passwordMatch"));
		confirmPassword.sendKeys(passConfirm);
		return this;
	}
	
	public LogInPage submitAccount() {
		WebElement submitAccount = webDriver.findElement(By.id("registerButton"));
		submitAccount.click();
		return new LogInPage(webDriver);
	}

}
