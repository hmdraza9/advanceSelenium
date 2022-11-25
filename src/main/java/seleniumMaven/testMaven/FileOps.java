package seleniumMaven.testMaven;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class FileOps extends testBase {

	static boolean isProp = true;

	public static Map<String, String> readPropFileInHashMap(String filePath) throws IOException {

		Map<String, String> hm = new HashMap<String, String>();
		isProp = FilenameUtils.getExtension(filePath).contentEquals("properties");
		if (isProp) {
			File file = new File(filePath);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st;

			while ((st = br.readLine()) != null) {

				String[] temp = st.split("=");
				hm.put(temp[0], temp[1]);

			}
			br.close();
		}
		FileUtils.copyFile(new File(filePath), new File(classPath + "\\Data\\DataBkp\\dataFile." + dateTimeFunction()));

		return hm;
	}

	public static void savePropNewFile(String filePath, Map<String, String> hm) throws IOException {

		System.out.println("In savePropNewFile");
		FileReader reader = null;
		FileWriter writer = null;
		File file = new File(filePath);

		reader = new FileReader(file);
		writer = new FileWriter(file);
		Properties p = new Properties();
		p.load(reader);
		
		Set st = hm.entrySet();
		Iterator it = st.iterator();
		
		for(Map.Entry entry : hm.entrySet()) {
			
			p.setProperty(entry.getKey().toString(), entry.getValue().toString());
			
		}
		p.store(writer,"write a file");
		
		writer.close();
	}

}
