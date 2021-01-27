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
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.homePage.AbstractHomePage;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.homePage.ClassicHomePage;
import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunitiesPage.AbstractOpportunitiesPage;
import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunitiesPage.ClassicOpportunitiesPage;
import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityCreationPage.AbstractOpportunityCreationPage;
import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityCreationPage.ClassicOpportunityCreationPage;
import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityDetailsPage.AbstractOpportunityDetailsPage;
import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityDetailsPage.ClassicOpportunityDetailsPage;

/**
 * [MR] Factory Class that provides page objects related to Classic Skin.
 */
public class ClassicSkinFactory implements ISkinFactory {

    /**
     * [MR] Returns Classic Factory's Home Page.
     *
     * @return HomePage
     */
    @Override
    public AbstractHomePage getHomePage() {
        return new ClassicHomePage();
    }

    /**
     * [MR] Gets Classic Factory's Accounts Page.
     *
     * @return AccountsPage
     */
    @Override
    public ClassicAccountsPage getAccountsPage() {
        return new ClassicAccountsPage();
    }

    /**
     * [MR] Gets Classic Factory's Account Creation Page.
     *
     * @return AccountCreationPage
     */
    @Override
    public ClassicAccountCreationPage getAccountCreationPage() {
        return new ClassicAccountCreationPage();
    }

    /**
     * [MR] Gets Classic Factory's Account Details Page.
     *
     * @return AccountDetailsPage
     */
    @Override
    public ClassicAccountDetailsPage getAccountDetailsPage() {
        return new ClassicAccountDetailsPage();
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
    /**
     * [MR] Returns Classic Factory's Opportunities Page.
     *
     * @return OpportunitiesPage
     */
    @Override
    public AbstractOpportunitiesPage getOpportunitiesPage() {
        return new ClassicOpportunitiesPage();
    }

    /**
     * [MR] Returns Classic Factory's Opportunity Creation Page.
     *
     * @return OpportunityCreationPage
     */
    @Override
    public AbstractOpportunityCreationPage getOpportunityCreationPage() {
        return new ClassicOpportunityCreationPage();
    }

    /**
     * [MR] Returns Classic Factory's Opportunity Details Page.
     *
     * @return OpportunityDetailsPage
     */
    @Override
    public AbstractOpportunityDetailsPage getOpportunityDetailsPage() {
        return new ClassicOpportunityDetailsPage();
    }
}
