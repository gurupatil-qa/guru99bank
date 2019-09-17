package com.guru99bank.web;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import resources.base;

public class temp extends base{

	public static WebDriver driver;
	public static LoginPage loginPage = new LoginPage(driver);;
	public static Logger log = LogManager.getLogger(LoginPageTest.class);

	@BeforeClass
	public void Launch() throws IOException {
		driver = initializeDriver();
		driver.get(prop.getProperty("baseurl"));
		log.info("Browser initialized");
		Reporter.log("Browser initialized and rediredcted to page successfully");

	}
	
	@Test
	public void testMethod()
	{
		
	}
	
	@AfterClass
	public void dismental() {
		teardown();
	}
}
