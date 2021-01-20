package org.fundacionjala.salesforce.ui.skins;

import org.fundacionjala.salesforce.ui.import_account.AbstractImportAccountPage;
import org.fundacionjala.salesforce.ui.import_account.ClassicImportAccount;
import org.fundacionjala.salesforce.ui.pageObjects.PersonalInformation.AbstractEditPersonalInformationPage;
import org.fundacionjala.salesforce.ui.pageObjects.PersonalInformation.ClassicEditPersonalInformationPage;

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

    /**
     * Returns importAccountPage.
     *
     * @return a AbstractImportAccountPage
     */
    @Override
    public AbstractImportAccountPage importAccount() {
        return new ClassicImportAccount();
    }
}
