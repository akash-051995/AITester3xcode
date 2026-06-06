package com.salesforce.automation.tests;

import com.salesforce.automation.base.BaseTest;
import com.salesforce.automation.pages.LoginPage;
import com.salesforce.automation.utils.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class InvalidLoginTest extends BaseTest {

    @Test(dataProvider = "invalidLoginData", description = "Verify error message is displayed for invalid login attempts")
    public void testInvalidLogin(String email, String password) {
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.performLogin(email, password);
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                    "Error message should be displayed for invalid login attempt with email: " + email);
        } catch (AssertionError e) {
            throw new AssertionError("Test failed: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error during invalid login test: " + e.getMessage(), e);
        }
    }

    @Test(description = "Verify user stays on login page after invalid credentials")
    public void testUserStaysOnLoginPageAfterInvalidLogin() {
        try {
            LoginPage loginPage = new LoginPage(driver);
            String initialUrl = driver.getCurrentUrl();
            loginPage.performLogin(TestDataProvider.INVALID_EMAIL, TestDataProvider.INVALID_PASSWORD);
            String currentUrl = driver.getCurrentUrl();
            Assert.assertEquals(currentUrl, initialUrl, 
                    "User should remain on login page after invalid login attempt");
        } catch (AssertionError e) {
            throw new AssertionError("Test failed: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error during page navigation test: " + e.getMessage(), e);
        }
    }

    @Test(description = "Verify login page loads correctly and is ready for input")
    public void testLoginPageInitialization() {
        try {
            LoginPage loginPage = new LoginPage(driver);
            Assert.assertTrue(loginPage.isUsernameFieldVisible(), 
                    "Username field should be visible and ready for input");
            Assert.assertTrue(loginPage.isPasswordFieldVisible(), 
                    "Password field should be visible and ready for input");
            Assert.assertTrue(loginPage.isLoginButtonVisible(), 
                    "Login button should be visible and enabled");
        } catch (AssertionError e) {
            throw new AssertionError("Test failed: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error during page initialization test: " + e.getMessage(), e);
        }
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] getInvalidLoginData() {
        return TestDataProvider.getInvalidLoginData();
    }
}
