package com.salesforce.automation.tests;

import com.salesforce.automation.base.BaseTest;
import com.salesforce.automation.pages.LoginPage;
import com.salesforce.automation.utils.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class ValidLoginTest extends BaseTest {

    @Test(dataProvider = "validLoginData", description = "Verify successful login with valid credentials")
    public void testValidLogin(String email, String password) {
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.performLogin(email, password);
            Assert.assertNotEquals(driver.getCurrentUrl(), "https://login.salesforce.com/?locale=in", 
                    "Login failed: User should be redirected after successful login");
        } catch (AssertionError e) {
            throw new AssertionError("Test failed: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error during valid login test: " + e.getMessage(), e);
        }
    }

    @Test(dataProvider = "validLoginData", description = "Verify login with Remember Me checkbox enabled")
    public void testValidLoginWithRememberMe(String email, String password) {
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.performLoginWithRememberMe(email, password);
            Assert.assertNotEquals(driver.getCurrentUrl(), "https://login.salesforce.com/?locale=in", 
                    "Login with Remember Me failed: User should be redirected");
        } catch (AssertionError e) {
            throw new AssertionError("Test failed: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error during Remember Me test: " + e.getMessage(), e);
        }
    }

    @Test(description = "Verify login page UI elements are visible on page load")
    public void testLoginPageUIElements() {
        try {
            LoginPage loginPage = new LoginPage(driver);
            Assert.assertTrue(loginPage.isUsernameFieldVisible(), 
                    "Username field should be visible on login page");
            Assert.assertTrue(loginPage.isPasswordFieldVisible(), 
                    "Password field should be visible on login page");
            Assert.assertTrue(loginPage.isLoginButtonVisible(), 
                    "Login button should be visible on login page");
            Assert.assertTrue(loginPage.isForgotPasswordLinkDisplayed(), 
                    "Forgot Password link should be displayed");
        } catch (AssertionError e) {
            throw new AssertionError("Test failed: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error during UI elements test: " + e.getMessage(), e);
        }
    }

    @DataProvider(name = "validLoginData")
    public Object[][] getValidLoginData() {
        return TestDataProvider.getValidLoginData();
    }
}
