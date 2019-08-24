package com.guru99bank.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {
	
	WebDriver driver;
	
	public AddCustomerPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@name='name']")
	WebElement cName;
	
	@FindBy(css="input[value='m']")
	WebElement genderMale;
	
	@FindBy(css="input[value='f']")
	@CacheLookup
	WebElement genderFemale;
	
	@FindBy(css="textarea[name='addr']")
	@CacheLookup
	WebElement address;
	
	@FindBy(css="input[name='city']")
	@CacheLookup
	WebElement city;
	
	@FindBy(css="input[name='state']")
	@CacheLookup
	WebElement state;
	
	@FindBy(css="input[name='pinno']")
	WebElement pinno;
	
	@FindBy(css="input[name='telephoneno']")
	WebElement mobileNumber;
	
	@FindBy(css="input[name='emailid']")
	WebElement emailid;
	
	@FindBy(css="input[name='password']")
	WebElement password;
	
	@FindBy(css="input[name='sub']")
	WebElement submitBtn;
	
	@FindBy(css="input[name='res']")
	WebElement resetBtn;
	
	@FindBy(css="input[name='dob']")
	WebElement dob;
	
	public WebElement dateOfBirth() 
	{
		return dob;
	}
	
	public WebElement resetBtn()
	{
		return resetBtn;
	}
	
	public WebElement submitBtn()
	{
		return submitBtn;
	}
	
	public WebElement password()
	{
		return password;
	}
	
	public WebElement mobileNumber()
	{
		return mobileNumber;
	}
	
	public WebElement emailID()
	{
		return emailid;
	}
	
	public WebElement ppin()
	{
		return pinno;
	}
	
	public WebElement state()
	{
		return state;
	}
	
	public WebElement city()
	{
		return city;
	}
	
	public WebElement addressBox()
	{
		return address;
	}
	
	public WebElement genderMaleRadioBtn()
	{
		return genderMale;
	}
	
	public WebElement genderFemaleRadioBtn()
	{
		return genderFemale;
	}
	
	public WebElement customerName()
	{
		return cName;
	}
	
}
