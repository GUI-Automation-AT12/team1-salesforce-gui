package org.fundacionjala.salesforce.ui.pageObjects.account.accountEditPage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.utils.DateConverter;
import org.openqa.selenium.By;

/**
 * [SL] ClassicImportAccountPage.
 */
public class ClassicAccountEditPage extends AbstractAccountEditPage {

    private static final By SLA_INPUT = By.xpath("//span[@class='dateInput dateOnlyInput']/input");
    private static final By SAVE_BUTTON = By.xpath("//input[@title='Save']");

    /**
     * [SL] Changes the sla expiration date.
     *
     * @param date a constant what represent the date
     */
    @Override
    public void changeSLAExpirationDate(final String date) {
        String dateFormat = DateConverter.convertDateToFormattedText(DateConverter.convertTextToDate(date));
        GuiInteractioner.setInputText(SLA_INPUT, dateFormat);
        GuiInteractioner.clickWebElement(SAVE_BUTTON);
    }
}
