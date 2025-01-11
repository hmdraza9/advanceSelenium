package seleniumMaven.Utils;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class myDriverFactory {

	private static WebDriver driver = null;
	private static ChromeOptions co;
//	WebDriver[] driverBox = new WebDriver[1];

	public myDriverFactory() {
		testBase.logger.info(new Throwable().getStackTrace()[0].getMethodName());
	}

	public static WebDriver getDriver() {

		if (driver == null) {

			driver = BrowserInitSetup();
		}

		System.out.println("Returning driver: "+driver);
		return driver;
	}

	private static WebDriver BrowserInitSetup() {
		testBase.logger.info(new Throwable().getStackTrace()[0].getMethodName());
		co = new ChromeOptions();
        try {
            if(PropertyReaderClass.configPropReader("headless").contentEquals("true")){
    	co.addArguments("--headless");
			}
            System.out.println("Browser initialization");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();


		} catch (Exception e) {
			e.printStackTrace();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		return driver;
	}

}
