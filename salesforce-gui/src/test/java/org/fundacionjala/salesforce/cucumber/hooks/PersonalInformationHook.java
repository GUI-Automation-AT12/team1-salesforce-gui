package org.fundacionjala.salesforce.cucumber.hooks;

import io.cucumber.java.After;
import io.restassured.response.Response;
import org.fundacionjala.core.api.client.RequestManager;
import org.testng.Assert;

public class PersonalInformationHook {

    static final int STATUS_OK_NO_CONTENT = 204;

    /**
     * [SL] Restore the data of the user to the default data.
     */
    @After(value = "@restorePersonalInformation", order = 1)
    public void personalInformationRestore() {
        Response response = RequestManager.get("/user");
        String userId = response.jsonPath().getString("recentItems[0].Id");

        String valeByDefect = "src/main/java/org/fundacionjala/salesforce/config/jsonFiles/defaultAccountData.json";
        int actual = RequestManager.patch("/user/" + userId, valeByDefect).getStatusCode();
        Assert.assertEquals(actual, STATUS_OK_NO_CONTENT);
    }
}
