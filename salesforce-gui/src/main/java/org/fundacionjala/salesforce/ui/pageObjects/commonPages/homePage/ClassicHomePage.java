package org.fundacionjala.salesforce.ui.pageObjects.commonPages.homePage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.homePage.components.resultsPanel.ClassicResultsPanel;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.searchResultsPage.ClassicSearchResultsPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * [MR] Class that represents Home Page in Classic skin.
 */
public class ClassicHomePage extends AbstractHomePage {

    @FindBy (css = "div.feeditemfooter")
    private WebElement feedFooter;

    @FindBy (id = "phSearchButton")
    private WebElement searchBtn;

    /**
     * [MR] Sets text in search text box and gets a ClassicResultsPanel.
     *
     * @param text to search
     * @return ClassicResultsPanel
     */
    @Override
    public ClassicResultsPanel search(final String text) {
        clickSearchBox();
        setSearchBox(text);
        return new ClassicResultsPanel();
    }

    private void clickSearchBtn() {
        GuiInteractioner.clickWebElement(searchBtn);
    }

    /**
     * [MR] Sets text in search text box and goes to Search Results Page.
     *
     * @return ClassicSearchResultsPage
     */
    @Override
    public final ClassicSearchResultsPage goToResultsPage(final String text) {
        search(text);
        clickSearchBtn();
        return new ClassicSearchResultsPage();
    }

    @Override
    protected final void waitLoadPage() {
        getDriverWait().until(ExpectedConditions.visibilityOf(feedFooter));
    }
}
