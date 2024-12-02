package seleniumMaven.pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.List;

import org.openqa.selenium.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import seleniumMaven.ObjectRepository;
import seleniumMaven.Utils.DriverUtils;
import seleniumMaven.Utils.FileOps;
import seleniumMaven.Utils.PropertyReaderClass;
import seleniumMaven.Utils.myDriverFactory;
import seleniumMaven.Utils.testBase;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class loginPageTest extends testBase {

	WebDriver driver = myDriverFactory.getDriver();
	ObjectRepository ob = new ObjectRepository();


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

	@Test (priority = -1)
	public void testScreenshots() throws IOException, InterruptedException {
		DriverUtils.openUrl("http://www.google.com");
		DriverUtils.enterKeys(ob.googleSearch, "Selenium");
		DriverUtils.pressEnter(ob.googleSearch);
		DriverUtils.takeSnaps("Screenshots/Screenshot");

		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		Thread.sleep(2000);
		DriverUtils.takeSnaps("Screenshots/Screenshot_full");

		List<WebElement> links = driver.findElements(By.xpath("//a[contains(@href,'http')]"));
		URL url;
		System.out.println("links size: "+links.size());
		HttpURLConnection connection;
		if(!links.isEmpty()){
			for(WebElement el : links){
				System.out.println("el.getAttribute(\"href\"): "+el.getAttribute("href"));
				url = new URL(el.getAttribute("href"));
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				System.out.println("connection.getResponseCode(): "+connection.getResponseCode());
			}
		}
	}

	@Test (priority = 0)
	public void testRelativeLocators() throws IOException, InterruptedException {
		testBase.logger.info(new Throwable().getStackTrace()[0].getMethodName());

		DriverUtils.openUrl(PropertyReaderClass.staticDataPropReader("guru99url"));

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
	}

	@Test (priority = 1)
	public void loginGuru99Demo() throws IOException, InterruptedException {
		testBase.logger.info(new Throwable().getStackTrace()[0].getMethodName());


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
