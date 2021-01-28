package org.fundacionjala.salesforce.ui.pageObjects.commonPages.homePage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.homePage.components.resultsPanel.AbstractResultsPanel;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.searchResultsPage.AbstractSearchResultsPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * [MR] Class that represents Home Page independent of the skin.
 */
public abstract class AbstractHomePage extends BasePage {

    @FindBy(css = "[title='Search...']")
    private WebElement searchTxtBox;

    protected final void setSearchBox(final String text) {
        GuiInteractioner.setInputText(searchTxtBox, text);
    }

    protected final void clickSearchBox() {
        GuiInteractioner.clickWebElement(searchTxtBox);
    }

    protected final void addAsteriskInSearchBox() {
        searchTxtBox.sendKeys("*");
    }

    /**
     * [MR] Sets text in search text box and gets a ResultsPanel.
     *
     * @param text to search
     * @return ResultsPanel
     */
    public abstract AbstractResultsPanel search(String text);

    /**
     * [MR] Sets text in search text box and goes to Search Results Page.
     *
     * @param text to search
     * @return SearchResultsPage
     */
    public abstract AbstractSearchResultsPage goToResultsPage(String text);
}
