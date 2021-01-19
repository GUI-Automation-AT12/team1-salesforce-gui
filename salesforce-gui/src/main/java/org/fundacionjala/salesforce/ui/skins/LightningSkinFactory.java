package org.fundacionjala.salesforce.ui.skins;

import org.fundacionjala.salesforce.ui.entities.Account;
import org.fundacionjala.salesforce.ui.skins.lightning.LightningAccountCreationPopup;
import org.fundacionjala.salesforce.ui.skins.lightning.LightningAccountDetailsPage;
import org.fundacionjala.salesforce.ui.skins.lightning.LightningAccountsPage;
import org.fundacionjala.salesforce.utils.PageTransporter;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * [MR] Factory Class that provides page objects related to Lightning Skin.
 */
public class LightningSkinFactory implements ISkinFactory {

    private LightningAccountsPage accountsPage = new LightningAccountsPage();
    private LightningAccountCreationPopup accountCreationPopup;
    private LightningAccountDetailsPage accountDetailsPage;

    final static Map<String, String> urlMap = new HashMap<>();

    static {
        urlMap.put("HOME", "page/home");
        urlMap.put("ACCOUNTS","o/Account/list");
    }

    /**
     * Goes to a specific page in Lightning skin.
     * @throws MalformedURLException
     */
    @Override
    public void goToPage(final String pageName) throws MalformedURLException {
        PageTransporter.navigateToUrl(urlMap.get(pageName.toUpperCase()));
    }

    @Override
    public void createNewAccount(final Set<String> formFields, final Account account) {
        accountCreationPopup = (LightningAccountCreationPopup)accountsPage.goToAccountCreation();
        accountDetailsPage = (LightningAccountDetailsPage)accountCreationPopup.fillAccountInformation(formFields, account);
    }

    @Override
    public Map<String, String> getAccountDetails(final Set<String> fields) {
        return accountDetailsPage.getAccountDetails(fields);
    }


}
