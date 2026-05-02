package com.selenium.bdd.runners;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

/**
 * Cucumber Test Runner for JUnit 5
 * Executes all feature files from features directory
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(
        key = Constants.PLUGIN_PROPERTY_NAME,
        value = "pretty," +
                "html:target/cucumber-report.html," +
                "json:target/cucumber-report.json," +
                "junit:target/cucumber-report.xml"
)
@ConfigurationParameter(
        key = Constants.GLUE_PROPERTY_NAME,
        value = "com.selenium.bdd.stepdefinitions"
)
@ConfigurationParameter(
        key = Constants.DRY_RUN_PROPERTY_NAME,
        value = "false"
)
public class CucumberTestRunner {
    /**
     * This class remains empty; it is used only as a holder for the above annotations.
     * JUnit 5 will use these annotations to configure and run Cucumber tests.
     */
}
