package org.fundacionjala.salesforce.ui.pageObjects.account.accountCreationPage;

import org.fundacionjala.salesforce.ui.pageObjects.account.accountDetailsPage.IAccountDetailsPage;
import org.fundacionjala.salesforce.ui.entities.Account;

import java.util.Set;

public interface IAccountCreationPage {

    /**
     * Fill Account information to create a new one.
     *
     * @param formFields to fill
     * @param account entity to get information to fill
     * @return AccountDetailsPage of the new account
     */
    IAccountDetailsPage fillAccountInformation(Set<String> formFields, Account account);
}
