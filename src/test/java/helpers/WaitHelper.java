package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {

    private static WaitHelper instance;

    public static synchronized WaitHelper getInstance(WebDriver drive){
        if (instance == null)
            instance = new WaitHelper(drive);
        return instance;
    }

    private final WebDriverWait wait;

    public WaitHelper(WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static void waitUntilVisible(WebDriver driver, WebElement webElement) {
        getInstance(driver).wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitToClick(WebDriver driver, WebElement webElement) {
        getInstance(driver).wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }
}
