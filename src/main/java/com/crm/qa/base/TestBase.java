//STEP 3 - create TestBase class as a parent class
// It is the parent class of all the classes in framework
// In this class u can initialize ur driver and properties
// U can define all the properties in the class -> eg maximizeWindow(), pageloadTimeout(), implicitWait(), deletedAllCookies(), driver.get(url)...
// Inheritance concept - All the child classes will be inherianting the properties of parent class meaning all the methods/variables declared in parent class are applicable to the child class as well.

package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {
	
	//creating global variables
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver; //create obj after adding web event listener
	public static WebEventListener eventListener; //create this obj after adding web event listener class
	
	//firstly here u need to create test base class constructor
	public TestBase(){
		//here u gonna read from properties file
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:\\Users\\Lajja Patel\\OneDrive\\Desktop\\MySelenium57\\Cucumber\\CucumberWorkspace\\FreeCrmPOMFramework\\src\\main\\java\\com\\crm\\qa\\config\\config.properties"); //give path of ur properties file here
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//secondly here u will create initialization method
	public static void initialization(){
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "C:/Users/Lajja Patel/OneDrive/Desktop/MySelenium57/SeleniumJars/chromedriver.exe");	
			driver = new ChromeDriver(); //with my chrome it says chrome version 96 is supported my chrome is not working
		}
		else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", "C:/Users/Lajja Patel/OneDrive/Desktop/MySelenium57/Cucumber/CucumberWorkspace/geckodriver.exe");	
			driver = new FirefoxDriver(); 
		}
		
		//add these changes after adding web event listener class
		//remember this 4 lines of code to get proper console logs from selenium
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		//this was done previously
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		//here comes step4 to create TestUtil class 
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url")); //fetch FreeCRM url from config.properties file
		//after these create relationship between parent and child classes
		// use extends TestBase on each child class
		//now go to 1.Login Page and start working on the code
	}
	
}
