package org.fundacionjala.salesforce.cucumber.hooks;

import io.cucumber.java.After;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.fundacionjala.core.api.client.RequestManager;
import org.fundacionjala.core.selenium.interaction.WebDriverManager;
import org.fundacionjala.salesforce.ui.context.Context;

/**
 * [MR] Hooks for scenarios related to Opportunities.
 */
public class OpportunityHooks {

    //Context
    private final Context context;

    /**
     * Adds Dependency injection to share Context information.
     * @param sharedContext
     */
    public OpportunityHooks(final Context sharedContext) {
        this.context = sharedContext;
    }

    /**
     * Hook that deletes an Opportunity saved in Context via API.
     */
    @After(value = "@deleteOpportunity", order = 1)
    public void deleteOpportunity() {
        Response response = RequestManager.get("/Opportunity/" + context.getOpportunity().getId());
        if (response.statusCode() == HttpStatus.SC_OK) {
            RequestManager.delete("/Opportunity/" + context.getOpportunity().getId());
        }
        WebDriverManager.getInstance().quit();
    }
}
