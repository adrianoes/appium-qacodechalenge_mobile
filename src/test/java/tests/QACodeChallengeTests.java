package tests;

import org.junit.Before;
import org.junit.Test;
import pages.CreateAccountScreenPage;
import pages.LoginScreenPage;
import pages.MainScreenPage;
import pages.UserLoggedInScreenPage;
import support.BaseTest;

import java.net.MalformedURLException;

/**
 * User Successfully Registered Test
 *
 * Scenario: User completes the create account flow and returns to main screen
 * Flow: Main Screen → Create Account Screen → Main Screen
 *
 * Expected Result: User should be able to register and return successfully
 */
public class QACodeChallengeTests extends BaseTest {

    private MainScreenPage mainScreenPage;
    private CreateAccountScreenPage createAccountScreenPage;
    private LoginScreenPage loginScreenPage;
    private UserLoggedInScreenPage userLoggedInScreenPage;

    @Before
    public void setUp() throws MalformedURLException {
        setupDriver();
        mainScreenPage = new MainScreenPage(driver, customCommands);
        createAccountScreenPage = new CreateAccountScreenPage(driver, customCommands);
        loginScreenPage = new LoginScreenPage(driver, customCommands);
        userLoggedInScreenPage = new UserLoggedInScreenPage(driver, customCommands);
    }

    @Test
    public void test01_CreateAccount_ReturnsToMainScreen() {
        // Screen 1: Main Screen
        mainScreenPage.clickCreateAccount();
        waitForScreenTransition();

        // Screen 2: Create Account Screen
        String firstName = createAccountScreenPage.generateFirstName();
        String lastName = createAccountScreenPage.generateLastName();
        String email = createAccountScreenPage.generateEmail();
        String password = createAccountScreenPage.generatePassword();

        createAccountScreenPage.fillFirstName(firstName);
        createAccountScreenPage.fillLastName(lastName);
        createAccountScreenPage.fillEmail(email);
        createAccountScreenPage.fillPassword(password);

        createAccountScreenPage.clickCreateAccount();
        waitForScreenTransition();

        // Back to Screen 1: Main Screen
        assert mainScreenPage.isCreateAccountButtonDisplayed() : "Should return to Main Screen";
    }

    @Test
    public void test02_CreateAccountAndLogin_ValidUser() {
        // Screen 1: Main Screen
        mainScreenPage.clickCreateAccount();
        waitForScreenTransition();

        // Screen 2: Create Account Screen
        String firstName = createAccountScreenPage.generateFirstName();
        String lastName = createAccountScreenPage.generateLastName();
        String email = createAccountScreenPage.generateEmail();
        String password = createAccountScreenPage.generatePassword();

        createAccountScreenPage.fillFirstName(firstName);
        createAccountScreenPage.fillLastName(lastName);
        createAccountScreenPage.fillEmail(email);
        createAccountScreenPage.fillPassword(password);

        createAccountScreenPage.clickCreateAccount();
        waitForScreenTransition();

        // Back to Screen 1: Main Screen
        assert mainScreenPage.isCreateAccountButtonDisplayed() : "Should return to Main Screen";

        mainScreenPage.clickLogin();
        waitForScreenTransition();

        // Screen 3: Login Screen
        loginScreenPage.fillEmail(email);
        loginScreenPage.fillPassword(password);

        loginScreenPage.clickLogin();
        waitForScreenTransition();

        // Screen 4: User Logged In Screen
        waitForScreenTransition();

        String greetingText = userLoggedInScreenPage.getGreetingText();
        assert greetingText.contains("User Logged In") : "Should display 'User Logged In'";

        String displayedFirstName = userLoggedInScreenPage.getFirstNameText();
        assert displayedFirstName.contains(firstName) : "First name mismatch";

        String displayedLastName = userLoggedInScreenPage.getLastNameText();
        assert displayedLastName.contains(lastName) : "Last name mismatch";

        String displayedEmail = userLoggedInScreenPage.getEmailText();
        assert displayedEmail.contains(email) : "Email mismatch";

        userLoggedInScreenPage.clickLogout();
        waitForScreenTransition();

        // Back to Screen 1: Main Screen
        assert mainScreenPage.isCreateAccountButtonDisplayed() : "Should return to Main Screen after logout";
    }

    @Test
    public void test03_NegativeTest_InvalidFirstNameWithNumbers() {
        // Screen 1: Main Screen
        mainScreenPage.clickCreateAccount();
        waitForScreenTransition();

        // Screen 2: Create Account Screen
        String firstName = "John123"; // Invalid: contains numbers
        String lastName = createAccountScreenPage.generateLastName();
        String email = createAccountScreenPage.generateEmail();
        String password = createAccountScreenPage.generatePassword();

        createAccountScreenPage.fillFirstName(firstName);
        createAccountScreenPage.fillLastName(lastName);
        createAccountScreenPage.fillEmail(email);
        createAccountScreenPage.fillPassword(password);

        createAccountScreenPage.clickCreateAccount();
        waitForScreenTransition();

        // EXPECTED: Should stay on Create Account Screen
        assert createAccountScreenPage.isFirstNameFieldDisplayed() :
                "Expected to stay on Create Account Screen";
    }

    @Test
    public void test04_NegativeTest_InvalidLastNameWithNumbers() {
        // Screen 1: Main Screen
        mainScreenPage.clickCreateAccount();
        waitForScreenTransition();

        // Screen 2: Create Account Screen
        String firstName = createAccountScreenPage.generateFirstName();
        String lastName = "Silva@456"; // Invalid: contains special characters and numbers
        String email = createAccountScreenPage.generateEmail();
        String password = createAccountScreenPage.generatePassword();

        createAccountScreenPage.fillFirstName(firstName);
        createAccountScreenPage.fillLastName(lastName);
        createAccountScreenPage.fillEmail(email);
        createAccountScreenPage.fillPassword(password);

        createAccountScreenPage.clickCreateAccount();
        waitForScreenTransition();

        // EXPECTED: Should stay on Create Account Screen
        assert createAccountScreenPage.isLastNameFieldDisplayed() :
                "Expected to stay on Create Account Screen";
    }

    @Test
    public void test05_NegativeTest_InvalidEmailFormat() {
        // Screen 1: Main Screen
        mainScreenPage.clickCreateAccount();
        waitForScreenTransition();

        // Screen 2: Create Account Screen
        String firstName = createAccountScreenPage.generateFirstName();
        String lastName = createAccountScreenPage.generateLastName();
        String email = "john.email.com"; // Invalid: missing @ symbol
        String password = createAccountScreenPage.generatePassword();

        createAccountScreenPage.fillFirstName(firstName);
        createAccountScreenPage.fillLastName(lastName);
        createAccountScreenPage.fillEmail(email);
        createAccountScreenPage.fillPassword(password);

        createAccountScreenPage.clickCreateAccount();
        waitForScreenTransition();

        // EXPECTED: Should stay on Create Account Screen
        assert createAccountScreenPage.isEmailFieldDisplayed() :
                "Expected to stay on Create Account Screen";
    }

    @Test
    public void test06_NegativeTest_InvalidPasswordTooShort() {
        // Screen 1: Main Screen
        mainScreenPage.clickCreateAccount();
        waitForScreenTransition();

        // Screen 2: Create Account Screen
        String firstName = createAccountScreenPage.generateFirstName();
        String lastName = createAccountScreenPage.generateLastName();
        String email = createAccountScreenPage.generateEmail();
        String password = "1"; // Invalid: too short (single character)

        createAccountScreenPage.fillFirstName(firstName);
        createAccountScreenPage.fillLastName(lastName);
        createAccountScreenPage.fillEmail(email);
        createAccountScreenPage.fillPassword(password);

        createAccountScreenPage.clickCreateAccount();
        waitForScreenTransition();

        // EXPECTED: Should stay on Create Account Screen
        assert createAccountScreenPage.isPasswordFieldDisplayed() :
                "Expected to stay on Create Account Screen";
    }

    @Test
    public void test07_LoginWithWrongPassword_ShouldStayOnLoginScreen() {
        // Screen 1: Main Screen
        mainScreenPage.clickCreateAccount();
        waitForScreenTransition();

        // Screen 2: Create Account Screen
        String firstName = createAccountScreenPage.generateFirstName();
        String lastName = createAccountScreenPage.generateLastName();
        String email = createAccountScreenPage.generateEmail();
        String password = createAccountScreenPage.generatePassword();

        createAccountScreenPage.fillFirstName(firstName);
        createAccountScreenPage.fillLastName(lastName);
        createAccountScreenPage.fillEmail(email);
        createAccountScreenPage.fillPassword(password);

        createAccountScreenPage.clickCreateAccount();
        waitForScreenTransition();

        // Back to Screen 1: Main Screen
        assert mainScreenPage.isCreateAccountButtonDisplayed() : "Should return to Main Screen";

        mainScreenPage.clickLogin();
        waitForScreenTransition();

        // Screen 3: Login Screen
        loginScreenPage.fillEmail(email);
        loginScreenPage.fillPassword(password + "_wrong");
        loginScreenPage.clickLogin();
        waitForScreenTransition();

        // EXPECTED: Should stay on Login Screen
        assert loginScreenPage.isEmailFieldDisplayed() : "Expected to stay on Login Screen";
        assert loginScreenPage.isPasswordFieldDisplayed() : "Expected to stay on Login Screen";
        assert loginScreenPage.isLoginButtonDisplayed() : "Expected to stay on Login Screen";
    }

    private void waitForScreenTransition() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
