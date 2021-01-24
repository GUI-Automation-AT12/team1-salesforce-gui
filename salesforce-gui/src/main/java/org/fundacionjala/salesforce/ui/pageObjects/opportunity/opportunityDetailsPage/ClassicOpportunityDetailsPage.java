package org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityDetailsPage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.constants.OpportunityConstants;
import org.fundacionjala.salesforce.constants.TagConstants;
import org.fundacionjala.salesforce.utils.PageTransporter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/**
 * [MR] Class that represents Opportunity Details Page in Classic skin.
 */
public class ClassicOpportunityDetailsPage extends AbstractOpportunityDetailsPage {

    private String detailXpath = "//td[preceding-sibling::td[text()='%1$s']][1]//%2$s";

    private String getTextFromTextDetail(final String fieldName, final String tagType) {
        return GuiInteractioner.getTextFromWebElement(By.xpath(String.format(detailXpath, fieldName, tagType)));
    }

    private HashMap<String, Supplier<String>> composeStrategyGetterMap() {
        HashMap<String, Supplier<String>> strategyMap = new HashMap<>();
        strategyMap.put(OpportunityConstants.NAME_KEY, () ->
                getTextFromTextDetail("Opportunity Name", TagConstants.DIV_TAG));
        strategyMap.put(OpportunityConstants.ACCOUNT_KEY, () ->
                getTextFromTextDetail("Account Name", TagConstants.A_TAG));
        strategyMap.put(OpportunityConstants.CLOSE_DATE_KEY, () ->
                getTextFromTextDetail("Close Date", TagConstants.DIV_TAG));
        strategyMap.put(OpportunityConstants.STAGE_KEY, () ->
                getTextFromTextDetail("Stage", TagConstants.DIV_TAG));
        return strategyMap;
    }

    /**
     * [MR] Gets the Opportunity info from Details Page.
     * @param fields to get data
     * @return Opportunity Details as Map
     */
    @Override
    public Map<String, String> getOpportunityDetails(final Set<String> fields) {
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
        getDriverWait().until(ExpectedConditions.not(ExpectedConditions.urlContains("/e")));
        String currentUrl = PageTransporter.getCurrentUrl();
        return currentUrl.substring(currentUrl.lastIndexOf("/") + 1);
    }
}
