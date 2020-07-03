package seleniumMaven.testMaven;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class regPageTest extends testBase {
	

	String headerText = "Guru99 Bank";
	String titleText = "Guru99 Bank Manager HomePage";
	PropertyReaderClass pr;


	@AfterTest
	public void afterTesting() {
		BrowserQuit();
	}

	@BeforeTest
	public void configMethod() {
    	BrowserInitSetup();
		System.out.println("I am in registration page.");
	}
	
    @Test
    public void landingPageTest() throws IOException, InterruptedException
    {
    	pr = new PropertyReaderClass();
    	Thread.sleep(5000);
    	driver.findElement(By.name("uid")).sendKeys(pr.propReader("guru99user"));
    	driver.findElement(By.name("password")).sendKeys(pr.propReader("password"));
    	driver.findElement(By.name("btnLogin")).click();  
    	Assert.assertTrue(driver.findElements(By.linkText("Log out")).size()>0);
    	Assert.assertTrue(driver.getTitle().contains(titleText));
    }
}
