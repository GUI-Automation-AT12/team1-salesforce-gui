package org.fundacionjala.salesforce.ui.PersonalInformation;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.ui.PersonalInformation.AbstractEditPersonalInformationPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

public class LightningEditPersonalInformationPage extends AbstractEditPersonalInformationPage {

    @FindBy(xpath = "//iframe [@title='Personal Information ~ Salesforce - Developer Edition']")
    private WebElement iframeXpath;

    /**
     * This method update the data of the entities and web.
     *
     * @param formFields is a map with the all data to update
     */
    @Override
    public void update(final Map<String, String> formFields) {
        String parentWindowHandle = getDriver().getWindowHandle();
        try {
            GuiInteractioner.frameToBeAvailableAndSwitchToIt(iframeXpath);
            setInformationToEntities(formFields);
            changeDataFormWeb();
            saveData();
        } finally {
            getDriver().switchTo().window(parentWindowHandle);
        }
    }

    /**
     * Returns a Map with all data of the form web.
     *
     * @return a Map with the information required
     */
    @Override
    public Map<String, String> getWebFromAsMap() {
        String parentWindowHandle = getDriver().getWindowHandle();
        try {
            GuiInteractioner.frameToBeAvailableAndSwitchToIt(iframeXpath);
            return getWebForm();
        } finally {
            getDriver().switchTo().window(parentWindowHandle);
        }
    }

    /**
     * Method wait to load the page.
     */
    @Override
    protected void waitLoadPage() {
    }
}
