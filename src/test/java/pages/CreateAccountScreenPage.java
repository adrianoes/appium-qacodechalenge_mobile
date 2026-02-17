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

    // Data storage
    private String generatedFirstName;
    private String generatedLastName;
    private String generatedEmail;
    private String generatedPassword;

    private Faker faker;

    public CreateAccountScreenPage(AppiumDriver driver, CustomCommands customCommands) {
        super(driver, customCommands);
        this.faker = new Faker();
    }

    /**
     * Fill all fields with generated data (using Faker)
     */
    public void fillWithGeneratedData() {
        this.generatedFirstName = faker.name().firstName();
        this.generatedLastName = faker.name().lastName();
        this.generatedEmail = faker.internet().emailAddress();
        this.generatedPassword = faker.internet().password(8, 12, true, true, true);

        fillFields(generatedFirstName, generatedLastName, generatedEmail, generatedPassword);
    }

    /**
     * Fill all fields with specific data
     */
    public void fillWithSpecificData(String firstName, String lastName, String email, String password) {
        this.generatedFirstName = firstName;
        this.generatedLastName = lastName;
        this.generatedEmail = email;
        this.generatedPassword = password;

        fillFields(firstName, lastName, email, password);
    }

    /**
     * Fill all input fields
     */
    private void fillFields(String firstName, String lastName, String email, String password) {
        customCommands.sendTextById(FIRST_NAME_FIELD, firstName);
        customCommands.sendTextById(LAST_NAME_FIELD, lastName);
        customCommands.sendTextById(EMAIL_FIELD, email);
        customCommands.sendTextById(PASSWORD_FIELD, password);
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
        this.generatedFirstName = firstName;
    }

    public void fillLastName(String lastName) {
        customCommands.sendTextById(LAST_NAME_FIELD, lastName);
        this.generatedLastName = lastName;
    }

    public void fillEmail(String email) {
        customCommands.sendTextById(EMAIL_FIELD, email);
        this.generatedEmail = email;
    }

    public void fillPassword(String password) {
        customCommands.sendTextById(PASSWORD_FIELD, password);
        this.generatedPassword = password;
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

    /**
     * Get error messages from fields
     */
    public String getFirstNameErrorMessage() {
        return getErrorText(FIRST_NAME_FIELD);
    }

    public String getLastNameErrorMessage() {
        return getErrorText(LAST_NAME_FIELD);
    }

    public String getEmailErrorMessage() {
        return getErrorText(EMAIL_FIELD);
    }

    public String getPasswordErrorMessage() {
        return getErrorText(PASSWORD_FIELD);
    }

    /**
     * Alias methods for error retrieval (used by tests)
     */
    public String getFirstNameError() {
        return getErrorText(FIRST_NAME_FIELD);
    }

    public String getLastNameError() {
        return getErrorText(LAST_NAME_FIELD);
    }

    public String getEmailError() {
        return getErrorText(EMAIL_FIELD);
    }

    public String getPasswordError() {
        return getErrorText(PASSWORD_FIELD);
    }

    /**
     * Check if error is visible (not null and not empty)
     */
    public boolean isFirstNameErrorVisible() {
        String error = getErrorText(FIRST_NAME_FIELD);
        return error != null && !error.isEmpty();
    }

    public boolean isLastNameErrorVisible() {
        String error = getErrorText(LAST_NAME_FIELD);
        return error != null && !error.isEmpty();
    }

    public boolean isEmailErrorVisible() {
        String error = getErrorText(EMAIL_FIELD);
        return error != null && !error.isEmpty();
    }

    public boolean isPasswordErrorVisible() {
        String error = getErrorText(PASSWORD_FIELD);
        return error != null && !error.isEmpty();
    }

    /**
     * Helper method to get error text from field
     * Try multiple approaches to get error message
     */
    private String getErrorText(String fieldId) {
        try {
            // Try to get error attribute
            String errorAttr = customCommands.getAttributeById(fieldId, "error");
            if (errorAttr != null && !errorAttr.isEmpty()) {
                return errorAttr;
            }

            // Try to get text
            String text = customCommands.getTextById(fieldId);
            if (text != null && !text.isEmpty()) {
                return text;
            }

            return "";
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Get generated data for validation
     */
    public String getGeneratedFirstName() {
        return generatedFirstName;
    }

    public String getGeneratedLastName() {
        return generatedLastName;
    }

    public String getGeneratedEmail() {
        return generatedEmail;
    }

    public String getGeneratedPassword() {
        return generatedPassword;
    }
}

