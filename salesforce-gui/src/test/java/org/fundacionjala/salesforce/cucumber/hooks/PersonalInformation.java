package org.fundacionjala.salesforce.cucumber.hooks;

import io.cucumber.java.After;
import io.restassured.response.Response;
import org.fundacionjala.salesforce.ui.context.Context;


public class PersonalInformation {

    private Context context;

    /**
     * Initializes an instance of Context class.
     *
     * @param dataContext
     */
    public PersonalInformation(final Context dataContext) {
        this.context = dataContext;
    }

    /**
     * Creates thee board before execute the step with the createBoard ta'g.
     */
    @After(value = "@restorePersonalInformation", order = 1)
    public void createOrganization() {
        Response response = context.getRequestSpec().get("/user/");
        String userId = response.jsonPath().getString("recentItems[0].Id");

        String valeByDefect = "src/main/java/org/fundacionjala/salesforce/config/jsonFiles/defaultAccountData.json";
        response = context.getRequestSpec().patch("/user/" + userId, valeByDefect);
    }
}
