package org.fundacionjala.salesforce.ui.skins;

import org.fundacionjala.salesforce.ui.pageObjects.PersonalInformation.AbstractEditPersonalInformationPage;


/**
 * [MR] Interface for Skin Factories classes.
 */
public interface ISkinFactory {

    /**
     * Returns personalInformationPage.
     *
     * @return a AbstractEditPersonalInformationPage
     */
    AbstractEditPersonalInformationPage personalInformation();

    /**
     * Returns importAccountPage.
     *
     * @return a AbstractImportAccountPage
     */
    AbstractEditPersonalInformationPage personalInformation();
}
