package seleniumMaven.testMaven;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class testBase 
{
	
	public static WebDriver driver;
	public static ChromeOptions co;
	public static PropertyReaderClass pr;
	
    public static void BrowserInitSetup() throws InterruptedException {
    	co = new ChromeOptions();
//    	co.addArguments("--headless");
    	System.out.println("Browser initialization");
    	System.setProperty("webdriver.chrome.driver", "C:/Users/Abdul Hamid Raza/Documents/all-drivers/chromedriver.exe");
		driver = new ChromeDriver(co);
		
		driver.manage().window().maximize();
		driver.get("http://www.demo.guru99.com/v4/");
    	Thread.sleep(2000);
		
    }

    public void BrowserQuit() {
    	System.out.println("Browser closing");
    	driver.quit();
    }//AfterClassMethod

    public void AfterClassMethod() {
    	System.out.println("I am in BaseClass App.java, method: AfterClassMethod");
    }//AfterClassMethod //beforeClass

    public void beforeClassMethod() {
    	System.out.println("I am in BaseClass App.java, method: Before ClassMethod");
    }//AfterClassMethod //beforeClass
}
