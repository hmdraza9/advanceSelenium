package seleniumMaven.pages;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import seleniumMaven.Utils.DriverUtils;
import seleniumMaven.Utils.FileOps;
import seleniumMaven.Utils.PropertyReaderClass;
import seleniumMaven.Utils.myDriverFactory;
import seleniumMaven.Utils.testBase;

public class loginPageTest extends testBase {

	WebDriver driver;

	@AfterTest
	public void AfterTest() {
		System.out.println("new Throwable().getStackTrace()[0].getMethodName(): "
				+ new Throwable().getStackTrace()[0].getMethodName());
		System.out.println("In After test");
	}

	@Test
	public void loginGuru99Demo() throws IOException {
		testBase.logger.info(new Throwable().getStackTrace()[0].getMethodName());

		DriverUtils.openUrl(driver, PropertyReaderClass.staticDataPropReader("guru99url"));
		DriverUtils.enterKeys(myDriverFactory.getDriver().findElement(By.name("uid")),
				PropertyReaderClass.dataPropReader("guru99user"));
		DriverUtils.enterKeys(myDriverFactory.getDriver().findElement(By.name("password")),
				PropertyReaderClass.dataPropReader("guru99password"));
		DriverUtils.click(myDriverFactory.getDriver().findElement(By.name("btnLogin")));
		testBase.logger.info("loginPageTest logged in, Title: " + myDriverFactory.getDriver().getTitle());

	}

	@BeforeTest
	public void BeforeTest() throws InterruptedException, IOException, ParseException {
		testBase.logger
				.info("loginPageTest logged in, Title: " + seleniumMaven.Utils.myDriverFactory.getDriver().getTitle());
		if (PropertyReaderClass.guru99CredsValidator(myDriverFactory.getDriver(), dataFilePath)) {

			String[] guru99NewCreds = PropertyReaderClass.guru99NewRegistration();
			List<String> propList = FileOps.readPropFileAsList(dataFilePath);
			propList.add("guru99user=" + guru99NewCreds[0]);
			propList.add("guru99password=" + guru99NewCreds[1]);
			propList.add("guruCredsDate=" + dateFunction());
			FileOps.savePropNewFile(dataFilePath, propList);

		}
	}

	@AfterSuite
	public void AfterSuite() {
		BrowserQuit();
	}

	@BeforeSuite
	public void BeforeSuite() {
		testBase.logger.info(new Throwable().getStackTrace()[0].getMethodName());
		myDriverFactory.getDriver();
	}
}
