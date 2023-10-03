//Page 3 - create ContactsPage java class
//similarly u can create other pages too like deals, taskspage etc.
//so if u have 100 pages in ur appliaction u have to crate 100 java classes - one to one mapping

package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase {

	@FindBy(xpath = "//td[contains(text(),'Contacts')]")
	WebElement contactsLabel; //used for method 1
	
	@FindBy(id="first_name")
	WebElement firstName; //used for method 3
	
	@FindBy(id="surname")
	WebElement lastName; //used for method 3
	
	@FindBy(name="client_lookup")
	WebElement company; //used for method 3
	
	@FindBy(xpath = "//input[@type='submit' and @value='Save']")
	WebElement saveBtn; //used for method 3
	
	
	
	// Initializing the Page Objects:
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
	
	//here after clicking on contacts title of the page is not changing it is same as homepagetitle
	
	//Method 1 - Contacts label (the one on purple bar) is displayed or not
	public boolean verifyContactsLabel(){
		return contactsLabel.isDisplayed();
	}
	
	//Method 2 - selecting contacts from checkbox- this method is not returning anything it just simply selecting the contact
	//refer how to become genius in xpath tutorial for learning dynamic xpath that is use to select from checkbox
	//also we are not using any page factory for this method coz we want to give different value for name not like fixed value
	public void selectContactsByName(String name){
		driver.findElement(By.xpath("//a[text()='"+name+"']//parent::td[@class='datalistrow']"
				+ "//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']")).click();
	}
	//now create ContactsPageTest class
	
	
	//Method 3 - not used before video 4
	//Video 4 - https://www.youtube.com/watch?v=H2-3w-GQZ3g&list=PLFGoYjJG_fqq6cHeqfsDes3pdVh3kpl74&index=4
	//data driven approach - we will mouse hover on Contacts and click on new contact link
	//then we will create new contact and data will be passed from excel file
	//our data excel file is created in src/main/java -> com.crm.qa.testdata -> FreeCrmTestData.xlsx
	public void createNewContact(String title, String ftName, String ltName, String comp) throws InterruptedException{
		Select select = new Select(driver.findElement(By.name("title"))); //we are not creating page factory for title here it's not always compulsory to create Pagefactory for webelements certain webelement can be defined whereever needed
		select.selectByVisibleText(title); //title is dropdown thus we need select class for dropdown
		Thread.sleep(1000);
		firstName.sendKeys(ftName);
		Thread.sleep(1000);
		lastName.sendKeys(ltName);
		Thread.sleep(1000);
		company.sendKeys(comp);
		Thread.sleep(1000);
		saveBtn.click();
		Thread.sleep(1000);
	}//now create the test case for this method in ContactsPageTest.java file
	
	
	

}
