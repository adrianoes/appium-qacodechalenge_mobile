package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.CustomCommands;

import java.time.Duration;

/**
 * Base Page Class
 * Contains common elements and methods for all pages
 */
public class BasePage {

    protected AppiumDriver driver;
    protected WebDriverWait wait;
    protected CustomCommands customCommands;

    public BasePage(AppiumDriver driver, CustomCommands customCommands) {
        this.driver = driver;
        this.customCommands = customCommands;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /**
     * Check if element is displayed by resource ID
     */
    public boolean isElementDisplayed(String resourceId) {
        return customCommands.isElementDisplayed(resourceId);
    }

    /**
     * Wait for element to be visible
     */
    public void waitForElementVisible(String resourceId, int timeoutSeconds) {
        customCommands.waitForElementVisible(resourceId, timeoutSeconds);
    }
}

