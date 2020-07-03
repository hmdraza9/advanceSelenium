package seleniumMaven.testMaven;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class loginPageTest extends App{

	@AfterTest
	public void afterTesting() {
		BrowserQuit();
	}
	
    @Test
    public void method1()
    {
        System.out.println("method1");
    }

	
    @Test
    public void afterMethodQuit()
    {
        System.out.println("afterMethodQuit");
        driver.quit();
    }

	@BeforeTest
	public void configMethod() {
    	BrowserInitSetup();
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
