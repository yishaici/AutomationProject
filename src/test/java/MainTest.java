import Pages.*;
import Utilities.BasePage;
import Utilities.Constants;
import Utilities.DriverSingleton;
import Utilities.Events;
import Utilities.SearchPresentOptions.Category;
import Utilities.SearchPresentOptions.PriceRange;
import Utilities.SearchPresentOptions.Region;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MainTest extends BasePage {
    String businessPageUrl = "https://buyme.co.il/search?budget=1&category=359&region=11";
    String reportPath = "/src/main/Output/extent.html";
    String testName = "BuyMeTest";
    String testDesc = "Sanity testing";
    WebDriver driver = DriverSingleton.getDriverInstance();


    @BeforeClass
    public void beforeClass() throws Exception {
        driver.get(getData("URL"));
        generateReport(reportPath, testName, testDesc);
    }

    @Test
    public void test01_Register() {
        RegisterPage reg = new RegisterPage();
        reg.register(Constants.PRIVATE_NAME, Constants.EMAIL, Constants.PASSWORD);
    }

    @Test
    public void test02_FindPresent() {
        try {
            HomePage home = new HomePage();
            home.searchPresent(PriceRange.UP_TO_99, Region.MERKAZ, Category.BEST2022);
            Assert.assertEquals(driver.getCurrentUrl(), businessPageUrl);
            test.log(Status.PASS, "User registered successfully");
        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, "User failed tp register " + e.getMessage());
        }

    }

    @Test
    public void test03_PickBusiness() {
        try {
            BusinessPage business = new BusinessPage();
            business.ChooseBusiness("דרך היין");
            test.log(Status.PASS, "Choose business");
        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, "Failed to choose a business " + e.getMessage());

        }
    }

    @Test
    public void test04_FillReceiverInfo() {
        try {
            ReceiverInfoPage receiver = new ReceiverInfoPage();
            receiver.fillReceiverDetails("Netanel", Events.BIRTHDAY, "Mazal Tov!");
            test.log(Status.PASS, "Receiver's details filled successfully");

        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, "Failed to fill receiver's details " + e.getMessage());

        }
    }

    @Test
    public void test05_FillInSenderInfo() {
        try {
            SenderInfoPage sender = new SenderInfoPage();
            sender.fillSenderDetails("oshri@oshri.com", "Oshri");
            test.log(Status.PASS, "Sender's details filled successfully");

        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, "Failed to fill sender's details " + e.getMessage());

        }
    }

    @AfterClass
    public void afterTest() {
        driver.quit();
        extent.flush();
    }
}
