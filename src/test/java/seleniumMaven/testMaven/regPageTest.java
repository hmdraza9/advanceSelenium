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
	public void configMethod() throws InterruptedException {
    	BrowserInitSetup();
	}
	
    @Test
    public void landingPageTest() throws IOException, InterruptedException
    {
    	pr = new PropertyReaderClass();
    	Thread.sleep(5000); 
    	System.out.println("regPageTest, Title: "+driver.getTitle());
    	System.out.println("Size of uid: "+driver.findElements(By.name("uid")).size());
    	System.out.println("Size of password: "+driver.findElements(By.name("password")).size());
    	driver.findElement(By.name("uid")).sendKeys(PropertyReaderClass.staticDataPropReader("guru99user"));
    	driver.findElement(By.name("password")).sendKeys(PropertyReaderClass.dataPropReader("password"));
    	driver.findElement(By.name("btnLogin")).click();
    	System.out.println("regPageTest logged in, Title: "+driver.getTitle());
    	Assert.assertTrue(driver.findElements(By.linkText("Log out")).size()>0);
    	Assert.assertTrue(driver.getTitle().contains(titleText));
    }
}
