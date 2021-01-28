package org.fundacionjala.salesforce.ui.pageObjects.commonPages.searchResultsPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Map;

/**
 * [MR] Class that represents the results Page after search in Classic skin.
 */
public class ClassicSearchResultsPage extends AbstractSearchResultsPage {

    @FindBy(id = "searchAllSummaryView")
    private WebElement searchAllBtn;

    @Override
    public final List<Map<String, String>> getDataAsListOfMaps(final String section) {
        return null;
    }

    @Override
    protected final void waitLoadPage() {
        getDriverWait().until(ExpectedConditions.visibilityOf(searchAllBtn));
    }
}
