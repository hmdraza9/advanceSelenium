package seleniumMaven.Utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class myDriverFactory {

	private static WebDriver driver = null;
	private static ChromeOptions co;

	public myDriverFactory() {
		testBase.logger.info(new Throwable().getStackTrace()[0].getMethodName());
//		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	}

	public static WebDriver getDriver() {

		if (driver == null) {

			driver = BrowserInitSetup();
		}

		return driver;
	}

	private static WebDriver BrowserInitSetup() {
		testBase.logger.info(new Throwable().getStackTrace()[0].getMethodName());
		new myDriverFactory();
		co = new ChromeOptions();
//    	co.addArguments("--headless");
		System.out.println("Browser initialization");
		try {
			driver = new ChromeDriver();
		} catch (Exception e) {
			e.printStackTrace();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		return driver;
	}

}
