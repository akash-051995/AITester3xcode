package com.salesforce.automation.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePageObject {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final long EXPLICIT_WAIT_SECONDS = 15;

    public BasePageObject(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_SECONDS));
        PageFactory.initElements(driver, this);
    }

    protected WebElement waitForElementPresence(By locator) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            throw new RuntimeException("Element not found: " + locator, e);
        }
    }

    protected WebElement waitForElementVisibility(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            throw new RuntimeException("Element not visible: " + locator, e);
        }
    }

    protected WebElement waitForElementClickable(By locator) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            throw new RuntimeException("Element not clickable: " + locator, e);
        }
    }

    protected void waitForElementClickable(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            throw new RuntimeException("WebElement not clickable", e);
        }
    }

    protected String waitForElementText(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return element.getText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve text from element: " + locator, e);
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected void sendKeys(WebElement element, String text) {
        try {
            waitForElementVisibility(element);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send keys to element: " + text, e);
        }
    }

    protected void click(WebElement element) {
        try {
            waitForElementClickable(element);
            element.click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click element", e);
        }
    }

    private void waitForElementVisibility(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            throw new RuntimeException("Element not visible", e);
        }
    }

    protected String getAttribute(WebElement element, String attributeName) {
        try {
            waitForElementPresence(element);
            return element.getAttribute(attributeName);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get attribute: " + attributeName, e);
        }
    }

    protected void waitForElementInvisibility(By locator) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            throw new RuntimeException("Element should be invisible: " + locator, e);
        }
    }

    private WebElement waitForElementPresence(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return element;
        } catch (Exception e) {
            throw new RuntimeException("Element not present or visible", e);
        }
    }
}
