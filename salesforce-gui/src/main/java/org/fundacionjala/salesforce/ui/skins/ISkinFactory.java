package org.fundacionjala.salesforce.ui.skins;

import org.fundacionjala.salesforce.ui.pageObjects.account.accountCreationPage.IAccountCreationPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountDetailsPage.IAccountDetailsPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountsPage.IAccountsPage;


/**
 * [MR] Interface for Skin Factories classes.
 */
public interface ISkinFactory {

    IAccountsPage getAccountsPage();

    IAccountCreationPage getAccountCreationPage();

    IAccountDetailsPage getAccountDetailsPage();
}
