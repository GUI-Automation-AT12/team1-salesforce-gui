package org.fundacionjala.salesforce.ui.skins.classic;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.ui.commonPages.BasePage;
import org.fundacionjala.salesforce.ui.skins.iPages.IAccountCreationPage;
import org.fundacionjala.salesforce.ui.skins.iPages.IAccountsPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ClassicAccountsPage extends BasePage implements IAccountsPage {

    @FindBy(name = "new")
    private WebElement newAccountBtn;

    private void clickNewAccountBtn() {
        GuiInteractioner.clickWebElement(newAccountBtn);
    }

    @Override
    public IAccountCreationPage goToAccountCreation() {
        clickNewAccountBtn();
        return new ClassicAccountCreationPage();
    }
}
