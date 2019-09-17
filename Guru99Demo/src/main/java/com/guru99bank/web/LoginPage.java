package com.guru99bank.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "uid")
	WebElement userId;

	public WebElement userID() {
		return userId;
	}

	@FindBy(name = "password")
	WebElement password;

	public WebElement password() {
		return password;
	}

	@FindBy(name = "btnLogin")
	WebElement login;

	public WebElement loginBtn() {
		return login;
	}

	@FindBy(id = "message23")
	WebElement userMsg;

	public String getUserMsg() {
		return userMsg.getText();
	}

	@FindBy(id = "message18")
	WebElement pwdMsg;

	public String getPasswordMsg() {
		return pwdMsg.getText();
	}

	@FindBy(name = "btnReset")
	WebElement restBtn;
	
	public WebElement resetBtn()
	{
		return restBtn;
	}

}
