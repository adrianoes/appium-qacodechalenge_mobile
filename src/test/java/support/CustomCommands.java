package support;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Custom commands for common Appium actions
 */
public class CustomCommands {

    private AppiumDriver driver;
    private WebDriverWait wait;

    public CustomCommands(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /**
     * Click on an element by resource ID
     */
    public void clickById(String resourceId) {
        By locator = By.id(resourceId);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    /**
     * Send text to an element by resource ID
     */
    public void sendTextById(String resourceId, String text) {
        By locator = By.id(resourceId);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Get text from an element by resource ID
     */
    public String getTextById(String resourceId) {
        By locator = By.id(resourceId);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getText();
    }

    /**
     * Check if element is displayed by resource ID
     */
    public boolean isElementDisplayed(String resourceId) {
        try {
            By locator = By.id(resourceId);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}

