package org.fundacionjala.salesforce.ui.skins;

import org.fundacionjala.salesforce.ui.pageObjects.account.accountCreationPage.LightningAccountCreationPopup;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountDetailsPage.LightningAccountDetailsPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountsPage.LightningAccountsPage;
import org.fundacionjala.salesforce.ui.pageObjects.PersonalInformation.AbstractEditPersonalInformationPage;
import org.fundacionjala.salesforce.ui.pageObjects.PersonalInformation.LightningEditPersonalInformationPage;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.homePage.AbstractHomePage;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.homePage.LightningHomePage;
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

    /**
     * [MR] Returns Lightning Factory's Home Page.
     *
     * @return HomePage
     */
    @Override
    public AbstractHomePage getHomePage() {
        return new LightningHomePage();
    }

    /**
     * [MR] Gets Lightning Factory's Accounts Page.
     *
     * @return AccountsPage
     */
    @Override
    public LightningAccountsPage getAccountsPage() {
        return new LightningAccountsPage();
    }

    /**
     * [MR] Gets Lightning Factory's Account Creation Page.
     *
     * @return AccountCreationPage
     */
    @Override
    public LightningAccountCreationPopup getAccountCreationPage() {
        return new LightningAccountCreationPopup();
    }

    /**
     * [MR] Gets Lightning Factory's Account Details Page.
     *
     * @return AccountDetailsPage
     */
    @Override
    public LightningAccountDetailsPage getAccountDetailsPage() {
        return new LightningAccountDetailsPage();
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

    /**
     * [MR] Returns Lightning Factory's Opportunities Page.
     *
     * @return OpportunitiesPage
     */
    @Override
    public AbstractOpportunitiesPage getOpportunitiesPage() {
        return new LightningOpportunitiesPage();
    }

    /**
     * [MR] Returns Lightning Factory's Opportunity Creation Popup.
     *
     * @return OpportunityCreationPopup
     */
    @Override
    public AbstractOpportunityCreationPage getOpportunityCreationPage() {
        return new LightningOpportunityCreationPopup();
    }

    /**
     * [MR] Returns Lightning Factory's Opportunity Details Page.
     *
     * @return OpportunityDetailsPage
     */
    @Override
    public AbstractOpportunityDetailsPage getOpportunityDetailsPage() {
        return  new LightningOpportunityDetailsPage();
    }
}
