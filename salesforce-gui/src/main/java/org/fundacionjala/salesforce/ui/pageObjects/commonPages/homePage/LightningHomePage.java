package org.fundacionjala.salesforce.ui.pageObjects.commonPages.homePage;

import org.fundacionjala.salesforce.ui.pageObjects.commonPages.homePage.components.resultsPanel.LightningResultsPanel;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * [MR] Class that represents Home Page in Lightning skin.
 */
public class LightningHomePage extends AbstractHomePage {

    @FindBy(css = "div.clickableCardArea")
    private WebElement notificationCard;

    /**
     * [MR] Sets text in search text box and gets a LightningResultsPanel.
     *
     * @param text to search
     * @return LightningResultsPanel
     */
    @Override
    public LightningResultsPanel search(final String text) {
        setSearchBox(text);
        return new LightningResultsPanel();
    }

    @Override
    protected final void waitLoadPage() {
        getDriverWait().until(ExpectedConditions.visibilityOf(notificationCard));
    }
}
