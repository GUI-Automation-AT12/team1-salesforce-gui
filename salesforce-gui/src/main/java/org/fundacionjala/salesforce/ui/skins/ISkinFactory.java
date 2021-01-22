package org.fundacionjala.salesforce.ui.skins;

import org.fundacionjala.salesforce.ui.pageObjects.account.accountCreationPage.IAccountCreationPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountDetailsPage.IAccountDetailsPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountsPage.IAccountsPage;
import org.fundacionjala.salesforce.ui.pageObjects.PersonalInformation.AbstractEditPersonalInformationPage;


/**
 * [MR] Interface for Skin Factories classes.
 */
public interface ISkinFactory {

    /**
     * Gets SkinFactory's Accounts Page.
     *
     * @return AccountsPage
     */
    IAccountsPage getAccountsPage();

    /**
     * Gets SkinFactory's Account Creation Page.
     *
     * @return AccountCreationPage
     */
    IAccountCreationPage getAccountCreationPage();

    /**
     * Gets SkinFactory's Account Details Page.
     *
     * @return AccountDetailsPage
     */
    IAccountDetailsPage getAccountDetailsPage();

    /**
     * Returns personalInformationPage.
     *
     * @return a AbstractEditPersonalInformationPage
     */
    AbstractEditPersonalInformationPage personalInformation();
}
