package org.fundacionjala.salesforce.ui.pageObjects.account.accountDetailsPage;

import java.util.Map;
import java.util.Set;

public interface IAccountDetailsPage {
    Map<String, String> getAccountDetails(Set<String> formFields);

    String getAccountId();
}
