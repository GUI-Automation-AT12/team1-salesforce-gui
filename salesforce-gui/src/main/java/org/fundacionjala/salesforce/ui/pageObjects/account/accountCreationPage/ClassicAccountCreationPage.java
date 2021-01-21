package org.fundacionjala.salesforce.ui.pageObjects.account.accountCreationPage;

import org.fundacionjala.salesforce.ui.pageObjects.account.accountDetailsPage.ClassicAccountDetailsPage;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;
import org.fundacionjala.salesforce.ui.entities.Account;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountDetailsPage.IAccountDetailsPage;

import java.util.Set;

public class ClassicAccountCreationPage extends BasePage implements IAccountCreationPage {
    @Override
    public IAccountDetailsPage fillAccountInformation(Set<String> formFields, Account account) {
        return new ClassicAccountDetailsPage();
    }
}
