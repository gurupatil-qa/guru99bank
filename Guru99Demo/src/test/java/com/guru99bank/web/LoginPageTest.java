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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import resources.ExcelUtils;
import resources.base;

public class LoginPageTest {
//Login Page Test
	public static WebDriver driver;
	public static LoginPage loginPage;
	public static Logger log = LogManager.getLogger(LoginPageTest.class);

	@BeforeClass
	public void Launch() throws IOException {
		driver = base.initializeDriver();
		driver.get(base.prop.getProperty("baseurl"));
		log.info("Browser initialized");
		Reporter.log("Browser initialized and rediredcted to page successfully");

	}

	@Test(dataProvider = "getData")
	public void verifyLogin(String uname, String pwd) throws IOException {
		loginPage = new LoginPage(driver);
		loginPage.userID().sendKeys(uname);
		log.info("UserID entered");
		loginPage.password().sendKeys(pwd);
		log.info("Password entered");
		loginPage.loginBtn().click();
		log.info("Clicked on login button");

		try {
			Alert alt = driver.switchTo().alert();
			String alertTile = alt.getText();
			if (alertTile.equalsIgnoreCase("User or Password is not valid")) {
				log.info("Test Passed :: Verified login for invalid user/password");
				Reporter.log("Test Passed :: Verified login for invalid user/password");
				alt.accept();

			} else {

				log.error("Test Failed :: Verified login for invalid user/password");
			}
		} catch (NoAlertPresentException Ex) {

			ManagerHomePage HP = new ManagerHomePage(driver);

			String heading3 = HP.verifyUserID().getText();

			if (heading3.contains(uname)) {

				Reporter.log("Test Passed :: Verified Login for valid user/password");

				log.info("Test Passed :: Verified Login for valid user/password");
				base.getScreenshot("verifyLogin");
				loginPage.logOut.click();
				driver.switchTo().alert().accept();
				driver.get(base.prop.getProperty("baseurl"));

			} else {

				log.error("Test Failed :: Verified Login for Vaild user/password");
			}

		}

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		Object[][] data = ExcelUtils.readExcel(0);

		// Object[][] data=
		// {{"mngr212524","@1234567"},{"abc","ubAtYrE"},{"mngr212524","abc"},{"abc","abc"}};

		return data;

	}

	@AfterClass
	public void dismental() {
		base.teardown();
	}

}
