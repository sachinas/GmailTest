package com.crossover.pages.home;

import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import org.junit.Assert;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crossover.utils.Constants;
import com.crossover.utils.ElementWait;
import com.crossover.utils.PageDriver;

public class InboxHome
{
    private WebDriver driver;
    public static ExpectedException expectedException = ExpectedException.none();

    @FindBy(xpath = "//*[@role='button' and (.)='COMPOSE']")
    private WebElement composeElement;

    @FindBy(name = "to")
    private WebElement emailTo;

    @FindBy(name = "subjectbox")
    private WebElement subjectBox;

    @FindBy(xpath = "//div[@class='Am Al editable LW-avf']")
    private WebElement emailBody;

    @FindBy(xpath = "//div[@class='a1 aaA aMZ']")
    private WebElement attachmentIcon;

    @FindBy(xpath = "//*[@class='T-I J-J5-Ji aoO T-I-atl L3']")
    private WebElement emailSend;

    public InboxHome()
    {
        driver = PageDriver.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    public void composeAndSendEmail(String userName)
            throws StaleElementReferenceException, TimeoutException, InterruptedException, AWTException
    {
        ElementWait.waitToLoadElement(this.composeElement);
        this.composeElement.click();
        ElementWait.waitToLoadElement(this.emailTo);
        this.emailTo.clear();
        this.emailTo.sendKeys(String.format("%s@gmail.com", userName));
        this.subjectBox.clear();
        this.subjectBox.sendKeys(Constants.EMAIL_SUBJECT);
        this.emailBody.clear();
        this.emailBody.sendKeys(Constants.EMAIL_BODY);
        this.attachmentIcon.click();

        StringSelection attachmentFilePath = new StringSelection(Constants.ATTACHMENT_FILE_PATH);
        // upload your file using RobotClass
        // attach your path where file is located.
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(attachmentFilePath, null);
        Robot robot = new Robot();
        Thread.sleep(Constants.WAIT_FOR_PAGE_LOAD);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        Thread.sleep(Constants.WAIT_FOR_PAGE_LOAD);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(Constants.WAIT_FOR_PAGE_LOAD);
        this.emailSend.click();
    }

    public void verifySentEmail()
            throws StaleElementReferenceException, AssertionError, InterruptedException, AWTException
    {
        Thread.sleep(Constants.WAIT_FOR_PAGE_LOAD);
        List<WebElement> emailList = driver.findElements(By.xpath("//*[@class='yW']/span"));
        for (WebElement emailSender : emailList)
        {
            if (emailSender.getText().equals(Constants.EMAIL_TO) == true)
            {
                emailSender.click();
                break;
            }
            else
            {
                expectedException.expectMessage("Email not found.");
                Assert.fail();
            }
        }

        assertTrue("Email Subject is verified.",
                driver.findElement(By.xpath("//*[@class='hP']")).getText().contains(Constants.EMAIL_SUBJECT));

        assertTrue("Body Content is verified.",
                driver.findElement(By.xpath("//*[@class='a3s aXjCH ']")).getText().contains(Constants.EMAIL_BODY));

        assertTrue("Attachment File is verified.", driver.findElement(By.xpath("//*[@class='aV3 zzV0ie']")).getText()
                .contains(Constants.ATTACHMENT_FILE_NAME));
    }
}