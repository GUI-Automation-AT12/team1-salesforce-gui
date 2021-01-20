package org.fundacionjala.salesforce.ui.account.accountCreationPage;

import org.fundacionjala.salesforce.ui.account.accountDetailsPage.IAccountDetailsPage;
import org.fundacionjala.salesforce.ui.entities.Account;

import java.util.Set;

public interface IAccountCreationPage {
    IAccountDetailsPage fillAccountInformation(final Set<String> formFields, Account account);
}
