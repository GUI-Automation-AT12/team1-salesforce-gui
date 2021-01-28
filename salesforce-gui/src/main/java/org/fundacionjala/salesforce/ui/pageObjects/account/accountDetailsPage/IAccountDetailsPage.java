package org.fundacionjala.salesforce.ui.pageObjects.account.accountDetailsPage;

import org.fundacionjala.salesforce.ui.pageObjects.account.accountEditPage.AbstractAccountEditPage;

import java.util.Map;
import java.util.Set;

/**
 * [MR] AccountDetailsPage independent o the skin.
 */
public interface IAccountDetailsPage {
    /**
     * [MR] Gets specific data from Account Details.
     *
     * @param fields to specify required data
     * @return Account Details as Map
     */
    Map<String, String> getAccountDetails(Set<String> fields);

    /**
     * [MR] Gets Account Id from the URL.
     *
     * @return Account Id
     */
    String getAccountId();

    /**
     * [MR] Search for an specific Opportunity in the Account Details Page.
     *
     * @param opportunityId to search
     * @return true if the opportunity is present, otherwise returns false
     */
    boolean isOpportunityInList(String opportunityId);

    /**
     * [SL] Gets account edit page.
     *
     * @return AbstractAccountEditPage
     */
    AbstractAccountEditPage getAccountEditPage();
}
