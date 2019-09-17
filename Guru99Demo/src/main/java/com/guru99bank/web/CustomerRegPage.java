package com.guru99bank.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerRegPage {

	WebDriver driver;

	public CustomerRegPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "p[class='heading3']")
	WebElement success;

	@FindBy(linkText = "Continue")
	WebElement continueLink;

	@FindBy(xpath = "//table[@id='customer']/tbody/tr[4]/td[2]")
	WebElement customerID;

	@FindBy(xpath = "//table[@id='customer']/tbody/tr[5]/td[2]")
	WebElement cname;

	@FindBy(xpath = "//table[@id='customer']/tbody/tr[6]/td[2]")
	WebElement gender;

	@FindBy(xpath = "//table[@id='customer']/tbody/tr[7]/td[2]")
	WebElement birthday;

	@FindBy(xpath = "//table[@id='customer']/tbody/tr[8]/td[2]")
	WebElement address;

	@FindBy(xpath = "//table[@id='customer']/tbody/tr[9]/td[2]")
	WebElement city;

	@FindBy(xpath = "//table[@id='customer']/tbody/tr[10]/td[2]")
	WebElement state;

	@FindBy(xpath = "//table[@id='customer']/tbody/tr[11]/td[2]")
	WebElement pin;

	@FindBy(xpath = "//table[@id='customer']/tbody/tr[12]/td[2]")
	WebElement mobielNo;

	@FindBy(xpath = "//table[@id='customer']/tbody/tr[13]/td[2]")
	WebElement email;
		
	public WebElement CustomerID() {
		return customerID;
	}
	
	public WebElement CustomerName() {
		return cname;
	}
	
	public WebElement gender() {
		return gender;
	}
	
	public WebElement birthday() {
		return birthday;
	}
	
	public WebElement address() {
		return address;
	}
	
	public WebElement state() {
		return state;
	}
	
	public WebElement city() {
		return city;
	}
	
	public WebElement pin() {
		return pin;
	}
	
	public WebElement mobileNo() {
		return mobielNo;
	}
	
	public WebElement email() {
		return email;
	}

	public String getSuccessMsg() {
		return success.getText();
	}

	public WebElement continueLink() {
		return continueLink;
	}

}
