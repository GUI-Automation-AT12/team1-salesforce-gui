package org.fundacionjala.salesforce.ui.skins.iPages;

import java.util.Map;
import java.util.Set;

public interface IAccountDetailsPage {
    Map<String, String> getAccountDetails(Set<String> formFields);

    String getAccountId();
}
