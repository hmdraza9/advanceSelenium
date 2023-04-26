package seleniumMaven.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class FileOps extends testBase {

	static boolean isProp = true;

	public static List<String> readPropFileAsList(String filePath) throws IOException {

		System.out.println("In readPropFileAsList");
		List<String> propList = new ArrayList<String>();
		isProp = FilenameUtils.getExtension(filePath).contentEquals("properties");
		if (isProp) {
			File file = new File(filePath);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st;

			while ((st = br.readLine()) != null) {

				propList.add(st);

			}
			br.close();
		}
		FileUtils.copyFile(new File(filePath), new File(classPath + "\\Data\\DataBkp\\dataFile." + dateTimeFunction()+".properties"));

		return propList;
	}

	public static void savePropNewFile(String filePath, List<String> propList) throws IOException {

		System.out.println("In savePropNewFile");
		FileReader reader = null;
		FileWriter writer = null;
		File file = new File(filePath);

		reader = new FileReader(file);
		writer = new FileWriter(file);
		Properties p = new Properties();
		p.load(reader);
		
		
		Iterator<String> it = propList.iterator();
		
		while(it.hasNext()) {
			
			String itNext = it.next();
			
			if (itNext.split("=").length>1) {
				String[] tempString = itNext.toString().split("=");
				p.setProperty(tempString[0], tempString[1]);
			}
			
		}
		p.store(writer,"write a file");
		
		writer.close();
	}

}
