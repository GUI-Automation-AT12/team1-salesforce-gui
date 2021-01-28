package org.fundacionjala.salesforce.ui.pageObjects.commonPages.homePage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.homePage.components.resultsPanel.LightningResultsPanel;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.searchResultsPage.LightningSearchResultsPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * [MR] Class that represents Home Page in Lightning skin.
 */
public class LightningHomePage extends AbstractHomePage {

    @FindBy(css = "div.clickableCardArea")
    private WebElement notificationCard;

    @FindBy(css = "a.SEARCH_OPTION")
    private WebElement searchLink;

    /**
     * [MR] Sets text in search text box and gets a LightningResultsPanel.
     *
     * @param text to search
     * @return LightningResultsPanel
     */
    @Override
    public LightningResultsPanel search(final String text) {
        clickSearchBox();
        setSearchBox(text);
        clickSearchBox();
        return new LightningResultsPanel();
    }

    private void clickSearchLink() {
        GuiInteractioner.clickWebElement(searchLink);
    }

    /**
     * [MR] Sets text in search text box and goes to Search Results Page.
     *
     * @return LightningSearchResultsPage
     */
    @Override
    public final LightningSearchResultsPage goToResultsPage(final String text) {
        search(text);
        clickSearchLink();
        return new LightningSearchResultsPage();
    }

    @Override
    protected final void waitLoadPage() {
        getDriverWait().until(ExpectedConditions.visibilityOf(notificationCard));
    }
}
