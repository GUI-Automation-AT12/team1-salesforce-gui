package org.fundacionjala.salesforce.ui.commonPages;

import org.fundacionjala.core.selenium.interaction.WebDriverManager;
import org.openqa.selenium.support.PageFactory;

/**
 * [MR] Base Page object, from this all pages should extend.
 */
public abstract class BasePage {
    protected BasePage() {
        PageFactory.initElements(WebDriverManager.getInstance().getWebDriver(), this);
        waitLoadPage();
    }

    protected abstract void waitLoadPage();
}