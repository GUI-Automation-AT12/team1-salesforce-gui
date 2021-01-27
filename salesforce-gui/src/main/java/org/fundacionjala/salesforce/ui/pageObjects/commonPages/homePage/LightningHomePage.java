package org.fundacionjala.salesforce.ui.pageObjects.commonPages.homePage;

import org.fundacionjala.salesforce.ui.pageObjects.commonPages.homePage.components.resultsPanel.LightningResultsPanel;

/**
 * [MR] Class that represents Home Page in Lightning skin.
 */
public class LightningHomePage extends AbstractHomePage{

    @Override
    public LightningResultsPanel search(final String text) {
        setSearchBox(text);
        return new LightningResultsPanel();
    }
}
