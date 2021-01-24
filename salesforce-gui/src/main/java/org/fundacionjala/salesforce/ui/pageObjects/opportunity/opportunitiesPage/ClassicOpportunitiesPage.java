package org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunitiesPage;

import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityCreationPage.AbstractOpportunityCreationPage;
import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityCreationPage.ClassicOpportunityCreationPage;

/**
 * [MR] Class that represents Opportunities Page in Classic skin.
 */
public class ClassicOpportunitiesPage extends AbstractOpportunitiesPage {

    /**
     * [MR] Goes to Creation page.
     *
     * @return OpportunityCreationPage
     */
    @Override
    public AbstractOpportunityCreationPage goToOpportunityCreation() {
        clickNewOpportunityBtn();
        return new ClassicOpportunityCreationPage();
    }
}
