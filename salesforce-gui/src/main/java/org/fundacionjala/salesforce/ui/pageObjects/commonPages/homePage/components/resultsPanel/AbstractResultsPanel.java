package org.fundacionjala.salesforce.ui.pageObjects.commonPages.homePage.components.resultsPanel;

import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;

import java.util.List;

/**
 * [MR] Class that represent Suggested Results Panel in any skin at inputting text in search text box.
 */
public abstract class AbstractResultsPanel extends BasePage {

    /**
     * [MR] Gets text from results at inputting text at search text box.
     *
     * @return a List of inner text.
     */
    public abstract List<String> getResults();
}
