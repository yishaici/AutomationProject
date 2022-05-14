package Pages;

import Utilities.BasePage;
import Utilities.SearchPresentOptions.Category;
import Utilities.SearchPresentOptions.PriceRange;
import Utilities.SearchPresentOptions.Region;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    // This method runs a full flow of searching a present in the home page - choosing a price range, a region and a category.
    // In the end it clicks on "Find"
    public void searchPresent(PriceRange priceRange, Region region, Category category) {
        clickPriceRangeBox();
        pickPriceRange(priceRange);
        clickOnRegionBox();
        pickRegion(region);
        clickCategoryBox();
        pickCategory(category);
        clickFindGift();
    }

    // This method clicks on thr category dropdown menu
    private void clickCategoryBox() {
        test.log(Status.INFO, "Click on gift category drop down menu");
        clickOnElementFromList(By.className("selected-name"), 2);
    }

    // This method clicks on the region dropdown menu
    private void clickOnRegionBox() {
        test.log(Status.INFO, "Click on the gift region dropdown menu");
        clickOnElementFromList(By.className("selected-name"), 1);
    }

    // This method clicks on the price range dropdown menu
    private void clickPriceRangeBox() {
        test.log(Status.INFO, "Click on the gift price range dropdown menu");
        clickOnElementFromList(By.className("selected-name"), 0);
    }

    // This method click on "Find"
    private void clickFindGift() {
        test.log(Status.INFO, "Click on 'Find gift' button");
        clickOnElement(By.id("ember1188"));
    }

    // This method picks a category from the ategory dropdown menu
    private void pickCategory(Category category) {
        test.log(Status.INFO, "Pick gift category: " + categoryToText(category));
        clickOnElement(By.xpath("//*[contains(text(),'" + categoryToText(category) + "')]"));
    }

    // This method converts the category options to text
    private String categoryToText(Category category) {

        switch (category) {
            case BEST2022:
                return "המתנות האהובות של 2022";
            case GIFT_TO_HOUSE:
                return "מתנות עד הבית";
            case GIFT_CARD_FASHION:
                return "גיפט קארד למותגי אופנה";
            case GIFT_CARD_BREAKFAST:
                return "גיפט קארד לארוחת בוקר ובתי קפה";
            case GIFT_CARD_SHEFF:
                return "גיפט קארד למסעדות שף";
            case GIFT_CARD_MOTHER_AND_BABY:
                return "גיפט קארד למתנות ליולדת וצעצועים";
            case GIFT_CARD_SPA:
                return "גיפט קארד לבתי ספא";
            case GIFT_CARD_RESTAURANT:
                return "גיפט קארד למסעדות";
            case GIFT_CARD_LOCAL_GIFT:
                return "מתנה מקומית";
            case GIFT_CARD_CULINARY:
                return "גיפט קארד למתנות קולינריות";
            case GIFT_CARD_HOME_KITCHEN_GADGETS:
                return "גיפט קארד לבית, מטבח וגאדג'טים";
            case GIFT_CARD_HEALTH_SPORT_EXTREME:
                return "גיפט קארד לבריאות, ספורט ואקסטרים";
            case GIFT_CARD_MUTUAL_EXPERIENCES:
                return "חוויות משותפות";
            case GIFT_CARD_VOCATIONS_HOTELS:
                return "נופש ומלונות";
            case GIFT_CARD_CULTURE:
                return "גיפט קארד לתרבות ופנאי";
            case GIFT_CARD_BEAUTY:
                return "גיפט קארד ליופי וטיפוח";
            case GIFT_CARD_ESCAPE_ROOMS:
                return "גיפט קארד לחדרי בריחה";
            case GIFT_CARD_WORKSHOP:
                return "סדנאות והעשרה";
        }
        return null;
    }

    // This method converts the region options to text
    private String regionToText(Region region) {

        switch (region) {
            case TEL_AVIV:
                return "ת\"א והסביבה";
            case MERKAZ:
                return "מרכז";
            case TZAFON:
                return "צפון";
            case DAROM:
                return "דרום";
            case JERUSALEM:
                return "ירושלים";
            case HAIFA:
                return "חיפה";
            case HASHARON:
                return "השרון";
            case EILAT:
                return "אילת";
            case PRISA_ARTZIT:
                return "פריסה ארצית";

        }
        return null;

    }

    // This method picks a price range from the price range dropdown menu
    private void pickPriceRange(PriceRange range) {
        test.log(Status.INFO, "Pick a gift price range: " + range);
        clickOnElement(By.xpath("//*[contains(text(),'" + priceRangeToText(range) + "')]"));
    }

    // This method picks a region from the region dropdown menu
    private void pickRegion(Region region) {
        test.log(Status.INFO, "Pick the gift region: " + regionToText(region));
        clickOnElement(By.xpath("//*[contains(text(),'" + regionToText(region) + "')]"));
    }

    // This method converts the price range options to text
    private String priceRangeToText(PriceRange range) {

        switch (range) {
            case UP_TO_99:
                return "עד 99 ש\"ח";
            case FROM_100_TO_199:
                return "100-199 ש\"ח";
            case FROM_200_TO_299:
                return "200-299 ש\"ח";
            case FROM_300_TO_499:
                return "300-499 ש\"ח";
            case FROM_500_TO_750:
                return "500-750 ש\"ח";
            case ABOVE750:
                return "מעל 750 ש\"ח";
        }
        return null;
    }
}
