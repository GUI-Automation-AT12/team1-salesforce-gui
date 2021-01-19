package org.fundacionjala.salesforce.ui.skins;

import org.fundacionjala.salesforce.ui.entities.Account;
import org.fundacionjala.salesforce.ui.skins.iPages.IAccountCreationPage;
import org.fundacionjala.salesforce.ui.skins.lightning.LightningAccountCreationPopup;
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

    private LightningAccountsPage lightningAccountsPage = new LightningAccountsPage();
    private IAccountCreationPage lightningAccountCreationPopup;

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
        lightningAccountCreationPopup = lightningAccountsPage.goToAccountCreation();
        LightningAccountCreationPopup creationPopup = (LightningAccountCreationPopup) lightningAccountCreationPopup;
        creationPopup.fillAccountInformation(formFields, account);
    }
}
