package org.fundacionjala.salesforce.ui.skins.classic.personalInformation;

import org.fundacionjala.core.selenium.interaction.WebDriverManager;
import org.fundacionjala.salesforce.ui.skins.abstractPage.personalInformation.AbstractEditPersonalInformation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class EditPersonalInformationClassic extends AbstractEditPersonalInformation {

    @FindBy(xpath = "//input [@title='Save']")
    private WebElement saveBtn;

    /**
     * Method wait to load BoardPage.
     */
    @Override
    protected void waitLoadPage() {
        WebDriverManager.getInstance().getWebDriverWait().until(ExpectedConditions.visibilityOf(saveBtn));
    }
}
