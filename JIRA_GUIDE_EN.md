# 🎯 Quick Guide - JIRA Integration

## ⚙️ Initial Setup (One Time Only)

### 1. Copy example file
```bash
# The example.env already exists in the project, now create the .env
# Create manually or run:
copy example.env .env
```

### 2. Get your JIRA API Token
1. Go to: https://id.atlassian.com/manage-profile/security/api-tokens
2. Click on **"Create API token"**
3. Give it a name (ex: "Appium QA Code Challenge")
4. Copy the generated token ⚠️ (it only appears once!)

### 3. Configure the .env file
Open the `.env` file and fill it with your data:

```env
JIRA_BASE_URL="https://your-company.atlassian.net"
JIRA_EMAIL="your.email@company.com"
JIRA_API_TOKEN="paste_your_token_here"
JIRA_PROJECT_KEY="DEV"
JIRA_ISSUE_TYPE="Bug"
```

**Where to find each information:**
- `JIRA_BASE_URL`: URL you use to access your JIRA (ex: https://mycompany.atlassian.net)
- `JIRA_EMAIL`: Your JIRA account email
- `JIRA_API_TOKEN`: Token you just generated
- `JIRA_PROJECT_KEY`: Project code (visible in ticket URLs, ex: if tickets are DEV-123, the key is DEV)
- `JIRA_ISSUE_TYPE`: Issue type (Bug, Task, Story, etc.)

### 4. Check permissions
Make sure your JIRA account has permission to:
- ✅ Create issues in the specified project
- ✅ Access JIRA REST API

---

## 🚀 How to Use

### Run Tests Only
```bash
mvn clean test
```

### Run Tests + HTML Report + JIRA Tickets (One Command)
```bash
mvn -Pjira-report verify
```

**What happens:**
1. ✅ Runs all Appium + JUnit tests
2. ✅ Generates Surefire HTML report (`target/site`)
3. ✅ Analyzes Surefire XML results (`target/surefire-reports`)
4. ✅ If any tests fail, creates **one JIRA issue per failed test**
5. ✅ Attaches reports, logs, and screenshots to each issue

---

## 📊 What Goes to JIRA

When a test fails, the created issue contains:

### ✅ Included Information:
- **Summary**: `[Automated Test Failure] <test name>`
- **Description with:**
  - ❌ Failed test name and duration
  - 📝 Error message and stack trace
  - 📅 Execution timestamp
  - 🧩 Environment details

### 🏷️ Automatic Metadata:
- **Labels**: `MOBILE`, `APPIUM`, `JAVA`, `MAVEN`, `automated-test`
- **Issue Type**: As configured in `.env` (default: Bug)
- **Project**: As configured in `.env`

### 📎 Attachments:
- Surefire HTML report (`target/site`)
- Surefire XML/TXT results (`target/surefire-reports`)
- Screenshots on failure (`target/screenshots`)
- Logs (`appium.log`, `appium-ci.log`, `test_output.txt` when available)

---

## 🔍 Example Output

### ✅ When All Tests Pass:
```
[OK] All tests passed. No JIRA issues created.
```

### ❌ When Tests Fail:
```
[INFO] Creating JIRA issue for: tests.QACodeChallengeTests.test03_NegativeTest_InvalidFirstNameWithNumbers
[OK] Issue created: DEV-164 - https://your-company.atlassian.net/browse/DEV-164
```

---

## ⚠️ Troubleshooting

### "Missing JIRA configuration"
**Solution**: Configure the `.env` file with your credentials

### "Failed to create issue: 401 Unauthorized"
**Cause**: Invalid credentials
**Solution**:
- Verify the email is correct
- Generate a new API token
- Confirm you copied the complete token

### "Failed to create issue: 404 Not Found"
**Cause**: Project or issue type not found
**Solution**:
- Verify `JIRA_PROJECT_KEY` is correct
- Confirm the issue type exists in the project

### JIRA is not creating issues even though tests are failing
**Check**:
1. Does `.env` file exist in the project root?
2. Are all variables filled?
3. Did you run `mvn -Pjira-report verify` (not just `mvn clean test`)?
4. Check the terminal output for error messages

---

## 🔐 Security

### ✅ Best Practices:
- ✅ `.env` is in `.gitignore` (won't go to Git)
- ✅ Use API token (never use your JIRA password)
- ✅ Revoke tokens you no longer use
- ✅ Don't share your `.env` with anyone

### ⚠️ NEVER do:
- ❌ Commit the `.env` file
- ❌ Share your API token
- ❌ Use your JIRA password instead of token
- ❌ Leave `.env` in public repositories

---

## 🧪 Testing the Integration

### 1. Run the Jira combo command:
```bash
mvn -Pjira-report verify
```

### 2. Check:
- ✅ Should show "Creating JIRA issue for:" in the terminal
- ✅ Issue links should appear in the output
- ✅ Access the issues and verify attachments

---

## 📚 Useful Commands

```bash
# Run tests only
mvn clean test

# Run tests + HTML report + JIRA tickets
mvn -Pjira-report verify

# Generate HTML report only (without running tests)
mvn -DskipTests surefire-report:report-only
```

---

## 🎯 Configuration Checklist

- [ ] Java 17 installed
- [ ] Maven installed
- [ ] Appium running
- [ ] Emulator/device running
- [ ] `.env` file created in root
- [ ] JIRA_BASE_URL configured
- [ ] JIRA_EMAIL configured
- [ ] JIRA_API_TOKEN generated and configured
- [ ] JIRA_PROJECT_KEY configured
- [ ] Permissions verified in JIRA
- [ ] Tests executed successfully (`mvn -Pjira-report verify`)

---

**Done! Now every time you run `mvn -Pjira-report verify` and there are failures, issues will be automatically created in JIRA! 🎉**
