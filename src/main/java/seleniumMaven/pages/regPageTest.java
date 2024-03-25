package seleniumMaven.pages;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import seleniumMaven.Utils.PropertyReaderClass;
import seleniumMaven.Utils.myDriverFactory;
import seleniumMaven.Utils.testBase;

public class regPageTest extends testBase {

	String headerText = "Guru99 Bank";
	String titleText = "Guru99 Bank Manager HomePage";
	PropertyReaderClass pr;

	@Test
	public void landingPageTest() throws IOException, InterruptedException {
		testBase.logger.info(new Throwable().getStackTrace()[0].getMethodName());
		pr = new PropertyReaderClass();
		Thread.sleep(5000);
		System.out.println("regPageTest logged in, Title: " + myDriverFactory.getDriver().getTitle());
		AssertJUnit.assertTrue(myDriverFactory.getDriver().findElements(By.linkText("Log out")).size() > 0);
		AssertJUnit.assertTrue(myDriverFactory.getDriver().getTitle().contains(titleText));
	}
}
