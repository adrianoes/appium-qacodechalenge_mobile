package support;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Base class for test setup and teardown
 * Manages Appium driver initialization and cleanup
 *
 * App Structure (4 Screens):
 * 1. Main Screen - Starting point with 2 buttons (Create Account, Login)
 * 2. Create Account Screen - Registration form with 4 fields
 * 3. Login Screen - Login form with 2 fields (Email, Password)
 * 4. User Logged In Screen - Protected screen showing user information
 */
public class BaseTest {

    protected static AppiumDriver driver;
    protected static CustomCommands customCommands;

    @Rule
    public TestWatcher screenshotOnFailure = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            captureFailureScreenshot(description);
        }

        @Override
        protected void finished(Description description) {
            teardownDriver();
        }
    };

    /**
     * Setup Appium driver with Android capabilities
     */
    public static void setupDriver() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();

        // Platform configuration
        options.setPlatformName("Android");
        options.setPlatformVersion("10.0");
        options.setDeviceName("Android SDK built for x86_64");
        options.setUdid("emulator-5554");

        // App configuration
        String appPath = System.getProperty("app.path", System.getProperty("user.dir") + "/apk/app-debug.apk");
        options.setApp(appPath);
        options.setAppPackage("com.hostelworld.qacodechallenge");
        options.setAppActivity(".MainActivity");
        options.setAppWaitActivity(".MainActivity");

        // Automation configuration
        options.setAutomationName("UIAutomator2");
        options.setAutoGrantPermissions(true);
        options.setNoReset(false);  // Reset app for clean state each run - prevents duplicate user conflicts

        // Timeouts
        options.setAdbExecTimeout(Duration.ofSeconds(120));
        options.setAppWaitDuration(Duration.ofSeconds(20));
        options.setUiautomator2ServerInstallTimeout(Duration.ofSeconds(60));

        // Initialize driver
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Wait for app to fully load
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Initialize custom commands
        customCommands = new CustomCommands(driver);
    }

    /**
     * Teardown - quit driver
     */
    public static void teardownDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void captureFailureScreenshot(Description description) {
        if (driver == null) {
            return;
        }
        if (!(driver instanceof TakesScreenshot)) {
            return;
        }

        try {
            Path screenshotsDir = Paths.get("target", "screenshots");
            Files.createDirectories(screenshotsDir);

            String safeName = description.getClassName() + "_" + description.getMethodName();
            String timestamp = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss").format(LocalDateTime.now());
            Path screenshotPath = screenshotsDir.resolve(safeName + "_" + timestamp + ".png");

            Path tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE).toPath();
            Files.copy(tempFile, screenshotPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ignored) {
            // Avoid masking the original test failure.
        }
    }
}
