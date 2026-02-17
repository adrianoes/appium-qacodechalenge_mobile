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

    /**
     * Wait for element to be visible and get its text
     */
    public String waitAndGetText(String resourceId, int timeoutSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        By locator = By.id(resourceId);
        WebElement element = customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getText();
    }

    /**
     * Get attribute value from element by resource ID
     */
    public String getAttributeById(String resourceId, String attributeName) {
        By locator = By.id(resourceId);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getAttribute(attributeName);
    }

    /**
     * Get attribute value from element without waiting (immediate)
     */
    public String getAttributeByIdNoWait(String resourceId, String attributeName) {
        try {
            By locator = By.id(resourceId);
            WebElement element = driver.findElement(locator);
            return element.getAttribute(attributeName);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Get WebElement by resource ID
     */
    public WebElement getElementById(String resourceId) {
        try {
            By locator = By.id(resourceId);
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Clear field by resource ID
     */
    public void clearFieldById(String resourceId) {
        By locator = By.id(resourceId);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
    }

    /**
     * Wait for an element to be visible
     */
    public void waitForElementVisible(String resourceId, int timeoutSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        By locator = By.id(resourceId);
        customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Go back (press back button)
     */
    public void goBack() {
        driver.navigate().back();
    }
}

