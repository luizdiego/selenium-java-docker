package com.selenium.bdd.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Screenshot and file utility helper class
 */
public class ScreenshotUtils {
    private static final Logger logger = LoggerFactory.getLogger(ScreenshotUtils.class);
    private static final String SCREENSHOT_DIR = "screenshots";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    static {
        createScreenshotDirectory();
    }

    /**
     * Create screenshot directory if it doesn't exist
     */
    private static void createScreenshotDirectory() {
        File dir = new File(SCREENSHOT_DIR);
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                logger.info("Screenshot directory created: {}", SCREENSHOT_DIR);
            }
        }
    }

    /**
     * Take screenshot and save to file
     */
    public static String takeScreenshot(WebDriver driver, String screenshotName) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            String timestamp = LocalDateTime.now().format(formatter);
            String destinationPath = SCREENSHOT_DIR + File.separator + screenshotName + "_" + timestamp + ".png";
            Files.copy(source.toPath(), Paths.get(destinationPath));
            logger.info("Screenshot saved: {}", destinationPath);
            return destinationPath;
        } catch (IOException e) {
            logger.error("Failed to take screenshot", e);
            return null;
        }
    }

    /**
     * Take screenshot with default naming
     */
    public static String takeScreenshot(WebDriver driver) {
        return takeScreenshot(driver, "screenshot");
    }

    /**
     * Delete all screenshots in the directory
     */
    public static void clearScreenshots() {
        try {
            File dir = new File(SCREENSHOT_DIR);
            if (dir.exists() && dir.isDirectory()) {
                File[] files = dir.listFiles((d, name) -> name.endsWith(".png"));
                if (files != null) {
                    for (File file : files) {
                        if (file.delete()) {
                            logger.debug("Deleted screenshot: {}", file.getName());
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error clearing screenshots", e);
        }
    }
}
