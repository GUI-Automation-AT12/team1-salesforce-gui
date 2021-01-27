package org.fundacionjala.salesforce.ui.pageObjects.commonPages.homePage;

import org.fundacionjala.salesforce.ui.pageObjects.commonPages.homePage.components.resultsPanel.ClassicResultsPanel;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * [MR] Class that represents Home Page in Classic skin.
 */
public class ClassicHomePage extends AbstractHomePage {

    @FindBy (css = "div.feeditemfooter")
    private WebElement feedFooter;

    /**
     * [MR] Sets text in search text box and gets a ClassicResultsPanel.
     *
     * @param text to search
     * @return ClassicResultsPanel
     */
    @Override
    public ClassicResultsPanel search(final String text) {
        setSearchBox(text);
        return new ClassicResultsPanel();
    }

    @Override
    protected final void waitLoadPage() {
        getDriverWait().until(ExpectedConditions.visibilityOf(feedFooter));
    }
}
