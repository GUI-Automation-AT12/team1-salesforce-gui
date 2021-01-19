package org.fundacionjala.salesforce.ui.skins.lightning.personalInformation;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.ui.skins.abstractPage.personalInformation.AbstractEditPersonalInformation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class EditPersonalInformationLightning extends AbstractEditPersonalInformation {

    @FindBy(xpath = "//iframe [@title='Personal Information ~ Salesforce - Developer Edition']")
    private WebElement iframeXpath;

    /**
     * Method wait to load BoardPage.
     */
    @Override
    protected void waitLoadPage() {
        GuiInteractioner.frameToBeAvailableAndSwitchToIt(iframeXpath);
    }
}
