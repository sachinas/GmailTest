package com.crossover.pages.administrator;

import java.util.Properties;

import org.junit.Assert;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;

import com.crossover.e2e.GMailTest;
import com.crossover.utils.Constants;
import com.google.common.base.Strings;

public class EmailLogin
{
    public static ExpectedException expectedException = ExpectedException.none();

    public static void verifyLogin() throws TimeoutException, AssertionError, InterruptedException, ClassCastException,
            StaleElementReferenceException
    {
        Properties pageURLProp = GMailTest.propertiesMap.get(Constants.PAGES_URL_FILENAME);
        Properties configProp = GMailTest.propertiesMap.get(Constants.TEST_METADATA_FILENAME);

        String email = configProp.getProperty(Constants.USER_NAME);
        String password = configProp.getProperty(Constants.PASSWORD);

        String loginUrl = pageURLProp.getProperty(Constants.LOGIN_PAGE_URL);

        if (Strings.isNullOrEmpty(email) && Strings.isNullOrEmpty(password) && Strings.isNullOrEmpty(loginUrl))
        {
            expectedException.expectMessage("Either Username, Password or Login Url was not found.");
            Assert.fail();

        }
        else
        {
            LoginForm loginForm = new LoginForm();
            loginForm.loadPage(loginUrl);
            loginForm.login(email, password);
        }
    }
}
