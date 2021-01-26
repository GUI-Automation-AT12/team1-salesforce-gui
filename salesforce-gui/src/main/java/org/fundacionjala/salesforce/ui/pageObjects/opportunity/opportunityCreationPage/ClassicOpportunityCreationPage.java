package org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityCreationPage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.constants.OpportunityConstants;
import org.fundacionjala.salesforce.constants.TagConstants;
import org.fundacionjala.salesforce.ui.entities.Opportunity;
import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityDetailsPage.AbstractOpportunityDetailsPage;
import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityDetailsPage.ClassicOpportunityDetailsPage;
import org.fundacionjala.salesforce.utils.DateConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.Set;

/**
 * [MR] Class that represents Opportunity Creation Page in Classic skin.
 */
public class ClassicOpportunityCreationPage extends AbstractOpportunityCreationPage {

    private String inputLocatorXpath = "//td[preceding-sibling::td/label[text()='%1$s']][1]//%2$s[@type='text']";
    private String selectLocatorXpath = "//td[preceding-sibling::td/label[text()='%s']][1]//select";

    private void fillTextBox(final String labelName, final String tagType, final String textToFill) {
        By inputLocator = By.xpath(String.format(inputLocatorXpath, labelName, tagType));
        GuiInteractioner.setInputText(inputLocator, textToFill);
    }

    private void fillDropdown(final String labelName, final String option) {
        By selectLocator = By.xpath(String.format(selectLocatorXpath, labelName));
        Select dropdown = new Select(getDriver().findElement(selectLocator));
        dropdown.selectByVisibleText(option);
    }

    private HashMap<String, Runnable> composeMapStrategy(final Opportunity opportunity) {
        HashMap<String, Runnable> strategyMap = new HashMap<>();
        strategyMap.put(OpportunityConstants.NAME_KEY, () -> fillTextBox("Opportunity Name",
                TagConstants.INPUT_TAG, opportunity.getName()));
        strategyMap.put(OpportunityConstants.ACCOUNT_KEY, () -> fillTextBox("Account Name",
                TagConstants.INPUT_TAG, opportunity.getAccount().getName()));
        strategyMap.put(OpportunityConstants.CLOSE_DATE_KEY, () -> fillTextBox("Close Date",
                TagConstants.INPUT_TAG, DateConverter.convertDateToFormattedText(opportunity.getCloseDate())));
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
        return new ClassicOpportunityDetailsPage();
    }
}
