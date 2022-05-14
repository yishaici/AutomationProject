package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BasePage {

    public static ExtentReports extent;
    public static ExtentTest test;
    private static String screenShotPath = "/src/main/Output/Screenshots/";
    private static String currentTime = String.valueOf(System.currentTimeMillis());
    private String key = "user.dir";
    private static String xmlFilePath = "src/main/resources/data.xml";

    // This method waits until an element is clickable and returns the element
    private WebElement getWebElement(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverSingleton.getDriverInstance(), Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.elementToBeClickable(DriverSingleton.getDriverInstance().findElement(locator)));

        } catch (Exception e) {
            e.printStackTrace();
            test.fail("Failed to find element " + e.getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(currentTime, screenShotPath)).build());


        }
        return null;
    }

    // This method waits until an element is clickable and returns the element from an element list using an index
    private WebElement getWebElements(By locator, int index) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverSingleton.getDriverInstance(), Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(DriverSingleton.getDriverInstance().findElements(locator).get(index)));
        } catch (Exception e) {
            e.printStackTrace();
            test.fail("Failed to find element " + e.getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(currentTime, screenShotPath)).build());
        }
        return null;
    }

    // This method clicks on an element
    public void clickOnElement(By locator) {
        try {
            getWebElement(locator).click();
            test.log(Status.PASS, "Clicked on element successfully ");

        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, "Failed to click on element " + e.getMessage());

        }
    }

    // This method sends text to an element
    public void sendKeys(By locator, String input) {
        try {
            getWebElement(locator).sendKeys(input);
            test.log(Status.PASS, "Text sent successfully ");

        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, "Failed to send text " + e.getMessage());

        }
    }

    // This method clicks on an element from a list of elements using an index
    public void clickOnElementFromList(By locator, int index) {
        getWebElements(locator, index).click();
//        try {
//            getWebElements(locator,index).click();
//            test.log(Status.INFO, "Clicked on element successfully ");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            test.log(Status.FAIL, "Failed to click on element " + e.getMessage());
//
//        }
    }

    // This method deletes existing text from a text box
    public void clearText(By locator) {
        try {
            getWebElement(locator).clear();
            test.log(Status.PASS, "Text cleared successfully ");

        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, "Failed to clear text " + e.getMessage());

        }
    }

    // This method compares between strings
    public void assertEqualString(By locator, String tagName, String expected) {

        try {
            Assert.assertEquals(getWebElement(locator).getAttribute(tagName), expected);
            test.log(Status.PASS, "Text asserted successfully ");

        } catch (Exception e) {
            e.printStackTrace();
            test.fail("Failed to assert text " + e.getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(currentTime, screenShotPath)).build());
        }
    }

    // This method generates an Extent report
    public void generateReport(String reportPath, String testName, String testDesc) {
        String cwd = System.getProperty(key);
        extent = new ExtentReports();
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(cwd + reportPath);
        extent.attachReporter(htmlReporter);
        test = extent.createTest(testName, testDesc);
    }

    // This method takes a screenshot
    protected static String takeScreenShot(String imageName, String imagesPath) {
        String file = imagesPath + imageName + ".png";
        TakesScreenshot takesScreenshot = (TakesScreenshot) DriverSingleton
                .getDriverInstance();
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(file);
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(file);
        return file;
    }

    // This method gets the browser from the XML file
    public static String getBrowser() {
        try {
            String browserType = getData("browserType");
            return browserType;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // This method gets the data from a specific tag in the XML file
    public static String getData(String keyName) throws Exception {
        File fXmlFile = new File(xmlFilePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(keyName).item(0).getTextContent();
    }
}
