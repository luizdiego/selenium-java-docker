Feature: Home Page Functionality
  As a user
  I want to interact with the home page
  So that I can verify basic page functionality

  Background:
    Given User navigates to the home page

  Scenario: Verify home page loads successfully
    Then Home page should be loaded successfully

  Scenario: Verify page title contains expected text
    When User gets the page title
    Then Page title should contain "Example"

  Scenario: Verify current URL
    When User captures the current URL
    Then URL should contain "example.com"

  Scenario: Refresh page functionality
    When User refreshes the page
    Then Home page should be loaded successfully
