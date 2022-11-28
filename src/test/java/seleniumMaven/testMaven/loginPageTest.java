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
    	driver.get(PropertyReaderClass.staticDataPropReader("guru99url"));
    	driver.findElement(By.name("uid")).sendKeys(PropertyReaderClass.dataPropReader("guru99user"));
    	driver.findElement(By.name("password")).sendKeys(PropertyReaderClass.dataPropReader("guru99password"));
    	driver.findElement(By.name("btnLogin")).click();
    	System.out.println("loginPageTest logged in, Title: "+driver.getTitle());
    	
//    	Assert.assertEquals(false, false);
    }

	

	@BeforeTest
	public void configMethod() throws InterruptedException, IOException, ParseException {
		System.out.println("In Before test");
    	if(PropertyReaderClass.guru99CredsValidator(BrowserInitSetup() , dataFilePath)) {
    		
    		String[] guru99NewCreds = PropertyReaderClass.guru99NewRegistration();
    		List<String> propList = FileOps.readPropFileAsList(dataFilePath);
    		propList.add("guru99user="+guru99NewCreds[0]);
    		propList.add("guru99password="+guru99NewCreds[1]);
    		propList.add("guruCredsDate="+dateFunction());
    		FileOps.savePropNewFile(dataFilePath,propList);
    		
    	}
	}
}
