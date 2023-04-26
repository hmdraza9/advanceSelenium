package seleniumMaven.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DriverUtils extends testBase {

	public static void click(WebElement el) {

		testBase.logger.info("new Throwable().getStackTrace()[0].getMethodName(): "
				+ new Throwable().getStackTrace()[0].getMethodName());
		try {
			el.click();
		} catch (Exception e) {
			testBase.logger.info("Closing driver");
			myDriverFactory.getDriver().quit();
		}

	}

	public static void enterKeys(WebElement el, String str) {

		testBase.logger.info("new Throwable().getStackTrace()[0].getMethodName(): "
				+ new Throwable().getStackTrace()[0].getMethodName());
		try {
			el.sendKeys(str);
		} catch (Exception e) {
			System.out.println("Closing driver");
			myDriverFactory.getDriver().quit();
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
			myDriverFactory.getDriver().quit();
		}

		return textLabel;

	}

	public static void openUrl(WebDriver driver, String url) {

		testBase.logger.info("new Throwable().getStackTrace()[0].getMethodName(): "
				+ new Throwable().getStackTrace()[0].getMethodName());
		try {
			myDriverFactory.getDriver().get(url);
		} catch (Exception e) {
			System.out.println("Closing driver");
			myDriverFactory.getDriver().quit();
		}

	}

}
