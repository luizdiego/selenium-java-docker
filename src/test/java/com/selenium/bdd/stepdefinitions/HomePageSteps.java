package com.selenium.bdd.stepdefinitions;

import com.selenium.bdd.core.DriverFactory;
import com.selenium.bdd.pages.HomePage;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Step Definitions for Home Page tests
 */
public class HomePageSteps {
    private static final Logger logger = LoggerFactory.getLogger(HomePageSteps.class);
    private WebDriver driver;
    private HomePage homePage;

    /**
     * Setup before each scenario
     */
    @Before
    public void setUp() {
        logger.info("Setting up test...");
        driver = DriverFactory.initializeDriver(null);
        homePage = new HomePage(driver);
    }

    /**
     * Teardown after each scenario
     */
    @After
    public void tearDown() {
        logger.info("Tearing down test...");
        DriverFactory.quitDriver();
    }

    /**
     * Step: User navigates to home page
     */
    @Given("User navigates to the home page")
    public void userNavigatesToHomePage() {
        logger.info("Step: User navigates to the home page");
        String homePageUrl = System.getProperty("base.url", "https://www.example.com");
        homePage.navigateTo(homePageUrl);
    }

    /**
     * Step: Verify home page is loaded
     */
    @Then("Home page should be loaded successfully")
    public void verifyHomePageIsLoaded() {
        logger.info("Step: Verifying home page is loaded");
        assertThat(homePage.isHomePageLoaded())
                .as("Home page should be loaded")
                .isTrue();
    }

    /**
     * Step: Get page title
     */
    @When("User gets the page title")
    public void userGetsPageTitle() {
        logger.info("Step: User gets the page title");
        String title = homePage.getHomePageTitle();
        logger.info("Page title: {}", title);
    }

    /**
     * Step: Verify page title contains specific text
     */
    @Then("Page title should contain {string}")
    public void verifyPageTitleContains(String expectedTitle) {
        logger.info("Step: Verifying page title contains '{}'", expectedTitle);
        String actualTitle = homePage.getHomePageTitle();
        assertThat(actualTitle)
                .as("Page title should contain '" + expectedTitle + "'")
                .contains(expectedTitle);
    }

    /**
     * Step: Get current URL
     */
    @When("User captures the current URL")
    public void userCapturesCurrentUrl() {
        logger.info("Step: User captures the current URL");
        String currentUrl = homePage.getCurrentUrl();
        logger.info("Current URL: {}", currentUrl);
    }

    /**
     * Step: Verify current URL contains specific text
     */
    @Then("URL should contain {string}")
    public void verifyUrlContains(String expectedUrl) {
        logger.info("Step: Verifying URL contains '{}'", expectedUrl);
        String actualUrl = homePage.getCurrentUrl();
        assertThat(actualUrl)
                .as("URL should contain '" + expectedUrl + "'")
                .contains(expectedUrl);
    }

    /**
     * Step: Refresh the page
     */
    @When("User refreshes the page")
    public void userRefreshesPage() {
        logger.info("Step: User refreshes the page");
        homePage.refreshPage();
    }
}
