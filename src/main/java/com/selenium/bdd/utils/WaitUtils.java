package com.selenium.bdd.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

/**
 * Utility class for common wait operations
 */
public class WaitUtils {
    private static final Logger logger = LoggerFactory.getLogger(WaitUtils.class);
    private static final int DEFAULT_TIMEOUT = 10;

    /**
     * Wait for an element to be visible
     */
    public static WebElement waitForElementToBeVisible(WebDriver driver, By locator) {
        return waitForElementToBeVisible(driver, locator, DEFAULT_TIMEOUT);
    }

    /**
     * Wait for an element to be visible with custom timeout
     */
    public static WebElement waitForElementToBeVisible(WebDriver driver, By locator, int timeoutInSeconds) {
        logger.debug("Waiting for element to be visible: {}", locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for an element to be clickable
     */
    public static WebElement waitForElementToBeClickable(WebDriver driver, By locator) {
        return waitForElementToBeClickable(driver, locator, DEFAULT_TIMEOUT);
    }

    /**
     * Wait for an element to be clickable with custom timeout
     */
    public static WebElement waitForElementToBeClickable(WebDriver driver, By locator, int timeoutInSeconds) {
        logger.debug("Waiting for element to be clickable: {}", locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Wait for an element to be present in DOM
     */
    public static WebElement waitForElementToBePresent(WebDriver driver, By locator) {
        return waitForElementToBePresent(driver, locator, DEFAULT_TIMEOUT);
    }

    /**
     * Wait for an element to be present in DOM with custom timeout
     */
    public static WebElement waitForElementToBePresent(WebDriver driver, By locator, int timeoutInSeconds) {
        logger.debug("Waiting for element to be present: {}", locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Wait for all elements to be visible
     */
    public static List<WebElement> waitForElementsToBeVisible(WebDriver driver, By locator) {
        return waitForElementsToBeVisible(driver, locator, DEFAULT_TIMEOUT);
    }

    /**
     * Wait for all elements to be visible with custom timeout
     */
    public static List<WebElement> waitForElementsToBeVisible(WebDriver driver, By locator, int timeoutInSeconds) {
        logger.debug("Waiting for elements to be visible: {}", locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    /**
     * Wait for element to disappear
     */
    public static boolean waitForElementToDisappear(WebDriver driver, By locator) {
        return waitForElementToDisappear(driver, locator, DEFAULT_TIMEOUT);
    }

    /**
     * Wait for element to disappear with custom timeout
     */
    public static boolean waitForElementToDisappear(WebDriver driver, By locator, int timeoutInSeconds) {
        logger.debug("Waiting for element to disappear: {}", locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Wait for text to be present in element
     */
    public static boolean waitForTextToBePresentInElement(WebDriver driver, By locator, String text) {
        return waitForTextToBePresentInElement(driver, locator, text, DEFAULT_TIMEOUT);
    }

    /**
     * Wait for text to be present in element with custom timeout
     */
    public static boolean waitForTextToBePresentInElement(WebDriver driver, By locator, String text, int timeoutInSeconds) {
        logger.debug("Waiting for text '{}' to be present in element: {}", text, locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    /**
     * Implicit wait
     */
    public static void implicitWait(WebDriver driver, int timeoutInSeconds) {
        logger.debug("Setting implicit wait to {} seconds", timeoutInSeconds);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeoutInSeconds));
    }

    /**
     * Explicit wait with custom polling interval
     */
    public static WebElement waitWithPollingInterval(WebDriver driver, By locator, int timeoutInSeconds, int pollingIntervalMs) {
        logger.debug("Waiting for element with polling interval: {}", locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds), Duration.ofMillis(pollingIntervalMs));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
