package org.fundacionjala.salesforce.ui.skins;

import org.fundacionjala.salesforce.ui.pageObjects.account.accountCreationPage.ClassicAccountCreationPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountDetailsPage.ClassicAccountDetailsPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountsPage.ClassicAccountsPage;

/**
 * [MR] Factory Class that provides page objects related to Classic Skin.
 */
public class ClassicSkinFactory implements ISkinFactory {

    private ClassicAccountsPage accountsPage;
    private ClassicAccountCreationPage accountCreationPage;
    private ClassicAccountDetailsPage accountDetailsPage;

    /**
     * Gets SkinFactory's Accounts Page.
     *
     * @return AccountsPage
     */
    @Override
    public ClassicAccountsPage getAccountsPage() {
        return accountsPage;
    }

    /**
     * Gets SkinFactory's Account Creation Page.
     *
     * @return AccountCreationPage
     */
    @Override
    public ClassicAccountCreationPage getAccountCreationPage() {
        return accountCreationPage;
    }

    /**
     * Gets SkinFactory's Account Details Page.
     *
     * @return AccountDetailsPage
     */
    @Override
    public ClassicAccountDetailsPage getAccountDetailsPage() {
        return accountDetailsPage;
    }
}
