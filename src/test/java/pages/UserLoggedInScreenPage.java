package pages;

import io.appium.java_client.AppiumDriver;
import support.CustomCommands;

/**
 * User Logged In Screen Page Object
 *
 * Screen Description:
 * - Protected screen that displays logged-in user information
 * - Only accessible after successful login
 * - Display 4 information lines:
 *   1. "User Logged In" - confirmation message
 *   2. "First name: [name]" - user's first name
 *   3. "Last name: [surname]" - user's last name
 *   4. "Email: [email]" - user's email
 * - Button: "Logout" - logs out the user and returns to MainScreen
 *
 * Elements:
 * - greetingTv: Text view showing "User Logged In"
 * - firstNameTv: Text view showing "First name: [name]"
 * - lastNameTv: Text view showing "Last name: [surname]"
 * - emailTv: Text view showing "Email: [email]"
 * - logoutBtn: Logout button
 */
public class UserLoggedInScreenPage extends BasePage {

    // Locators - User Logged In Screen
    private final String GREETING_TEXT = "com.hostelworld.qacodechallenge:id/greetingTv";
    private final String FIRST_NAME_TEXT = "com.hostelworld.qacodechallenge:id/firstNameTv";
    private final String LAST_NAME_TEXT = "com.hostelworld.qacodechallenge:id/lastNameTv";
    private final String EMAIL_TEXT = "com.hostelworld.qacodechallenge:id/emailTv";
    private final String BTN_LOGOUT = "com.hostelworld.qacodechallenge:id/logoutBtn";

    public UserLoggedInScreenPage(AppiumDriver driver, CustomCommands customCommands) {
        super(driver, customCommands);
    }

    /**
     * Get the greeting message text
     * Expected: "User Logged In"
     */
    public String getGreetingText() {
        return customCommands.getTextById(GREETING_TEXT);
    }

    /**
     * Get the displayed first name text
     * Expected format: "First name: [name]"
     */
    public String getFirstNameText() {
        return customCommands.getTextById(FIRST_NAME_TEXT);
    }

    /**
     * Get the displayed last name text
     * Expected format: "Last name: [surname]"
     */
    public String getLastNameText() {
        return customCommands.getTextById(LAST_NAME_TEXT);
    }

    /**
     * Get the displayed email text
     * Expected format: "Email: [email]"
     */
    public String getEmailText() {
        return customCommands.getTextById(EMAIL_TEXT);
    }

    /**
     * Click "Logout" button
     * Returns to MainScreen
     */
    public void clickLogout() {
        customCommands.clickById(BTN_LOGOUT);
    }

    /**
     * Check if greeting text is displayed
     */
    public boolean isGreetingTextDisplayed() {
        return isElementDisplayed(GREETING_TEXT);
    }

    /**
     * Check if first name is displayed
     */
    public boolean isFirstNameDisplayed() {
        return isElementDisplayed(FIRST_NAME_TEXT);
    }

    /**
     * Check if last name is displayed
     */
    public boolean isLastNameDisplayed() {
        return isElementDisplayed(LAST_NAME_TEXT);
    }

    /**
     * Check if email is displayed
     */
    public boolean isEmailDisplayed() {
        return isElementDisplayed(EMAIL_TEXT);
    }

    /**
     * Check if logout button is displayed
     */
    public boolean isLogoutButtonDisplayed() {
        return isElementDisplayed(BTN_LOGOUT);
    }

    /**
     * Validate that all user information is displayed correctly
     */
    public boolean validateAllInformationDisplayed() {
        return isGreetingTextDisplayed() &&
               isFirstNameDisplayed() &&
               isLastNameDisplayed() &&
               isEmailDisplayed() &&
               isLogoutButtonDisplayed();
    }
}

