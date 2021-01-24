package org.fundacionjala.salesforce.cucumber.stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.fundacionjala.salesforce.ui.context.Context;
import org.fundacionjala.salesforce.ui.entities.Opportunity;
import org.fundacionjala.salesforce.ui.skins.ISkinFactory;
import org.fundacionjala.salesforce.ui.skins.SkinManager;
import org.testng.asserts.SoftAssert;

import java.util.Map;

/**
 * [MR] StepDefinitions class for salesforce Opportunities.
 */
public class OpportunitySteps {

    private Opportunity opportunity;

    private ISkinFactory skin = SkinManager.getInstance().getSkinFactory();

    //Context
    private final Context context;

    private String incorrectAssertionMessage = "The %1$s from %2$s does not match with the %1$s edited previously.";

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
    public void createANewOpportunity(final Map opportunityInfo) {
        //Updating Entity
        opportunity = new Opportunity();
        opportunity.setInformation(opportunityInfo);
        opportunity.setUpdatedFields(opportunityInfo.keySet());

        skin.getOpportunitiesPage().goToOpportunityCreation();
        skin.getOpportunityCreationPage().fillOpportunityInformation(opportunity.getUpdatedFields(), opportunity);

        opportunity.setId(skin.getOpportunityDetailsPage().getOpportunityId());
        context.setOpportunity(opportunity);
    }

    /**
     * [MR] Checks that the Details Page contains the data stored before.
     */
    @Then("Opportunity's data should be displayed at details")
    public void verifyOpportunityDataIsDisplayedAtDetails() {
        Map<String, String> actualOpportunityDetails = skin.getOpportunityDetailsPage().
                getOpportunityDetails(opportunity.getUpdatedFields());
        Map<String, String> expectedOpportunityDetails = opportunity.getOpportunityInfo();
        SoftAssert softAssert = new SoftAssert();
        actualOpportunityDetails.forEach((field, actualValue) -> {
            softAssert.assertEquals(actualValue,expectedOpportunityDetails.get(field),
                    String.format(incorrectAssertionMessage, field, "Opportunity Details Page"));
        });
        softAssert.assertAll();
    }
}
