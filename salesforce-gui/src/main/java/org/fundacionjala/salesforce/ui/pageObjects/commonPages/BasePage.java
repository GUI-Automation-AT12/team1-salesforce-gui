package org.fundacionjala.salesforce.ui.pageObjects.commonPages;

import org.fundacionjala.core.selenium.interaction.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * [MR] Base Page object, from this all pages should extend.
 */
public class BasePage {

    private WebDriver driver;

    private WebDriverWait driverWait;

    protected BasePage() {
        PageFactory.initElements(WebDriverManager.getInstance().getWebDriver(), this);
        driver = WebDriverManager.getInstance().getWebDriver();
        driverWait = WebDriverManager.getInstance().getWebDriverWait();
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
}
