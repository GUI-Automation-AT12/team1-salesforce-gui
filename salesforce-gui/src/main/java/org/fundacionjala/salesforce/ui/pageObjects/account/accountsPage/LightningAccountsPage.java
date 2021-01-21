package org.fundacionjala.salesforce.ui.pageObjects.account.accountsPage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.constants.AccountConstants;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountCreationPage.IAccountCreationPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountCreationPage.LightningAccountCreationPopup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;

public class LightningAccountsPage extends BasePage implements IAccountsPage {

    @FindBy(xpath = "//tbody/tr/th/span/a")
    private List<WebElement> accountLinks;

    private String accountNameXpath = "//tr[count(//tr[.//a[contains(@href,'%s')]]/preceding-sibling::tr) + 1]//a[@data-refid]";

    private String accountSiteXpath = "//tr[count(//tr[.//a[contains(@href,'%s')]]/preceding-sibling::tr) + 1]/td[not(contains(@class,'lock'))]//span[contains(@data-aura-class,'OutputText')]";

    private String accountPhoneXpath = "//tr[count(//tr[.//a[contains(@href,'%s')]]/preceding-sibling::tr) + 1]//span[contains(@data-aura-class,'OutputPhone')]";

    @FindBy(xpath = "//a[@title='New']")
    private WebElement newAccountBtn;

    @FindBy(css = "div.actionBody")
    private WebElement newAccountModal;

    private void clickNewAccountBtn() {
        GuiInteractioner.clickWebElement(newAccountBtn);
    }

    @Override
    public IAccountCreationPage goToAccountCreation() {
        clickNewAccountBtn();
        return new LightningAccountCreationPopup();
    }

    private String getTextFromXpathFormattingId(final String locator, final String accountId) {
        return GuiInteractioner.getTextFromWebElement(By.xpath(String.format(locator, accountId)));
    }

    /**
     *
     * @param accountId
     * @return
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
