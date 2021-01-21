package org.fundacionjala.salesforce.ui.skins;

import io.restassured.response.Response;
import org.fundacionjala.salesforce.ui.entities.Account;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * [MR] Interface for Skin Factories classes.
 */
public interface ISkinFactory {

    /**
     * Goes to a specific page.
     * @throws MalformedURLException
     */
    void goToPage(final String pageName) throws MalformedURLException;

    void createNewAccount(final Set<String> formFields, final Account account);

    Map<String, String> getAccountDetails(final Set<String> fields);

    String getAccountId();

    Map<String, String> getAccountDataFromTable(final String accountId);
}
