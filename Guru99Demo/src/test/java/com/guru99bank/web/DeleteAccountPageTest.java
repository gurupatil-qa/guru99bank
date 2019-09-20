package com.guru99bank.web;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import resources.ExcelUtils;
import resources.base;

public class DeleteAccountPageTest extends base {

	private WebDriver driver;
	private ManagerHomePage ManagerHomePage;
	private DeleteAccountPage DeleteAccountPage;
	private Logger log = LogManager.getLogger(DeleteAccountPageTest.class);

	@BeforeTest
	public void launch() throws IOException {
		driver = appLogin();
		ManagerHomePage = new ManagerHomePage(driver);
		ManagerHomePage.deleteAccountLink().click();
		log.info("Navigated to Delete Account Page");
		DeleteAccountPage = new DeleteAccountPage(driver);
	}

	@Test(dataProvider = "accountNo")
	public void verifyDeleteAccountPopUP(String custid, String accountNo) {
		DeleteAccountPage.accouuntNo().clear();
		DeleteAccountPage.accouuntNo().sendKeys(accountNo);
		DeleteAccountPage.submitBtn().click();
		String actualMsg = getAlert().getText();
		Assert.assertEquals(actualMsg, "Do you really want to delete this Account?");
		log.info("Test Passed : Delete this account pop is shown");
		Reporter.log("Test Passed : Delete this account pop is shown");
		getAlert().dismiss();
	}

	@Test(priority = 1, dataProvider = "accountNo")
	public void verifyDeleteAccount(String custid, String accountNo) {
		DeleteAccountPage.accouuntNo().clear();
		DeleteAccountPage.accouuntNo().sendKeys(accountNo);
		DeleteAccountPage.submitBtn().click();
		getAlert().accept();
		Assert.assertEquals(getAlert().getText(), "Account Deleted Sucessfully");
		log.info("Test Passed : Account Deleted Sucessfully");
		Reporter.log("Test Passed : Account Deleted Sucessfully");
		getAlert().accept();
		ManagerHomePage.deleteAccountLink().click();

	}

	@Test(priority = 2, dataProvider = "accountNo")
	public void verifyDeleteAccountDoesNotExistPopUp(String custid, String accountNo) {
		DeleteAccountPage.accouuntNo().clear();
		DeleteAccountPage.accouuntNo().sendKeys(accountNo);
		DeleteAccountPage.submitBtn().click();
		getAlert().accept();
		Assert.assertEquals(getAlert().getText(), "Account does not exist");
		log.info("Test Passed : Account does not exist pop is shown");
		Reporter.log("Test Passed : Account does not exist pop is shown");
		getAlert().accept();

	}

	@DataProvider(name = "accountNo")
	public Object[][] getAccountNo() throws IOException {
		Object[][] data = ExcelUtils.getData("Delete");
		return data;

	}

	@AfterTest
	public void dismental() {
		teardown();
	}
}