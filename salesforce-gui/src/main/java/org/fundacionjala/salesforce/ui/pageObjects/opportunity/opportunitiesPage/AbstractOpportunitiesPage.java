package org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunitiesPage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;
import org.fundacionjala.salesforce.ui.pageObjects.opportunity.opportunityCreationPage.AbstractOpportunityCreationPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * [MR] Class that represents Opportunities Page independent of the skin.
 */
public abstract class AbstractOpportunitiesPage extends BasePage {

    @FindBy(css = "[title='New']")
    private WebElement newOpportunityBtn;

    @Override
    protected void waitLoadPage() {
    }

    /**
     * Clicks the New Opportunity button.
     */
    protected void clickNewOpportunityBtn() {
        GuiInteractioner.clickWebElement(newOpportunityBtn);
    }

    /**
     * [MR] Goes to Creation page.
     *
     * @return OpportunityCreationPage
     */
    public abstract AbstractOpportunityCreationPage goToOpportunityCreation();
}
