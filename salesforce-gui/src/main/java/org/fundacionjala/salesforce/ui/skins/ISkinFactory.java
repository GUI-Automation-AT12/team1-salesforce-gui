package org.fundacionjala.salesforce.ui.skins;

import org.fundacionjala.salesforce.ui.skins.iPages.IHomePage;

import java.net.MalformedURLException;

/**
 * [MR] Interface for Skin Factories classes.
 */
public interface ISkinFactory {

    /**
     * Allows to go to Home Page.
     * @return IHomePage
     * @throws MalformedURLException
     */
    IHomePage goHomePage() throws MalformedURLException;
}
