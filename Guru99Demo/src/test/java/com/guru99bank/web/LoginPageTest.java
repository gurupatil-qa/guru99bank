package com.guru99bank.web;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import resources.ExcelUtils;
import resources.base;

public class LoginPageTest extends base {

	public static WebDriver driver;
	public static LoginPage loginPage;
	public static ManagerHomePage HP;
	public static Logger log = LogManager.getLogger(LoginPageTest.class);

	@BeforeClass
	public void Launch() throws IOException {
		driver = initializeDriver();
		driver.get(prop.getProperty("baseurl"));
		log.info("Browser initialized");
		Reporter.log("Browser initialized and rediredcted to page successfully");

	}

	@Test(priority = 1)
	public void validateUserIDRequiredMessage() {
		loginPage = new LoginPage(driver);
		loginPage.userID().sendKeys(Keys.TAB);
		String msg = loginPage.getUserMsg();
		Assert.assertEquals(msg, "User-ID must not be blank");
	}

	@Test(priority = 2)
	public void validatePasswordRequiredMessage() {
		loginPage.password().sendKeys(Keys.TAB);
		String msg = loginPage.getPasswordMsg();
		Assert.assertEquals(msg, "Password must not be blank");
	}

	@Test(priority = 3)
	public void validateResetBtn() {
		loginPage.userID().sendKeys(prop.getProperty("mgrID"));
		loginPage.password().sendKeys(prop.getProperty("mgrPwd"));
		loginPage.resetBtn().click();
		Assert.assertEquals(loginPage.userID().getAttribute("value"), "");
		Assert.assertEquals(loginPage.password().getAttribute("value"), "");
	}

	@Test(dataProvider = "getData", priority = 4)
	public void verifyInvalidLogin(String uname, String pwd) throws IOException {
		loginPage.userID().sendKeys(uname);
		log.info("UserID entered");
		loginPage.password().sendKeys(pwd);
		log.info("Password entered");
		loginPage.loginBtn().click();
		log.info("Clicked on login button");

		try {
			String alertTile = getAlert().getText();
			Assert.assertEquals(alertTile, "User or Password is not valid");
			log.info("Test Passed :: Verified login for invalid user/password");
			Reporter.log("Test Passed :: Verified login for invalid user/password");
			getAlert().accept();
		} catch (NoAlertPresentException Ex) {

			log.info("Test Failed :: invalid user/password");
			Reporter.log("Test Failed :: invalid user/password");
		}
	}

	@Test(priority = 5)
	public void verifNullValueLogin() {
		loginPage.loginBtn().click();
		try {
			String alertTile = getAlert().getText();
			Assert.assertEquals(alertTile, "User or Password is not valid");
			getAlert().accept();
		} catch (NoAlertPresentException Ex) {

			log.info("Test Failed :: invalid user/password");
			Reporter.log("Test Failed :: invalid user/password");
		}
	}

	@Test(priority = 6)
	public void verifyValidLogin() {

		loginPage.userID().sendKeys(prop.getProperty("mgrID"));
		log.info("UserID entered");
		loginPage.password().sendKeys(prop.getProperty("mgrPwd"));
		log.info("Password entered");
		loginPage.loginBtn().click();
		log.info("Clicked on login button");
		HP = new ManagerHomePage(driver);
		String heading3 = HP.verifyUserID().getText();
		Assert.assertEquals(heading3, "Manger Id : " + prop.getProperty("mgrID"));
		Reporter.log("Test Passed :: Verified Login for valid user/password");
		log.info("Test Passed :: Verified Login for valid user/password");

	}

	@Test(priority = 7)
	public void verifyLogout() {

		HP.logOut.click();
		Assert.assertEquals(getAlert().getText(), "You Have Succesfully Logged Out!!");
		getAlert().accept();

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		Object[][] data = ExcelUtils.getData("login");

		// Object[][] data=
		// {{"mngr212524","@1234567"},{"abc","ubAtYrE"},{"mngr212524","abc"},{"abc","abc"}};

		return data;

	}

	@AfterClass
	public void dismental() {
		teardown();
	}

}
