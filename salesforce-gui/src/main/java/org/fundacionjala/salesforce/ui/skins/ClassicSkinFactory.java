package org.fundacionjala.salesforce.ui.skins;

import org.fundacionjala.salesforce.ui.entities.Account;
import org.fundacionjala.salesforce.ui.skins.classic.ClassicAccountsPage;
import org.fundacionjala.salesforce.ui.skins.iPages.IAccountCreationPage;
import org.fundacionjala.salesforce.utils.PageTransporter;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * [MR] Factory Class that provides page objects related to Classic Skin.
 */
public class ClassicSkinFactory implements ISkinFactory {

    private ClassicAccountsPage classicAccountsPage = new ClassicAccountsPage();
    private IAccountCreationPage classicAccountCreationPage;

    final static Map<String, String> urlMap = new HashMap<>();

    static {
        urlMap.put("HOME", "home/home.jsp?source=lex");
        urlMap.put("ACCOUNTS","001/o");
    }

    /**
     * Goes to a specific page in Classic skin.
     * @throws MalformedURLException
     */
    @Override
    public void goToPage(final String pageName) throws MalformedURLException {
        PageTransporter.navigateToUrl(urlMap.get(pageName.toUpperCase()));
    }

    @Override
    public void createNewAccount(final Set<String> formFields, final Account account) {
        classicAccountCreationPage = classicAccountsPage.goToAccountCreation();
    }
}
