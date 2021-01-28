package org.fundacionjala.salesforce.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.path.json.JsonPath;
import org.fundacionjala.core.api.client.RequestManager;
import org.fundacionjala.salesforce.api.ApiAuthenticator;
import org.fundacionjala.salesforce.ui.entities.Account;
import org.fundacionjala.salesforce.ui.entities.EntitiesParser;
import org.fundacionjala.salesforce.ui.entities.User;

import java.io.IOException;
import java.util.List;

/**
 * [SL] This class contains setup functions for the hooks.
 */
public final class Setup {
    /**
     * [SL] Constructor.
     */
    private Setup() {

    }

    /**
     * [SL] Create multiple accounts from json file.
     *
     * @param user
     * @return a List of Accounts
     * @throws IOException
     */

    public static List<Account> createAccounts(final User user) throws IOException {
        RequestManager.setRequestSpec(ApiAuthenticator.getLoggedReqSpec(user));
        List<Account> accounts = EntitiesParser.getAccountListFromJson();

        ObjectMapper mapper;
        ObjectNode accountJs;
        String json = "";
        String id = "";
        for (int i = 0; i < accounts.size(); i++) {
            mapper = new ObjectMapper();
            accountJs = mapper.createObjectNode();
            accountJs.put("Name", accounts.get(i).getName());
            accountJs.put("Phone", accounts.get(i).getPhone());
            accountJs.put("site", accounts.get(i).getSite());
            accountJs.put("BillingCity", accounts.get(i).getBillingCity());
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(accountJs);
            JsonPath jsonPath = RequestManager.post("/Account", json).jsonPath();
            try {
                id = jsonPath.get("id");
            } catch (Exception e) {
                e.printStackTrace();
            }
            accounts.get(i).setId(id);
        }
        return accounts;
    }

    /**
     * [SL] Deletes the created accounts.
     *
     * @param listAccounts a List of Accounts
     */
    public static void deleteAccounts(final List<Account> listAccounts) {
        for (Account account : listAccounts) {
            RequestManager.delete("/Account/" + account.getId());
        }
    }
}
