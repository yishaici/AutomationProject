package Pages;

import Utilities.BasePage;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;

public class BusinessPage extends BasePage {

    //This method runs a full flow of chossing a business, entering the amount of the gift and clicks on "Continue"
    public void ChooseBusiness(String business) {
        clickBusiness(business);
        typeAmount("100");
        clickChooseButton();
    }

    // This method clicks on the "Choose" button
    private void clickChooseButton() {
        test.log(Status.INFO, "Click on 'Choose' button");
        clickOnElement(By.xpath("//*[contains(@type,'submit')]"));
    }

    // This method entered the amount of the gift inside the business page
    private void typeAmount(String amount) {
        test.log(Status.INFO, "Type the amount: " + amount);
        sendKeys(By.xpath("//*[contains(@placeholder,'הכנס סכום')]"), amount);
    }

    // This method clicks on a business from the business results screen
    private void clickBusiness(String business) {
        test.log(Status.INFO, "Click on the desired business: " + business);
        clickOnElement(By.linkText(business));
    }
}
