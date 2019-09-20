package com.guru99bank.web;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resources.base;

public class ChangePasswordPageTest extends base {

	private static WebDriver driver;
	private static ChangePasswordPage changesPasswordPage;
	private static ManagerHomePage managerHomePage;
	private static Logger log = LogManager.getLogger(ChangePasswordPageTest.class);

	@BeforeTest
	public void launch() throws IOException {
		driver = appLogin();
		changesPasswordPage = new ChangePasswordPage(driver);
		managerHomePage = new ManagerHomePage(driver);
		managerHomePage.changePasswordLink().click();
	}

	@BeforeMethod
	public void clearFileds() {
		changesPasswordPage.oldPassword().clear();
		changesPasswordPage.newPassword().clear();
		changesPasswordPage.confirmPassword().clear();
	}

	@Test(priority = 1)
	public void verifyInocrrectOldPassword() {

		changesPasswordPage.oldPassword().sendKeys("old");
		changesPasswordPage.newPassword().sendKeys("new@1");
		changesPasswordPage.confirmPassword().sendKeys("new@1");
		changesPasswordPage.submit().click();
		Assert.assertEquals(getAlert().getText(), "Old Password is incorrect");
		getAlert().accept();
		log.info("Test Passed :: Verified incorrect old password");
		Reporter.log("Test Passed :: Verified incorrect old password");

	}

	@Test(priority = 2) // Test case failure to check retry logic and screenshot.
	public void verifyUpdatePassword() {
		changesPasswordPage.oldPassword().sendKeys(prop.getProperty("mgrPwd"));
		changesPasswordPage.newPassword().sendKeys(prop.getProperty("mgrPwd"));
		changesPasswordPage.confirmPassword().sendKeys(prop.getProperty("mgrPwd"));
		changesPasswordPage.submit().click();
		String actual = getAlert().getText();
		getAlert().accept();
		Assert.assertEquals(actual, "Password is Changed");
		log.info("Test Passed :: Verified update password");
		Reporter.log("Test Passed :: Verified update password");

	}

	@AfterTest
	public void dismental() {
		teardown();
	}

}
