//STEP 2 is to create page classes for each pages u want to automate but u don't write any code yet
//Page 1 is Login Page - of FREE CRM 
//once u login with correct cred. u will be on Home Page - of FREE CRM


package com.crm.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	//Page Factory - OR(object repository) is a collection of all the web elements:
	@FindBy(name="username1")
	WebElement username;
	
	@FindBy(name="password1")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement loginBtn;
	
	@FindBy(xpath="//button[contains(text(),'Sign Up')]")
	WebElement signUpBtn;
	
	@FindBy(xpath="//img[contains(@class,'img-responsive')]")
	WebElement crmLogo; //FreeCRM.com logo on LHS of the page
	
	//Initializing the Page Objects: interview quest.(how)
	//create the constructor of this(LoginPage)class 
	//inside the constructor u initialize these elements
	public LoginPage(){
		PageFactory.initElements(driver, this); //PageFactory is class while initElements() is method
		//"this" means current class objects
	}
	
	//Actions:
	
	//Method 1 - will return u title of the login page which will be string
	public String validateLoginPageTitle(){
		return driver.getTitle(); //getTitle() inbuilt method will give title of the login page
	}
	
	//Method 2 - will check if FreeCRM.com logo is present or not
	public boolean validateCRMImage(){
		return crmLogo.isDisplayed();//isDisplayed() method will return true or false if logo is available or not respectively
	}
	
	//Method 3 - will allow u to login into application return type of this method is obj of HomePage class
	public HomePage login(String un, String pwd){
		username.sendKeys(un); // u will pass this values in LoginPageTest class not here
		password.sendKeys(pwd);
		//loginBtn.click();
		    	JavascriptExecutor js = (JavascriptExecutor)driver;
		    	js.executeScript("arguments[0].click();", loginBtn);
		    	
		return new HomePage(); //so this method should return Homepage class object
		//after giving login details we are entering into homepage
		//HomePage is the landing page for LoginPage
//after u click on any link or button in any method then that method should return
//object of the next landing page(after clicking) --- REMEMBER this
//In POM - for each and every page u create separate java class
	}
	
	//after this u will write test cases for LoginPage in LoginPageTest java class
	
}
