package org.fundacionjala.salesforce.ui.skins;

import org.fundacionjala.salesforce.ui.import_account.AbstractImportAccountPage;
import org.fundacionjala.salesforce.ui.import_account.LightningImportAccount;
import org.fundacionjala.salesforce.ui.pageObjects.PersonalInformation.AbstractEditPersonalInformationPage;
import org.fundacionjala.salesforce.ui.pageObjects.PersonalInformation.LightningEditPersonalInformationPage;

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

    /**
     * Returns importAccountPage.
     *
     * @return a AbstractImportAccountPage
     */
    @Override
    public AbstractImportAccountPage importAccount() {
        return new LightningImportAccount();
    }
}
