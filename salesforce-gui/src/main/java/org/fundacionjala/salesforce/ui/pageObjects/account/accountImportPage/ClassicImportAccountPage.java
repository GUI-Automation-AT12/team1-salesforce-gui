package org.fundacionjala.salesforce.ui.pageObjects.account.accountImportPage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountImportPage.bulkDataLoadJobs.BulkDataLoadJobsPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountImportPage.editMapping.EditMappingPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * [SL] ClassicImportAccountPage.
 */
public class ClassicImportAccountPage extends AbstractImportAccountPage {

    @FindBy(css = ".toolsContentRight div.lbBody ul li span [href*='Import']")
    private WebElement importButton;

    /**
     * [SL] Wait the page load end.
     */
    @Override
    protected void waitLoadPage() {
        GuiInteractioner.waitLoadPage(importButton);
    }

    /**
     * [SL] Clicks in the button or link for open the import account.
     */
    @Override
    public void clickImportElement() {
        GuiInteractioner.clickWebElement(importButton);
    }

    /**
     * [SL] This method contain the first steps of the importation.
     */
    @Override
    public void chooseDataStep(final String fieldEmail, final String fileName, final String option) {
        getChooseData().clickAccountsAndContactsOption();
        getChooseData().clickAddNewRecordsOption();
        getChooseData().selectMatchContact(fieldEmail);
        getChooseData().selectOption(option);
        getChooseData().selectFile(fileName);
        getChooseData().clickNextButton();
    }

    /**
     * [SL] This abstract method is for the second step of the importation.
     */
    @Override
    public EditMappingPage getEditMappingStep() {
        return new EditMappingPage();
    }

    /**
     * [SL] Gets BulkDataLoadJobs page.
     *
     * @return a instance of BulkDataLoadJobs
     */
    @Override
    public BulkDataLoadJobsPage getBulkDataLoadJobs() {
        return new BulkDataLoadJobsPage();
    }
}
