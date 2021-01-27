package org.fundacionjala.salesforce.ui.pageObjects.commonPages.homePage;

import org.fundacionjala.salesforce.ui.pageObjects.commonPages.homePage.components.resultsPanel.ClassicResultsPanel;

/**
 * [MR] Class that represents Home Page in Classic skin.
 */
public class ClassicHomePage extends AbstractHomePage {

    @Override
    public ClassicResultsPanel search(final String text) {
        setSearchBox(text);
        return new ClassicResultsPanel();
    }
}
