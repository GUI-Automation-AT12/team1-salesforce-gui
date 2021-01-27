package org.fundacionjala.salesforce.ui.pageObjects.commonPages.homePage.components.resultsPanel;

import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;

import java.util.List;

public abstract class AbstractResultsPanel extends BasePage {

    public abstract List<String> getResults();
}
