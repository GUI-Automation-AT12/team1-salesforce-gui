package org.fundacionjala.salesforce.ui.skins;

import org.fundacionjala.salesforce.ui.entities.Account;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountCreationPage.ClassicAccountCreationPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountDetailsPage.ClassicAccountDetailsPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountsPage.ClassicAccountsPage;
import org.fundacionjala.salesforce.utils.PageTransporter;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

/**
 * [MR] Factory Class that provides page objects related to Classic Skin.
 */
public class ClassicSkinFactory implements ISkinFactory {

    private ClassicAccountsPage accountsPage;
    private ClassicAccountCreationPage accountCreationPage;
    private ClassicAccountDetailsPage accountDetailsPage;

    @Override
    public ClassicAccountsPage getAccountsPage() {
        return accountsPage;
    }

    @Override
    public ClassicAccountCreationPage getAccountCreationPage() {
        return accountCreationPage;
    }

    @Override
    public ClassicAccountDetailsPage getAccountDetailsPage() {
        return accountDetailsPage;
    }
}
