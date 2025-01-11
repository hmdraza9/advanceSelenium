package seleniumMaven.pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.List;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import seleniumMaven.ObjectRepository;
import seleniumMaven.Utils.*;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class LoginTests extends testBase {

    WebDriver driver = myDriverFactory.getDriver();
    ObjectRepository ob = new ObjectRepository();


    @AfterTest
    public void AfterTest() {
        System.out.println("In After test");
    }

    @BeforeTest
    public void BeforeTest() throws InterruptedException, IOException, ParseException {

        driver.navigate().to(PropertyReaderClass.staticDataPropReader("guru99url"));
        testBase.logger.info("loginPageTest logged in, Title: " + driver.getTitle());


        if (!PropertyReaderClass.isGuru99CredsValid(driver, guru99Creds)) {
            String[] guru99NewCreds = PropertyReaderClass.guru99NewRegistration();
            List<String> propList = FileOps.readPropFileAsList(guru99Creds);
            String tempText = dateTimeFunction("dd-MM-yyyy")+"_12345";
            propList.add("USEDFORSECURITY=" + tempText.substring(0, 16));
            FileOps.savePropNewFile(guru99Creds, propList);
            propList.add("guru99user=" + encryptedText(guru99NewCreds[0]));
            propList.add("guru99password=" + encryptedText(guru99NewCreds[1]));
            propList.add("guruCredsDate=" + dateTimeFunction("dd-MM-yyyy"));
            FileOps.savePropNewFile(guru99Creds, propList);

        }
        driver.navigate().to(PropertyReaderClass.staticDataPropReader("guru99url"));
    }

    @Test(priority=2, enabled = false)
    public void shadowRootTest(){

        driver.get("http://watir.com/examples/shadow_dom.html");
//        driver.findElement(By.xpath("//div[@class='ipalBtn']")).click(); // for URL - https://www.icicibank.com/
        // 1. Access the Shadow Host
        WebElement shadowHost = driver.findElement(By.id("shadow_host"));

        System.out.println("shadowHost.getAttribute(\"id\"): "+shadowHost.getAttribute("id"));
        System.out.println("shadowHost.getAttribute(\"innerHTML\"): "+shadowHost.getAttribute("outerHTML"));

        // 2. Access the Shadow Root
        SearchContext shadowRoot = shadowHost.getShadowRoot();


        // 3. Query within the Shadow Root
        // Example: Finding an element with class 'bot-message' inside the Shadow DOM
        WebElement botMessage = shadowHost.findElement(By.cssSelector("div"));
//        WebElement botMessage = shadowRoot.findElement(By.cssSelector("div"));
        System.out.println("botMessage.getAttribute(\"id\"): "+botMessage.getAttribute("id"));
//        botMessage = shadowRoot.findElement(By.xpath("//div"));
//        System.out.println("botMessage.getAttribute(\"id\"): "+botMessage.getAttribute("id"));
        botMessage = shadowRoot.findElement(By.xpath("//div/parent::*"));
        System.out.println("botMessage.getAttribute(\"id\"): "+botMessage.getAttribute("id"));

        System.exit(1);
    }


    @Test(priority = -1, enabled = false)
    public void findBrokenLinks() throws IOException, InterruptedException {
        DriverUtils.openUrl("http://www.google.com");
        DriverUtils.enterKeys(ob.googleSearch, "Selenium");
        DriverUtils.pressEnter(ob.googleSearch);
        DriverUtils.takeSnaps("Screenshots/Screenshot");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(123);
        DriverUtils.takeSnaps("Screenshots/Screenshot_full");

        List<WebElement> links = driver.findElements(By.xpath("//a[contains(@href,'http')]"));
        URL url;
        System.out.println("links size: " + links.size());
        HttpURLConnection connection;
        if (!links.isEmpty()) {
            for (WebElement el : links) {
                System.out.println("el.getAttribute(\"href\"): " + el.getAttribute("href"));
                url = new URL(el.getAttribute("href"));
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                System.out.println("connection.getResponseCode(): " + connection.getResponseCode());
            }
        }
    }

    @Test(priority = 0, enabled = true)
    public void testRelativeLocators() throws IOException, InterruptedException {
        testBase.logger.info(new Throwable().getStackTrace()[0].getMethodName());

        DriverUtils.openUrl(PropertyReaderClass.staticDataPropReader("guru99url"));

        //relative locator - below
        driver.findElement(with(By.xpath("//td/input[not(@type='submit' or @type='reset')]"))
                .below(By.xpath("//*[@onkeyup='validateuserid();']"))).sendKeys("testtttttttttttt");
        Thread.sleep(1234);
        driver.findElement(with(By.xpath("//td/input[not(@type='submit' or @type='reset')]"))
                .below(By.xpath("//*[@onkeyup='validateuserid();']"))).clear();
        Thread.sleep(1234);
        Thread.sleep(1234);

        //relative locator - above
        driver.findElement(with(By.xpath("//input[contains(@onkeyup,'validate')]"))
                .above(By.xpath("//input[contains(@onkeyup,'validatepassword()')]"))).sendKeys("testtttttttttttt");
        Thread.sleep(1234);
        driver.findElement(with(By.xpath("//input[contains(@onkeyup,'validate')]"))
                .above(By.xpath("//input[contains(@onkeyup,'validatepassword()')]"))).clear();
        Thread.sleep(1234);
        Thread.sleep(1234);

        //relative locator - toLeftOf
        System.out.println(driver.findElement(with(By.xpath("//td/input"))
                .toLeftOf(By.name("btnReset"))).getAttribute("value"));//LOGIN
        Thread.sleep(1234);
        Thread.sleep(1234);

        //relative locator - toRightOf
        System.out.println(driver.findElement(with(By.xpath("//td/input"))
                .toRightOf(By.name("btnLogin"))).getAttribute("value"));//RESET
        Thread.sleep(1234);
        Thread.sleep(1234);

        //relative locator - near
        System.out.println(driver.findElement(with(By.xpath("//td/input"))
                .near(By.name("btnLogin"))).getAttribute("value"));//RESET
        Thread.sleep(1234);
        Thread.sleep(1234);


        //relative locator - chaining 1
        System.out.println(driver.findElement(
                with(By.xpath("//td/input"))
                        .near(By.name("btnLogin"))
                        .below(By.xpath("//input[contains(@onkeyup,'validateuserid()')]"))).getAttribute("type"));//password
        Thread.sleep(1234);
        Thread.sleep(1234);

        //relative locator - chaining 2
        System.out.println(driver.findElement(
                with(By.xpath("//td/input"))
                        .near(By.name("btnLogin"))
                        .above(By.xpath("//input[contains(@onkeyup,'validatepassword()')]"))).getAttribute("onkeyup"));//validateuserid();
        Thread.sleep(1234);
        Thread.sleep(1234);
    }

    @Test(priority = 1)
    public void loginGuru99Demo() throws IOException, InterruptedException {
        testBase.logger.info(new Throwable().getStackTrace()[0].getMethodName());


        DriverUtils.enterKeys(driver.findElement(By.name("uid")),
                decryptedText(PropertyReaderClass.dataPropReader("guru99user")));
        DriverUtils.enterKeys(driver.findElement(By.name("password")),
                decryptedText(PropertyReaderClass.dataPropReader("guru99password")));
        DriverUtils.click(driver.findElement(By.name("btnLogin")));
    }

    @AfterSuite
    public void AfterSuite() {
        BrowserQuit();
    }

    @BeforeSuite
    public void BeforeSuite() {
        testBase.logger.info(new Throwable().getStackTrace()[0].getMethodName());
        myDriverFactory.getDriver();
    }
}
