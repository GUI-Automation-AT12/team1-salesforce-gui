package org.fundacionjala.salesforce.ui.pageObjects.account.accountImportPage.bulkDataLoadJobs;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;
import org.fundacionjala.salesforce.utils.CSVUtil;
import org.fundacionjala.salesforce.utils.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BulkDataLoadJobsPage extends BasePage {

    private static final By HEADER_TABLE = By.xpath("//table[@class='detailList']/*/tr/th");
    private static final By CONTEND_TABLE = By.xpath("//table[@class='detailList']/*/tr/td/span");
    private static final String DOWNLOAD_OPTION = "//a[text()='%s']";
    private static final String DOWNLOAD_PATH = System.getProperty("user.dir") + "/src/test/resources/tmp/";
    private List<String> fileList;
    private static final int WAIT_TIME = 3000;
    private static final int NUMBER_THREE = 3;


    /**
     * Gets all data of the table Bulk Data Load Job.
     *
     * @return a map with the all data of the table
     */
    public Map<String, String> bulkDataLoadJobDetail() {
        Map<String, String> map = new HashMap<>();
        List<WebElement> headerList = getDriver().findElements(HEADER_TABLE);
        List<WebElement> contendList = getDriver().findElements(CONTEND_TABLE);

        for (int i = 0; i < headerList.size(); i++) {
            String key = headerList.get(i).getText();
            String value = contendList.get(i).getText();
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
        fileList = FileUtils.listOfFiles(DOWNLOAD_PATH);
        for (String file : fileList) {
            List<String[]> contend = CSVUtil.readCSVFile(file);
            for (String[] column : contend) {
                if (column[1].contains("false") || column[NUMBER_THREE].length() > 2) {
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
    public List<String> accountIdList() throws IOException {
        fileList = FileUtils.listOfFiles(DOWNLOAD_PATH);
        List<String> accountIdList = new ArrayList<>();
        for (int i = 0; i < fileList.size(); i++) {
            List<String[]> contend = CSVUtil.readCSVFile(fileList.get(i));
            for (String[] column : contend) {
                accountIdList.add(column[0]);
            }
            FileUtils.deleteFile(fileList.get(i));
        }
        return accountIdList;
    }

    @Override
    protected void waitLoadPage() {

    }
}
