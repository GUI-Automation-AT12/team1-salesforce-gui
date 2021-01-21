package org.fundacionjala.salesforce.ui.pageObjects.account.accountCreationPage;

import org.fundacionjala.salesforce.ui.pageObjects.account.accountDetailsPage.ClassicAccountDetailsPage;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;
import org.fundacionjala.salesforce.ui.entities.Account;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountDetailsPage.IAccountDetailsPage;

import java.util.Set;

/**
 * [MR] ClassicAccountCreationPage object.
 */
public class ClassicAccountCreationPage extends BasePage implements IAccountCreationPage {

    /**
     * Fill Account information to create a new one.
     *
     * @param formFields to fill
     * @param account entity to get information to fill
     * @return AccountDetailsPage of the new account
     */
    @Override
    public IAccountDetailsPage fillAccountInformation(final Set<String> formFields, final Account account) {
        return new ClassicAccountDetailsPage();
    }
}
