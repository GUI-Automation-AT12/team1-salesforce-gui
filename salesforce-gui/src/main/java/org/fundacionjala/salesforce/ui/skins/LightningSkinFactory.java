package org.fundacionjala.salesforce.ui.skins;

import org.fundacionjala.salesforce.ui.pageObjects.account.accountCreationPage.LightningAccountCreationPopup;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountDetailsPage.LightningAccountDetailsPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountsPage.LightningAccountsPage;
import org.fundacionjala.salesforce.ui.pageObjects.PersonalInformation.AbstractEditPersonalInformationPage;
import org.fundacionjala.salesforce.ui.pageObjects.PersonalInformation.LightningEditPersonalInformationPage;

/**
 * [MR] Factory Class that provides page objects related to Lightning Skin.
 */
public class LightningSkinFactory implements ISkinFactory {

    private LightningAccountsPage accountsPage = new LightningAccountsPage();
    private LightningAccountCreationPopup accountCreationPopup = new LightningAccountCreationPopup();
    private LightningAccountDetailsPage accountDetailsPage = new LightningAccountDetailsPage();

    /**
     * Gets SkinFactory's Accounts Page.
     *
     * @return AccountsPage
     */
    @Override
    public LightningAccountsPage getAccountsPage() {
        return accountsPage;
    }

    /**
     * Gets SkinFactory's Account Creation Page.
     *
     * @return AccountCreationPage
     */
    @Override
    public LightningAccountCreationPopup getAccountCreationPage() {
        return accountCreationPopup;
    }

    /**
     * Gets SkinFactory's Account Details Page.
     *
     * @return AccountDetailsPage
     */
    @Override
    public LightningAccountDetailsPage getAccountDetailsPage() {
        return accountDetailsPage;
    }

    /**
     * [SL] Returns personalInformationPage.
     *
     * @return a LightningEditPersonalInformationPage
     */
    @Override
    public AbstractEditPersonalInformationPage personalInformation() {
        return new LightningEditPersonalInformationPage();
    }
}