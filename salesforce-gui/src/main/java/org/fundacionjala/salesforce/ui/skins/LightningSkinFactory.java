package org.fundacionjala.salesforce.ui.skins;

import org.fundacionjala.salesforce.ui.pageObjects.account.accountCreationPage.LightningAccountCreationPopup;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountDetailsPage.LightningAccountDetailsPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountsPage.LightningAccountsPage;

/**
 * [MR] Factory Class that provides page objects related to Lightning Skin.
 */
public class LightningSkinFactory implements ISkinFactory {

    private LightningAccountsPage accountsPage = new LightningAccountsPage();
    private LightningAccountCreationPopup accountCreationPopup = new LightningAccountCreationPopup();
    private LightningAccountDetailsPage accountDetailsPage = new LightningAccountDetailsPage();

    @Override
    public LightningAccountsPage getAccountsPage() {
        return accountsPage;
    }

    @Override
    public LightningAccountCreationPopup getAccountCreationPage() {
        return accountCreationPopup;
    }

    @Override
    public LightningAccountDetailsPage getAccountDetailsPage() {
        return accountDetailsPage;
    }
}
