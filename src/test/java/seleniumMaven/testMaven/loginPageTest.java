package seleniumMaven.testMaven;

import org.testng.annotations.Test;
import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class loginPageTest extends testBase{
	
	
	String url = "http://www.demo.guru99.com/v4/";
	PropertyReaderClass pr;
	
	@AfterTest
	public void afterTesting() {
		BrowserQuit();
	}
	
    @Test
    public void loginGuru99Demo() throws IOException, InterruptedException
    {
    	pr = new PropertyReaderClass();
    	driver.get(url);
    	System.out.println("loginPageTest, Title: "+driver.getTitle());
    	driver.findElement(By.name("uid")).sendKeys(pr.propReader("guru99user"));
    	driver.findElement(By.name("password")).sendKeys(pr.propReader("password"));
    	driver.findElement(By.name("btnLogin")).click();
    	System.out.println("loginPageTest logged in, Title: "+driver.getTitle());
    }

	

	@BeforeTest
	public void configMethod() throws InterruptedException {
    	BrowserInitSetup();
	}
}
