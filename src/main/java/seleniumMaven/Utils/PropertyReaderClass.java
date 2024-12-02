package seleniumMaven.Utils;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PropertyReaderClass extends testBase {

	public static final WebDriver driver = myDriverFactory.getDriver();
	
	
	public static String dataPropReader(String propKey) throws IOException {
		// TODO Auto-generated method stub

		FileReader reader = new FileReader(dataFilePath);
		Properties pr = new Properties();
		pr.load(reader);
		String tempPropValue = pr.getProperty(propKey);
		System.out.println("Property fetched: "+"\n"+propKey+", "+tempPropValue);
		return pr.getProperty(propKey);

	}

	public static String staticDataPropReader(String propKey) throws IOException {
		// TODO Auto-generated method stub

		FileReader reader = new FileReader(siteStatDataFilePath);
		Properties pr = new Properties();
		pr.load(reader);
		String tempPropValue = pr.getProperty(propKey);
		System.out.println("Property fetched: "+"\n"+propKey+", "+tempPropValue);
		return tempPropValue;

	}

	public static boolean isGuru99CredsValid(WebDriver driver, String filePath) throws IOException, ParseException {

		System.out.println("In guru99CredsValidator");
		String propGuru99CredsDate = dataPropReader("guruCredsDate");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		long currTimeInMill = System.currentTimeMillis();
		long oldTimeInMill = sdf.parse(propGuru99CredsDate).getTime();

		long diffL = currTimeInMill - oldTimeInMill;
		diffL = diffL / ((1000 * 3600 * 24));
		int diffInt = (int) diffL;

		if (diffInt > 19) {

			System.out.println("Data invalid");
			return false;

		}

		System.out.println("Data valid");

		return true;
	}

	public static String[] guru99NewRegistration() throws IOException, InterruptedException {

		System.out.println("In guru99NewRegistration");

		String[] guruNewCreds = new String[2];
		driver.navigate().to(PropertyReaderClass.staticDataPropReader("guru99url"));
//		driver.navigate().to("https://www.google.com/search?q="+PropertyReaderClass.staticDataPropReader("guru99url"));
		driver.findElement(By.xpath("//a[contains(@href,'http') and contains(@href,'guru99') and text()='here']"))
				.click();
		

		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		String signUpGuru99Xpath = "//a[contains(@href,'http') and contains(@href,'guru99') and text()='here']";
		if (!driver.findElements(By.xpath(signUpGuru99Xpath)).isEmpty()) {
			driver.findElements(By.xpath(signUpGuru99Xpath)).getFirst().click();
		}

		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(dateTimeFunction("ddMMyyyyhhmmss") + "aa@aa.com");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		Thread.sleep(5000);
		guruNewCreds[0] = driver.findElement(By.xpath("//table/tbody/tr/td[text()='User ID :']/parent::tr/td[2]"))
				.getText().trim();
		guruNewCreds[1] = driver.findElement(By.xpath("//table/tbody/tr/td[text()='Password :']/parent::tr/td[2]"))
				.getText().trim();

		return guruNewCreds;
	}

}
