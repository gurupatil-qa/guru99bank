package com.guru99bank.web;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resources.base;

public class ManagerHomePageTest extends base{
	
	private static WebDriver driver;
	private static ManagerHomePage HP;
	
	@BeforeTest
	public void setup() throws IOException
	{
		driver = appLogin();
	}
	
	
	@Test(priority=1)
	public void verifyTitle()
	{
		Assert.assertEquals(getTitle(), "Guru99 Bank Manager HomePage");
	}
	
	@Test(priority=2)
	public void verifyWelcomeText()
	{
		HP = new ManagerHomePage(driver);
		Assert.assertEquals(HP.getWelcomeText(), "Welcome To Manager's Page of Guru99 Bank");
	
	}
	
	@AfterTest
	public void dismetnal()
	{
		teardown();
	}

}
