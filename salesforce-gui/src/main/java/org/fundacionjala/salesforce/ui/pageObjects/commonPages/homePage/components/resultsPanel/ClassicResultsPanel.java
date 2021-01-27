package org.fundacionjala.salesforce.ui.pageObjects.commonPages.homePage.components.resultsPanel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class ClassicResultsPanel extends AbstractResultsPanel{

    @FindBy(css = "ul.autoCompleteGroup")
    private WebElement resultList;

    @FindBy(css = "ul.autoCompleteGroup li a")
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
