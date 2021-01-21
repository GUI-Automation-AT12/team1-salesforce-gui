package org.fundacionjala.salesforce.ui.pageObjects.account.accountDetailsPage;

import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;

import java.util.Map;
import java.util.Set;

/**
 * [MR] ClassicAccountDetailsPage object.
 */
public class ClassicAccountDetailsPage extends BasePage implements IAccountDetailsPage {
    /**
     * Gets specific data from Account Details.
     *
     * @param fields to specify required data
     * @return Account Details as Map
     */
    @Override
    public Map<String, String> getAccountDetails(final Set<String> fields) {
        return null;
    }

    /**
     * Gets Account Id from the URL.
     *
     * @return Account Id
     */
    @Override
    public String getAccountId() {
        return null;
    }
}
