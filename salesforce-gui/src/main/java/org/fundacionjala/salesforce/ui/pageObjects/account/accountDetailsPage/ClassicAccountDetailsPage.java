package org.fundacionjala.salesforce.ui.pageObjects.account.accountDetailsPage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.constants.AccountConstants;
import org.fundacionjala.salesforce.constants.TagConstants;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountEditPage.AbstractAccountEditPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountEditPage.ClassicAccountEditPage;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;
import org.fundacionjala.salesforce.utils.PageTransporter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
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
    private String opportunityToSearchXpath =
            "//div[contains(@id,'RelatedOpportunityList_body')]//th/a[contains(@href, '%s')]";
    private static final By EDIT_BUTTON = By.xpath("//input[@title='Edit']");

    private String getTextFromTextDetail(final String fieldName, final String tagType) {
        return GuiInteractioner.getTextFromWebElement(By.xpath(String.format(accountInfoXpath, fieldName, tagType)));
    }

    private HashMap<String, Supplier<String>> composeStrategyGetterMap() {
        HashMap<String, Supplier<String>> strategyMap = new HashMap<>();
        strategyMap.put(AccountConstants.NAME_KEY, () -> getTextFromTextDetail("Account Name", TagConstants.DIV_TAG));
        strategyMap.put(AccountConstants.RATING_KEY, () -> getTextFromTextDetail("Rating", TagConstants.DIV_TAG));
        strategyMap.put(AccountConstants.SITE_KEY, () -> getTextFromTextDetail("Account Site", TagConstants.DIV_TAG));
        strategyMap.put(AccountConstants.DESCRIPTION_KEY, () ->
                getTextFromTextDetail("Description", TagConstants.DIV_TAG));
        strategyMap.put(AccountConstants.BILLING_CITY_KEY, () ->
                getTextFromTextDetail("Billing Address", TagConstants.DIV_TAG));
        strategyMap.put(AccountConstants.PARENT_ACCOUNT_KEY, () ->
                getTextFromTextDetail("Parent Account", TagConstants.A_TAG));
        strategyMap.put(AccountConstants.PHONE_KEY, () -> getTextFromTextDetail("Phone", TagConstants.DIV_TAG));
        strategyMap.put(AccountConstants.SLA_EXPIRATION, () ->
                getTextFromTextDetail("SLA Expiration Date", TagConstants.DIV_TAG));
        strategyMap.put(AccountConstants.LAST_MODIFIED_BY, () ->
                getTextFromTextDetail("Last Modified By", TagConstants.DIV_TAG));
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

    /**
     * [SL] Gets an accountEditPage.
     *
     * @return a instance of LightningAccountEditPage
     */
    @Override
    public AbstractAccountEditPage getAccountEditPage() {
        GuiInteractioner.clickWebElement(EDIT_BUTTON);
        return new ClassicAccountEditPage();
    }

    /**
     * Method wait to load BoardPage.
     */
    @Override
    protected final void waitLoadPage() {
        WebElement element = getDriver().findElement(By.cssSelector("[title='Edit']"));
        getDriverWait().until(ExpectedConditions.visibilityOf(element));
    }
}
