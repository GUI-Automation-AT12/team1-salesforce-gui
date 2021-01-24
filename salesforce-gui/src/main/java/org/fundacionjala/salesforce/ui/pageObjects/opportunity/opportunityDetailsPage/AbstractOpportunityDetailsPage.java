package org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityDetailsPage;

import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;

/**
 * [MR] Class that represents Opportunity Details Page independent of the skin.
 */
public abstract class AbstractOpportunityDetailsPage extends BasePage {
    @Override
    protected void waitLoadPage() {
    }

    /**
     * [MR] Gets the Opportunity's ID from the URL.
     *
     * @return Opportunity's Id
     */
    public abstract String getOpportunityId();
}
