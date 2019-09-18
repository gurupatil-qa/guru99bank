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

public class AddCustomerPageTest extends base {

	public static WebDriver driver;
	public static Logger log = LogManager.getLogger(AddCustomerPageTest.class);
	public static String customerID;
	public static ManagerHomePage ManagerHomePage;
	public static AddCustomerPage AddCustomerPage;
	public static CustomerRegPage CustomerRegPage;

	@BeforeClass
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

	@AfterClass
	public void dismental() {
		teardown();
	}

}
