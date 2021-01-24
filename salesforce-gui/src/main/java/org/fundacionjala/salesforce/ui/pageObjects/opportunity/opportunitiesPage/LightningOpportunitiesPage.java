package org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunitiesPage;

import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityCreationPage.AbstractOpportunityCreationPage;
import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityCreationPage.LightningOpportunityCreationPopup;

/**
 * [MR] Class that represents Opportunities Page in Lightning skin.
 */
public class LightningOpportunitiesPage extends AbstractOpportunitiesPage {

    /**
     * [MR] Goes to Creation page.
     *
     * @return OpportunityCreationPage
     */
    @Override
    public AbstractOpportunityCreationPage goToOpportunityCreation() {
        clickNewOpportunityBtn();
        return new LightningOpportunityCreationPopup();
    }
}
