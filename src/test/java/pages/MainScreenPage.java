package pages;

import io.appium.java_client.AppiumDriver;
import support.CustomCommands;

/**
 * Main Screen Page Object
 *
 * Screen Description:
 * - The initial screen with 2 buttons
 * - Button 1: "Create Account" - navigates to CreateAccountScreen
 * - Button 2: "Login" - navigates to LoginScreen
 *
 * Elements:
 * - btnCreateAccount: The "Create Account" button
 * - btnLogin: The "Login" button
 */
public class MainScreenPage extends BasePage {

    // Locators - Main Screen buttons
    private final String BTN_CREATE_ACCOUNT = "com.hostelworld.qacodechallenge:id/btnCreateAccount";
    private final String BTN_LOGIN = "com.hostelworld.qacodechallenge:id/btnLogin";

    public MainScreenPage(AppiumDriver driver, CustomCommands customCommands) {
        super(driver, customCommands);
    }

    /**
     * Click on "Create Account" button
     * Navigates to CreateAccountScreen
     */
    public void clickCreateAccount() {
        customCommands.clickById(BTN_CREATE_ACCOUNT);
    }

    /**
     * Click on "Login" button
     * Navigates to LoginScreen
     */
    public void clickLogin() {
        customCommands.clickById(BTN_LOGIN);
    }

    /**
     * Check if Create Account button is displayed
     */
    public boolean isCreateAccountButtonDisplayed() {
        return isElementDisplayed(BTN_CREATE_ACCOUNT);
    }

    /**
     * Check if Login button is displayed
     */
    public boolean isLoginButtonDisplayed() {
        return isElementDisplayed(BTN_LOGIN);
    }
}

