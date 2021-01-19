package org.fundacionjala.salesforce.ui.skins;

import org.fundacionjala.salesforce.ui.entities.Account;

import java.net.MalformedURLException;
import java.util.Set;

/**
 * [MR] Interface for Skin Factories classes.
 */
public interface ISkinFactory {

    /**
     * Goes to a specific page.
     * @throws MalformedURLException
     */
    void goToPage(final String pageName) throws MalformedURLException;

    void createNewAccount(final Set<String> formFields, final Account account);
}
