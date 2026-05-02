package com.selenium.bdd.pages;

import com.selenium.bdd.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;

/**
 * Home Page Object Model
 * This is a sample page object for demonstration
 */
public class HomePage extends BasePage {
    
    // Example locators
    @FindBy(css = "h1")
    private WebElement pageTitle;
    
    @FindBy(xpath = "//a[@href='#']")
    private WebElement sampleLink;
    
    // Constructor
    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Get the home page title
     */
    public String getHomePageTitle() {
        logger.info("Getting home page title");
        return getPageTitle();
    }
    
    /**
     * Verify page is loaded
     */
    public boolean isHomePageLoaded() {
        logger.info("Verifying home page is loaded");
        return isElementDisplayed(pageTitle);
    }
    
    /**
     * Get the main heading text
     */
    public String getMainHeadingText() {
        logger.info("Getting main heading text");
        return getElementText(pageTitle);
    }
    
    /**
     * Click on sample link
     */
    public void clickSampleLink() {
        logger.info("Clicking on sample link");
        clickElement(sampleLink);
    }
}
