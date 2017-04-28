package com.techelevator.pageobject;

import org.openqa.selenium.WebDriver;

public class LogInPage {
	
		private WebDriver webDriver;
		
	
		public LogInPage(WebDriver webDriver) {
			this.webDriver = webDriver;
		}



		public String getPageUrl() {
			String pageUrl = webDriver.getCurrentUrl();
			return pageUrl;
		}
	

	

}
