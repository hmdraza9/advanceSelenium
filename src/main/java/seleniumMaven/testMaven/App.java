package seleniumMaven.testMaven;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class App 
{
	
	public static WebDriver driver;
	public static ChromeOptions co;
	
    public static void BrowserInitSetup() {
    	co = new ChromeOptions();
//    	co.addArguments("--headless");
    	System.out.println("I am in BaseClass App.java, method: BrowserInitSetup");
    	System.setProperty("webdriver.chrome.driver", "C:/Users/Abdul Hamid Raza/Documents/all-drivers/chromedriver.exe");
		driver = new ChromeDriver(co);
		
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/popup.php");
		
    }

    public void BrowserQuit() {
    	System.out.println("I am in BaseClass App.java, method: BrowserQuit");
    }//AfterClassMethod

    public void AfterClassMethod() {
    	System.out.println("I am in BaseClass App.java, method: AfterClassMethod");
    }//AfterClassMethod //beforeClass

    public void beforeClassMethod() {
    	System.out.println("I am in BaseClass App.java, method: Before ClassMethod");
    }//AfterClassMethod //beforeClass
}
