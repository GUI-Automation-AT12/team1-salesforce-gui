package org.fundacionjala.salesforce.ui.skins;

import org.fundacionjala.salesforce.ui.skins.abstractPage.personalInformation.AbstractEditPersonalInformation;
import org.fundacionjala.salesforce.ui.skins.iPages.IHomePage;
import org.fundacionjala.salesforce.ui.skins.lightning.LightningHomePage;
import org.fundacionjala.salesforce.ui.skins.lightning.personalInformation.LightningEditPersonalInformation;
import org.fundacionjala.salesforce.utils.PageTransporter;

import java.net.MalformedURLException;

/**
 * [MR] Factory Class that provides page objects related to Lightning Skin.
 */
public class LightningSkinFactory implements ISkinFactory {

    /**
     * Goes to home page in Lightning skin.
     * @return a new instance of LightningHomePage.
     * @throws MalformedURLException
     */
    @Override
    public IHomePage goHomePage() throws MalformedURLException {
        PageTransporter.navigateToUrl("page/home");
        return new LightningHomePage();
    }
    /**
     * Returns personalInformationPage.
     *
     * @return a LightningEditPersonalInformation
     */
    @Override
    public AbstractEditPersonalInformation personalInformation() {
        return new LightningEditPersonalInformation();
    }
}
