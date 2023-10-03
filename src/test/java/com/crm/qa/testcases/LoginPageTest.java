//Step 5 - to write test cases for LoginPage after writing code in LoginPage

package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;


//LoginPageTest class is child of TestBase class thus inherianting the properties 
public class LoginPageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage; // no need to initialize here 
	
	//u need LoginPageTest class constructor to call super constructor
	//this constructor will be executed first then it will go to BeforeMethod
	public LoginPageTest(){
		super(); //this super() will call TestBase() class constructor which will initialize/define all the properties at first
	}
	
	// @BeforeMethod, @Test, @AfterMethod are TestNG annotations we are using
	
	// @BeforeMethod launch the browser
	@BeforeMethod
	public void setUp(){
		//after super it will come here and thus u will be saved and will not get null pointer exception as u have already defined ur properties in TestBase class
		initialization();//coming from parent TestBase class which will launch the browser
		loginPage = new LoginPage(); //after this u create obj of LoginPage class to access all the methods here from LoginPage class
	}
	
	//HERE for LOGIN Page we have 3 TestCases so it launch the browser 3 times
	//always make a habit to write Test as prefix or suffix for all Test methods so that it is easily understandable
	@Test(priority=1)
	public void loginPageTitleTest() throws InterruptedException{
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Free CRM software for customer relationship management, sales, and support."); //call assert from testNG not from Junit
		Thread.sleep(3000);
	}
	
	@Test(priority=2)
	public void crmLogoImageTest() throws InterruptedException{
		boolean flag = loginPage.validateCRMImage();
		Assert.assertTrue(flag); //if flag is true ur assertion will pass otherwise it will fail
		Thread.sleep(3000);
	}
	
	@Test(priority=3)
	public void loginTest() throws InterruptedException{
		//login method is returning obj of Homepage thus stored in there
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));  //reading values from config.properties
		Thread.sleep(3000);
	}
	
	
	// @AfterMethod close the browser
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	
	//if u run this test and refresh ur project by default u will get test-output folder in which if u open index.html file u can see the normal reports 
	

}
