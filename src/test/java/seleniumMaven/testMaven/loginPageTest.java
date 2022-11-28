package seleniumMaven.testMaven;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

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
    	if(PropertyReaderClass.guru99CredsValidator(BrowserInitSetup() , PropertyReaderClass.filePath)) {
    		
    		String[] guru99NewCreds = PropertyReaderClass.guru99NewRegistrationDummy();
    		List<String> propList = FileOps.readPropFileInHashMap(PropertyReaderClass.filePath);
    		propList.add("guru99user="+guru99NewCreds[0]);
    		propList.add("guru99password="+guru99NewCreds[1]);
    		propList.add("guruCredsDate="+dateFunction());
    		FileOps.savePropNewFile(PropertyReaderClass.filePath,propList);
    		
    	}
	}
}
