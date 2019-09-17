package com.guru99bank.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManagerHomePage {
	public WebDriver driver;

	public ManagerHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//marquee[@class='heading3']")
	@CacheLookup
	WebElement welcomeText;

	@FindBy(xpath = "//tr[@class='heading3']/td")
	@CacheLookup
	WebElement managerID;

	@FindBy(linkText = "Change Password")
	@CacheLookup
	WebElement changePasswordLink;

	@FindBy(linkText = "New Customer")
	@CacheLookup
	WebElement newCustomer;

	@FindBy(linkText = "Edit Customer")
	@CacheLookup
	WebElement editCustomer;

	@FindBy(linkText = "Delete Customer")
	@CacheLookup
	WebElement deleteCustomer;

	@FindBy(linkText = "New Account")
	@CacheLookup
	WebElement newAccount;

	@FindBy(linkText = "Edit Account")
	@CacheLookup
	WebElement editAccount;

	@FindBy(linkText = "Delete Account")
	@CacheLookup
	WebElement deleteAccount;

	@FindBy(linkText = "Log out")
	WebElement logOut;

	public WebElement logoutLink() {
		return logOut;
	}
	
	public WebElement deleteAccountLink() {
		return deleteAccount;
	}

	public WebElement editAccountLink() {
		return editAccount;
	}

	public WebElement newAccountLink() {
		return newAccount;
	}

	public WebElement deleteCustomerLinkk() {
		return deleteCustomer;
	}

	public WebElement editCustomeLink() {
		return editCustomer;
	}

	public WebElement newCustomeLink() {
		return newCustomer;
	}

	public WebElement verifyWelcomeText() {
		return welcomeText;
	}

	public WebElement verifyUserID() {
		return managerID;
	}

	public WebElement changePasswordLink() {
		return changePasswordLink;
	}

}
