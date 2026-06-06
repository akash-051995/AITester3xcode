package com.salesforce.automation.pages;

import com.salesforce.automation.base.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePageObject {

    @FindBy(xpath = "//input[@id='username']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@id='pw']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='rememberUn']")
    private WebElement rememberMeCheckbox;

    @FindBy(xpath = "//input[@id='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@id='error']")
    private WebElement errorMessage;

    @FindBy(xpath = "//a[contains(text(), 'Forgot Your Password?')]")
    private WebElement forgotPasswordLink;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        try {
            sendKeys(usernameField, username);
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter username: " + username, e);
        }
    }

    public void enterPassword(String password) {
        try {
            sendKeys(passwordField, password);
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter password", e);
        }
    }

    public void clickRememberMe() {
        try {
            click(rememberMeCheckbox);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Remember Me checkbox", e);
        }
    }

    public void clickLoginButton() {
        try {
            click(loginButton);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Login button", e);
        }
    }

    public void performLogin(String username, String password) {
        try {
            enterUsername(username);
            enterPassword(password);
            clickLoginButton();
        } catch (Exception e) {
            throw new RuntimeException("Login operation failed", e);
        }
    }

    public void performLoginWithRememberMe(String username, String password) {
        try {
            enterUsername(username);
            enterPassword(password);
            clickRememberMe();
            clickLoginButton();
        } catch (Exception e) {
            throw new RuntimeException("Login with Remember Me failed", e);
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return isElementPresent(org.openqa.selenium.By.xpath("//div[@id='error']"));
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        try {
            if (isErrorMessageDisplayed()) {
                return waitForElementText(org.openqa.selenium.By.xpath("//div[@id='error']"));
            }
            return "";
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve error message", e);
        }
    }

    public boolean isForgotPasswordLinkDisplayed() {
        try {
            return isElementPresent(org.openqa.selenium.By.xpath("//a[contains(text(), 'Forgot Your Password?')]"));
        } catch (Exception e) {
            return false;
        }
    }

    public void clickForgotPasswordLink() {
        try {
            click(forgotPasswordLink);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Forgot Password link", e);
        }
    }

    public boolean isUsernameFieldVisible() {
        try {
            return usernameField.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPasswordFieldVisible() {
        try {
            return passwordField.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginButtonVisible() {
        try {
            return loginButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
