package org.fundacionjala.salesforce.ui.pageObjects.account.accountEditPage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.utils.DateConverter;
import org.openqa.selenium.By;

/**
 * [SL] ClassicImportAccountPage.
 */
public class ClassicAccountEditPage extends AbstractAccountEditPage {

    private By slaInput = By.xpath("//span[@class='dateInput dateOnlyInput']/input");
    private By saveButton = By.xpath("//input[@title='Save']");

    /**
     * [SL] Changes the sla expiration date.
     *
     * @param date a constant what represent the date
     */
    @Override
    public void changeSLAExpirationDate(final String date) {
        String dateFormat = DateConverter.convertDateToFormattedText(DateConverter.convertTextToDate(date));
        GuiInteractioner.setInputText(slaInput, dateFormat);
        GuiInteractioner.clickWebElement(saveButton);
    }
}
