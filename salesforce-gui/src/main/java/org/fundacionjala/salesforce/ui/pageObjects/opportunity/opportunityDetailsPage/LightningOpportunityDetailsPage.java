package org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityDetailsPage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.constants.OpportunityConstants;
import org.fundacionjala.salesforce.constants.TagConstants;
import org.fundacionjala.salesforce.utils.PageTransporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/**
 * [MR] Class that represents Opportunity Details Page in Lightning skin.
 */
public class LightningOpportunityDetailsPage extends AbstractOpportunityDetailsPage {

    private static final int OPPORTUNITY_STRING_SIZE = 12;

    @FindBy(xpath = "//ul[@role='tablist']/li[@title='Details']/a")
    private WebElement detailsTab;

    @FindBy(css = "button[title='OK']")
    private WebElement okBtn;

    private String accountInfoXpath = "//span[.='%1$s']/../../div[2]//%2$s";

    private void clickDetailsTab() {
        GuiInteractioner.clickWebElement(detailsTab);
    }

    private void clickOkbtn() {
        GuiInteractioner.clickWebElement(okBtn);
    }

    private String getTextFromDetail(final String fieldName, final String tagType) {
        return GuiInteractioner.getTextFromWebElement(By.xpath(String.format(accountInfoXpath, fieldName, tagType)));
    }

    private HashMap<String, Supplier<String>> composeStrategyGetterMap() {
        HashMap<String, Supplier<String>> strategyMap = new HashMap<>();
        strategyMap.put(OpportunityConstants.NAME_KEY, () ->
                getTextFromDetail("Opportunity Name", TagConstants.LIGHTNING_FORMATTED_TEXT_TAG));
        strategyMap.put(OpportunityConstants.ACCOUNT_KEY, () ->
                getTextFromDetail("Account Name", TagConstants.A_TAG + TagConstants.SLASH + TagConstants.SPAN_TAG));
        strategyMap.put(OpportunityConstants.CLOSE_DATE_KEY, () ->
                getTextFromDetail("Close Date", TagConstants.LIGHTNING_FORMATTED_TEXT_TAG));
        strategyMap.put(OpportunityConstants.STAGE_KEY, () ->
                getTextFromDetail("Stage", TagConstants.LIGHTNING_FORMATTED_TEXT_TAG));
        return strategyMap;
    }

    /**
     * [MR] Gets the Opportunity info from Details Page.
     * @param fields to get data
     * @return OpportunityDetails as Map
     */
    @Override
    public Map<String, String> getOpportunityDetails(final Set<String> fields) {
        clickDetailsTab();
        //clickOkbtn();
        Map opportunityInfoMap = new HashMap<String, String>();
        fields.forEach(field -> opportunityInfoMap.put(field, composeStrategyGetterMap().get(field).get()));
        return opportunityInfoMap;
    }

    /**
     * [MR] Gets the Opportunity's ID from the URL.
     *
     * @return Opportunity's Id
     */
    @Override
    public String getOpportunityId() {
        getDriverWait().until(ExpectedConditions.not(ExpectedConditions.urlContains("new")));
        String currentUrl = PageTransporter.getCurrentUrl();
        return currentUrl.
                substring(currentUrl.indexOf("Opportunity/") + OPPORTUNITY_STRING_SIZE, currentUrl.indexOf("/view"));
    }
}
