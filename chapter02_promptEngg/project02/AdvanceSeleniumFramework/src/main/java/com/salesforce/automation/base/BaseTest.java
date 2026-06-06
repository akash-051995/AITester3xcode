package com.salesforce.automation.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    private static final String BASE_URL = "https://login.salesforce.com/?locale=in";
    private static final long IMPLICIT_WAIT = 10;
    private static final String BROWSER = "chrome";

    @BeforeTest
    public void setUp() {
        try {
            driver = initializeDriver();
            driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get(BASE_URL);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize WebDriver: " + e.getMessage(), e);
        }
    }

    private WebDriver initializeDriver() {
        try {
            if (BROWSER.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--disable-notifications");
                return new ChromeDriver(options);
            } else if (BROWSER.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            } else {
                throw new IllegalArgumentException("Unsupported browser: " + BROWSER);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize driver for browser: " + BROWSER, e);
        }
    }

    @AfterTest
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            System.err.println("Error closing WebDriver: " + e.getMessage());
        }
    }
}
