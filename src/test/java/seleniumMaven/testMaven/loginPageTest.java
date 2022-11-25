package seleniumMaven.testMaven;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class loginPageTest extends testBase{
	
	
	
	@AfterTest
	public void afterTesting() {
		System.out.println("In After test");
		BrowserQuit();
	}
	
    @Test
    public void loginGuru99Demo() throws IOException, InterruptedException
    {

		System.out.println("In Test");
    	driver.get(PropertyReaderClass.propReader("guru99url"));
    	System.out.println("loginPageTest, Title: "+driver.getTitle());
    	driver.findElement(By.name("uid")).sendKeys(PropertyReaderClass.propReader("guru99user"));
    	driver.findElement(By.name("password")).sendKeys(PropertyReaderClass.propReader("guru99password"));
    	driver.findElement(By.name("btnLogin")).click();
    	System.out.println("loginPageTest logged in, Title: "+driver.getTitle());
    }

	

	@BeforeTest
	public void configMethod() throws InterruptedException, IOException, ParseException {
		System.out.println("In Before test");
    	BrowserInitSetup();
    	if(!PropertyReaderClass.guru99CredsValidator(PropertyReaderClass.filePath)) {
    		
    		String[] guru99NewCreds = PropertyReaderClass.guru99NewRegistration();
    		Map<String, String> hm = FileOps.readPropFileInHashMap(PropertyReaderClass.filePath);
    		hm.put("guru99user", guru99NewCreds[0]);
    		hm.put("guru99password", guru99NewCreds[1]);
    		hm.put("gutuCredsDate", dateFunction());
    		FileOps.savePropNewFile(PropertyReaderClass.filePath,hm);
    		
    	}
	}
}
