package org.fundacionjala.salesforce.ui.pageObjects.account.accountsPage;

import org.fundacionjala.salesforce.ui.pageObjects.account.accountCreationPage.IAccountCreationPage;

import java.util.Map;

/**
 * [MR] Interface for salesforce AccountsPage independent of the skin.
 */
public interface IAccountsPage {
    /**
     * Drives the user to account creation.
     *
     * @return AccountCreationPage
     */
    IAccountCreationPage goToAccountCreation();

    /**
     * Gets data for an specific Account from Accounts Table.
     *
     * @param accountId to get data
     * @return Account Data as Map
     */
    Map<String, String> getAccountDataFromTable(String accountId);
}
