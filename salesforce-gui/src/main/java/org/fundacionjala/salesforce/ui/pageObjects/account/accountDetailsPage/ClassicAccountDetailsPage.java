package org.fundacionjala.salesforce.ui.pageObjects.account.accountDetailsPage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.constants.AccountConstants;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;
import org.fundacionjala.salesforce.utils.PageTransporter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/**
 * [MR] ClassicAccountDetailsPage object.
 */
public class ClassicAccountDetailsPage extends BasePage implements IAccountDetailsPage {

    private String accountInfoXpath = "//td[preceding-sibling::td[text()='%1$s']][1]//%2$s";

    private String getTextFromTextDetail(final String fieldName, final String tagType) {
        return GuiInteractioner.getTextFromWebElement(By.xpath(String.format(accountInfoXpath, fieldName, tagType)));
    }

    private HashMap<String, Supplier<String>> composeStrategyGetterMap() {
        HashMap<String, Supplier<String>> strategyMap = new HashMap<>();
        strategyMap.put(AccountConstants.NAME_KEY, () -> getTextFromTextDetail("Account Name", "div"));
        strategyMap.put(AccountConstants.RATING_KEY, () -> getTextFromTextDetail("Rating", "div"));
        strategyMap.put(AccountConstants.SITE_KEY, () -> getTextFromTextDetail("Account Site", "div"));
        strategyMap.put(AccountConstants.DESCRIPTION_KEY, () -> getTextFromTextDetail("Description", "div"));
        strategyMap.put(AccountConstants.BILLING_CITY_KEY, () -> getTextFromTextDetail("Billing Address", "div"));
        strategyMap.put(AccountConstants.PARENT_ACCOUNT_KEY, () -> getTextFromTextDetail("Parent Account", "a"));
        strategyMap.put(AccountConstants.PHONE_KEY, () -> getTextFromTextDetail("Phone", "div"));
        return strategyMap;
    }

    /**
     * Gets specific data from Account Details.
     *
     * @param fields to specify required data
     * @return Account Details as Map
     */
    @Override
    public Map<String, String> getAccountDetails(final Set<String> fields) {
        Map accountInfoMap = new HashMap<String, String>();
        fields.forEach(field -> accountInfoMap.put(field, composeStrategyGetterMap().get(field).get()));
        return accountInfoMap;
    }

    /**
     * Gets Account Id from the URL.
     *
     * @return Account Id
     */
    @Override
    public String getAccountId() {
        getDriverWait().until(ExpectedConditions.not(ExpectedConditions.urlContains("/e")));
        String currentUrl = PageTransporter.getCurrentUrl();
        return currentUrl.substring(currentUrl.lastIndexOf("/") + 1);
    }

    @Override
    protected void waitLoadPage() {
    }
}