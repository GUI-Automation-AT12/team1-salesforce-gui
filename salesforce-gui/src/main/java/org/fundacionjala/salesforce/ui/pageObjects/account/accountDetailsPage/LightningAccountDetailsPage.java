package org.fundacionjala.salesforce.ui.pageObjects.account.accountDetailsPage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.constants.AccountConstants;
import org.fundacionjala.salesforce.constants.TagConstants;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountEditPage.AbstractAccountEditPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountEditPage.LightningAccountEditPage;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;
import org.fundacionjala.salesforce.utils.PageTransporter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
    private static final String ACCOUNT_INFO = "//span[.='%1$s']/../../div[2]//%2$s";
    private static final String OPPORTUNITY_TO_SEARCH =
            "//div[contains(@class,'normal')]//article[.//span[@title='Opportunities']]//a[contains(@href,'%s')]";
    private static final By DETAIL_TAB = By.xpath("//ul[@role='tablist']/li[@title='Details']/a");
    private static final By SLA_EDITION_BUTTON = By.xpath("//button[@title='Edit SLA Expiration Date']");

    private void clickDetailsTab() {
        GuiInteractioner.clickWebElement(DETAIL_TAB);
    }

    private String getTextFromDetail(final String fieldName, final String tagType) {
        return GuiInteractioner.getTextFromWebElement(By.xpath(String.format(ACCOUNT_INFO, fieldName, tagType)));
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
        strategyMap.put(AccountConstants.SLA_EXPIRATION, () ->
                getTextFromDetail("SLA Expiration Date", TagConstants.LIGHTNING_FORMATTED_TEXT_TAG));
        strategyMap.put(AccountConstants.LAST_MODIFIED_BY, () ->
                getTextFromDetail("Last Modified By",
                        TagConstants.LIGHTNING_FORMATTED_TEXT_TAG));
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
        try {
            clickDetailsTab();
        } catch (Exception e) {
            clickDetailsTab();
        }

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
            getDriver().findElement(By.xpath(String.format(OPPORTUNITY_TO_SEARCH, opportunityId)));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * [SL] Gets an accountEditPage.
     *
     * @return a instance of LightningAccountEditPage
     */

    public AbstractAccountEditPage getAccountEditPage() {
        clickDetailsTab();
        GuiInteractioner.clickWebElement(SLA_EDITION_BUTTON);
        return new LightningAccountEditPage();
    }

    /**
     * Method wait to load BoardPage.
     */
    @Override
    protected void waitLoadPage() {
        getDriverWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DETAIL_TAB));
    }
}
