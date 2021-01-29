package org.fundacionjala.salesforce.ui.pageObjects.commonPages.searchResultsPage;

import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;

import java.util.List;
import java.util.Map;

/**
 * [MR] Class that represents the results Page after search independent of the skin.
 */
public abstract class AbstractSearchResultsPage extends BasePage {

    /**
     * Gets data from a section as List of Maps.
     *
     * @param section to get data
     * @return a List Of Map
     */
    public abstract List<Map<String, String>> getDataAsListOfMaps(String section);
}
