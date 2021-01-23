package org.fundacionjala.salesforce.cucumber.stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.fundacionjala.salesforce.ui.context.Context;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountImportPage.AbstractImportAccountPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountImportPage.bulkDataLoadJobs.BulkDataLoadJobsPage;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountImportPage.editMapping.EditMappingPage;
import org.fundacionjala.salesforce.ui.skins.SkinManager;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.Map;

/**
 * [SL] This class contains the steps for import_account feature.
 */
public class ImportAccountStep {

    private AbstractImportAccountPage importAccount;
    private EditMappingPage editMappingPage;
    private BulkDataLoadJobsPage bulkDataLoadJobsPage;
    private Context context;

    /**
     * Adds Dependency injection to share Context information.
     *
     * @param sharedContext
     */
    public ImportAccountStep(final Context sharedContext) {
        context = sharedContext;
    }

    /**
     * Clicks in the Import button.
     */
    @When("I go to Import section in Accounts page")
    public void goToImportSectionInAccountsPage() {
    }

    /**
     * Selects the first step of the importation, in this step is need select the
     * the options for the importation and the file for the importation.
     *
     * @param fieldEmail
     * @param filePath
     * @param option
     */
    @When("I import a new Account matching by {string} with the file {string} and the {string}")
    public void importANewAccount(final String fieldEmail, final String filePath, final String option) {
        importAccount = SkinManager.getInstance().getSkinFactory().importAccountPage();
        importAccount.clickImportElement();
        importAccount.chooseDataStep(fieldEmail, filePath, option);
    }

    /**
     * Displays a preview table of headers of the CSV.
     *
     * @param expected a map with the all data of the table
     */
    @Then("the auto-mapped fields table should contain the next data")
    public void shouldBeTheNextTable(final Map<String, String> expected) {
        editMappingPage = importAccount.getEditMappingStep();
        Map<String, String> actual = editMappingPage.chooseEditMapping();
        Assert.assertEquals(actual, expected);
        editMappingPage.clickNextButton();
    }

    /**
     * Displays the number of mapped fields and unmapped fields.
     *
     * @param expectedMapped   a number of representation of mapped fields
     * @param expectedUnmapped a number of representation of unmapped fields
     */
    @When("the Mapped Fields value should be {int} and Unmapped Field should be {int}")
    public void mappedFieldsValueShouldBeAndUnmappedFieldShould(final int expectedMapped, final int expectedUnmapped) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(editMappingPage.getMappedValue(), expectedMapped);
        softAssert.assertEquals(editMappingPage.getUnmappedValue(), expectedUnmapped);
        softAssert.assertAll();
        editMappingPage.clickEditMappingNextPage();
    }

    /**
     * Displays a table with the summary of the importation.
     *
     * @param expectedTable a Map with the all value
     */
    @When("the Data Load Job Details should contain the following importation data")
    public void theDataLoadJobDetailsShouldContainCorrectImportationData(final Map<String, String> expectedTable) {
        bulkDataLoadJobsPage = importAccount.getBulkDataLoadJobs();
        Map<String, String> actual = bulkDataLoadJobsPage.bulkDataLoadJobDetail();
        Assert.assertTrue(actual.entrySet().containsAll(expectedTable.entrySet()));
    }

    /**
     * Clicks in the download link.
     *
     * @param optionToDownload
     * @throws Exception
     */
    @When("I click the {string} link to download file")
    public void downloadTheFile(final String optionToDownload) throws Exception {
        bulkDataLoadJobsPage.downloadFile(optionToDownload);
    }

    /**
     * Checks the downloaded file should not contain false Success and any errors.
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Then("the downloaded file should not contain false Success and any errors")
    public void downloadedFileShouldNotContainFalseSuccessAndAnyErrors() throws IOException, InterruptedException {
        boolean actual = bulkDataLoadJobsPage.downloadedFileNotContainFalse();
        Assert.assertTrue(actual);
    }

    /**
     * Stores the Id of imported Accounts from the downloaded file.
     *
     * @throws IOException
     */
    @When("I store the Id of imported Accounts from the downloaded file")
    public void storeTheIdOfImportedAccountsFromTheDownloadedFile() throws IOException {
        context.setAccountIdList(bulkDataLoadJobsPage.accountIdList());
    }

    /**
     * Checks if the new Accounts should be in New This Week view at Account page.
     */
    @Then("the new Accounts should be in New This Week view at Account page")
    public void newAccountsShouldBeInNewThisWeekViewAtAccountPage() {
    }
}
