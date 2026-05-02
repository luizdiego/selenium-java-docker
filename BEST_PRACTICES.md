# Best Practices for Selenium Java BDD Project

## Project Structure

```
src/
├── main/
│   └── java/com/selenium/bdd/
│       ├── core/          # Core framework classes
│       ├── pages/         # Page Object Models
│       └── utils/         # Utility classes
└── test/
    ├── java/com/selenium/bdd/
    │   ├── stepdefinitions/   # Cucumber step definitions
    │   └── runners/           # Test runners
    └── resources/
        ├── features/          # Gherkin feature files
        └── application.properties  # Configuration
```

## BDD (Behavior-Driven Development) Guidelines

### Feature Files (Gherkin)

1. **Use descriptive feature names**: Feature names should describe what the feature does
   ```gherkin
   Feature: User login functionality
   ```

2. **Use scenarios with clear Given-When-Then structure**:
   ```gherkin
   Scenario: Successful login with valid credentials
       Given User is on the login page
       When User enters valid credentials
       Then User should be logged in successfully
   ```

3. **Keep scenarios independent**: Each scenario should be able to run independently

4. **Use Scenario Outlines for multiple data sets**:
   ```gherkin
   Scenario Outline: Test with multiple browsers
       Given Browser is <browser>
       Then Test runs successfully
       Examples:
           | browser |
           | chrome  |
           | firefox |
   ```

### Step Definitions

1. **Keep steps simple and reusable**: Each step should represent a single user action
   ```java
   @When("User clicks the login button")
   public void userClicksLoginButton() {
       homePage.clickLoginButton();
   }
   ```

2. **Use meaningful step names**: Steps should be readable and self-documenting

3. **Avoid hard-coded values**: Use scenario parameters
   ```java
   @When("User enters {string} in the email field")
   public void userEntersEmailField(String email) {
       loginPage.enterEmail(email);
   }
   ```

4. **Use Before and After hooks** for setup and teardown:
   ```java
   @Before
   public void setUp() {
       driver = DriverFactory.initializeDriver(null);
   }

   @After
   public void tearDown() {
       DriverFactory.quitDriver();
   }
   ```

## Page Object Model (POM) Guidelines

1. **Extend BasePage**: All page objects should extend the BasePage class
   ```java
   public class LoginPage extends BasePage {
       public LoginPage(WebDriver driver) {
           super(driver);
       }
   }
   ```

2. **Use @FindBy for element location**:
   ```java
   @FindBy(id = "username")
   private WebElement usernameField;
   ```

3. **Encapsulate page actions**: Hide implementation details
   ```java
   public void login(String username, String password) {
       typeText(usernameField, username);
       typeText(passwordField, password);
       clickElement(loginButton);
   }
   ```

4. **Return appropriate types from methods**:
   - Return `void` for actions that don't navigate
   - Return page object for navigation (fluent pattern)
   - Return data for verification

## Test Automation Best Practices

### 1. Use Explicit Waits
```java
WebElement element = WaitUtils.waitForElementToBeVisible(driver, locator);
```

### 2. Avoid Hard-Coded Waits
```java
// BAD
Thread.sleep(5000);

// GOOD
WaitUtils.waitForElementToBeClickable(driver, locator);
```

### 3. Use Descriptive Test Names
```java
// BAD
public void test1() { }

// GOOD
public void userShouldNotLoginWithInvalidCredentials() { }
```

### 4. Implement Proper Exception Handling
```java
try {
    // Test code
} catch (TimeoutException e) {
    logger.error("Element not found within timeout", e);
    // Take screenshot for debugging
}
```

### 5. Use Logging
```java
logger.info("Clicking on login button");
logger.debug("Element properties: {}", element.getAttribute("id"));
logger.error("Test failed with exception", exception);
```

### 6. Screenshot on Failure
```java
@After
public void tearDown(Scenario scenario) {
    if (scenario.isFailed()) {
        ScreenshotUtils.takeScreenshot(driver, scenario.getName());
    }
    DriverFactory.quitDriver();
}
```

### 7. Use Assertions from AssertJ
```java
assertThat(actualValue)
    .as("User should be logged in")
    .isEqualTo(expectedValue);

assertThat(elementText)
    .as("Login successful message should be displayed")
    .contains("Welcome");
```

## Configuration Management

1. **Use application.properties for configuration**:
   ```properties
   base.url=https://example.com
   browser=chrome
   implicit.wait=10
   ```

2. **Use environment variables for sensitive data**:
   ```java
   String password = System.getenv("TEST_PASSWORD");
   ```

3. **Use system properties for test execution**:
   ```bash
   mvn test -Dbrowser=firefox -Dheadless=true
   ```

## Code Quality

1. **Follow Java naming conventions**
   - Classes: PascalCase (HomePage)
   - Methods: camelCase (clickLoginButton)
   - Constants: UPPER_SNAKE_CASE (DEFAULT_TIMEOUT)

2. **Keep methods small and focused**: Each method should do one thing

3. **Use meaningful variable names**: Avoid single-letter names (except loop counters)

4. **Add JavaDoc comments to public methods**:
   ```java
   /**
    * Login with provided credentials
    * @param username user email or username
    * @param password user password
    */
   public void login(String username, String password) { }
   ```

## CI/CD Integration

1. **Run tests in Docker**: Ensures consistency across environments

2. **Generate reports**: HTML and XML reports for analysis

3. **Implement parallel execution**: For faster test runs

4. **Monitor test results**: Track pass/fail rates over time

## Maintenance

1. **Keep dependencies updated**: Regular Maven updates

2. **Review and refactor**: Remove duplicate code

3. **Update documentation**: Keep README and guides current

4. **Archive test data**: Old test results and screenshots

5. **Review logs**: Identify and fix intermittent failures

## Troubleshooting

1. **Intermittent failures**: Usually caused by timing issues
   - Use explicit waits
   - Check for race conditions

2. **Element not found**: Verify selectors are correct
   - Use browser developer tools
   - Check for dynamic element creation

3. **WebDriver timeout**: Increase timeout or check network
   - Verify application is responding
   - Check system resources

4. **Port conflicts**: Change port numbers in configuration
   - docker-compose.yml
   - application.properties

## Resources

- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [Cucumber Documentation](https://cucumber.io/docs/gherkin/)
- [JUnit 5 Documentation](https://junit.org/junit5/docs/current/user-guide/)
- [AssertJ Documentation](https://assertj.github.io/assertj-core/)
