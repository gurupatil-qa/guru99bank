package com.guru99bank.web;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChangePasswordPage {
	WebDriver driver;

	public ChangePasswordPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@name='oldpassword']")
	WebElement oldpassword;

	@FindBy(css = "input[name='newpassword']")
	WebElement newpassword;

	@FindBy(xpath = "//input[@name='confirmpassword']")
	WebElement confirmpassword;

	@FindBy(xpath = "//input[@value='Submit']")
	WebElement submit;

	@FindBy(css = "input[value='Reset']")
	WebElement reset;

	public WebElement oldPassword() {
		return oldpassword;
	}

	public WebElement newPassword() {
		return newpassword;
	}

	public WebElement confirmPassword() {
		return confirmpassword;
	}
	
	public WebElement submit()
	{
		return submit;
	}
	
	public WebElement reset()
	{
		return reset;
	}
	
}
