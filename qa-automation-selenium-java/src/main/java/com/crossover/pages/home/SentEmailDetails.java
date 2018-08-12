package com.crossover.pages.home;

import java.awt.AWTException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

public class SentEmailDetails
{
    public static void verifySentEmail()
            throws StaleElementReferenceException, AssertionError, InterruptedException, AWTException
    {
        InboxHome inboxHome = new InboxHome();
        inboxHome.verifySentEmail();
    }
}
