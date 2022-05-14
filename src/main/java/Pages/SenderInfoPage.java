package Pages;
import Utilities.BasePage;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;

public class SenderInfoPage extends BasePage {

    // This method run a full flow of filling in the senders details
    public void fillSenderDetails(String email, String senderName) {
        clickOnNowRadioButton();
        clickOnEmailButton();
        fillEmail(email);
        FillSenderName(senderName);
        clickPayButton();
    }

    // This method clicks on the "Pay" button
    private void clickPayButton() {
        test.log(Status.INFO, "Click on the 'Payment' button");
        clickOnElement(By.xpath("//button[@gtm=\"המשך לתשלום\"]"));
    }

    // This method sends the sender's first name string
    private void FillSenderName(String name) {
        test.log(Status.INFO, "Fill in the sender's name: " + name);
        String locator = "//input[@placeholder=\"שם שולח המתנה\"]";
        sendKeys(By.xpath(locator), name);
        assertEqualString(By.xpath((locator)), "value", name);
    }

    // This method sends the email string
    private void fillEmail(String email) {
        test.log(Status.INFO, "Fill in the receiver's email: " + email);
        sendKeys(By.id("email"), email);
    }

    // This method clicks on the email button
    private void clickOnEmailButton() {
        test.log(Status.INFO, "Click on the email button");
        clickOnElementFromList(By.className("method-icon"), 1);
    }

    private void clickOnNowRadioButton() {
        test.log(Status.INFO, "Click on the 'Now' radio button");
        clickOnElement(By.xpath("//div[@gtm=\"עכשיו\"]"));
    }
}
