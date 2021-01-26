package org.fundacionjala.salesforce.ui.skins;

import org.fundacionjala.salesforce.ui.pageObjects.account.accountImportPage.AbstractImportAccountPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountImportPage.ClassicImportAccountPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountCreationPage.ClassicAccountCreationPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountDetailsPage.ClassicAccountDetailsPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountListPage.AbstractAccountListPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountListPage.ClassicAccountListPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountsPage.ClassicAccountsPage;
import org.fundacionjala.salesforce.ui.pageObjects.PersonalInformation.AbstractEditPersonalInformationPage;
import org.fundacionjala.salesforce.ui.pageObjects.PersonalInformation.ClassicEditPersonalInformationPage;

/**
 * [MR] Factory Class that provides page objects related to Classic Skin.
 */
public class ClassicSkinFactory implements ISkinFactory {

    private ClassicAccountsPage accountsPage = new ClassicAccountsPage();
    private ClassicAccountCreationPage accountCreationPage =  new ClassicAccountCreationPage();
    private ClassicAccountDetailsPage accountDetailsPage = new ClassicAccountDetailsPage();

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

    /**
     * [SL] Returns personalInformationPage.
     *
     * @return a ClassicEditPersonalInformationPage
     */
    @Override
    public final AbstractEditPersonalInformationPage personalInformationPage() {
        return new ClassicEditPersonalInformationPage();
    }

    @Override
    public final AbstractImportAccountPage importAccountPage() {
        return new ClassicImportAccountPage();
    }

    /**
     * Returns accountListPage.
     *
     * @return a AbstractAccountListPage
     */
    @Override
    public AbstractAccountListPage accountListPage() {
        return new ClassicAccountListPage();
    }
}
