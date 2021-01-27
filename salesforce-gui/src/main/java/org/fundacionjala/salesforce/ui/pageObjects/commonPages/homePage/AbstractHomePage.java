package org.fundacionjala.salesforce.ui.pageObjects.commonPages.homePage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.homePage.components.resultsPanel.AbstractResultsPanel;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * [MR] Class that represents Home Page independent of the skin.
 */
public abstract class AbstractHomePage extends BasePage {

    @FindBy(css = "[title='Search...']")
    private WebElement searchTxtBox;

    protected void setSearchBox(final String text) {
        GuiInteractioner.setInputText(searchTxtBox, text);
    }

    public abstract AbstractResultsPanel search(String text);

    @Override
    protected final void waitLoadPage() {
        getDriverWait().until(ExpectedConditions.visibilityOf(searchTxtBox));
    }
}
