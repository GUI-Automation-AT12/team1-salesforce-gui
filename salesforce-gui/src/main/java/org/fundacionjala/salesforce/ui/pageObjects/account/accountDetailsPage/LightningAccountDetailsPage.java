package org.fundacionjala.salesforce.ui.pageObjects.account.accountDetailsPage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.core.selenium.interaction.WebDriverManager;
import org.fundacionjala.salesforce.constants.AccountConstants;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;
import org.fundacionjala.salesforce.utils.PageTransporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class LightningAccountDetailsPage extends BasePage implements IAccountDetailsPage {

    private static final int ACCOUNT_STRING_SIZE = 8;

    @FindBy(xpath = "//ul[@role='tablist']/li[@title='Details']/a")
    private WebElement detailsTab;

    private void clickDetailsTab() {
        GuiInteractioner.clickWebElement(detailsTab);
    }

    private String lightningFormattedTextXpath = "//span[.='%s']/../../div[2]//lightning-formatted-text";

    private String linkWithChildXpath = "//span[.='%s']/../../div[2]//a/*";

    private String linkWithoutChildXpath = "//span[.='%s']/../../div[2]//a";

    private String getTextFromBy(By by) {
        return GuiInteractioner.getTextFromWebElement(by);
    }

    private String getTextFromTextDetail(final String fieldName) {
        return getTextFromBy(By.xpath(String.format(lightningFormattedTextXpath, fieldName)));
    }

    private String getTextFromChildOfLinkDetail(final String fieldName) {
        return getTextFromBy(By.xpath(String.format(linkWithChildXpath, fieldName)));
    }

    private String getTextFromLinkDetail(final String fieldName) {
        return getTextFromBy(By.xpath(String.format(linkWithoutChildXpath, fieldName)));
    }

    private HashMap<String, Supplier<String>> composeStrategyGetterMap() {
        HashMap<String, Supplier<String>> strategyMap = new HashMap<>();
        strategyMap.put(AccountConstants.NAME_KEY, () -> getTextFromTextDetail("Account Name"));
        strategyMap.put(AccountConstants.RATING_KEY, () -> getTextFromTextDetail("Rating"));
        strategyMap.put(AccountConstants.SITE_KEY, () -> getTextFromTextDetail("Account Site"));
        strategyMap.put(AccountConstants.DESCRIPTION_KEY, () -> getTextFromTextDetail("Description"));
        strategyMap.put(AccountConstants.BILLING_CITY_KEY, () -> getTextFromChildOfLinkDetail("Billing Address"));
        strategyMap.put(AccountConstants.PARENT_ACCOUNT_KEY, () -> getTextFromChildOfLinkDetail("Parent Account"));
        strategyMap.put(AccountConstants.PHONE_KEY, () -> getTextFromLinkDetail("Phone"));
        return strategyMap;
    }

    @Override
    public Map<String, String> getAccountDetails(final Set<String> fields) {
        clickDetailsTab();
        Map accountInfoMap = new HashMap<String, String>();
        fields.forEach(field -> accountInfoMap.put(field, composeStrategyGetterMap().get(field).get()));
        return accountInfoMap;
    }

    @Override
    public String getAccountId() {
        WebDriverManager.getInstance().getWebDriverWait().until(ExpectedConditions.not(ExpectedConditions.urlContains("new")));
        String currentUrl = PageTransporter.getCurrentUrl();
        return currentUrl.substring(currentUrl.indexOf("Account/") + ACCOUNT_STRING_SIZE, currentUrl.indexOf("/view"));
    }
}
