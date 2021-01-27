package org.fundacionjala.salesforce.ui.pageObjects.account.accountImportPage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountImportPage.bulkDataLoadJobs.BulkDataLoadJobsPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountImportPage.editMapping.EditMappingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * [SL] LightningImportAccountPage.
 */
public class LightningImportAccountPage extends AbstractImportAccountPage {

    @FindBy(xpath = "//a [@title='Import']")
    private WebElement importButton;

    @FindBy(xpath = "//iframe [@title='accessibility title']")
    private WebElement iframeXpath;

    private By iframeBulkJob = By.xpath("//iframe[contains(@name, 'vfFrame')]");
    private By iframeBy = By.xpath("//iframe [@title='accessibility title']");
    private EditMappingPage editMapping;
    private BulkDataLoadJobsPage bulkDataLoadJobsPage;

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
     * [SL] Calls methods for the first step of the importation.
     */
    @Override
    public void chooseDataStep(final String fieldEmail, final String filePath, final String option) {
        waitLoadFrameImportData();
        getChooseData().clickAccountsAndContactsOption();
        getChooseData().clickAddNewRecordsOption();
        getChooseData().selectMatchContact(fieldEmail);
        getChooseData().selectOption(option);
        getChooseData().selectFile(filePath);
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
        waitLoadFrameBulkData();
        return new BulkDataLoadJobsPage();
    }

    /**
     * [SL] Wait the frame load end.
     */
    public void waitLoadFrameImportData() {
        getDriverWait().until(ExpectedConditions.presenceOfElementLocated(iframeBy));
        getDriverWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeXpath));
    }

    /**
     * [SL] Wait the other frame load end.
     */
    public void waitLoadFrameBulkData() {
        getDriverWait().until(ExpectedConditions.presenceOfElementLocated(iframeBulkJob));
        getDriverWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeBulkJob));
    }
}
