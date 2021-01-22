package org.fundacionjala.salesforce.ui.pageObjects.commonPages;

import org.fundacionjala.core.selenium.interaction.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * [MR-SL] Base Page object, from this all pages should extend.
 */
public abstract class BasePage {

    private WebDriver driver;

    private WebDriverWait driverWait;

    /**
     * Constructor for BasePage class.
     */
    public BasePage() {
        driver = WebDriverManager.getInstance().getWebDriver();
        driverWait = WebDriverManager.getInstance().getWebDriverWait();
        PageFactory.initElements(driver, this);
        waitLoadPage();
    }

    /**
     * Gets WebDriver.
     *
     * @return driver
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Gets WebDriverWait.
     *
     * @return driverWait
     */
    public WebDriverWait getDriverWait() {
        return driverWait;
    }

    protected abstract void waitLoadPage();
}
