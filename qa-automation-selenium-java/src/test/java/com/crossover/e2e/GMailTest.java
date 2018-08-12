package com.crossover.e2e;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import com.crossover.pages.administrator.EmailLogin;
import com.crossover.pages.home.ComposeAndSendEmail;
import com.crossover.pages.home.SentEmailDetails;
import com.crossover.utils.Constants;
import com.crossover.utils.PageDriver;
import com.crossover.utils.TestProperties;
import com.google.common.base.Strings;

import junit.framework.TestCase;

public class GMailTest extends TestCase
{
    private WebDriver driver;

    public ExpectedException expectedException = ExpectedException.none();

    public static Map<String, Properties> propertiesMap = null;

    public void setUp()
    {
        try
        {
            propertiesMap = TestProperties.readPropertiesFile();
            EmailLogin.verifyLogin();
        }
        catch (TimeoutException | AssertionError | InterruptedException | ClassCastException
                | StaleElementReferenceException | IOException e)
        {
            expectedException.expectMessage(e.getMessage().toString());
            Assert.fail();
        }
    }

    public void tearDown() throws Exception
    {
        Properties pageURLProp = GMailTest.propertiesMap.get(Constants.PAGES_URL_FILENAME);
        String logoutPageUrl = pageURLProp.getProperty(Constants.LOGOUT_PAGE_URL);

        if (Strings.isNullOrEmpty(logoutPageUrl))
        {
            expectedException.expectMessage("Log Out Page URL not found.");
        }
        else
        {
            driver = PageDriver.getInstance().getDriver();
            driver.navigate().to(logoutPageUrl);
        }
        driver.quit();
    }

    @Test
    public void testSendEmail()
    {
        try
        {
            ComposeAndSendEmail.composeAndSendEmail();
            SentEmailDetails.verifySentEmail();
        }
        catch (TimeoutException | InterruptedException | AWTException | StaleElementReferenceException | AssertionError
                | ClassCastException e)
        {
            expectedException.expectMessage(e.getMessage().toString());
            Assert.fail();
        }
    }
}
