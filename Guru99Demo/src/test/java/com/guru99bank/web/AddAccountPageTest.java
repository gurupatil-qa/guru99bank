package com.guru99bank.web;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import resources.ExcelUtils;
import resources.base;

public class AddAccountPageTest extends base {

	public static WebDriver driver;
	public static ManagerHomePage ManagerHomePage;
	public static AddAccountPage AddAccountPage;
	public static AccountGeneratedPage AccountGeneratedPage;
	public static String accountID;
	public static Logger log = LogManager.getLogger(AddAccountPageTest.class);

	@BeforeClass
	public void launch() throws IOException {
		driver = setup();
		ManagerHomePage = new ManagerHomePage(driver);
		ManagerHomePage.newAccountLink().click();
	}

	@Test(dataProvider = "accountDetails")
	public void verifyAddNewAccount(String custid, String actType, String depositAmt) {
		AddAccountPage = new AddAccountPage(driver);
		AddAccountPage.customerID().sendKeys(custid);
		try {
			AddAccountPage.accouuntType().selectByValue(actType);
		} catch (Exception e) {
			Reporter.log("Provide valid Account Type...");
			log.error("Provide valid Account Type...");
		}
		AddAccountPage.initialDeposit().sendKeys(depositAmt);
		AddAccountPage.submitBtn().click();

		AccountGeneratedPage = new AccountGeneratedPage(driver);
		Assert.assertEquals(AccountGeneratedPage.getAccountSuccessMsg(), "Account Generated Successfully!!!");

	}

	@DataProvider
	public static Object[][] accountDetails() throws IOException {

		Object[][] data = ExcelUtils.getData("addAccount");
		// String[][] data = { { "38911", "Savings", "10000" } };
		return data;

	}

	@AfterClass
	public void dismental() {
		teardown();
	}

}
