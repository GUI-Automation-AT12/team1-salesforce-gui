package org.fundacionjala.salesforce.ui.pageObjects.account.accountsPage;

import org.fundacionjala.salesforce.ui.pageObjects.account.accountCreationPage.IAccountCreationPage;

import java.util.Map;

/**
 * [MR] Interface for salesforce AccountsPage independent of the skin.
 */
public interface IAccountsPage {
    IAccountCreationPage goToAccountCreation();

    Map<String, String> getAccountDataFromTable(final String accountId);
}
