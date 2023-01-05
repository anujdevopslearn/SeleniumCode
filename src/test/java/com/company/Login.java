package com.company;

import org.junit.Assert;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.annotations.BeforeTest;  
import org.testng.annotations.Test; 
import org.testng.annotations.AfterTest;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {

    WebDriver driver;
    @BeforeTest
    public void setup(){
		System.setProperty("WebDriver.chrome.driver","/root/SeleniumTest/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
        options.addArguments("--headless");
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        driver = new ChromeDriver(options); //initialise the WebDriver

    }

	@Test
	public void loginTest(){   
		driver.get("https://opensource-demo.orangehrmlive.com/"); //define the url
		String pageTitle = driver.getTitle();       //get the title of the webpage
		System.out.println("The title of this page is ===> " +pageTitle);
		Assert.assertEquals("OrangeHRM", pageTitle);    //verify the title of the webpage
	
	        TakesScreenshot scrShot =((TakesScreenshot)driver);
		//Call getScreenshotAs method to create image file
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		//Move image file to new destination
		File DestFile=new File("/tmp/screenshot.png");
		//Copy file at destination
try{
			FileUtils.copyFile(SrcFile, DestFile);
		}
		catch(Exception exp){
			System.out.println(exp.toString());
		}
		driver.get("https://www.facebook.com/");
		driver.findElement(By.id("email")).sendKeys("anuj_sharma401@yahoo.com");
		driver.findElement(By.id("pass")).sendKeys("");
		driver.findElement(By.xpath("//*[@name='login']")).click();
		System.out.println("Login");
		System.out.println("Successfully logged in");
	}
	
	@AfterTest
	public void teardown(){
		driver.quit();
	}
}
