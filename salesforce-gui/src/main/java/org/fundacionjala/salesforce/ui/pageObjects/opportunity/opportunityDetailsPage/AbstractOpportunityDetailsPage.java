package org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityDetailsPage;

import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;

import java.util.Map;
import java.util.Set;

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

    /**
     * [MR] Gets the Opportunity info from Details Page.
     * @param fields to get data
     * @return OpportunityDetails as Map
     */
    public abstract Map<String, String> getOpportunityDetails(Set<String> fields);
}
