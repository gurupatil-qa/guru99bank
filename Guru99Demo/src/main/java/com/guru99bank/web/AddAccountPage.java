package com.guru99bank.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddAccountPage {
	
	WebDriver driver;
	
	public AddAccountPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[name='cusid']")
	@CacheLookup
	WebElement custid;
	
	@FindBy(css="select[name='selaccount']")
	@CacheLookup
	WebElement accountType;
	
	@FindBy(css="input[name='inideposit']")
	@CacheLookup
	WebElement inideposit;
	
	@FindBy(css="input[name='button2']")
	@CacheLookup
	WebElement submitBtn;
	
	public WebElement customerID()
	{
		return custid;
	}

	public Select accouuntType()
	{
		return new Select(accountType);
	}

	public WebElement initialDeposit()
	{
		return inideposit;
	}
	
	public WebElement submitBtn()
	{
		return submitBtn;	
	}
}
