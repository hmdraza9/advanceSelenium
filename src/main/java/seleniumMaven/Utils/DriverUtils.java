package seleniumMaven.Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;

public class DriverUtils extends testBase {

	public static WebDriver driver = myDriverFactory.getDriver();

	public static void click(WebElement el) {


		testBase.logger.info("new Throwable().getStackTrace()[0].getMethodName(): "
				+ new Throwable().getStackTrace()[0].getMethodName());
		try {
			el.click();
		} catch (Exception e) {
			testBase.logger.info("Closing driver");
			driver.quit();
		}

	}

	public static void click(String xpath) {


		testBase.logger.info("new Throwable().getStackTrace()[0].getMethodName(): "
				+ new Throwable().getStackTrace()[0].getMethodName());
		WebElement el = driver.findElement(By.xpath(xpath));
		try {
			el.click();
		} catch (Exception e) {
			testBase.logger.info("Closing driver");
			driver.quit();
		}

	}

	public static void enterKeys(WebElement el, String str) {

		testBase.logger.info("new Throwable().getStackTrace()[0].getMethodName(): "
				+ new Throwable().getStackTrace()[0].getMethodName());
		try {
			el.sendKeys(str);
		} catch (Exception e) {
			System.out.println("Closing driver");
			driver.quit();
		}

	}

	public static void enterKeys(String xpath, String value) {

		testBase.logger.info("new Throwable().getStackTrace()[0].getMethodName(): "
				+ new Throwable().getStackTrace()[0].getMethodName());
		WebElement el = driver.findElement(By.xpath(xpath));
		try {
			el.sendKeys(value);
		} catch (Exception e) {
			System.out.println("Closing driver");
			driver.quit();
		}

	}

	public static void takeSnaps(String path) throws IOException {
		File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scr, new File(path));
	}

	public static void pressEnter(String xpath) {

		testBase.logger.info("new Throwable().getStackTrace()[0].getMethodName(): "
				+ new Throwable().getStackTrace()[0].getMethodName());
		WebElement el = driver.findElement(By.xpath(xpath));
		try {
			el.submit();
		} catch (Exception e) {
			System.out.println("Closing driver");
			driver.quit();
		}

	}

	public static String getLabel(WebElement el) {

		testBase.logger.info("new Throwable().getStackTrace()[0].getMethodName(): "
				+ new Throwable().getStackTrace()[0].getMethodName());
		String textLabel = "";
		try {
			textLabel = el.getText().toString().trim();
		} catch (Exception e) {
			System.out.println("Closing driver");
			driver.quit();
		}

		return textLabel;

	}

	public static void openUrl(String url) {

		testBase.logger.info("new Throwable().getStackTrace()[0].getMethodName(): "
				+ new Throwable().getStackTrace()[0].getMethodName());
		try {
			myDriverFactory.getDriver().get(url);
		} catch (Exception e) {
			System.out.println("Closing driver");
			driver.quit();
		}

	}

}
