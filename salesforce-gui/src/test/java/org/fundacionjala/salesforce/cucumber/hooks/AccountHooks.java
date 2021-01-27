package org.fundacionjala.salesforce.cucumber.hooks;

import io.cucumber.java.After;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.fundacionjala.core.api.client.RequestManager;
import org.fundacionjala.core.selenium.interaction.WebDriverManager;
import org.fundacionjala.salesforce.ui.context.Context;
import org.fundacionjala.salesforce.ui.entities.Account;

/**
 * [MR] Hooks for scenarios related to Accounts.
 */
public class AccountHooks {

    private final Context context;

    /**
     * Adds Dependency injection to share Context information.
     *
     * @param sharedContext
     */
    public AccountHooks(final Context sharedContext) {
        this.context = sharedContext;
    }

    /**
     * Hook that delete an Account saved in Context via API.
     */
    @After(value = "@deleteAccount", order = 1)
    public void deleteAccount() {
        Response response = RequestManager.get("/Account/" + context.getAccount().getId());
        if (response.statusCode() == HttpStatus.SC_OK) {
            RequestManager.delete("/Account/" + context.getAccount().getId());
        }
        WebDriverManager.getInstance().quit();
    }

    /**
     * [SL] Hook that delete an Account saved in Context via API.
     */
    @After(value = "@deleteAccounts", order = 1)
    public void deleteAccounts() {
        for (Account account : context.getAccountList()) {
            RequestManager.delete("/Account/" + account.getId());
        }
    }
}
