package com.crossover.utils;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementWait
{
    public static boolean waitToLoadElement(WebElement actualElement) throws TimeoutException
    {
        WebDriverWait explicitWait = new WebDriverWait(PageDriver.getInstance().getDriver(), 90);
        WebElement expectedElement = explicitWait.until(ExpectedConditions.visibilityOf(actualElement));
        return expectedElement.equals(actualElement);
    }
}
