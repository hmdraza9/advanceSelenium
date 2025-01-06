package seleniumMaven.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class testBase {
	public static PropertyReaderClass pr;
	public static String classPath = System.getProperty("user.dir");
	public static final Logger logger = LogManager.getLogger(testBase.class.getName());

	public static String dataFilePath = classPath + "//Data//dataFile.properties";
	public static String siteStatDataFilePath = classPath + "//siteStaticData//siteStaticData.properties";
	public static String propsFilePath = classPath + "//resources//props.properties";
	public static String userHome = System.getProperty("user.home");


	public void BrowserQuit() {
		System.out.println(new Throwable().getStackTrace()[0].getMethodName());
		logger.info("Browser closing");
		myDriverFactory.getDriver().quit();
	}// AfterClassMethod


	public static String dateTimeFunction(String format) {
		logger.info(new Throwable().getStackTrace()[0].getMethodName());
		SimpleDateFormat sdfDate = new SimpleDateFormat(format); //"ddMMyyyyhhmmss", "dd-MM-yyyy"
		Date now = new Date();
		return sdfDate.format(now);
	}

}

