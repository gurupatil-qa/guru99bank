package com.guru99bank.web;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import resources.base;

public class AddAccountPageTest {

	public static WebDriver driver;
	public static ManagerHomePage ManagerHomePage;
	public static AddAccountPage AddAccountPage;
	public static AccountGeneratedPage AccountGeneratedPage;
	public static String accountID;
	public static Logger log = LogManager.getLogger(AddAccountPageTest.class);

	@BeforeClass
	public void launch() throws IOException {
		driver = base.setup();
		ManagerHomePage = new ManagerHomePage(driver);
		ManagerHomePage.newAccountLink().click();
	}

	@Test(dataProvider = "accountDetails")
	public void verifyAddNewAccount(String custid, String actType, String depositAmt) {
		AddAccountPage = new AddAccountPage(driver);
		AddAccountPage.customerID().sendKeys(custid);
		AddAccountPage.accouuntType().selectByValue(actType);
		AddAccountPage.initialDeposit().sendKeys(depositAmt);
		AddAccountPage.submitBtn().click();

		AccountGeneratedPage = new AccountGeneratedPage(driver);
		if (AccountGeneratedPage.accountSuccessMsg().getText().equals("Account Generated Successfully!!!")) {
			accountID = AccountGeneratedPage.accountID.getText();
			log.info("Test Passed :: Account ID " + accountID);
			Reporter.log("Test Passed :: Account ID " + accountID);
			Reporter.log("Test Passed :: Account Generated Successfully");
			log.info("Test Passed :: Account Generated Successfully");
		} else {
			Reporter.log("Test Failed :: Account Generation failed");
			log.info("Test Failed :: Account Generation Successfully");
		}
	}

	@DataProvider
	public static String[][] accountDetails() {
		String[][] data = { { "38911", "Savings", "10000" } };
		return data;
		// custid-91034
	}

	@AfterClass
	public void dismental() {
		base.teardown();
	}

}
