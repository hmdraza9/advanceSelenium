package praticeJava.GetterSetter;

import java.io.IOException;

import seleniumMaven.Utils.FileOps;
import seleniumMaven.Utils.testBase;

public class DataStoreUtilizeDemo extends testBase{

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		DataStore.setUser("userName");
		DataStore.setPass("pass");

		System.out.println(DataStore.getUser());
		System.out.println(DataStore.getPass());
		
//		DataStore nn = new DataStore();
		FileOps.savePropNewFile(guru99Creds, FileOps.readPropFileAsList(guru99Creds));
		
	}

}
