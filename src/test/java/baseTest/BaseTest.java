package baseTest;

import driverFactory.GetChromeDriver;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.asserts.SoftAssert;
import utilies.ConfigHandler;
import utilies.JSONFileManager;
import utilies.ScreenShot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;
    public SoftAssert softAssert;
    public ConfigHandler configHandler;
    public JSONFileManager jsonFileManager;

    @BeforeMethod
    public void setUp(){
        configHandler = new ConfigHandler("src/main/resources/config.properties");
        jsonFileManager = new JSONFileManager("src/main/resources/product.json");
        driver = GetChromeDriver.getWebDriver();
        driver.manage().window().maximize();
        driver.get(configHandler.getValue("url"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        softAssert = new SoftAssert();
    }

    @AfterMethod
    public void failedTestCase(ITestResult result) throws IOException {
        if(result.getStatus()== ITestResult.FAILURE){
            File image = ScreenShot.takeScreenShot(driver);
            FileInputStream fis = new FileInputStream(image);
            Allure.addAttachment("Failure screenshot for TC: "+result.getTestName(), "image/png", fis ,"png");
        }
    }
    public void tearDown(){

        GetChromeDriver.quitDriver();
        driver = null;
    }
}