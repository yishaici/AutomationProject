package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverSingleton extends BasePage {

    private static WebDriver driver = null;

    public static WebDriver getDriverInstance() {

        if (driver == null) {

            if (getBrowser().equals("Chrome")) {
                System.setProperty("webdriver.chrome.driver", Constants.CHROMEDRIVER_PATH);
                ChromeOptions options = new ChromeOptions();
                driver = new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                options.addArguments("start-maximized");
            } else if (getBrowser().equals("FF")) {
                System.setProperty("webdriver.gecko.driver", Constants.FIRE_FOX_PATH);
                driver = new FirefoxDriver();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            }
        }
        driver.manage().window().maximize();
        return driver;
    }

}
