package org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityDetailsPage;

import org.fundacionjala.salesforce.utils.PageTransporter;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * [MR] Class that represents Opportunity Details Page in Lightning skin.
 */
public class LightningOpportunityDetailsPage extends AbstractOpportunityDetailsPage {

    private static final int OPPORTUNITY_STRING_SIZE = 12;

    /**
     * [MR] Gets the Opportunity's ID from the URL.
     *
     * @return Opportunity's Id
     */
    @Override
    public String getOpportunityId() {
        getDriverWait().until(ExpectedConditions.not(ExpectedConditions.urlContains("new")));
        String currentUrl = PageTransporter.getCurrentUrl();
        return currentUrl.
                substring(currentUrl.indexOf("Opportunity/") + OPPORTUNITY_STRING_SIZE, currentUrl.indexOf("/view"));
    }
}
