package com.guru99bank.web;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import resources.base;

public class ChangePasswordPageTest {

	public static WebDriver driver;
	public static ChangePasswordPage changesPasswordPage;
	public static ManagerHomePage managerHomePage;
	public static Logger log = LogManager.getLogger(ChangePasswordPageTest.class);

	@BeforeClass
	public void launch() throws IOException {
		driver = base.setup();
	}

	@Test
	public void verifyInocrrectOldPassword() {
		changesPasswordPage = new ChangePasswordPage(driver);
		managerHomePage = new ManagerHomePage(driver);

		managerHomePage.changePasswordLink().click();
		changesPasswordPage.oldPassword().sendKeys("incorrect");
		changesPasswordPage.newPassword().sendKeys("@123456");
		changesPasswordPage.confirmPassword().sendKeys("@123456");
		changesPasswordPage.submit().click();

		try {
			Alert alt = driver.switchTo().alert();
			String alertTile = alt.getText();
			if (alertTile.equals("Old Password is incorrect")) {
				log.info("Test Passed :: Verified incorrect password");
				Reporter.log("Test Passed :: Verified incorrect password");
				alt.accept();

			} else {

				log.error("Test Failed:: Failed for incorrect password");
			}

		} catch (NoAlertPresentException Ex) {
			log.error("Test Failed :: No alert present");
			Reporter.log("Test Failed :: No alert present");
		}
	}

	@Test(priority = 1)
	public void verifyUpdatePassword() {
		changesPasswordPage.oldPassword().sendKeys("@1234567");
		changesPasswordPage.newPassword().sendKeys("@1234567");
		changesPasswordPage.confirmPassword().sendKeys("@1234567");
		changesPasswordPage.submit().click();

		try {
			Alert alt = driver.switchTo().alert();
			String alertTile = alt.getText();
			if (alertTile.equals("Password is Changed")) {
				alt.accept();
				String loginPageTitle = driver.getTitle();
				if (loginPageTitle.equals("Guru99 Bank Home Page")) {
					log.info("Test Passed :: Verified update password");
					Reporter.log("Test Passed :: Verified update password");
				}

			} else {

				log.error("Test Failed:: Update password failed");
			}

		} catch (NoAlertPresentException Ex) {
			log.error("Test Failed :: No alert present");
			Reporter.log("Test Failed :: No alert present");
		}

	}

	@AfterClass
	public void dismental() {
		base.teardown();
	}

}
