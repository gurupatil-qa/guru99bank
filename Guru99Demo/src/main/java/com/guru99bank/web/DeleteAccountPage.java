package com.guru99bank.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteAccountPage {
	
	WebDriver driver;
	
	public DeleteAccountPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[name='accountno']")
	WebElement accountno;
	
	@FindBy(css="input[name='AccSubmit']")
	WebElement AccSubmit;
	
	public WebElement accouuntNo()
	{
		return accountno;
	}
	
	public WebElement submitBtn()
	{
		return AccSubmit;
	}

}
