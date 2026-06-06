package com.salesforce.automation.utils;

public class TestDataProvider {
    public static final String VALID_EMAIL = "your_valid_email@example.com";
    public static final String VALID_PASSWORD = "YourValidPassword123!";

    public static final String INVALID_EMAIL = "invalid@test.com";
    public static final String INVALID_PASSWORD = "WrongPassword123!";

    public static final String EMPTY_STRING = "";
    public static final String INVALID_EMAIL_FORMAT = "notanemail";

    public static Object[][] getValidLoginData() {
        return new Object[][] {
                { VALID_EMAIL, VALID_PASSWORD }
        };
    }

    public static Object[][] getInvalidLoginData() {
        return new Object[][] {
                { INVALID_EMAIL, INVALID_PASSWORD },
                { EMPTY_STRING, VALID_PASSWORD },
                { VALID_EMAIL, EMPTY_STRING },
                { INVALID_EMAIL_FORMAT, VALID_PASSWORD }
        };
    }
}
