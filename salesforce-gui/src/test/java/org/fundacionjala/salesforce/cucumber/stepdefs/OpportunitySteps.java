package org.fundacionjala.salesforce.cucumber.stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.fundacionjala.core.api.client.RequestManager;
import org.fundacionjala.salesforce.constants.OpportunityConstants;
import org.fundacionjala.salesforce.ui.context.Context;
import org.fundacionjala.salesforce.ui.entities.Opportunity;
import org.fundacionjala.salesforce.ui.skins.ISkinFactory;
import org.fundacionjala.salesforce.ui.skins.SkinManager;
import org.fundacionjala.salesforce.utils.ApiResponseDataExtractor;
import org.fundacionjala.salesforce.utils.PageTransporter;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.util.Map;

import static org.testng.Assert.assertTrue;

/**
 * [MR] StepDefinitions class for salesforce Opportunities.
 */
public class OpportunitySteps {

    private Opportunity opportunity;
    private ISkinFactory skin = SkinManager.getInstance().getSkinFactory();
    private String incorrectAssertionMessage = "The %1$s from %2$s does not match with the %1$s edited previously.";

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
            softAssert.assertEquals(actualValue, expectedOpportunityDetails.get(field),
                    String.format(incorrectAssertionMessage, field, "Opportunity Details Page"));
        });
        softAssert.assertAll();
    }

    /**
     * [MR] Checks if the opportunity is in the table and its data is the correct.
     */
    @Then("Opportunity's data should be displayed in Opportunities table")
    public void verifyOpportunityDataIsDisplayedInOpportunitiesTable() throws MalformedURLException {
        PageTransporter.navigateToPage("OPPORTUNITIES");
        Map<String, String> actualTableData = skin.getOpportunitiesPage().
                getOpportunityDataFromTable(opportunity.getId());
        Map<String, String> expectedTableData = opportunity.getOpportunityInfo(actualTableData.keySet());
        expectedTableData.put(OpportunityConstants.ACCOUNT_SITE_KEY, opportunity.getAccount().getSite());
        SoftAssert softAssert = new SoftAssert();
        actualTableData.forEach((field, actualValue) -> {
            softAssert.assertEquals(actualValue, expectedTableData.get(field),
                    String.format(incorrectAssertionMessage, field, "Account Table"));
        });
        softAssert.assertAll();
    }

    /**
     * [MR] Check if the opportunity is present in the related Account Details Page.
     * @throws MalformedURLException
     */
    @Then("the new Opportunity should be present in Account details")
    public void verifyThatTheNewOpportunityIsPresentInExistentAccountDetails() throws MalformedURLException {
        PageTransporter.navigateToPage("ACCOUNT DETAILS", opportunity.getAccount().getId());
        assertTrue(skin.getAccountDetailsPage().isOpportunityInList(opportunity.getId()),
                "The Opportunity: " + opportunity.getName() + " is not present in related Account details.");
    }

    /**
     * [MR] Check if the API response contains the data of the new Opportunity.
     */
    @Then("the API response about the Opportunity should contain the new data")
    public void verifyIfTheApiResponseAboutTheOpportunityContainsTheNewData() {
        Response response = RequestManager.get("/Opportunity/" + opportunity.getId());
        Map<String, String> actualApiResponseData = ApiResponseDataExtractor
                .getDataFromApi(response, opportunity.getUpdatedFields());
        Map<String, String> expectedApiResponseData = opportunity.getOpportunityInfo();
        SoftAssert softAssert = new SoftAssert();
        //
        actualApiResponseData.forEach((field, actualValue) -> {
            if (!field.equals(OpportunityConstants.ACCOUNT_KEY)) {
                softAssert.assertEquals(actualValue, expectedApiResponseData.get(field),
                        String.format(incorrectAssertionMessage, field, "Account API response"));
            } else {
                softAssert.assertEquals(actualValue, opportunity.getAccount().getId(),
                        String.format(incorrectAssertionMessage, field, "Account API response"));
            }
        });
        softAssert.assertAll();
    }
}
