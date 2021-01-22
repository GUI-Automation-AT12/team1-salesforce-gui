package org.fundacionjala.salesforce.cucumber.hooks;

import io.cucumber.java.After;
import io.restassured.response.Response;
import org.fundacionjala.core.api.client.RequestManager;
import org.fundacionjala.core.selenium.interaction.WebDriverManager;

public class PersonalInformationHook {

    /**
     * [SL] Restore the data of the user to the default data.
     */
    @After(value = "@restorePersonalInformation", order = 1)
    public void personalInformationRestore() {
        Response response = RequestManager.get("/user");
        String userId = response.jsonPath().getString("recentItems[0].Id");
        String valueByDefect = "src/main/java/org/fundacionjala/salesforce/config/jsonFiles/defaultAccountData.json";
        RequestManager.patch("/user/" + userId, valueByDefect);
        WebDriverManager.getInstance().quit();
    }
}
