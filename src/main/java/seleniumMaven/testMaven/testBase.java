package seleniumMaven.testMaven;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class testBase {
	


	public static WebDriver driver;
	public static ChromeOptions co;
	public static PropertyReaderClass pr;
	public static String classPath = System.getProperty("user.dir");

	public static String filePath = classPath + "\\Data\\dataFile.properties";

	public static WebDriver BrowserInitSetup() throws InterruptedException {
		co = new ChromeOptions();
//    	co.addArguments("--headless");
		System.out.println("Browser initialization");
		System.setProperty("webdriver.chrome.driver", "C:/Users/hmdra_gnp5/Documents/all-drivers/chromedriver.exe");
		driver = new ChromeDriver(co);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		return driver;

	}

	public void BrowserQuit() {
		System.out.println("Browser closing");
		driver.quit();
	}// AfterClassMethod

	public void AfterClassMethod() {
		System.out.println("I am in BaseClass App.java, method: AfterClassMethod");
	}// AfterClassMethod //beforeClass

	public void beforeClassMethod() {
		System.out.println("I am in BaseClass App.java, method: Before ClassMethod");
	}// AfterClassMethod //beforeClass

	public static String dateTimeFunction() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("ddMMyyyyhhmmss");
		Date now = new Date();
		return sdfDate.format(now);
	}

	public static String dateFunction() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");
		Date now = new Date();
		return sdfDate.format(now);
	}
}
