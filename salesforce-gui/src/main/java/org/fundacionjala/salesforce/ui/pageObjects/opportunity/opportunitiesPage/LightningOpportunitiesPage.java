package org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunitiesPage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.constants.OpportunityConstants;
import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityCreationPage.AbstractOpportunityCreationPage;
import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityCreationPage.LightningOpportunityCreationPopup;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

/**
 * [MR] Class that represents Opportunities Page in Lightning skin.
 */
public class LightningOpportunitiesPage extends AbstractOpportunitiesPage {

    private static final String ROW_XPATH = "//tr[count(//tr[.//a[contains(@href,'%s')]]/preceding-sibling::tr) + 1]";

    private String opportunityXpath = ROW_XPATH + "//th//a[@data-refid]";

    private String accountXpath = ROW_XPATH + "//td//a[@data-refid]";

    private String accountSiteXpath = ROW_XPATH + "//span[following-sibling::span[./button[contains(@title,'Site')]]]";

    private String stageXpath = ROW_XPATH + "//span/span[not(@data-aura-class) and @class='slds-truncate']";

    private String closeDateXPath = ROW_XPATH + "//span/span/span[@data-aura-class]";

    /**
     * [MR] Goes to Creation page.
     *
     * @return OpportunityCreationPage
     */
    @Override
    public AbstractOpportunityCreationPage goToOpportunityCreation() {
        clickNewOpportunityBtn();
        return new LightningOpportunityCreationPopup();
    }

    private String getTextFromXpathFormattingId(final String locator, final String opportunityId) {
        return GuiInteractioner.getTextFromWebElement(By.xpath(String.format(locator, opportunityId)));
    }

    /**
     * [MR] Gets data of an opportunity in the Opportunities table.
     * @param opportunityId to get data
     * @return opportunity's data as Map
     */
    @Override
    public Map<String, String> getOpportunityDataFromTable(final String opportunityId) {
        Map<String, String> accountInfo = new HashMap<>();
        accountInfo.put(OpportunityConstants.NAME_KEY,
                getTextFromXpathFormattingId(opportunityXpath, opportunityId));
        accountInfo.put(OpportunityConstants.ACCOUNT_KEY,
                getTextFromXpathFormattingId(accountXpath, opportunityId));
        accountInfo.put(OpportunityConstants.ACCOUNT_SITE_KEY,
                getTextFromXpathFormattingId(accountSiteXpath, opportunityId));
        accountInfo.put(OpportunityConstants.STAGE_KEY,
                getTextFromXpathFormattingId(stageXpath, opportunityId));
        accountInfo.put(OpportunityConstants.CLOSE_DATE_KEY,
                getTextFromXpathFormattingId(closeDateXPath, opportunityId));
        return accountInfo;
    }
}
