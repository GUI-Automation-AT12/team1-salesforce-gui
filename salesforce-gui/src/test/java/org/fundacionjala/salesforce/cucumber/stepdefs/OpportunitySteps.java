package org.fundacionjala.salesforce.cucumber.stepdefs;

import io.cucumber.java.en.When;
import org.fundacionjala.salesforce.ui.context.Context;
import org.fundacionjala.salesforce.ui.entities.Opportunity;
import org.fundacionjala.salesforce.ui.skins.ISkinFactory;
import org.fundacionjala.salesforce.ui.skins.SkinManager;

import java.util.Map;

public class OpportunitySteps {

    private Opportunity opportunity;

    private ISkinFactory skin = SkinManager.getInstance().getSkinFactory();

    //Context
    private final Context context;

    /**
     * Adds Dependency injection to share Context information.
     * @param sharedContext
     */
    public OpportunitySteps(final Context sharedContext) {
        this.context = sharedContext;
    }

    /**
     * StepDef to create a new Opportunity fromUI.
     * @param opportunityInfo as map
     */
    @When("I create a new Opportunity with the following data")
    public void iCreateANewOpportunityWithTheFollowingData(final Map opportunityInfo) {
    }
}
