package org.fundacionjala.salesforce.ui.pageObjects.account.accountDetailsPage;

import java.util.Map;
import java.util.Set;

public interface IAccountDetailsPage {
    /**
     * Gets specific data from Account Details.
     *
     * @param fields to specify required data
     * @return Account Details as Map
     */
    Map<String, String> getAccountDetails(Set<String> fields);

    /**
     * Gets Account Id from the URL.
     *
     * @return Account Id
     */
    String getAccountId();
}
