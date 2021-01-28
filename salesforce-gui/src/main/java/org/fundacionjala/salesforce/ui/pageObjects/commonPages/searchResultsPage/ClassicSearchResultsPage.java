package org.fundacionjala.salesforce.ui.pageObjects.commonPages.searchResultsPage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [MR] Class that represents the results Page after search in Classic skin.
 */
public class ClassicSearchResultsPage extends AbstractSearchResultsPage {

    @FindBy(id = "searchAllSummaryView")
    private WebElement searchAllBtn;

    private static final int TIME_TO_SLEEP = 2500;
    private String section;
    private String sectionXpath = "//a[./span[contains(text(),'%s') and @class='truncate']]";
    private String sectHeadersRowXpath = "//div[contains(@class,'bPageBlock')][.//span[contains(text(),'%s')]]"
            + "//tr[@class='headerRow']";
    private String sectHeadersXpath = "//div[contains(@class,'bPageBlock')][.//span[contains(text(),'%s')]]"
            + "//th[contains(@class,'zen-deemphasize')]/a[not(contains(text(),'Owner'))]";
    private String tableRowsXpath = "//div[contains(@class,'bPageBlock')]//table[@class='list']"
        + "/tbody/tr[not(@class='headerRow')]";
    private String resultsCounterXpath = "//span[contains(@class,'searchFirstCell')]";
    private String showMoreLinkCss = "span.pShowMore";

    private void openSection() {
        GuiInteractioner.clickWebElement(By.xpath(String.format(sectionXpath, section)));
    }

    private List<String> getSectionHeaders() {
        getDriverWait().until(ExpectedConditions.visibilityOf(
                getDriver().findElement(By.xpath(String.format(sectHeadersRowXpath, section)))));
        List<String> headerTitlesList = new ArrayList<>();
        List<WebElement> sectionHeaders = getDriver().findElements(By.xpath(String.format(sectHeadersXpath, section)));
        sectionHeaders.forEach((header) -> {
            headerTitlesList.add(GuiInteractioner.getTextFromWebElement(header));
        });
        return headerTitlesList;
    }

    @Override
    public final List<Map<String, String>> getDataAsListOfMaps(final String searchSection) {
        List<Map<String, String>> listOfMaps = new ArrayList<>();
        this.section = searchSection;
        List<String> headers = getSectionHeaders();
        openSection();
        while (true) {
            try {
                Thread.sleep(TIME_TO_SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<WebElement> results = getDriver().findElements(By.xpath(tableRowsXpath));
            results.forEach(result -> {
                Map<String, String> resultMap = new HashMap<>();
                headers.forEach(field -> {
                    resultMap.put(field, result.findElement(
                            By.xpath("./*[" + (headers.indexOf(field) + 2) + "]")).getText().trim());
                });
                listOfMaps.add(resultMap);
            });
            if (GuiInteractioner.getTextFromWebElement(getDriver()
                    .findElement(By.xpath(resultsCounterXpath))).contains("+")) {
                GuiInteractioner.clickWebElement(By.cssSelector(showMoreLinkCss));
            } else {
                break;
            }
        }
        return listOfMaps;
    }

    @Override
    protected final void waitLoadPage() {
        getDriverWait().until(ExpectedConditions.visibilityOf(searchAllBtn));
    }
}
