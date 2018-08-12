package com.crossover.pages.administrator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crossover.pages.home.InboxHome;
import com.crossover.utils.Constants;
import com.crossover.utils.ElementWait;
import com.crossover.utils.PageDriver;

public class LoginForm
{
    private WebDriver driver;

    @FindBy(id = "identifierId")
    private WebElement userElement;

    @FindBy(id = "identifierNext")
    private WebElement userNext;

    @FindBy(name = "password")
    private WebElement passwordElement;

    @FindBy(id = "passwordNext")
    private WebElement passwordNext;

    public LoginForm()
    {
        driver = PageDriver.getInstance().getDriver();
    }

    public void loadPage(String pageUrl) throws TimeoutException, AssertionError, StaleElementReferenceException
    {
        driver.get(pageUrl);
        PageFactory.initElements(driver, this);
        assertTrue(ElementWait.waitToLoadElement(userElement));
        assertEquals(driver.getTitle(), Constants.LOGIN_PAGE_TITLE);
    }

    private void setEmailAddress(String loginEmail) throws TimeoutException, StaleElementReferenceException
    {
        ElementWait.waitToLoadElement(this.userElement);
        this.userElement.clear();
        this.userElement.sendKeys(loginEmail);
        this.userNext.click();
    }

    private void setPassword(String loginPassword) throws TimeoutException, StaleElementReferenceException
    {
        ElementWait.waitToLoadElement(this.passwordElement);
        this.passwordElement.clear();
        this.passwordElement.sendKeys(loginPassword);
    }

    private void clickLogin() throws TimeoutException, StaleElementReferenceException
    {
        ElementWait.waitToLoadElement(this.passwordNext);
        this.passwordNext.click();
    }

    public InboxHome login(String loginEmail, String loginPassword) throws StaleElementReferenceException
    {
        setEmailAddress(loginEmail);
        setPassword(loginPassword);
        clickLogin();
        return new InboxHome();
    }
}
