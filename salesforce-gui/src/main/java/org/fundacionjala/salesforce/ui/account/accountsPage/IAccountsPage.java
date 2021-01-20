package org.fundacionjala.salesforce.ui.account.accountsPage;

import org.fundacionjala.salesforce.ui.account.accountCreationPage.IAccountCreationPage;

/**
 * [MR] Interface for salesforce AccountsPage independent of the skin.
 */
public interface IAccountsPage {
    IAccountCreationPage goToAccountCreation();
}
