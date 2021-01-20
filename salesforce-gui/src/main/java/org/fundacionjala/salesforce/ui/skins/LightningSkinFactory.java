package org.fundacionjala.salesforce.ui.skins;

import org.fundacionjala.salesforce.ui.PersonalInformation.AbstractEditPersonalInformationPage;
import org.fundacionjala.salesforce.ui.PersonalInformation.LightningEditPersonalInformationPage;

/**
 * [MR] Factory Class that provides page objects related to Lightning Skin.
 */
public class LightningSkinFactory implements ISkinFactory {

    /**
     * [SL] Returns personalInformationPage.
     *
     * @return a LightningEditPersonalInformationPage
     */
    @Override
    public AbstractEditPersonalInformationPage personalInformation() {
        return new LightningEditPersonalInformationPage();
    }
}
