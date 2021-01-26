package org.fundacionjala.salesforce.ui.skins;

import org.fundacionjala.salesforce.ui.pageObjects.account.accountImportPage.AbstractImportAccountPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountImportPage.LightningImportAccountPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountCreationPage.LightningAccountCreationPopup;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountDetailsPage.LightningAccountDetailsPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountListPage.AbstractAccountListPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountListPage.LightningImportAccountListPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountsPage.LightningAccountsPage;
import org.fundacionjala.salesforce.ui.pageObjects.PersonalInformation.AbstractEditPersonalInformationPage;
import org.fundacionjala.salesforce.ui.pageObjects.PersonalInformation.LightningEditPersonalInformationPage;
import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunitiesPage.AbstractOpportunitiesPage;
import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunitiesPage.LightningOpportunitiesPage;
import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityCreationPage.AbstractOpportunityCreationPage;
import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityCreationPage.LightningOpportunityCreationPopup;
import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityDetailsPage.AbstractOpportunityDetailsPage;
import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityDetailsPage.LightningOpportunityDetailsPage;

/**
 * [MR] Factory Class that provides page objects related to Lightning Skin.
 */
public class LightningSkinFactory implements ISkinFactory {

    private LightningAccountsPage accountsPage = new LightningAccountsPage();
    private LightningAccountCreationPopup accountCreationPopup = new LightningAccountCreationPopup();
    private LightningAccountDetailsPage accountDetailsPage = new LightningAccountDetailsPage();
    private LightningOpportunitiesPage opportunitiesPage = new LightningOpportunitiesPage();
    private LightningOpportunityCreationPopup opportunityCreationPage = new LightningOpportunityCreationPopup();
    private LightningOpportunityDetailsPage opportunityDetailsPage = new LightningOpportunityDetailsPage();

    /**
     * [MR] Gets Lightning Factory's Accounts Page.
     *
     * @return AccountsPage
     */
    @Override
    public LightningAccountsPage getAccountsPage() {
        return accountsPage;
    }

    /**
     * [MR] Gets Lightning Factory's Account Creation Page.
     *
     * @return AccountCreationPage
     */
    @Override
    public LightningAccountCreationPopup getAccountCreationPage() {
        return accountCreationPopup;
    }

    /**
     * [MR] Gets Lightning Factory's Account Details Page.
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
    public final AbstractEditPersonalInformationPage personalInformationPage() {
        return new LightningEditPersonalInformationPage();
    }

    @Override
    public final AbstractImportAccountPage importAccountPage() {
        return new LightningImportAccountPage();
    }

    @Override
    public final AbstractAccountListPage accountListPage() {
        return new LightningImportAccountListPage();
    }

    /**
     * [MR] Returns Lightning Factory's Opportunities Page.
     *
     * @return OpportunitiesPage
     */
    @Override
    public AbstractOpportunitiesPage getOpportunitiesPage() {
        return opportunitiesPage;
    }

    /**
     * [MR] Returns Lightning Factory's Opportunity Creation Popup.
     *
     * @return OpportunityCreationPopup
     */
    @Override
    public AbstractOpportunityCreationPage getOpportunityCreationPage() {
        return opportunityCreationPage;
    }

    /**
     * [MR] Returns Lightning Factory's Opportunity Details Page.
     *
     * @return OpportunityDetailsPage
     */
    @Override
    public AbstractOpportunityDetailsPage getOpportunityDetailsPage() {
        return opportunityDetailsPage;
    }
}
