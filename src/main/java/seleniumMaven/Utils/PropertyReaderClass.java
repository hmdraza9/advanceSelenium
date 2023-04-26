package seleniumMaven.Utils;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PropertyReaderClass extends testBase {

	public static String dataPropReader(String propKey) throws IOException {
		// TODO Auto-generated method stub

		FileReader reader = new FileReader(dataFilePath);
		Properties pr = new Properties();
		pr.load(reader);
		return pr.getProperty(propKey);

	}

	public static String staticDataPropReader(String propKey) throws IOException {
		// TODO Auto-generated method stub

		FileReader reader = new FileReader(siteStatDataFilePath);
		Properties pr = new Properties();
		pr.load(reader);
		return pr.getProperty(propKey);

	}

	public static boolean guru99CredsValidator(WebDriver driver, String filePath) throws IOException, ParseException {

		System.out.println("In guru99CredsValidator");
		String propGuru99CredsDate = dataPropReader("guruCredsDate");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		long currTimeInMill = System.currentTimeMillis();
		long oldTimeInMill = sdf.parse(propGuru99CredsDate).getTime();

		long diffL = currTimeInMill - oldTimeInMill;
		diffL = diffL / ((1000 * 3600 * 24));
		int diffInt = (int) diffL;

		if (diffInt > 19) {

			return true;

		}

		return false;
	}

	public static String[] guru99NewRegistration() throws IOException, InterruptedException {

		System.out.println("In guru99NewRegistration");

		String[] guruNewCreds = new String[2];
		myDriverFactory.getDriver().navigate().to(PropertyReaderClass.staticDataPropReader("guru99url"));
		myDriverFactory.getDriver().findElement(By.xpath("//a[contains(@href,'http') and contains(@href,'guru99') and text()='here']"))
				.click();
//		if (myDriverFactory.getDriver().findElements(By.xpath("//div[@id='dismiss-button' and @aria-label='Close ad']")).size() > 0) {
//			myDriverFactory.getDriver().findElements(By.xpath("//div[@id='dismiss-button' and @aria-label='Close ad']/div")).get(0).click();
//		}

		Thread.sleep(3000);
		myDriverFactory.getDriver().navigate().refresh();
		Thread.sleep(3000);
		String signUpGuru99Xpath = "//a[contains(@href,'http') and contains(@href,'guru99') and text()='here']";
		if (myDriverFactory.getDriver().findElements(By.xpath(signUpGuru99Xpath)).size() > 0) {
			myDriverFactory.getDriver().findElements(By.xpath(signUpGuru99Xpath)).get(0).click();
		}

		myDriverFactory.getDriver().findElement(By.xpath("//input[@name='emailid']")).sendKeys(dateTimeFunction() + "aa@aa.com");
		myDriverFactory.getDriver().findElement(By.xpath("//input[@name='btnLogin']")).click();
		Thread.sleep(5000);
		guruNewCreds[0] = myDriverFactory.getDriver().findElement(By.xpath("//table/tbody/tr/td[text()='User ID :']/parent::tr/td[2]"))
				.getText().trim();
		guruNewCreds[1] = myDriverFactory.getDriver().findElement(By.xpath("//table/tbody/tr/td[text()='Password :']/parent::tr/td[2]"))
				.getText().trim();

		return guruNewCreds;
	}

}
