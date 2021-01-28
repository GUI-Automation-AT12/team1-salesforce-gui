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
 * [MR] Class that represents the results Page after search in Lightning skin.
 */
public class LightningSearchResultsPage extends AbstractSearchResultsPage {

    @FindBy (css = ".resultsWrapper")
    private WebElement resultsPanel;

    private String section;
    private String sectionXpath = "//a[contains(@class,'scopesItem')][.//span[contains(text(),'%s')]]";
    private String sectHeadersXpath = "//div[contains(@class,'resultsWrapper')][.//div[contains(text(),'%s')]]"
            + "//thead//a/span[@class='slds-truncate'][not(contains(@title,'Owner'))]";
    private String sectBodyXpath = "//div[contains(@class,'resultsWrapper')][.//div[contains(text(),'%s')]]"
            + "//tbody/tr/th";
    private String tableRowsXpath = "//tbody/tr";
    private String tableRowsHeadXpath = "//tbody/tr/th";
    private String resultCounterXpath = "//div[not(contains(@class,'hide'))]/p";

    private void openSection() {
        GuiInteractioner.clickWebElement(By.xpath(String.format(sectionXpath, section)));
    }

    private List<String> getSectionHeaders() {
        List<String> headerTitlesList = new ArrayList<>();
        List<WebElement> sectionHeaders = getDriver().findElements(By.xpath(String.format(sectHeadersXpath, section)));
        sectionHeaders.forEach((header) -> {
            headerTitlesList.add(GuiInteractioner.getTextFromWebElement(header));
        });
        return headerTitlesList;
    }

    private void expandAllResults() {
        GuiInteractioner.clickWebElement(By.xpath(String.format(sectBodyXpath, section)));
        while (true) {
            List<WebElement> resultList = getDriver().findElements(By.xpath(tableRowsHeadXpath));
            GuiInteractioner.goDown(resultList.get(resultList.size() - 1));
            String counter = getDriver().findElement(By.xpath(resultCounterXpath)).getText();
            if (!counter.contains("+") && (Integer.parseInt(counter.replaceAll("[^0-9]", "")) == resultList.size())) {
                break;
            }
        }
    }

    @Override
    public final List<Map<String, String>> getDataAsListOfMaps(final String searchSection) {
        List<Map<String, String>> listOfMaps = new ArrayList<>();
        this.section = searchSection;
        openSection();
        List<String> headers = getSectionHeaders();
        expandAllResults();
        List<WebElement> resultList = getDriver().findElements(By.xpath(tableRowsXpath));
        resultList.forEach(result -> {
            Map<String, String> resultMap = new HashMap<>();
            headers.forEach(field -> {
                resultMap.put(field, result.findElement(
                        By.xpath("./*[" + (headers.indexOf(field) + 2) + "]")).getText());
            });
            listOfMaps.add(resultMap);
        });
        return listOfMaps;
    }

    @Override
    protected final void waitLoadPage() {
        getDriverWait().until(ExpectedConditions.visibilityOf(resultsPanel));
    }
}
