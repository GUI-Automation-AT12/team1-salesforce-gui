package org.fundacionjala.salesforce.ui.pageObjects.account.accountsPage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.constants.AccountConstants;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountCreationPage.IAccountCreationPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountCreationPage.LightningAccountCreationPopup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.Map;
import java.util.HashMap;

/**
 * [MR] LightningAccountsPage object.
 */
public class LightningAccountsPage extends BasePage implements IAccountsPage {

    private String accountNameXpath = "//tr[count(//tr[.//a[contains(@href,'%s')]]/preceding-sibling::tr) + 1]"
            + "//a[@data-refid]";

    private String accountSiteXpath = "//tr[count(//tr[.//a[contains(@href,'%s')]]/preceding-sibling::tr) + 1]"
            + "/td[not(contains(@class,'lock'))]//span[contains(@data-aura-class,'OutputText')]";

    private String accountPhoneXpath = "//tr[count(//tr[.//a[contains(@href,'%s')]]/preceding-sibling::tr) + 1]"
            + "//span[contains(@data-aura-class,'OutputPhone')]";

    @FindBy(xpath = "//a[@title='New']")
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
        return new LightningAccountCreationPopup();
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
        accountInfo.put(AccountConstants.SITE_KEY, getTextFromXpathFormattingId(accountSiteXpath, accountId));
        accountInfo.put(AccountConstants.PHONE_KEY, getTextFromXpathFormattingId(accountPhoneXpath, accountId));
        return accountInfo;
    }
}
