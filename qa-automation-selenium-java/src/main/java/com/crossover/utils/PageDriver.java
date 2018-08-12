package com.crossover.utils;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageDriver
{
    private WebDriver driver;
    private static PageDriver pageDriver;

    private PageDriver()
    {
        String chromeDriverPath = "Gmail-Selenium-Gradle-master/qa-automation-selenium-java/bin/main/chromedriver";

        // Check if executable binary is placed in the dir of the system.
        // Since it is not a good practice to commit binaries on repo.

        if (Files.exists(Paths.get("/usr/bin/chromedriver")))
        {
            chromeDriverPath = "/usr/bin/chromedriver";
        }

        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public static PageDriver getInstance()
    {
        if (pageDriver == null)
        {
            pageDriver = new PageDriver();
        }
        return pageDriver;
    }

    public WebDriver getDriver()
    {
        return driver;
    }
}
