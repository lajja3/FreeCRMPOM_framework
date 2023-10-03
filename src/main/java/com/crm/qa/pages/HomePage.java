// Page2 - create Home Page java class 
// after homepage u want to click on contacts page

package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {
    
	/*@FindBy(xpath = "//td[contains(text(),'User: Lajja Patel')]") 
	@CacheLookup
	WebElement userNameLabel;
	this was hard coded code
	*/ 
	
	/*@CacheLookup imp for interview - helps to improve script performance, leads to increase the speed of framework
	//@CacheLookup annotation available in selenium page factory api only
	//it is not recommended to use this annotation for each WebElement
	//only use this for webelement which u think will not change it's value, if webelement changes it's value in future then this annotation will lead to steal script failure error
	//Purpose of this annotation is whereever u define this on pages after @FindBy 
	//meaning it will create one cache memory and store that particular webelement in the memory
	//next time whenever you use that webelement instead of going to webpage and find the webelement it will directly take the value from cache memory
	//this will save us interacting with webpage all the time u call the webelement no need to find the element again and again
	*/
	

	@FindBy(xpath = "//a[contains(text(),'Contacts')]")
	WebElement contactsLink;
	
	//Done in part 4 - we are creating newcontactlink on homepage coz
	//after login u are coming on home page and from there only we are mouse hovering
	//on contacts to see new contact link. we are not clicking on contact here.
	@FindBy(xpath = "//a[contains(text(),'New Contact')]")
	WebElement newContactLink;
	
    // not covering this in video
	@FindBy(xpath = "//a[contains(text(),'Deals')]")
	WebElement dealsLink;

	//not covering this in video
	@FindBy(xpath = "//a[contains(text(),'Tasks')]")
	WebElement tasksLink;

	// Initializing the Page Objects:
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	//Method 1 of HomePage
	//whenever u change the page first thing u have to check is verify the title of the page
	public String verifyHomePageTitle(){
		return driver.getTitle();
	}
	
	/*
	//Method 2 of HomePage - hard coded
	//we are checking the user - name, after logged in
	public boolean verifyCorrectUserName(){
		return userNameLabel.isDisplayed();//isDisplayed() method will return boolean - true or false
	}*/
	
	//Method 2 alternate way
	public String verifyCorrectUser() {
		String userNameLabel = prop.getProperty("User_label");
		System.out.println("logged in user is:" +userNameLabel);
		return userNameLabel;
	}
	
	//Method 3 - click on Contacts
	public ContactsPage clickOnContactsLink(){ //return type of clickOnContactsLink() method is ContactsPage
		contactsLink.click(); //after u click on Contacts what it will do is land on Contacts Page , so next landing page is Contacts Page
		return new ContactsPage(); //returning new ContactsPage() object
	}
	
	/*
	public DealsPage clickOnDealsLink(){
		dealsLink.click();
		return new DealsPage();
	}
	
	public TasksPage clickOnTasksLink(){
		tasksLink.click();
		return new TasksPage();
	}*/
	
	//In video 4 - Method 4 is created to click on new contact link after making it visible from mouse hovering on contacts
	public void clickOnNewContactLink(){
		Actions action = new Actions(driver); // Actions class is from selenium and it is used to do mouse moments like hovering, drag and drop, scroll, right click,etc.
		action.moveToElement(contactsLink).build().perform();//mouse hover to Contacts -> contactsLink is webelement created for Contacts 
		//whenever u use action classes it is compulsory to use .build().perform()
		newContactLink.click(); //click on new contact for newcontact u need one xpath
		//after clicking on newContactLink u have to fill up the form
		//and that form is available on Contacts page thus u have create method in contactsPage.java to fill the form 
	}
	
	//now u gonna create HomePageTest() class to write testcases for this page
	
	
	
	
	

}
