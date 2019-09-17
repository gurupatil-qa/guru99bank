package com.guru99bank.web;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import resources.ExcelUtils;
import resources.base;

public class DeleteAccountPageTest extends base{

	public WebDriver driver;
	public ManagerHomePage ManagerHomePage;
	public DeleteAccountPage DeleteAccountPage;
	public Alert alert;
	public Logger log = LogManager.getLogger(DeleteAccountPageTest.class);

	@BeforeClass
	public void launch() throws IOException {
		driver = setup();
		ManagerHomePage = new ManagerHomePage(driver);
		ManagerHomePage.deleteAccountLink().click();
		DeleteAccountPage = new DeleteAccountPage(driver);
	}

	@BeforeMethod
	public void clickOnDelete() {	
		DeleteAccountPage.accouuntNo().clear();
		DeleteAccountPage.accouuntNo().sendKeys("66932");
		DeleteAccountPage.submitBtn().click();
		log.info("Navigated to Delete Account Page");
	}

	@Test
	public void verifyDeleteAccountPopUP() {
		try {
			alert = getAlert();
			String actualMsg = alert.getText();
			Assert.assertEquals(actualMsg, "Do you really want to delete this Account?");
			log.info("Test Passed : Delete this account pop is shown");
			Reporter.log("Test Passed : Delete this account pop is shown");
			alert.dismiss();
		} catch (NoAlertPresentException e) {
			log.info("Test Failed : " + e.toString());
			Reporter.log("Test Passed : " + e.toString());
		} catch (UnhandledAlertException e) {
			log.info("Test Failed : " + e.getMessage());
			Reporter.log("Test Passed : " + e.getMessage());
		}
	}

	@Test(priority = 1)
	public void verifyDeleteAccount() {
		alert.accept();
		try {
			alert = getAlert();
			String actualMsg = alert.getText();
			Assert.assertEquals(actualMsg, "Account Deleted Sucessfully");
			log.info("Test Passed : Account Deleted Sucessfully");
			Reporter.log("Test Passed : Account Deleted Sucessfully");
			alert.accept();
			ManagerHomePage.deleteAccountLink().click();
		} catch (NoAlertPresentException e) {
			log.info("Test Failed : " + e.getMessage());
			Reporter.log("Test Passed : " + e.getMessage());
		} catch (UnhandledAlertException e) {
			log.info("Test Failed : " + e.getMessage());
			Reporter.log("Test Passed : " + e.getMessage());
		}
	}

	@Test(priority = 2)
	public void verifyDeleteAccountDoesNotExistPopUp() {
		alert.accept();
		try {
			alert = getAlert();
			String actualMsg = alert.getText();
			Assert.assertEquals(actualMsg, "Account does not exist");
			log.info("Test Passed : Account does not exist pop is shown");
			Reporter.log("Test Passed : Account does not exist pop is shown");
			alert.accept();
		} catch (NoAlertPresentException e) {
			log.info("Test Failed : " + e.getMessage());
			Reporter.log("Test Passed : " + e.getMessage());
		} catch (UnhandledAlertException e) {
			log.info("Test Failed : " + e.getMessage());
			Reporter.log("Test Passed : " + e.getMessage());
		}
	}

	@Test(enabled = false)
	public void verifyDeleteAccountUnauthorizedPopUp() {
		try {
			alert = getAlert();
			String actualMsg = alert.getText();
			Assert.assertEquals(actualMsg, "You are not authorize to delete this Account!!");
			log.info("Test Passed : Not authorize to delete this account pop is shown");
			Reporter.log("Test Passed : Not authorize to delete this account pop is shown");
		} catch (NoAlertPresentException e) {
			log.info("Test Failed : " + e.toString());
			Reporter.log("Test Failed : " + e.toString());
		} catch (UnhandledAlertException e) {
			log.info("Test Failed : " + e.getMessage());
			Reporter.log("Test Passed : " + e.getMessage());
		}
	}
	
	@DataProvider(name="accountNo")
	public String getAccountNo() throws IOException
	{
		ExcelUtils.getData("Delete");
		return null;
		
	}

	@AfterClass
	public void dismental() {
		teardown();
	}
}