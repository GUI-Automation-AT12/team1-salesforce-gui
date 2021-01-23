package org.fundacionjala.salesforce.ui.pageObjects.account.accountImportPage;

import org.fundacionjala.salesforce.ui.pageObjects.account.accountImportPage.bulkDataLoadJobs.BulkDataLoadJobsPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountImportPage.chooseDataStep.ChooseDataPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountImportPage.editMapping.EditMappingPage;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;

/**
 * AbstractImportAccountPage is abstract class for the skins
 * of the import page.
 */
public abstract class AbstractImportAccountPage extends BasePage {

    private final ChooseDataPage chooseDataPage = new ChooseDataPage();

    /**
     * Gets ChooseDataPage.
     *
     * @return a instance of ChooseDataPage
     */
    public ChooseDataPage getChooseData() {
        return chooseDataPage;
    }

    /**
     * clicks in the button or link for open the import account.
     */
    public abstract void clickImportElement();

    /**
     * This abstract method is for the first step of the importation.
     *
     * @param fieldEmail
     * @param filePath
     * @param option
     */
    public abstract void chooseDataStep(String fieldEmail, String filePath, String option);

    /**
     * This abstract method is for the second step of the importation.
     *
     * @return a instance of EditMappingPage
     */
    public abstract EditMappingPage getEditMappingStep();

    /**
     * This abstract method gets BulkDataLoadJobsPage.
     *
     * @return a instance of BulkDataLoadJobsPage
     */
    public abstract BulkDataLoadJobsPage getBulkDataLoadJobs();

}
