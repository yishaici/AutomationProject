package Pages;

import Utilities.BasePage;
import Utilities.DriverSingleton;
import Utilities.Events;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;

public class ReceiverInfoPage extends BasePage {

    String picturePath = "/Users/oshriavigadol/Dropbox/אושרי ונתנאלילה/קוד אושרי/AutomationProject/src/main/resources/download.jpeg";

    // This method runs a full flow of filling the gift receiver's details - Entering first name, picking an event, entering a blessing,
    // uploading a picture and clicking on continue
    public void fillReceiverDetails(String receiverName, Events event, String blessing) {
        clickOnSomeoneElseButton();
        fillReceiverFirstName(receiverName);
        clickOnEventsBox();
        chooseEvent(event);
        fillBlessing(blessing);
        uploadPicture(picturePath);
        clickContinue();
    }

    // This method clicks on the "Someone else" radio button
    private void clickOnSomeoneElseButton() {
        test.log(Status.INFO, "Click on 'Someone else' button");
        clickOnElement(By.xpath("//div[@gtm='למישהו אחר']"));
    }

    // This method clicks on the "Continue" button
    private void clickContinue() {
        test.log(Status.INFO, "Click on 'Continue' button");
        clickOnElement(By.xpath("//button[@type='submit']"));
    }

    // This method receives a picture path and sends the picture to an element
    private void uploadPicture(String picturePath) {
        DriverSingleton.getDriverInstance().findElement(By.xpath("//input[@type='file']")).sendKeys(picturePath);

    }

    // This method sends a blessing text
    private void fillBlessing(String blessing) {
        test.log(Status.INFO, "Fill in a blessing: " + blessing);

        clearText(By.className("parsley-success"));
        sendKeys(By.className("parsley-success"), blessing);
    }

    // This method clicks on the events' dropdown menu
    private void clickOnEventsBox() {
        test.log(Status.INFO, "Click on the event dropdown menu");
        clickOnElement(By.className("selected-name"));
    }

    // This method enteres the gift receier's first name
    private void fillReceiverFirstName(String name) {
        test.log(Status.INFO, "Fill in the receiver's name: " + name);
        sendKeys(By.id("friendName"), name);
        assertEqualString(By.xpath("//input[@type='text']"), "value", name);
    }

    // This method picks an event from the events dropdown menu
    private void chooseEvent(Events event) {
        test.log(Status.INFO, "Choose the event: " + eventsToString(event));
        clickOnElement(By.xpath("//*[contains(text(),'" + eventsToString(event) + "')]"));
    }

    // This method converts the events options to string
    private String eventsToString(Events event) {
        switch (event) {
            case BIRTHDAY:
                return "יום הולדת";
            case BIRTH:
                return "לידה";
            case THANKS:
                return "תודה";
            case GET_WELL:
                return "החלמה מהירה";
            case GOODBYE:
                return "פרידה";
            case HINA:
                return "חינה";
            case WEDDING:
                return "חתונה";
            case BAR_MITZVA:
                return "בר מצווה";
            case BAT_MITZVA:
                return "בת מצווה";
            case QUARANTINE:
                return "בידוד";
            case FOR_NO_REASON:
                return "סתם כי בא לי לפנק";
            case NEW_HOUSE:
                return "כניסה לבית חדש";
            case ANNIVERSARY:
                return "יום נישואין";
            case GOOD_LUCK:
                return "בהצלחה";
            case EMPLOYEE_GIFT:
                return "מתנה לעובדים";
        }
        return null;
    }


}
