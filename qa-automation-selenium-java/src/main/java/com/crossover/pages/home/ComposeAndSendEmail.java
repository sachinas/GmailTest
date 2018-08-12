package com.crossover.pages.home;

import java.awt.AWTException;
import java.util.Properties;

import org.junit.Assert;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;

import com.crossover.e2e.GMailTest;
import com.crossover.utils.Constants;
import com.google.common.base.Strings;

public class ComposeAndSendEmail
{
    public static ExpectedException expectedException = ExpectedException.none();

    public static void composeAndSendEmail() throws ClassCastException, TimeoutException, InterruptedException,
            AWTException, StaleElementReferenceException
    {
        Properties configProp = GMailTest.propertiesMap.get(Constants.TEST_METADATA_FILENAME);

        String userName = configProp.getProperty(Constants.USER_NAME);

        if (Strings.isNullOrEmpty(userName))
        {
            expectedException.expectMessage("Username not loaded from properties file");
            Assert.fail();
        }
        else
        {
            InboxHome inboxHome = new InboxHome();
            inboxHome.composeAndSendEmail(userName);
        }
    }
}
