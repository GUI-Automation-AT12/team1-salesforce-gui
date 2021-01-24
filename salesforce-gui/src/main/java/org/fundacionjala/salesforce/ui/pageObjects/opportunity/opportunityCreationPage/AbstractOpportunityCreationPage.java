package org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityCreationPage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.ui.entities.Opportunity;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;
import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityDetailsPage.AbstractOpportunityDetailsPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Set;

/**
 * [MR] Class that represents Opportunity Creation Page independent of the skin.
 */
public abstract class AbstractOpportunityCreationPage extends BasePage {

    @Override
    protected void waitLoadPage() {
    }

    @FindBy(css = "[title='Save']")
    private WebElement saveBtn;

    /**
     * Clicks Save button in new Opportunity form.
     */
    protected void clickSaveBtn() {
        GuiInteractioner.clickWebElement(saveBtn);
    }

    /**
     * [MR] Fill New Opportunity form to create a new one.
     *
     * @param formFields to fill
     * @param opportunity to get information
     * @return OpportunityDetailsPage
     */
    public abstract AbstractOpportunityDetailsPage fillOpportunityInformation(
            Set formFields, Opportunity opportunity);
}
