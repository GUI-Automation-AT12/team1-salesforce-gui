package org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunitiesPage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.constants.OpportunityConstants;
import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityCreationPage.AbstractOpportunityCreationPage;
import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityCreationPage.ClassicOpportunityCreationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * [MR] Class that represents Opportunities Page in Classic skin.
 */
public class ClassicOpportunitiesPage extends AbstractOpportunitiesPage {

    private String opportunityXpath = "//th/a[contains(@href,'%s')]";

    private String accountXpath = "//td[1][preceding-sibling::th/a[contains(@href,'%s')]]/a";

    private String closeDateXpath = "//td[2][preceding-sibling::th/a[contains(@href,'%s')]]";

    /**
     * [MR] Goes to Creation page.
     *
     * @return OpportunityCreationPage
     */
    @Override
    public AbstractOpportunityCreationPage goToOpportunityCreation() {
        clickNewOpportunityBtn();
        return new ClassicOpportunityCreationPage();
    }

    private String getTextFromXpathFormattingId(final String locator, final String opportunityId) {
        try {
            return GuiInteractioner.getTextFromWebElement(By.xpath(String.format(locator, opportunityId)));
        } catch (NoSuchElementException e) {
            return null;
        }
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
        accountInfo.put(OpportunityConstants.CLOSE_DATE_KEY,
                getTextFromXpathFormattingId(closeDateXpath, opportunityId));
        accountInfo.values().removeAll(Collections.singleton(null));
        return accountInfo;
    }
}
