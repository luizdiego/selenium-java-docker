package com.selenium.bdd.core;

import org.openqa.selenium.WebDriver;

/**
 * Test context to share data between step definitions
 * This is a singleton pattern implementation for Cucumber scenarios
 */
public class ScenarioContext {
    private WebDriver driver;
    private String lastCapturedValue;

    /**
     * Get WebDriver instance
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Set WebDriver instance
     */
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Get last captured value
     */
    public String getLastCapturedValue() {
        return lastCapturedValue;
    }

    /**
     * Set last captured value
     */
    public void setLastCapturedValue(String value) {
        this.lastCapturedValue = value;
    }

    /**
     * Clear context data
     */
    public void clear() {
        this.driver = null;
        this.lastCapturedValue = null;
    }
}
