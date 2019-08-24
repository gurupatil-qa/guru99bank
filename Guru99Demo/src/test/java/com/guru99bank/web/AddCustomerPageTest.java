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

import resources.base;

public class AddCustomerPageTest {

	public static WebDriver driver;
	public static Logger log = LogManager.getLogger(AddCustomerPageTest.class);
	public static String customerID;
	public static ManagerHomePage ManagerHomePage;
	public static AddCustomerPage AddCustomerPage;
	public static CustomerRegPage CustomerRegPage;

	@BeforeClass
	public void launch() throws IOException {
		driver = base.setup();
		ManagerHomePage = new ManagerHomePage(driver);
		ManagerHomePage.newCustomeLink().click();

	}

	@Test(dataProvider = "customerDetails")
	public void verifyAddNewCustomer(String cname, String dob, String address, String city, String state, String pin,
			String mobileNo, String emailID, String pwd) {
		AddCustomerPage = new AddCustomerPage(driver);
		AddCustomerPage.customerName().sendKeys(cname);
		AddCustomerPage.genderMaleRadioBtn().click();
		String gender = "male";
		AddCustomerPage.dateOfBirth().sendKeys(dob);
		AddCustomerPage.addressBox().sendKeys(address);
		AddCustomerPage.city().sendKeys(city);
		AddCustomerPage.state().sendKeys(state);
		AddCustomerPage.ppin().sendKeys(pin);
		AddCustomerPage.mobileNumber().sendKeys(mobileNo);
		AddCustomerPage.emailID().sendKeys(emailID);
		AddCustomerPage.password().sendKeys(pwd);
		AddCustomerPage.submitBtn().click();

		CustomerRegPage = new CustomerRegPage(driver);
		if (CustomerRegPage.successMsg().getText().equals("Customer Registered Successfully!!!")) {

			customerID = CustomerRegPage.CustomerID().getText();
			log.info("Test Passed :: Customer ID " + customerID);
			Reporter.log("Test Passed :: Customer ID " + customerID);
			Reporter.log("Test Passed :: Customer Registered Successfully");
			log.info("Test Passed :: Customer Registered Successfully");

			Assert.assertEquals(CustomerRegPage.CustomerName().getText(), cname);
			Assert.assertEquals(CustomerRegPage.gender().getText(), gender);
			Assert.assertEquals(CustomerRegPage.address().getText(), address);
			Assert.assertEquals(CustomerRegPage.city().getText(), city);
			Assert.assertEquals(CustomerRegPage.state().getText(), state);
			Assert.assertEquals(CustomerRegPage.pin().getText(), pin);
			Assert.assertEquals(CustomerRegPage.mobileNo().getText(), mobileNo);
			Assert.assertEquals(CustomerRegPage.email().getText(), emailID);

			Reporter.log("Test Passed :: Customer Details Verified Successfully");
			log.info("Test Passed :: Customer Details Verified Successfully");

		} else {
			Reporter.log("Test Failed :: Customer Registration failed");
			log.info("Test Failed :: Customer Registration Successfully");
		}
	}

	@DataProvider
	public static String[][] customerDetails() {
		String[][] customerDetails = { { "Demo User", "15-06-1990", "Demo address", "Pune", "Maharashtra", "410000",
				"8900000000", "demo21q7@gsp.com", "@123456" } };
		return customerDetails;
	}

	@AfterClass
	public void dismental() {
		base.teardown();
	}

}
