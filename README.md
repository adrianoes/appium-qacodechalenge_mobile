# appium-qacodechalenge_mobile

UI testing in QA Code Challenge apk from Hostelworld using Appium and Java. This project contains basic examples on how to use Appium, Java and JUnit to test mobile UI. Good practices such as Page Object Model, custom commands and setup/teardown, among others, are used. All the necessary support documentation to develop this project is placed here.

# Pre-requirements:

| Requirement                             | Version       | Note                                                            |
|:----------------------------------------|:--------------| :-------------------------------------------------------------- |
| IntelliJ IDEA Community Edition         | 2025.3.2      | -                                                               |
| JDK                                     | 17.0.12       | -                                                               |
| Maven                                   | 3.9.12        | -                                                               |
| Node.js                                 | 22.16.0       | -                                                               |
| Appium                                  | 3.2.0         | -                                                               |
| Appium Doctor                           | 2.1.15        | -                                                               |
| Appium Inspector                        | 2026.1.3      | -                                                               |
| uiautomator2 driver                     | 4.2.3         | -                                                               |
| SDK Plataform Tools                     | 36.0.2        | -                                                               |
| Android Studio                          | 2025.3.1      | -                                                               |
| app-debug apk                           | 1.0           | -                                                               |
| Virtual device                          | Pixel 4       | -                                                               |
| Virtual device API                      | 10            | -                                                               |
| Selenium Java maven dependency          | 4.40.0        | -                                                               |
| Java client for Appium maven dependency | 10.0.0        | -                                                               |
| JUnit 4 Maven Repository                | 4.13.2        | -                                                               |
| Java Faker Maven Repository             | 1.0.2         | -                                                               |
| JSON in Java Maven Repository           | 20251224      | -                                                               |

# Installation:

- See [IntelliJ IDEA Community Edition download page](https://www.jetbrains.com/idea/download/?section=windows), download and install IntelliJ IDEA Community Edition. Keep all the prefereced options as they are until you reach Instalation Options page. Then, check the checkboxes below:
    - :white_check_mark: **IntelliJ IDEA Community Edition** on Create Desktop Shortcut frame;
    - :white_check_mark: **Add "Open Folder as Project"** in Update Context Menu frame;
    - :white_check_mark: **Add "bin" Folder to the PATH** in Update PATH Variable (restart needed) frame;
    - :white_check_mark: **.java** in Create Associations frame;
    - :white_check_mark: **.gradle** in Create Associations frame;
    - :white_check_mark: **.pom** in Create Associations frame;
    - Hit :point_right: **Next**, :point_right: **Install**, :radio_button: **I want to manually reboot later** and :point_right: **Finish**. Save your stuff and reboot the computer.
- See [Java SE 17 Archive Downloads](https://www.oracle.com/br/java/technologies/javase/jdk17-archive-downloads.html), download the proper version for your OS and install it by keeping the preferenced options.
    - Right click :point_right: **My Computer** and select :point_right: **Properties**. On the :point_right: **Advanced** tab, select :point_right: **Environment Variables**, :point_right: **New** in System Variables frame and create a variable called JAVA_HOME containing the path that leads to where the JDK software is located (e.g. C:\Program Files\Java\jdk-17).
    - Right click :point_right: **My Computer** and select :point_right: **Properties**. On the :point_right: **Advanced** tab, select :point_right: **Environment Variables**, and then edit Path system variable with the new %JAVA_HOME%\bin entry.
- See [Maven download page](https://maven.apache.org/download.cgi), download the xxxBinary zip archive and unzip it in a place of your preference (e.g. C:\Program Files\Maven\apache-maven-3.9.12).
    - Right click :point_right: **My Computer** and select :point_right: **Properties**. On the :point_right: **Advanced** tab, select :point_right: **Environment Variables**, :point_right: **New** in System Variables frame and create a variable called MAVEN_HOME containing the path that leads to where the apache-maven software is located (e.g. C:\Program Files\Maven\apache-maven-3.9.12).
    - Right click :point_right: **My Computer** and select :point_right: **Properties**. On the :point_right: **Advanced** tab, select :point_right: **Environment Variables**, and then edit Path system variable with the new %MAVEN_HOME%\bin entry.
- See [Node.js page](https://nodejs.org/en) and install the aforementioned Node.js version. Keep all the preferenced options as they are.
- See [SDK Plataform Tools download page](https://developer.android.com/tools/releases/platform-tools?hl=pt-br), download the last version and unzip it in C:\Program Files\platform-tools. This step is needed so tests can be executed in real devices.
    - Right click :point_right: **My Computer** and select :point_right: **Properties**. On the :point_right: **Advanced** tab, select :point_right: **Environment Variables**, and then edit Path system variable with the new C:\Program Files\platform-tools entry.
- See [Android Studio download page](https://developer.android.com/), download the last version and install it by keeping the preferenced options. Open Virtual Device Manager and create an image that has Virtual device as Pixel 4 and Virtual device API as 10.
    - Right click :point_right: **My Computer** and select :point_right: **Properties**. On the :point_right: **Advanced** tab, select :point_right: **Environment Variables**, :point_right: **New** in System Variables frame and create a variable called ANDROID_HOME to point to where the sdk software is located (e.g. C:\Users\<user_name>\AppData\Local\Android\Sdk).
    - Right click :point_right: **My Computer** and select :point_right: **Properties**. On the :point_right: **Advanced** tab, select :point_right: **Environment Variables**, and then edit Path system variable with the new %ANDROID_HOME%\platform-tools entry.
- Open your terminal in your project directory and execute ```npm install -g appium``` to install Appium.
- Open your terminal in your project directory and execute ```npm i appium-doctor``` to install Appium Doctor.
- Open your terminal in your project directory and execute ```npx appium-doctor --android``` to run Appium Doctor and check Appium instalation status.
- Open your terminal in your project directory and execute ```npx appium driver install uiautomator2``` to install drivers for automationName and platformName capabilities.
- See [Appium Inspector download page](https://github.com/appium/appium-inspector/releases), download and install it. Open Virtual Device in Android Studio, drag the app-debug.apk to the screen of the virtual device and drop it. Wait for the app to be installed and open it. With the app-debug.apk opened and in first plan, execute the ```adb shell dumpsys window | findstr "mCurrentFocus"``` command in the Command Prompt. It should return something like mCurrentFocus=Window{3558095 u0 com.hostelworld.qacodechallenge/com.hostelworld.qacodechallenge.MainActivity}, where com.hostelworld.qacodechallenge is the appium:appPackage and com.hostelworld.qacodechallenge.MainActivity is the appium:appActivity. Also in the Command Prompt, execute the ```adb devices``` command. It should return a list of attached devices. If you have just the virtual device attached, it will return something like emulator-5554, which is exactly the appium:udid (important in case of multiple devices). In the virtual device, hit :point_right: **Settings**, :point_right: **About emulated device** and use device name as appium:deviceName (e.g. Android SDK built for x86_64). Your capabilities on the Appium Inspector should be, then, like:

  ```
  {
    "platformName": "Android",
    "appium:udid": "emulator-5554",
    "appium:platformVersion": "10.0",
    "appium:deviceName": "Android SDK built for x86_64",
    "appium:automationName": "UIAutomator2",
    "appium:app": "C:\\Users\\<user_name>\\IdeaProjects\\appium-qacodechalenge_mobile\\apk\\app-debug.apk",
    "appium:adbExecTimeout": 120000,
    "appium:autoGrantPermissions": true,
    "appium:appPackage": "com.hostelworld.qacodechallenge",
    "appium:appActivity": ".MainActivity",
    "appium:appWaitActivity": ".MainActivity",
    "appium:appWaitDuration": 20000,
    "appium:noReset": false,
    "appium:autoDismissAlerts": true,
    "appium:uiautomator2ServerInstallTimeout": 60000
  }
  ```  
- Open IntelliJ IDEA, hit :point_right: **New Project**, hit :point_right: **Java** in New Project frame, hit :point_right: **Maven** as Build system option and check the checkboxes below:
    - :white_check_mark: **Add sample code**,
      Hit :point_right: **Create**.
- See [Selenium Java](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java/4.40.0), copy the maven dependency code. Open a dependencies tag in the pom.xml file right below the properties tag and paste the maven dependency copied code there.
- See [Java client for Appium](https://mvnrepository.com/artifact/io.appium/java-client/10.0.0), copy the maven dependency code and paste it in the dependency tag.
- See [JUnit 4](https://mvnrepository.com/artifact/junit/junit/4.13.2), copy the maven dependency code and paste it in the dependency tag.
- See [Java Faker](https://mvnrepository.com/artifact/com.github.javafaker/javafaker/1.0.2), copy the maven dependency code and paste it in the dependency tag.
- See [JSON in Java](https://mvnrepository.com/artifact/org.json/json/20251224), copy the maven dependency code and paste it in the dependency tag. Hit :point_right: **Sync maven changes**. Your dependency tag in the pom.xml file, now, should be something like:

  ```
    <dependencies>
        <!-- Source: https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>4.40.0</version>
            <scope>compile</scope>
        </dependency>

        <!-- Source: https://mvnrepository.com/artifact/io.appium/java-client -->
        <dependency>
            <groupId>io.appium</groupId>
            <artifactId>java-client</artifactId>
            <version>10.0.0</version>
            <scope>compile</scope>
        </dependency>

        <!-- Source: https://mvnrepository.com/artifact/junit/junit -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>

        <!-- Source: https://mvnrepository.com/artifact/com.github.javafaker/javafaker -->
        <dependency>
            <groupId>com.github.javafaker</groupId>
            <artifactId>javafaker</artifactId>
            <version>1.0.2</version>
            <scope>compile</scope>
        </dependency>

        <!-- Source: https://mvnrepository.com/artifact/org.json/json -->
        <dependency>
            <groupId>org.json</groupId>
            <artifactId>json</artifactId>
            <version>20251224</version>
            <scope>compile</scope>
        </dependency>

    </dependencies>
  ``` 
- Open Command Prompt and execute ```appium``` to start appium session.
- Execute Virtual Device Manager on Android Studio.
- Open Appium Inspector and start the appium session.

# Tests:

- `mvn clean test` - Run all JUnit tests.
- `mvn clean test -Dtest=QACodeChallengeTests` - Run the QA Code Challenge tests.

**Note:** This project uses only JUnit 4 (no Cucumber), so all tests are straightforward Java unit tests.

# Test Structure:

All tests are consolidated in a single file: `QACodeChallengeTests.java`

**Reason for consolidation:**
- All tests are part of the same context (user registration and login flow)
- Cleaner code and easier to maintain
- Shared setup between all tests
- Less code duplication

**Tests included:**
- `test01_UserSuccessfullyRegistered()` - Happy path test that validates complete user registration and login flow
- `test02_NegativeTest_InvalidFirstNameWithNumbers()` - Negative test for BUG #2 - validates that first name with numbers should show error
- `test03_NegativeTest_InvalidLastNameWithNumbers()` - Negative test for BUG #2 - validates that last name with special characters should show error
- `test04_NegativeTest_InvalidEmailFormat()` - Negative test for BUG #3 - validates that invalid email format should show error
- `test05_NegativeTest_InvalidPasswordTooShort()` - Negative test for BUG #4 - validates that weak password should show error

**Note:** The 4 negative tests (test02 through test05) are **expected to fail** because they test for validation errors that the app currently does not implement. These tests document the expected behavior that should be present in the application.

# Support:

- [Capabilities](https://appium.io/docs/en/2.0/guides/caps/)
- [Finding Elements - Official Appium Documentation](https://appium.io/docs/en/2.0/guides/finding-elements/)
- [Element Location Strategies - Official Appium Documentation](https://appium.io/docs/en/2.0/guides/locating-elements/)
- [Finding Elements - Best Practices](https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/finding-elements.md)
- [UIAutomator2 Driver - Element Location](https://github.com/appium/appium-uiautomator2-driver#element-location)
- [Altenative way to get appium:appActivity and appium:appPackage with Apk Info](https://apk-info.en.softonic.com/android)
- [Maven repositories](https://mvnrepository.com/)
- [Page Object Model Pattern](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/)
- [Appium Android Emulator not closing or making tests fail](https://stackoverflow.com/q/30405693)
- [mvn command is not recognized as an internal or external command](https://stackoverflow.com/a/26048165)

# Tips:

- When needed, open pom.xml directory and execute ```mvn clean install```. It removes previous build files to ensure a clean environment, compiles the source code and runs tests to recompile the automation.
- **Element Locator Priority (Use in this order - as shown in Appium Inspector from top to bottom):**
  1. **Accessibility ID** - Fastest, cross-platform (Android & iOS), promotes accessibility, most stable. Maps to `content-desc` (Android) or `accessibility-id` (iOS).
  2. **ID/Resource-ID** - Very fast, unique identifier, stable across builds, but Android-specific only.
  3. **Android UIAutomator** - Very powerful for complex queries, fast performance, but complex syntax and Android-only.
  4. **Class Name** - Returns multiple elements, not unique, use only for lists or collections.
  5. **XPath** - Slowest (traverses entire DOM), fragile (breaks with UI changes), use only as last resort. Never use absolute XPath with indexes.
  
  **Best Practices:** Always prioritize Accessibility ID → ID → UIAutomator. Avoid XPath unless absolutely necessary. Use explicit waits (WebDriverWait) instead of Thread.sleep(). Create Page Objects to encapsulate locators. The Appium Inspector displays locators in efficiency order (top = best).
- To check installed Appium drivers, run `appium driver list --installed`.
- The project uses **Page Object Model (POM)** pattern with:
  - `BasePage.java` - Base class with common functionality
  - Individual Page Objects for each app screen
  - `CustomCommands.java` - Reusable commands for element interaction
  - `BaseTest.java` - Test setup and teardown with driver management

# Known Issues:

- **BUG #1 - Email field uses incorrect ID in Login screen**
    - :red_circle: **Severity:** CRITICAL
    - :memo: **Description:** The email field in the Login screen uses ID `usernameEt` instead of `emailEt`. This is a naming inconsistency in the APK that causes confusion about field purpose.
    - :target: **Affected Element:** `com.hostelworld.qacodechallenge:id/usernameEt`
    - :warning: **Impact:** Semantic error in the application. Tests must use the incorrect ID to fill email field.
    - :wrench: **Workaround:** Use `com.hostelworld.qacodechallenge:id/usernameEt` for email input during login.

- **BUG #2 - App accepts numbers and special characters in First Name and Last Name fields**
    - :orange_circle: **Severity:** HIGH
    - :memo: **Description:** The First Name and Last Name fields accept numeric and special characters when they should only accept alphabetic characters.
    - :target: **Affected Elements:**
      - `com.hostelworld.qacodechallenge:id/firstNameEt`
      - `com.hostelworld.qacodechallenge:id/lastNameEt`
    - :x: **Invalid Examples Accepted:** `John123`, `Silva@456`, `Pedro#789`
    - :warning: **Impact:** Invalid user data is saved to the database, causing data integrity issues and violating business rules.
    - :test_tube: **Related Tests:** `test02_NegativeTest_InvalidFirstNameWithNumbers()`, `test03_NegativeTest_InvalidLastNameWithNumbers()`

- **BUG #3 - App accepts invalid email formats**
    - :orange_circle: **Severity:** HIGH
    - :memo: **Description:** The email field accepts invalid email formats that do not follow standard email validation (missing @ symbol, missing domain, etc).
    - :target: **Affected Element:** `com.hostelworld.qacodechallenge:id/emailEt`
    - :x: **Invalid Examples Accepted:** `john.email.com` (missing @), `john@` (missing domain), `@email.com` (missing user)
    - :warning: **Impact:** Invalid emails are stored in database, making it impossible to send communications to these users and storing invalid contact information.
    - :test_tube: **Related Tests:** `test04_NegativeTest_InvalidEmailFormat()`

- **BUG #4 - App accepts weak and very short passwords**
    - :yellow_circle: **Severity:** MEDIUM
    - :memo: **Description:** The password field accepts very weak passwords without proper validation (single characters, no complexity requirements).
    - :target: **Affected Element:** `com.hostelworld.qacodechallenge:id/passwordEt`
    - :x: **Invalid Examples Accepted:** `1` (single character), `a` (single character), `123` (numbers only)
    - :warning: **Impact:** User accounts with weak passwords are vulnerable to brute force attacks, creating security risks and not meeting minimum password standards.
    - :test_tube: **Related Tests:** `test05_NegativeTest_InvalidPasswordTooShort()`

- **BUG #5 - Last Name field is not mandatory**
    - :yellow_circle: **Severity:** MEDIUM
    - :memo: **Description:** The Last Name field can be left blank during user registration, when it should be a required field.
    - :target: **Affected Element:** `com.hostelworld.qacodechallenge:id/lastNameEt`
    - :warning: **Impact:** Users can be created with incomplete data, violating business requirements and causing issues with displaying full user names.
    - :bulb: **Expected Behavior:** Field should be marked as required with error message "Invalid Last name" when left empty.

- **BUG #6 - Validation error messages are confusing**
    - :yellow_circle: **Severity:** MEDIUM
    - :memo: **Description:** When required fields are not filled, the app displays generic error message "Invalid email" instead of descriptive messages like "Field is required" or "This field is mandatory".
    - :target: **Affected Behavior:** 
      - Leave email blank → Error: "Invalid email" ❌ (should be "Field is required")
      - Leave password blank → Error: "Invalid email" ❌ (should be "Field is required")
    - :warning: **Impact:** Users are confused about the actual problem, creating poor user experience and making validation tests more difficult to verify.

# JIRA Automation (Java):

- This project includes a Java runner that executes tests, generates the Surefire HTML report, and opens one JIRA ticket per failed test with attached reports and logs.
- The script reads credentials from `.env` (based on `example.env`) and uses the JIRA REST API.

**One command (tests + HTML report + JIRA):**

```
mvn -Pjira-report verify
```

**Run (manual Java):**

```
java -cp target/classes org.example.jira.JiraMavenReporter
```

**Build first if needed:**

```
mvn -DskipTests clean compile
```
