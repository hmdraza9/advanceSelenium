package seleniumMaven.Utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class testBase {
	public static final String classPath = System.getProperty("user.dir");
	public static final Logger logger = LogManager.getLogger(testBase.class.getName());

	public static final String guru99Creds = classPath + "//Data//dataFile.properties";
	public static final String guru99URL = classPath + "//siteStaticData//siteStaticData.properties";
	public static final String propsFilePath = classPath + "//resources//props.properties";


	public void BrowserQuit() {
		System.out.println(new Throwable().getStackTrace()[0].getMethodName());
		logger.info("Browser closing");
		myDriverFactory.getDriver().quit();
	}


	public static String dateTimeFunction(String format) {
		logger.info(new Throwable().getStackTrace()[0].getMethodName());
		SimpleDateFormat sdfDate = new SimpleDateFormat(format); //"ddMMyyyyhhmmss", "dd-MM-yyyy"
		Date now = new Date();
		return sdfDate.format(now);
	}

	public static String encryptedText(String text) throws IOException {
		return PasswordEncryption.encrypt(text, PropertyReaderClass.dataPropReader("USEDFORSECURITY"));
	}

	public static String decryptedText(String text) throws IOException {
		return PasswordEncryption.decrypt(text, PropertyReaderClass.dataPropReader("USEDFORSECURITY"));
	}

}

