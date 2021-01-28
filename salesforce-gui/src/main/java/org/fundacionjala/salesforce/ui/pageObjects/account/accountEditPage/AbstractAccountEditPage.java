package org.fundacionjala.salesforce.ui.pageObjects.account.accountEditPage;

import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;

/**
 * [SL] Abstract class of the account edit page.
 */
public abstract class AbstractAccountEditPage extends BasePage {

    /**
     * [SL] Changes the sla expiration date.
     * @param date a constant what represent the date
     */
    public abstract void changeSLAExpirationDate(String date);

    /**
     * [SL] Method wait to load BoardPage.
     */
    @Override
    protected void waitLoadPage() {

    }
}
