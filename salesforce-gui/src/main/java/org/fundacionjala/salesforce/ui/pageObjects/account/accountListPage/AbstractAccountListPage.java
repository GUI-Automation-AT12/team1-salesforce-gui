package org.fundacionjala.salesforce.ui.pageObjects.account.accountListPage;

import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

/**
 * [SL] AbstractAccountListPage is abstract class for the skins
 * of the import page.
 */
public abstract class AbstractAccountListPage extends BasePage {


    /**
     * [sl] Return a list with all data of the table.
     *
     * @return a list of maps with the data
     * @throws MalformedURLException
     */
    public abstract List<Map<String, String>> getTableNewThisWeek() throws MalformedURLException;

    @Override
    protected void waitLoadPage() {

    }
}
