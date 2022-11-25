package seleniumMaven.testMaven;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;

public class PropertyReaderClass extends testBase {

	public static String filePath = classPath + "\\Data\\dataFile.properties";

	public static String propReader(String propKey) throws IOException {
		// TODO Auto-generated method stub

		FileReader reader = new FileReader(filePath);
		Properties pr = new Properties();
		pr.load(reader);
		return pr.getProperty(propKey);

	}

	public static boolean guru99CredsValidator(String filePath) throws IOException, ParseException {

		System.out.println("In guru99CredsValidator");
		String propGuru99CredsDate = propReader("gutuCredsDate");
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(sdf.parse(propGuru99CredsDate));
		c1.add(Calendar.DATE, 20);
		String c1Date = c1.getTime().toString().substring(4, 9) + c1.getTime().toString().substring(24, 27);
		String c2Date = c2.getTime().toString().substring(4, 9) + c2.getTime().toString().substring(24, 27);

		System.out.println(c1.getTime());
		System.out.println(c2.getTime());
		
		Date d1 = sdf.parse(c1Date);
		Date d2 = sdf.parse(c2Date);

		return (d1.compareTo(d2) >= 0);
	}

	public static String[] guru99NewRegistration() throws IOException {

		
		String[] guruNewCreds = new String[2];
		driver.navigate().to(PropertyReaderClass.propReader("guru99url"));
		driver.findElement(By.xpath("//a[contains(@href,'http') and contains(@href,'guru99') and text()='here']"))
				.click();
		if (driver.findElements(By.xpath("//div[@id='dismiss-button' and @aria-label='Close ad']")).size() > 0)
			driver.findElements(By.xpath("//div[@id='dismiss-button' and @aria-label='Close ad']/div")).get(0).click();
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys("aa@aa.com");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		guruNewCreds[0] = driver.findElement(By.xpath("//table/tbody/tr/td[text()='User ID :']/parent::tr/td[2]"))
				.getText().trim();
		guruNewCreds[1] = driver
				.findElement(By.xpath("//table/tbody/tr/td[text()='Password :']/parent::tr/td[2]")).getText().trim();

		return guruNewCreds;
	}

}
