package pages;

import com.github.javafaker.Faker;
import io.appium.java_client.AppiumDriver;
import support.CustomCommands;

/**
 * Create Account Screen Page Object
 *
 * Screen Description:
 * - Registration form with 4 input fields and 1 button
 * - Field 1: "First Name" - accepts any text
 * - Field 2: "Last Name" - accepts any text
 * - Field 3: "Email" - for email registration
 * - Field 4: "Password" - for password entry
 * - Button: "Create Account" - submits the form and returns to MainScreen
 *
 * Note: BUG #2 - App accepts numbers in First Name and Last Name
 * Note: BUG #3 - App accepts invalid email format
 * Note: BUG #4 - Password validation is weak
 *
 * Elements:
 * - firstNameEt: First Name input field
 * - lastNameEt: Last Name input field
 * - emailEt: Email input field
 * - passwordEt: Password input field
 * - btnCreateAccount: Create Account button
 */
public class CreateAccountScreenPage extends BasePage {

    // Locators - Create Account Screen
    private final String FIRST_NAME_FIELD = "com.hostelworld.qacodechallenge:id/firstNameEt";
    private final String LAST_NAME_FIELD = "com.hostelworld.qacodechallenge:id/lastNameEt";
    private final String EMAIL_FIELD = "com.hostelworld.qacodechallenge:id/emailEt";
    private final String PASSWORD_FIELD = "com.hostelworld.qacodechallenge:id/passwordEt";
    private final String BTN_CREATE_ACCOUNT = "com.hostelworld.qacodechallenge:id/btnCreateAccount";


    private Faker faker;

    public CreateAccountScreenPage(AppiumDriver driver, CustomCommands customCommands) {
        super(driver, customCommands);
        this.faker = new Faker();
    }


    /**
     * Click "Create Account" button to submit the form
     * After successful submission, returns to MainScreen
     */
    public void clickCreateAccount() {
        customCommands.clickById(BTN_CREATE_ACCOUNT);
    }

    /**
     * Fill individual fields
     */
    public void fillFirstName(String firstName) {
        customCommands.sendTextById(FIRST_NAME_FIELD, firstName);
    }

    public void fillLastName(String lastName) {
        customCommands.sendTextById(LAST_NAME_FIELD, lastName);
    }

    public void fillEmail(String email) {
        customCommands.sendTextById(EMAIL_FIELD, email);
    }

    public void fillPassword(String password) {
        customCommands.sendTextById(PASSWORD_FIELD, password);
    }

    /**
     * Generate individual data fields
     */
    public String generateFirstName() {
        return faker.name().firstName();
    }

    public String generateLastName() {
        return faker.name().lastName();
    }

    public String generateEmail() {
        return faker.internet().emailAddress();
    }

    public String generatePassword() {
        return faker.internet().password(8, 12, true, true, true);
    }

    /**
     * Check if fields are displayed
     */
    public boolean isFirstNameFieldDisplayed() {
        return isElementDisplayed(FIRST_NAME_FIELD);
    }

    public boolean isLastNameFieldDisplayed() {
        return isElementDisplayed(LAST_NAME_FIELD);
    }

    public boolean isEmailFieldDisplayed() {
        return isElementDisplayed(EMAIL_FIELD);
    }

    public boolean isPasswordFieldDisplayed() {
        return isElementDisplayed(PASSWORD_FIELD);
    }
}

