package org.fundacionjala.salesforce.ui.skins.iPages;

import org.fundacionjala.salesforce.ui.entities.Account;

import java.util.Set;

public interface IAccountCreationPage {
    IAccountDetailsPage fillAccountInformation(final Set<String> formFields, Account account);
}
