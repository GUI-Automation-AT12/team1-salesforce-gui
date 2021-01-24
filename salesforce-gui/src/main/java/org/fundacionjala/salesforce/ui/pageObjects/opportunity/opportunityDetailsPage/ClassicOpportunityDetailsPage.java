package org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityDetailsPage;

import org.fundacionjala.salesforce.utils.PageTransporter;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * [MR] Class that represents Opportunity Details Page in Classic skin.
 */
public class ClassicOpportunityDetailsPage extends AbstractOpportunityDetailsPage {

    /**
     * [MR] Gets the Opportunity's ID from the URL.
     *
     * @return Opportunity's Id
     */
    @Override
    public String getOpportunityId() {
        getDriverWait().until(ExpectedConditions.not(ExpectedConditions.urlContains("/e")));
        String currentUrl = PageTransporter.getCurrentUrl();
        return currentUrl.substring(currentUrl.lastIndexOf("/") + 1);
    }
}
