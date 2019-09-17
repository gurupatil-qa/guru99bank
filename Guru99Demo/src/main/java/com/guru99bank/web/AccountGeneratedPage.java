package com.guru99bank.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountGeneratedPage {
	
	WebDriver driver;

	public AccountGeneratedPage(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="p[class='heading3']")
	WebElement accountSuccess;
	
	@FindBy(xpath = "//table[@id='account']/tbody/tr[4]/td[2]")
	WebElement accountID;
	
	@FindBy(xpath = "//table[@id='account']/tbody/tr[5]/td[2]")
	WebElement customerID;
	
	@FindBy(xpath = "//table[@id='account']/tbody/tr[6]/td[2]")
	WebElement customerName;
	
	@FindBy(xpath = "//table[@id='account']/tbody/tr[7]/td[2]")
	WebElement email;
	
	@FindBy(xpath = "//table[@id='account']/tbody/tr[8]/td[2]")
	WebElement accountType;
	
	@FindBy(xpath = "//table[@id='account']/tbody/tr[9]/td[2]")
	WebElement dateOfOpening;
	
	@FindBy(xpath = "//table[@id='account']/tbody/tr[10]/td[2]")
	WebElement currentAmount;
	
	public String getAccountSuccessMsg()
	{
		return accountSuccess.getText();
	}
	
	public WebElement accountID()
	{
		return accountID;
	}
	
	public WebElement customerID()
	{
		return customerID;
	}
	
	public WebElement customerName()
	{
		return customerName;
	}
	
	public WebElement email()
	{
		return email;
	}
	
	public WebElement accountType()
	{
		return accountType;
	}
	
	public WebElement dateOfOpening()
	{
		return dateOfOpening;
	}
	
	public WebElement currentAmount()
	{
		return currentAmount;
	}
	
	

}
