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
    	driver.findElement(By.name("uid")).sendKeys(pr.propReader("guru99user"));
    	driver.findElement(By.name("btnReset")).click();
    	driver.findElement(By.name("uid")).sendKeys(pr.propReader("guru99user"));
    	driver.findElement(By.name("password")).sendKeys(pr.propReader("password"));
    	driver.findElement(By.name("btnLogin")).click();   	
    }

	

	@BeforeTest
	public void configMethod() {
    	BrowserInitSetup();
		System.out.println("I am in loginPage page.");
	}
	
    @Test
    public void method2()
    {
        System.out.println("method2");
    }

    @Test
    public void method3()
    {
        System.out.println("method3");
    }

    @Test
    public void method4()
    {
        System.out.println("method4");
    }

    @Test
    public void assertMethod()
    {
        System.out.println("assertMethod");
    }
}
