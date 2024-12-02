package seleniumMaven.pages;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
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

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class loginPageTest extends testBase {

	WebDriver driver = myDriverFactory.getDriver();

	@AfterTest
	public void AfterTest() {
		System.out.println("new Throwable().getStackTrace()[0].getMethodName(): "
				+ new Throwable().getStackTrace()[0].getMethodName());
		System.out.println("In After test");
	}

	@BeforeTest
	public void BeforeTest() throws InterruptedException, IOException, ParseException {
		testBase.logger
				.info("loginPageTest logged in, Title: " + driver.getTitle());
		if (!PropertyReaderClass.isGuru99CredsValid(driver, dataFilePath)) {


			String[] guru99NewCreds = PropertyReaderClass.guru99NewRegistration();
			List<String> propList = FileOps.readPropFileAsList(dataFilePath);
			propList.add("guru99user=" + guru99NewCreds[0]);
			propList.add("guru99password=" + guru99NewCreds[1]);
			propList.add("guruCredsDate=" + dateTimeFunction("dd-MM-yyyy"));
			FileOps.savePropNewFile(dataFilePath, propList);

		}
	}

	@Test
	public void loginGuru99Demo() throws IOException, InterruptedException {
		testBase.logger.info(new Throwable().getStackTrace()[0].getMethodName());

		DriverUtils.openUrl(driver, PropertyReaderClass.staticDataPropReader("guru99url"));

		//relative locator - below
		driver.findElement(with(By.xpath("//td/input[not(@type='submit' or @type='reset')]"))
				.below(By.xpath("//*[@onkeyup='validateuserid();']"))).sendKeys("testtttttttttttt");
		Thread.sleep(1234);
		driver.findElement(with(By.xpath("//td/input[not(@type='submit' or @type='reset')]"))
				.below(By.xpath("//*[@onkeyup='validateuserid();']"))).clear();
		Thread.sleep(1234);
		Thread.sleep(1234);

		//relative locator - above
		driver.findElement(with(By.xpath("//input[contains(@onkeyup,'validate')]"))
				.above(By.xpath("//input[contains(@onkeyup,'validatepassword()')]"))).sendKeys("testtttttttttttt");
		Thread.sleep(1234);
		driver.findElement(with(By.xpath("//input[contains(@onkeyup,'validate')]"))
				.above(By.xpath("//input[contains(@onkeyup,'validatepassword()')]"))).clear();
		Thread.sleep(1234);
		Thread.sleep(1234);

		//relative locator - toLeftOf
		System.out.println(driver.findElement(with(By.xpath("//td/input"))
				.toLeftOf(By.name("btnReset"))).getAttribute("value"));//LOGIN
		Thread.sleep(1234);
		Thread.sleep(1234);

		//relative locator - toRightOf
		System.out.println(driver.findElement(with(By.xpath("//td/input"))
				.toRightOf(By.name("btnLogin"))).getAttribute("value"));//RESET
		Thread.sleep(1234);
		Thread.sleep(1234);

		//relative locator - near
		System.out.println(driver.findElement(with(By.xpath("//td/input"))
				.near(By.name("btnLogin"))).getAttribute("value"));//RESET
		Thread.sleep(1234);
		Thread.sleep(1234);

		//relative locator - chaining 1
		System.out.println(driver.findElement(
				with(By.xpath("//td/input"))
						.near(By.name("btnLogin"))
						.below(By.xpath("//input[contains(@onkeyup,'validateuserid()')]"))).getAttribute("type"));//password
		Thread.sleep(1234);
		Thread.sleep(1234);

		//relative locator - chaining 2
		System.out.println(driver.findElement(
				with(By.xpath("//td/input"))
						.near(By.name("btnLogin"))
						.above(By.xpath("//input[contains(@onkeyup,'validatepassword()')]"))).getAttribute("onkeyup"));//validateuserid();
		Thread.sleep(1234);
		Thread.sleep(1234);

		DriverUtils.enterKeys(driver.findElement(By.name("uid")),
				PropertyReaderClass.dataPropReader("guru99user"));
		DriverUtils.enterKeys(driver.findElement(By.name("password")),
				PropertyReaderClass.dataPropReader("guru99password"));
        DriverUtils.click(driver.findElement(By.name("btnLogin")));
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
