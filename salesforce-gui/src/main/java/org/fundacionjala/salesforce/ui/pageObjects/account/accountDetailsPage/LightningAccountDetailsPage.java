package org.fundacionjala.salesforce.ui.pageObjects.account.accountDetailsPage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.constants.AccountConstants;
import org.fundacionjala.salesforce.constants.TagConstants;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;
import org.fundacionjala.salesforce.utils.PageTransporter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/**
 * [MR] LightningAccountDetailsPage object.
 */
public class LightningAccountDetailsPage extends BasePage implements IAccountDetailsPage {

    private static final int ACCOUNT_STRING_SIZE = 8;

    private String opportunityToSearchXpath =
            "//div[contains(@class,'normal')]//article[.//span[@title='Opportunities']]//a[contains(@href,'%s')]";

    @FindBy(xpath = "//ul[@role='tablist']/li[@title='Details']/a")
    private WebElement detailsTab;

    private void clickDetailsTab() {
        GuiInteractioner.clickWebElement(detailsTab);
    }

    private String accountInfoXpath = "//span[.='%1$s']/../../div[2]//%2$s";

    private String getTextFromDetail(final String fieldName, final String tagType) {
        return GuiInteractioner.getTextFromWebElement(By.xpath(String.format(accountInfoXpath, fieldName, tagType)));
    }

    private HashMap<String, Supplier<String>> composeStrategyGetterMap() {
        HashMap<String, Supplier<String>> strategyMap = new HashMap<>();
        strategyMap.put(AccountConstants.NAME_KEY, () -> getTextFromDetail("Account Name",
                TagConstants.LIGHTNING_FORMATTED_TEXT_TAG));
        strategyMap.put(AccountConstants.RATING_KEY, () -> getTextFromDetail("Rating",
                TagConstants.LIGHTNING_FORMATTED_TEXT_TAG));
        strategyMap.put(AccountConstants.SITE_KEY, () -> getTextFromDetail("Account Site",
                TagConstants.LIGHTNING_FORMATTED_TEXT_TAG));
        strategyMap.put(AccountConstants.DESCRIPTION_KEY, () ->
                getTextFromDetail("Description", TagConstants.LIGHTNING_FORMATTED_TEXT_TAG));
        strategyMap.put(AccountConstants.BILLING_CITY_KEY, () -> getTextFromDetail("Billing Address",
                TagConstants.A_TAG + TagConstants.SLASH + TagConstants.DIV_TAG));
        strategyMap.put(AccountConstants.PARENT_ACCOUNT_KEY, () -> getTextFromDetail("Parent Account",
                TagConstants.A_TAG + TagConstants.SLASH + TagConstants.SPAN_TAG));
        strategyMap.put(AccountConstants.PHONE_KEY, () -> getTextFromDetail("Phone",
                TagConstants.A_TAG));
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
        clickDetailsTab();
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
        getDriverWait().until(ExpectedConditions.not(ExpectedConditions.urlContains("new")));
        String currentUrl = PageTransporter.getCurrentUrl();
        return currentUrl.
                substring(currentUrl.indexOf("Account/") + ACCOUNT_STRING_SIZE, currentUrl.indexOf("/view"));
    }

    /**
     * [MR] Search for an specific Opportunity in the Account Details Page.
     *
     * @param opportunityId to search
     * @return true if the opportunity is present, otherwise returns false
     */
    @Override
    public boolean isOpportunityInList(final String opportunityId) {
        try {
            getDriver().findElement(By.xpath(String.format(opportunityToSearchXpath, opportunityId)));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    protected void waitLoadPage() {
    }
}
