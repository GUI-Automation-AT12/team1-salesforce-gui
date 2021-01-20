package org.fundacionjala.salesforce.ui.skins;

import org.fundacionjala.salesforce.ui.skins.abstractPage.personalInformation.AbstractEditPersonalInformationPage;
import org.fundacionjala.salesforce.ui.skins.classic.ClassicHomePage;
import org.fundacionjala.salesforce.ui.skins.classic.personalInformation.ClassicEditPersonalInformationPage;
import org.fundacionjala.salesforce.ui.skins.iPages.IHomePage;
import org.fundacionjala.salesforce.utils.PageTransporter;

import java.net.MalformedURLException;

/**
 * [MR] Factory Class that provides page objects related to Classic Skin.
 */
public class ClassicSkinFactory implements ISkinFactory {

    /**
     * Goes to home page in Classic skin.
     * @return a new instance of ClassicHomePage.
     * @throws MalformedURLException
     */
    @Override
    public IHomePage goHomePage() throws MalformedURLException {
        PageTransporter.navigateToUrl("home/home.jsp?source=lex");
        return new ClassicHomePage();
    }

    /**
     * Returns personalInformationPage.
     *
     * @return a ClassicEditPersonalInformationPage
     */
    @Override
    public AbstractEditPersonalInformationPage personalInformation() {
        return new ClassicEditPersonalInformationPage();
    }
}
