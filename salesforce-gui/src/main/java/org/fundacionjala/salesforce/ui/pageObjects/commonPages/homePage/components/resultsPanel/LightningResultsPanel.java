package org.fundacionjala.salesforce.ui.pageObjects.commonPages.homePage.components.resultsPanel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class LightningResultsPanel extends AbstractResultsPanel {

    @FindBy(css = "ul.lookup__list.visible")
    private WebElement resultList;

    @FindBy(xpath = "//li[not(contains(@class,'OPTION'))]/a/div[@class='slds-truncate']")
    private List<WebElement> results;

    @Override
    public List<String> getResults() {
        List<String> resultList = new ArrayList<>();
        for (WebElement result : results) {
            resultList.add(result.getText());
        }
        return resultList;
    }

    @Override
    protected void waitLoadPage() {
        getDriverWait().until(ExpectedConditions.visibilityOf(resultList));
    }
}
