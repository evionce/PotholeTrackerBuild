package com.techelevator.selenium;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.techelevator.pageobject.HomePageObject;
import com.techelevator.pageobject.LogInPage;
import com.techelevator.pageobject.RegistrationInputPage;

public class RegistrationTest {
	
	private static WebDriver webDriver;
	private HomePageObject homePage;
	
	@BeforeClass
	public static void openWebBrowserForTesting() {
		
		String homeDir = System.getProperty("user.home");
		System.setProperty("webdriver.chrome.driver", homeDir+"/dev-tools/chromedriver/chromedriver");
		webDriver = new ChromeDriver();
		
	}
	
	@Before
	public void openHomePage() {
		webDriver.get("http://localhost:8080/capstone/");
		homePage = new HomePageObject(webDriver);
	}
	
	@AfterClass
	public static void closeWebBrowser() {
		webDriver.close();
	}
	
	@Test
	public void new_user_can_be_registered() {
		LogInPage resultPage = homePage.clickRegisterLink()
								.enterEmailAddress("sue@who.com")
								.enterFirstName("Sue")
								.enterLastName("Who")
								.enterPhoneNumber("614-555-5555")
								.enterPassword("Password1")
								.enterConfirmPassword("Password1")
								.submitAccount();
								
		Assert.assertEquals("http://localhost:8080/capstone/login", resultPage);
		
	}
	
	
	

}
