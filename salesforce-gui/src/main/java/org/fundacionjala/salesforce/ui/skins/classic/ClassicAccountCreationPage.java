package org.fundacionjala.salesforce.ui.skins.classic;

import org.fundacionjala.salesforce.ui.commonPages.BasePage;
import org.fundacionjala.salesforce.ui.entities.Account;
import org.fundacionjala.salesforce.ui.skins.iPages.IAccountCreationPage;
import org.fundacionjala.salesforce.ui.skins.iPages.IAccountDetailsPage;

import java.util.Set;

public class ClassicAccountCreationPage extends BasePage implements IAccountCreationPage {
    @Override
    public IAccountDetailsPage fillAccountInformation(Set<String> formFields, Account account) {
        return new ClassicAccountDetailsPage();
    }
}
