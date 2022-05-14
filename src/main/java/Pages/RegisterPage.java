package Pages;

import Utilities.BasePage;
import Utilities.Constants;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;


public class RegisterPage extends BasePage {

    // This method runs a full registration flow
    public void register(String privateName, String email, String password) {
        clickLoginRegisterButton();
        clickRegisterLink();
        enterPrivateName(privateName);
        enterEmail(email);
        enterPassword(password);
        reEnterPassword(password);
        clickRegisterButton();
    }

    // This method clicks on the "Register" button
    private void clickRegisterButton() {
        test.log(Status.INFO, "Click on 'Registration' button");
        clickOnElement(By.id("ember1822"));
    }

    // This method sends the password text to the password textbox
    private void enterPassword(String password) {
        test.log(Status.INFO, "Enter password");
        sendKeys(By.id("valPass"), password);
    }

    // This method sends the password string again
    private void reEnterPassword(String password) {
        test.log(Status.INFO, "ReEnter password");
        sendKeys(By.id("ember1816"), password);
    }

    // This method sends the email text
    private void enterEmail(String email) {
        test.log(Status.INFO, "Enter email: " + email);
        String locator = "ember1802";
        sendKeys(By.id(locator), email);
        assertEqualString(By.id((locator)), "value", Constants.EMAIL);

    }

    // This method sends the user's first name string
    private void enterPrivateName(String privateName) {
        test.log(Status.INFO, "Enter first name: " + privateName);
        String locator = "ember1795";
        sendKeys(By.id(locator), privateName);
        assertEqualString(By.id((locator)), "value", Constants.PRIVATE_NAME);
    }

    // This method clicks on the "Register" link
    private void clickRegisterLink() {
        test.log(Status.INFO, "Click on the 'Register' link");
        clickOnElement(By.xpath("//*[@id=\"ember952\"]/div/div[1]/div/div/div[3]/div[1]/span"));
    }

    // This method clicks on the "Login/Register" button
    private void clickLoginRegisterButton() {
        test.log(Status.INFO, "click on the 'Login/Register' button");
        clickOnElement(By.className("notSigned"));
    }

}
