package com.crm.qa.testcases;

//ctrl+shift+o shortcut to import automatically 
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase {
	LoginPage loginPage; //we need this obj to login to the application first-time
	HomePage homePage;
	TestUtil testUtil; //u need this obj to switch to frame 
	ContactsPage contactsPage;

	public HomePageTest() {
		super();
	}

	//test cases should be separated -- independent with each other - meaning each time u open and close browser for each test
	//before each test case -- launch the browser and login
	//@test -- execute test case
	//after each test case -- close the browser - this the best practice otherwise many problems can occur if u don't close ur browser
	
//in this before method we are checking homepage so to check that page we need to login to the application first thus login part will come in @BeforeMethod
	@BeforeMethod
	public void setUp() {
		initialization();//launch the browser
		testUtil = new TestUtil();//this obj will allow u to access the methods of TestUtil class here
		contactsPage = new ContactsPage();
		loginPage = new LoginPage();//this obj will allow u to access the methods of LoginPage class here
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password")); // prop is from TestBase class ie using the inheritance concept here
	    //login method is returning homepage obj thus it is stored in homepage obj
	}
	
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() throws InterruptedException{
		Thread.sleep(3000);
		String homePageTitle = homePage.verifyHomePageTitle();
		Thread.sleep(5000);
		Assert.assertEquals(homePageTitle, "CRMPRO","Home page title not matched"); //string msg will be displayed at a time of reporting when ur assertion will fail 
		Thread.sleep(3000);
	}
	
	@Test(priority=2)
	public void verifyUserNameTest() throws InterruptedException{
		Thread.sleep(2000);
		// if u don't do this ur code will fail
		testUtil.switchToFrame(); //before username is displayed u have to switch to frame
		Thread.sleep(3000);
		//Assert.assertTrue(homePage.verifyCorrectUserName()); //naveen's way
		String user = homePage.verifyCorrectUser();
		Assert.assertEquals(user, "Lajja Patel", "incorrect user logged in");
		Thread.sleep(2000);
	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest() throws InterruptedException{
		Thread.sleep(2000);
		testUtil.switchToFrame();//contacts is also available on frame so we have to switch to frame first
		Thread.sleep(3000);
		contactsPage = homePage.clickOnContactsLink();//clickOnContactsLink() method returns obj of contacts page
		Thread.sleep(2000);
		//click on contacts and close the browser
	}
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	//after this u will write code for Contacts Page

}
