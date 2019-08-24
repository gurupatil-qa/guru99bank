package com.guru99bank.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	public WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="uid")
	WebElement userId;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(name="btnLogin")
	WebElement login;
	
	@FindBy(linkText="Log out")
	WebElement logOut;
	
	public WebElement userID()
	{
		return userId;
	}
	
	public WebElement password()
	{
		return password;
	}
	
	public WebElement loginBtn()
	{
		return login;
	}
	
	public WebElement logoutLink()
	{
		return logOut;
	}
}
