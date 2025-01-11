package seleniumMaven.Utils;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class myDriverFactory {

	private static ChromeOptions co;
	private static WebDriver[] driverBox = new WebDriver[1];

	public myDriverFactory() {
		testBase.logger.info(new Throwable().getStackTrace()[0].getMethodName());
	}

	public static WebDriver getDriver() {

		System.out.println("Existing driver : "+driverBox[0]);
		if (driverBox[0]==null) {

			System.out.println("Driver is null : "+driverBox[0]);
			driverBox[0] = BrowserInitSetup();
		}

		System.out.println("Returning driver: "+driverBox[0]);
		return driverBox[0];
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
				if(driverBox[0]==null){
					WebDriverManager.chromedriver().setup();
					driverBox[0] = new ChromeDriver();
				}


		} catch (Exception e) {
			e.printStackTrace();
		}

		driverBox[0].manage().window().maximize();
		driverBox[0].manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		return driverBox[0];
	}

}
