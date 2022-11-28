package seleniumMaven.testMaven;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PropertyReaderClass extends testBase {


	public static String propReader(String propKey) throws IOException {
		// TODO Auto-generated method stub

		FileReader reader = new FileReader(filePath);
		Properties pr = new Properties();
		pr.load(reader);
		return pr.getProperty(propKey);

	}

	public static boolean guru99CredsValidator(WebDriver driver , String filePath) throws IOException, ParseException {

		System.out.println("In guru99CredsValidator");
		String propGuru99CredsDate = propReader("gutuCredsDate");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		System.out.println("propGuru99CredsDate: "+propGuru99CredsDate);
		
		int diffDays = (int) ((System.currentTimeMillis() - sdf.parse(propGuru99CredsDate).getTime())/(1000*3600*24));

		System.out.println("diffDays: "+diffDays);
		
		if(diffDays>19) {
			
			String[] newCreds = guru99NewRegistrationDummy();
			List<String> oldFileProps = FileOps.readPropFileInHashMap(filePath);
			oldFileProps.add(newCreds[0]+"="+newCreds[1]);
			System.out.println("oldFileProps: "+oldFileProps);
			return true;
			
		}
		
		return false;
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

	public static String[] guru99NewRegistrationDummy() throws IOException {

		
		String[] guruNewCreds = new String[2];

		guruNewCreds[0] = "testUser";
		guruNewCreds[1] = "testPass";
		
		return guruNewCreds;
	}

}
