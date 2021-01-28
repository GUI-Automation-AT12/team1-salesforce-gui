package org.fundacionjala.salesforce.cucumber.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.fundacionjala.core.api.client.RequestManager;
import org.fundacionjala.core.selenium.interaction.WebDriverManager;
import org.fundacionjala.salesforce.api.ApiAuthenticator;
import org.fundacionjala.salesforce.cucumber.stepdefs.LoginSteps;
import org.fundacionjala.salesforce.ui.context.Context;
import org.fundacionjala.salesforce.ui.entities.Account;
import org.fundacionjala.salesforce.utils.CSVReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * [MR] Hooks for scenarios related to Accounts.
 */
public class AccountHooks {

    //Dependency Injection
    private final Context context;

    private static final int CHARS_TO_TRIM = 3;
    private static List<String> csvAccountsList = new ArrayList<>();

    /**
     * Adds Dependency injection to share Context information.
     *
     * @param sharedContext
     */
    public AccountHooks(final Context sharedContext) {
        this.context = sharedContext;
    }

    /**
     * [MR] Hook that delete an Account saved in Context via API.
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
     * [MR] Hook that create many account from a Csv file through Salesforce API.
     *
     * @throws IOException
     */
    @Before(value = "@createAccountsFromCsv")
    public void createAccountsFromCsv() throws IOException {
        try {
            RequestManager.get("/Account/");
        } catch (IllegalArgumentException e) {
            String sourceFile = "src/test/resources/files/csv/preconditionAccounts.csv";
            RequestManager.setRequestSpec(ApiAuthenticator.getLoggedReqSpec(
                    context.getUserByAlias("Account Owner User")));
            List<Map<String, String>> accountsList = CSVReader.getListOfMapsFromCsvFile(sourceFile);
            for (Map<String, String> accountInfo : accountsList) {
                String body = "{\n";
                for (String key : accountInfo.keySet()) {
                    body += "\"" + key + "\": \"" + accountInfo.get(key) + "\", \n";
                }
                body = body.substring(0, body.length() - CHARS_TO_TRIM) + "\n}";
                Response response = RequestManager.post("/Account/", body);
                if (response.getStatusCode() == HttpStatus.SC_CREATED) {
                    csvAccountsList.add(response.jsonPath().getString("id"));
                }
            }
        }
    }

    /**
     * [MR] Hook that delete many accounts created from a Csv file through Salesforce API.
     *
     * @param scenario run before this hook
     */
    @After(value = "@deleteAccountsFromCsv")
    public void deleteAccountsFromCsv(final Scenario scenario) {
        if (scenario.getSourceTagNames().contains("@FinalExample")) {
            for (String id : csvAccountsList) {
                RequestManager.delete("/Account/" + id);
            }
            csvAccountsList.clear();
            LoginSteps.setLoggedFalse();
            WebDriverManager.getInstance().quit();
        }
    }

    /**
     * [SL] Hook that delete Accounts List saved in Context via API.
     */
    @After(value = "@deleteAccounts", order = 1)
    public void deleteAccounts() {
        for (Account account : context.getAccountList()) {
            RequestManager.delete("/Account/" + account.getId());
        }
    }
}
