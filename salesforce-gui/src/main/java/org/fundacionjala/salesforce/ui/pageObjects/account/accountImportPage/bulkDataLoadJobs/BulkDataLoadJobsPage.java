package org.fundacionjala.salesforce.ui.pageObjects.account.accountImportPage.bulkDataLoadJobs;

import org.apache.commons.csv.CSVRecord;
import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.ui.entities.Account;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;
import org.fundacionjala.salesforce.utils.CSVReader;
import org.fundacionjala.salesforce.utils.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BulkDataLoadJobsPage extends BasePage {

    private static final By HEADER_TABLE = By.xpath("//table[@class='detailList']/*/tr/th");
    private static final By CONTENT_TABLE = By.xpath("//table[@class='detailList']/*/tr/td/span");
    private static final String DOWNLOAD_OPTION = "//a[text()='%s']";
    private static final String DOWNLOAD_PATH = System.getProperty("user.dir") + "/src/test/resources/tmp/";
    private List<String> fileListResult;
    private List<String> fileListRequest;
    private static final int WAIT_TIME = 3000;


    /**
     * Gets all data of the table Bulk Data Load Job.
     *
     * @return a map with the all data of the table
     */
    public Map<String, String> bulkDataLoadJobDetail() {
        Map<String, String> map = new HashMap<>();
        List<WebElement> headerList = getDriver().findElements(HEADER_TABLE);
        List<WebElement> contentList = getDriver().findElements(CONTENT_TABLE);

        for (int i = 0; i < headerList.size(); i++) {
            String key = headerList.get(i).getText();
            String value = contentList.get(i).getText();
            map.put(key, value);
        }
        return map;
    }

    /**
     * [SL] VClicks on the download option.
     *
     * @param optionToDownload
     */
    public void downloadFile(final String optionToDownload) {
        GuiInteractioner.clickWebElement(By.xpath(String.format(DOWNLOAD_OPTION, optionToDownload)));
    }

    /**
     * [SL] Verifies if the download file contains any error or status false.
     *
     * @return if found false or any error
     * @throws IOException
     * @throws InterruptedException
     */
    public boolean downloadedFileNotContainFalse() throws IOException, InterruptedException {
        Thread.sleep(WAIT_TIME);
        fileListResult = FileUtils.listOfFiles(DOWNLOAD_PATH, "result", 1);
        for (String file : fileListResult) {
            for (CSVRecord record : CSVReader.getRecords(file)) {
                if (record.get("Success").equals("false") || !record.get("Error").isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * [SL] Gets a list with the id of all accounts imported.
     *
     * @return a list
     * @throws IOException
     */
    public List<Account> accountResultList() throws IOException, InterruptedException {
        Thread.sleep(WAIT_TIME);
        fileListResult = FileUtils.listOfFiles(DOWNLOAD_PATH, "result", 1);
        fileListRequest = FileUtils.listOfFiles(DOWNLOAD_PATH, "request", 1);
        List<Account> accountList = new ArrayList<>();
        for (int i = 0; i < fileListResult.size(); i++) {
            List<CSVRecord> csvRecordsResult = CSVReader.getRecords(fileListResult.get(i));
            List<CSVRecord> csvRecordsRequest = CSVReader.getRecords(fileListRequest.get(i));
            for (int j = 0; j < csvRecordsResult.size(); j++) {
                CSVRecord csvResult = csvRecordsResult.get(j);
                CSVRecord csvRequest = csvRecordsRequest.get(j);
                accountList.add(buildAccount(csvResult, csvRequest));
            }
            FileUtils.deleteFile(fileListResult.get(i));
        }
        return accountList;
    }

    private Account buildAccount(final CSVRecord csvResult, final CSVRecord csvRequest) {
        Account account = new Account();
        account.setId(csvResult.get("Id"));
        account.setName(csvRequest.get("Name"));
        account.setDescription(csvRequest.get("Description"));
        account.setPhone(csvRequest.get("Phone"));
        account.setSite(csvRequest.get("Website"));
        return account;
    }

    /**
     * [SL] Method wait to load BoardPage.
     */
    @Override
    protected void waitLoadPage() {
        getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(CONTENT_TABLE));
    }
}
