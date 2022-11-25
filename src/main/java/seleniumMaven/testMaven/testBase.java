package seleniumMaven.testMaven;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class testBase {

	public static WebDriver driver;
	public static ChromeOptions co;
	public static PropertyReaderClass pr;
	public static String classPath = System.getProperty("user.dir");

	public static void BrowserInitSetup() throws InterruptedException {
		co = new ChromeOptions();
//    	co.addArguments("--headless");
		System.out.println("Browser initialization");
		System.setProperty("webdriver.chrome.driver", "C:/Users/hmdra_gnp5/Documents/all-drivers/chromedriver.exe");
		driver = new ChromeDriver(co);

		driver.manage().window().maximize();

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
		SimpleDateFormat sdfDate = new SimpleDateFormat("ddMMyyyy");
		Date now = new Date();
		return sdfDate.format(now);
	}
}
