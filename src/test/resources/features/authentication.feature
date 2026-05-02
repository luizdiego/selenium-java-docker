Feature: Authentication Flow
  As a user
  I want to be able to authenticate on the system
  So that I can access protected resources

  Scenario: User navigates to the application
    Given User navigates to the home page
    Then Home page should be loaded successfully

  Scenario: Verify page elements are accessible
    Given User navigates to the home page
    When User captures the current URL
    Then URL should contain "example.com"
