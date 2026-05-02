package com.selenium.bdd.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * Base Page Object Model class
 * All page objects should extend this class
 */
public class BasePage {
    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    protected WebDriver driver;
    protected WebDriverWait wait;

    /**
     * Constructor to initialize WebDriver and WebDriverWait
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Navigate to a specific URL
     */
    public void navigateTo(String url) {
        logger.info("Navigating to URL: {}", url);
        driver.navigate().to(url);
    }

    /**
     * Get the current page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Get the current page URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Refresh the page
     */
    public void refreshPage() {
        logger.info("Refreshing the page");
        driver.navigate().refresh();
    }

    /**
     * Click on a web element
     */
    public void clickElement(WebElement element) {
        logger.info("Clicking on element");
        element.click();
    }

    /**
     * Type text into a web element
     */
    public void typeText(WebElement element, String text) {
        logger.info("Typing text: {} into element", text);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Get text from a web element
     */
    public String getElementText(WebElement element) {
        String text = element.getText();
        logger.info("Retrieved text from element: {}", text);
        return text;
    }

    /**
     * Check if an element is displayed
     */
    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            logger.warn("Element is not displayed", e);
            return false;
        }
    }

    /**
     * Switch to a new window/tab
     */
    public void switchToNewWindow() {
        logger.info("Switching to new window");
        String currentHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(currentHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    /**
     * Switch back to main window
     */
    public void switchToMainWindow() {
        logger.info("Switching to main window");
        driver.switchTo().window(driver.getWindowHandles().iterator().next());
    }
}
