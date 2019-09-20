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

public class AddCustomerPageTest extends base {

	private static WebDriver driver;
	private static Logger log = LogManager.getLogger(AddCustomerPageTest.class);
	private static String customerID;
	private static ManagerHomePage ManagerHomePage;
	private static AddCustomerPage AddCustomerPage;
	private static CustomerRegPage CustomerRegPage;

	@BeforeTest
	public void launch() throws IOException {
		driver = appLogin();
		ManagerHomePage = new ManagerHomePage(driver);
		ManagerHomePage.newCustomeLink().click();

	}

	@Test(dataProvider = "customerDetails", priority = 1)
	public void verifyAddNewCustomer(String cname, String gender, String dob, String address, String city, String state,
			String pin, String mobileNo, String emailID, String pwd) throws IOException {
		AddCustomerPage = new AddCustomerPage(driver);
		AddCustomerPage.customerName().sendKeys(cname);
		
		if (gender.equals("Male")) {
			
			AddCustomerPage.genderMaleRadioBtn().click();
			
		} else {
		
			AddCustomerPage.genderFemaleRadioBtn().click();
		}

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
		Assert.assertEquals(CustomerRegPage.getSuccessMsg(), "Customer Registered Successfully!!!");

		customerID = CustomerRegPage.CustomerID().getText();
		ExcelUtils.setData("addAccount", 1, 1, customerID);
		ExcelUtils.setData("delete", 1, 1, customerID);
		
		log.info("Test Passed :: Customer ID " + customerID);
		Reporter.log("Test Passed :: Customer ID " + customerID);
		Reporter.log("Test Passed :: Customer Registered Successfully");
		log.info("Test Passed :: Customer Registered Successfully");

	}

	@DataProvider
	public static Object[][] customerDetails() throws IOException {

		Object[][] data = ExcelUtils.getData("addCustomer");

		// String[][] customerDetails = { { "Demo User", "15-06-1990", "Demo address",
		// "Pune", "Maharashtra", "410000",
		// "8900000000", "demo21q7@gsp.com", "@123456" } };
		return data;
	}

	@AfterTest
	public void dismental() {
		teardown();
	}

}
