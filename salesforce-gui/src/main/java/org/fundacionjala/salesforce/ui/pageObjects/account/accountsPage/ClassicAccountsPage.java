package org.fundacionjala.salesforce.ui.pageObjects.account.accountsPage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.constants.AccountConstants;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountCreationPage.ClassicAccountCreationPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountCreationPage.IAccountCreationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.Map;

/**
 * [MR] ClassicAccountsPage object.
 */
public class ClassicAccountsPage extends BasePage implements IAccountsPage {

    private String accountNameXpath = "//th/a[contains(@href,'%s')]";
    private String accountBillingCityXpath = "//td[1][preceding-sibling::th/a[contains(@href,'%s')]]";
    private String accountPhoneXpath = "//td[2][preceding-sibling::th/a[contains(@href,'%s')]]";

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

    private String getTextFromXpathFormattingId(final String locator, final String accountId) {
        return GuiInteractioner.getTextFromWebElement(By.xpath(String.format(locator, accountId)));
    }

    /**
     * Gets data for an specific Account from Accounts Table.
     *
     * @param accountId to get data
     * @return Account Data as Map
     */
    @Override
    public Map<String, String> getAccountDataFromTable(final String accountId) {
        Map<String, String> accountInfo = new HashMap<>();
        accountInfo.put(AccountConstants.NAME_KEY, getTextFromXpathFormattingId(accountNameXpath, accountId));
        accountInfo.put(AccountConstants.BILLING_CITY_KEY,
                getTextFromXpathFormattingId(accountBillingCityXpath, accountId));
        accountInfo.put(AccountConstants.PHONE_KEY, getTextFromXpathFormattingId(accountPhoneXpath, accountId));
        return accountInfo;
    }

    @Override
    protected final void waitLoadPage() {
        getDriverWait().until(ExpectedConditions.visibilityOf(newAccountBtn));
    }
}
