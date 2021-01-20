package org.fundacionjala.salesforce.ui.account.accountsPage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.ui.commonPages.BasePage;
import org.fundacionjala.salesforce.ui.account.accountCreationPage.ClassicAccountCreationPage;
import org.fundacionjala.salesforce.ui.account.accountCreationPage.IAccountCreationPage;
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
