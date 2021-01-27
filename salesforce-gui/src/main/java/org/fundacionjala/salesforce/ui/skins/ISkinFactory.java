package org.fundacionjala.salesforce.ui.skins;

import org.fundacionjala.salesforce.ui.pageObjects.account.accountImportPage.AbstractImportAccountPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountCreationPage.IAccountCreationPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountDetailsPage.IAccountDetailsPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountListPage.AbstractAccountListPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountsPage.IAccountsPage;
import org.fundacionjala.salesforce.ui.pageObjects.PersonalInformation.AbstractEditPersonalInformationPage;
import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunitiesPage.AbstractOpportunitiesPage;
import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityCreationPage.AbstractOpportunityCreationPage;
import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityDetailsPage.AbstractOpportunityDetailsPage;

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
    AbstractEditPersonalInformationPage personalInformationPage();

    /**
     * Returns importAccountPage.
     *
     * @return a AbstractImportAccountPage
     */
    AbstractImportAccountPage importAccountPage();

    /**
     * Returns Skin Factory's Opportunities Page.
     *
     * @return OpportunitiesPage
     */
    AbstractOpportunitiesPage getOpportunitiesPage();

    /**
     * Returns Skin Factory's Opportunity Creation Page.
     *
     * @return OpportunityCreationPage.
     */
    AbstractOpportunityCreationPage getOpportunityCreationPage();

    /**
     * Returns accountListPage.
     *
     * @return a AbstractAccountListPage
     */
    AbstractAccountListPage accountListPage();

    /**
     * Returns Skin Factory's Opportunity Details Page.
     *
     * @return OpportunityDetailsPage.
     */
    AbstractOpportunityDetailsPage getOpportunityDetailsPage();
}

