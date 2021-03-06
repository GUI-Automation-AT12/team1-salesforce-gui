package org.fundacionjala.salesforce.ui.pageObjects.commonPages.homePage.components.resultsPanel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

/**
 * [MR] Class that represent Suggested Results Panel in Classic skin at inputting text in search text box.
 */
public class ClassicResultsPanel extends AbstractResultsPanel {

    @FindBy(css = "ul.autoCompleteGroup li a")
    private List<WebElement> results;

    /**
     * [MR] Gets text from results at inputting text at search text box.
     *
     * @return a List of inner text.
     */
    @Override
    public List<String> getResults() {
        List<String> resultList = new ArrayList<>();
        for (WebElement result : results) {
            resultList.add(result.getText());
        }
        return resultList;
    }
}
