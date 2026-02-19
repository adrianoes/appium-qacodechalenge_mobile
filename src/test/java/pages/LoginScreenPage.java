package pages;

import io.appium.java_client.AppiumDriver;
import support.CustomCommands;

/**
 * Login Screen Page Object
 *
 * Screen Description:
 * - Login form with 2 input fields and 1 button
 * - Field 1: "Email/Username" - for entering user email
 * - Field 2: "Password" - for entering user password
 * - Button: "Login" - submits credentials and navigates to UserLoggedInScreen if successful
 *
 * Note: BUG #1 - The email field is mislabeled as "usernameEt" instead of "emailEt"
 *
 * Elements:
 * - usernameEt: Email input field (BUG: should be emailEt)
 * - passwordEt: Password input field
 * - btnLogin: Login button
 */
public class LoginScreenPage extends BasePage {

    // Locators - Login Screen
    private final String EMAIL_FIELD = "com.hostelworld.qacodechallenge:id/usernameEt"; // BUG #1: Should be emailEt
    private final String PASSWORD_FIELD = "com.hostelworld.qacodechallenge:id/passwordEt";
    private final String BTN_LOGIN = "com.hostelworld.qacodechallenge:id/btnLogin";

    public LoginScreenPage(AppiumDriver driver, CustomCommands customCommands) {
        super(driver, customCommands);
    }

    /**
     * Fill email field
     */
    public void fillEmail(String email) {
        customCommands.sendTextById(EMAIL_FIELD, email);
    }

    /**
     * Fill password field
     */
    public void fillPassword(String password) {
        customCommands.sendTextById(PASSWORD_FIELD, password);
    }


    /**
     * Click "Login" button to submit login form
     * After successful login, navigates to UserLoggedInScreen
     */
    public void clickLogin() {
        customCommands.clickById(BTN_LOGIN);
    }

    /**
     * Check if email field is displayed
     */
    public boolean isEmailFieldDisplayed() {
        return isElementDisplayed(EMAIL_FIELD);
    }

    /**
     * Check if password field is displayed
     */
    public boolean isPasswordFieldDisplayed() {
        return isElementDisplayed(PASSWORD_FIELD);
    }

    /**
     * Check if login button is displayed
     */
    public boolean isLoginButtonDisplayed() {
        return isElementDisplayed(BTN_LOGIN);
    }
}

