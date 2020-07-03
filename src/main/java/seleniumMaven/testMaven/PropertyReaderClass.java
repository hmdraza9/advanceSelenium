package seleniumMaven.testMaven;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReaderClass {

	public String propReader(String propKey) throws IOException {
		// TODO Auto-generated method stub

		FileReader reader = new FileReader("C:\\workspace\\testMaven\\Data\\dataFile.properties");
		Properties pr = new Properties();
		pr.load(reader);
		
		return pr.getProperty(propKey);
		
	}

}
