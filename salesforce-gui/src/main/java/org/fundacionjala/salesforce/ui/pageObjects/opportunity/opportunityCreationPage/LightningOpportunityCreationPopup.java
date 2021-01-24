package org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityCreationPage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.constants.OpportunityConstants;
import org.fundacionjala.salesforce.constants.TagConstants;
import org.fundacionjala.salesforce.ui.entities.Opportunity;
import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityDetailsPage.AbstractOpportunityDetailsPage;
import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityDetailsPage.LightningOpportunityDetailsPage;
import org.fundacionjala.salesforce.utils.DateConverter;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Set;

/**
 * [MR] Class that represents Opportunity Creation Page in Lightning skin.
 */
public class LightningOpportunityCreationPopup extends AbstractOpportunityCreationPage {


    private String dropdownLocatorXpath = "//span[.='%s' and @class='']/../../div";

    private String inputLocatorXpath = "//label/span[.='%1$s']/../../%2$s";

    private String selectedXpath = "//a[.='%s']";

    private String fillToSearchXpath = "//div[.='%s'][contains(@class,'primaryLabel')]";

    private void fillTextBox(final String labelName, final String tagType, final String text) {
        By by = By.xpath(String.format(inputLocatorXpath, labelName, tagType));
        GuiInteractioner.setInputText(by, text);
    }

    private void fillDropdown(final String labelName, final String option) {
        GuiInteractioner.clickWebElement(By.xpath(String.format(dropdownLocatorXpath, labelName)));
        GuiInteractioner.scrollDownToFindElement(By.xpath(String.format(selectedXpath, option)));
        GuiInteractioner.clickWebElement(By.xpath(String.format(selectedXpath, option)));
    }

    private void fillAndSearch(final String labelName, final String text) {
        fillTextBox(labelName, TagConstants.DIV_TAG + TagConstants.DOUBLE_SLASH + TagConstants.INPUT_TAG, text);
        By by = By.xpath(String.format(fillToSearchXpath, text));
        GuiInteractioner.clickWebElement(by);
    }

    private HashMap<String, Runnable> composeMapStrategy(final Opportunity opportunity) {
        HashMap<String, Runnable> strategyMap = new HashMap<>();
        strategyMap.put(OpportunityConstants.NAME_KEY, () -> fillTextBox("Opportunity Name",
                TagConstants.INPUT_TAG, opportunity.getName()));
        strategyMap.put(OpportunityConstants.ACCOUNT_KEY, () -> fillAndSearch("Account Name",
                opportunity.getAccount().getName()));
        strategyMap.put(OpportunityConstants.CLOSE_DATE_KEY, () -> fillTextBox("Close Date",
                TagConstants.DIV_TAG + TagConstants.SLASH + TagConstants.INPUT_TAG,
                DateConverter.convertDateToFormattedText(opportunity.getCloseDate())));
        strategyMap.put(OpportunityConstants.STAGE_KEY, () -> fillDropdown("Stage", opportunity.getStage()));
        return strategyMap;
    }

    private void setInformation(final Set<String> formFields, final Opportunity opportunity) {
        HashMap<String, Runnable> strategyMap = composeMapStrategy(opportunity);
        formFields.forEach(key -> strategyMap.get(key).run());
    }

    /**
     * [MR] Fill New Opportunity form to create a new one.
     *
     * @param formFields to fill
     * @param opportunity to get information
     * @return OpportunityDetailsPage
     */
    @Override
    public AbstractOpportunityDetailsPage fillOpportunityInformation(
            final Set formFields, final Opportunity opportunity) {
        setInformation(formFields, opportunity);
        clickSaveBtn();
        return new LightningOpportunityDetailsPage();
    }
}
