package com.selenium.bdd.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory class for creating and managing WebDriver instances
 */
public class DriverFactory {
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Initialize WebDriver based on browser type
     */
    public static WebDriver initializeDriver(String browserType) {
        if (driver.get() != null) {
            return driver.get();
        }

        String browser = browserType != null ? browserType : System.getProperty("browser", "chrome").toLowerCase();
        logger.info("Initializing WebDriver for browser: {}", browser);

        WebDriver webDriver = switch (browser) {
            case "firefox" -> createFirefoxDriver();
            case "chrome" -> createChromeDriver();
            default -> {
                logger.warn("Unsupported browser: {}. Defaulting to Chrome", browser);
                yield createChromeDriver();
            }
        };

        driver.set(webDriver);
        logger.info("WebDriver initialized successfully");
        return webDriver;
    }

    /**
     * Create Chrome WebDriver instance
     */
    private static WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        
        // Add headless mode if system property is set
        if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
            options.addArguments("--headless");
            logger.info("Running Chrome in headless mode");
        }
        
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        
        return new ChromeDriver(options);
    }

    /**
     * Create Firefox WebDriver instance
     */
    private static WebDriver createFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        
        // Add headless mode if system property is set
        if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
            options.addArguments("--headless");
            logger.info("Running Firefox in headless mode");
        }
        
        return new FirefoxDriver(options);
    }

    /**
     * Get the current WebDriver instance
     */
    public static WebDriver getDriver() {
        WebDriver webDriver = driver.get();
        if (webDriver == null) {
            logger.warn("WebDriver is null. Initializing...");
            return initializeDriver(null);
        }
        return webDriver;
    }

    /**
     * Quit WebDriver and clean up
     */
    public static void quitDriver() {
        WebDriver webDriver = driver.get();
        if (webDriver != null) {
            logger.info("Quitting WebDriver...");
            try {
                webDriver.quit();
            } catch (Exception e) {
                logger.error("Error while quitting WebDriver", e);
            } finally {
                driver.remove();
            }
        }
    }

    /**
     * Close the current WebDriver window
     */
    public static void closeDriver() {
        WebDriver webDriver = driver.get();
        if (webDriver != null) {
            logger.info("Closing WebDriver window...");
            try {
                webDriver.close();
            } catch (Exception e) {
                logger.error("Error while closing WebDriver", e);
            }
        }
    }
}
