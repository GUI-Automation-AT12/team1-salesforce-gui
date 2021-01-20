package org.fundacionjala.salesforce.ui.skins;

import org.fundacionjala.salesforce.ui.PersonalInformation.AbstractEditPersonalInformationPage;
import org.fundacionjala.salesforce.ui.PersonalInformation.ClassicEditPersonalInformationPage;

/**
 * [MR] Factory Class that provides page objects related to Classic Skin.
 */
public class ClassicSkinFactory implements ISkinFactory {

    /**
     * [SL] Returns personalInformationPage.
     *
     * @return a ClassicEditPersonalInformationPage
     */
    @Override
    public AbstractEditPersonalInformationPage personalInformation() {
        return new ClassicEditPersonalInformationPage();
    }
}
