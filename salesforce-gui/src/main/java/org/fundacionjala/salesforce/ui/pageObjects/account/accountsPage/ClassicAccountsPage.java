package org.fundacionjala.salesforce.ui.pageObjects.account.accountsPage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountCreationPage.ClassicAccountCreationPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountCreationPage.IAccountCreationPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * [MR] ClassicAccountsPage object.
 */
public class ClassicAccountsPage extends BasePage implements IAccountsPage {

    @FindBy(name = "new")
    private WebElement newAccountBtn;

    private void clickNewAccountBtn() {
        GuiInteractioner.clickWebElement(newAccountBtn);
    }

    /**
     * Drives the user to account creation.
     *
     * @return AccountCreationPage
     */
    @Override
    public IAccountCreationPage goToAccountCreation() {
        clickNewAccountBtn();
        return new ClassicAccountCreationPage();
    }

    /**
     * Gets data for an specific Account from Accounts Table.
     *
     * @param accountId to get data
     * @return Account Data as Map
     */
    @Override
    public Map<String, String> getAccountDataFromTable(final String accountId) {
        return null;
    }
}
