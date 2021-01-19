package org.fundacionjala.salesforce.ui.commonPages;

import org.fundacionjala.core.selenium.interaction.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * [MR] Base Page object, from this all pages should extend.
 */
public abstract class BasePage {

    private WebDriver driver;

    /**
     * Constructor.
     */
    public BasePage() {
        driver = WebDriverManager.getInstance().getWebDriver();
        PageFactory.initElements(driver, this);
        waitLoadPage();
    }

    /**
     * Returns a instance of WebDriver.
     *
     * @return a instance of WebDriver
     */
    public WebDriver getDriver() {
        return driver;
    }

    protected abstract void waitLoadPage();
}
